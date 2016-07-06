package com.liuzhuang.base;

import java.util.Random;
import java.util.regex.Matcher;

import org.cocos2d.actions.CCScheduler;
import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCFiniteTimeAction;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import com.liuzhuang.util.Util;

public class BaseEnemy extends Base {

	/*
	 * 通过setPoint来更新位置,这个不同于people,因为people是通过按键来移动的,随时都有可能停下,
	 * 还有那个需要判断边界,这个enemy每次移动的距离都是固定的,所以没有必要!
	 * 不是啊,后来想起来了,还有moveToPeopel呢,这个过程的终点是人物周边,受到攻击就会停下来.
	 */
	protected int flagI = 0;
	// private int flagJ = 1;
	protected int number;
	protected CCSpawn spawn;
	protected CCSequence sequence;
	private CGPoint peoplePoint;
	protected int pointX;
	protected int pointY;
	protected CGRect EnemyBoundingBox;
	protected CCScheduler scheduler;
	protected boolean isBoss;
	private int i = 1;
	// protected CGRect leftRect; //左边的矩形
	// protected CGRect rightRect; //左边的矩形

	protected double attackDistance;

	public BaseEnemy(int i) {
		
		this.i=i;
		scheduler = CCScheduler.sharedScheduler();
		scheduler.schedule("upDataPoint", this, 0.1f, true);
		scheduler.pause(this);
	}
	
	public boolean isBoss(){
		return isBoss;
	}

	public CGRect getBoundingBox() {

		return EnemyBoundingBox;

	}

	public double getAttackDistance() {
		return attackDistance;
	}

	public void upDataPoint(float t) {
		x = sprite.getPosition().x;
		y = sprite.getPosition().y;
		// System.out.print("14564444444444444444444444444444444444444444444444444444444444444444442222211578424564654444554564456646");
	}

	public void unUpDate() {

		scheduler.pause(this);
	}

	// public void makeRect(float x,float y){}

	public int getAttackPointX(CGPoint isPeoplePoint) {
		if (isPeoplePoint.x < x) {
			return pointX;
		} else {
			return -pointX;
		}
	}

	public int getAttackPointY() {

		return pointY;
	}

	public void setIsVisable(boolean visable) {

		head.setVisible(visable);
	}

	public CGPoint getPoint() {

		point = CGPoint.ccp(x, y);

		return point;
	}

	public void setX(float x) {

		this.x = x;
	}

	public void setY(float y) {

		this.y = y;
	}

	protected void judgeDirection() {

		if (flagI == i) {
			sprite.setFlipX(true);
		}
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void removeSelf() {

		sprite.removeSelf();
	}

	public void setPoint() {

		x = sprite.getPosition().x;
		y = sprite.getPosition().y;

		/*
		 * if(distance>winSize.height/2){
		 * 
		 * sprite.removeSelf(); move(); }
		 */

	}

	private boolean isBeat = false;

	public void beat(int attackPower) {
		
			isBeat=true;
			bloodNumber = bloodNumber - attackPower;

			sprite = CCSprite.sprite(defaultFormat);
			sprite.setScale(2.5F);
			sprite.setPosition(x, y);
			judgeDirection();
			map.addChild(sprite, 1);
			action = Util.getAnimate(format, num, false, 0.1F);

			sequence = CCSequence.actions((CCFiniteTimeAction) action,
					CCCallFunc.action(this, "setPoint"),
					CCCallFunc.action(this, "unLock"),
					CCCallFunc.action(this, "unBeat"));
			sprite.runAction(sequence);
		
	}
	
	public void setIsBeat() {

		isBeat = false;
	}

	public boolean getIsBeat() {

		return isBeat;
	}

	public void unBeat() {
		head.setVisible(false);
		isBeat = false;
	}

	public void down() {

		flag = false;
		sprite.setScale(2.5f);
		sprite.setPosition(x, y);
		judgeDirection();

		map.getParent().addChild(sprite, 1);
		System.out
				.println("++++++++++++++++++++++++++++++++++++++++++++++这是down++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
						+ point);

		action = Util.getAnimate(format, num, false, 0.3f);

		CCSequence sequence = CCSequence.actions((CCFiniteTimeAction) action,
				CCCallFunc.action(this, "setPoint"),
				CCCallFunc.action(this, "removeSelf"));
		// 取消了removeself
		sprite.runAction(sequence);

	}

	public void attack() {

		flag = false;
		//新加的
		
		scheduler.resume(this);
		
		sprite = CCSprite.sprite(defaultFormat);
		sprite.setScale(2.5F);
		sprite.setPosition(x, y);
		judgeDirection();

		map.addChild(sprite, 1);
		action = Util.getAnimate(format, num, false, 0.15F);

		sequence = CCSequence.actions((CCFiniteTimeAction) action,
				CCCallFunc.action(this, "setPoint"),
				CCCallFunc.action(this, "unLock"),
				CCCallFunc.action(this, "unUpDate"));
		sprite.runAction(sequence);

	}

	private float distance;

	// private float peoplePoint;
	public int moveToPeople(CGPoint isPeoplePoint, CGPoint peoplePoint,
			float distance, CGPoint enemyPoint) {
		scheduler.resume(this);
		flag = false;

		this.peoplePoint = peoplePoint;
		this.distance = distance;

		sprite = CCSprite.sprite(defaultFormat);

		sprite.setScale(2.5F);
		sprite.setPosition(x, y);

		
		if (isPeoplePoint.x > enemyPoint.x) {

			flagI = 1;

		} else if (isPeoplePoint.x < enemyPoint.x) {

			flagI = 0;

		}

		judgeDirection();

		map.addChild(sprite, 1);
		action = Util.getAnimate(format, num, true, 0.1f);

		sprite.runAction(action);
		this.peoplePoint = peoplePoint;

		moveTo = CCMoveTo.action(distance / 80, peoplePoint);

		sequence = CCSequence.actions(moveTo,
				CCCallFunc.action(this, "unLock"),
				CCCallFunc.action(this, "unUpDate"),
				CCCallFunc.action(this, "attack"));


		// 100/distance=dx/(px-ex);

		sprite.runAction(sequence);

		return 0;
	}

	protected int random() {

		Random random = new Random();
		number = random.nextInt(6);

		return number;
	}

	public int getFlagI() {
		return flagI;
	}

	public int move() {
		// scheduler.resume(this);
		flag = false;
		switch (number) {
		case 0:

			sprite = CCSprite.sprite(defaultFormat);
			sprite.setScale(2.5F);
			sprite.setPosition(x, y);

			judgeDirection();
			map.addChild(sprite, 1);
			action = Util.getAnimate(format, num, false, 0.15F);
			
			y = y + 100;
			if (y > 300) {
				y = y - 100;
				
			}
			moveTo = CCMoveTo.action(num * 0.15f, CGPoint.ccp(x, y));
			
			
			spawn = CCSpawn.actions((CCFiniteTimeAction) action, moveTo);
			sequence = CCSequence.actions(spawn,
					CCCallFunc.action(this, "move"),
					CCCallFunc.action(this, "unLock"),
					CCCallFunc.action(this, "setPoint"));

			sprite.runAction(sequence);

			break;
		case 1:
			sprite = CCSprite.sprite(defaultFormat);
			sprite.setScale(2.5F);
			sprite.setPosition(x, y);

			judgeDirection();
			map.addChild(sprite, 1);
			action = Util.getAnimate(format, num, false, 0.15F);
			
			y = y - 100;
			if ( y < 50) {
				y = y + 100;
				
			}
			moveTo = CCMoveTo.action(num * 0.15f, CGPoint.ccp(x, y));
			
			spawn = CCSpawn.actions((CCFiniteTimeAction) action, moveTo);
			sequence = CCSequence.actions(spawn,
					CCCallFunc.action(this, "move"),
					CCCallFunc.action(this, "unLock"),
					CCCallFunc.action(this, "setPoint"));

			sprite.runAction(sequence);

			break;
		case 2:
			flagI = 0;

			sprite = CCSprite.sprite(defaultFormat);
			sprite.setScale(2.5F);
			sprite.setPosition(x, y);

			judgeDirection();
			map.addChild(sprite, 1);
			action = Util.getAnimate(format, num, false, 0.15F);
			
			x=x-100;
			if(x<0){
				x=x+100;
			}
			moveTo = CCMoveTo.action(num * 0.15f, CGPoint.ccp(x , y));
			
			/*moveTo = CCMoveTo.action(num * 0.15f, CGPoint.ccp(x - 100, y));
			x = x - 100;
*/
			spawn = CCSpawn.actions((CCFiniteTimeAction) action, moveTo);
			sequence = CCSequence.actions(spawn,
					CCCallFunc.action(this, "move"),
					CCCallFunc.action(this, "unLock"),
					CCCallFunc.action(this, "setPoint"));

			sprite.runAction(sequence);

			break;
		case 3:
			flagI = 1;

			sprite = CCSprite.sprite(defaultFormat);
			sprite.setScale(2.5F);
			sprite.setPosition(x, y);

			judgeDirection();
			map.addChild(sprite, 1);
			action = Util.getAnimate(format, num, false, 0.15F);
			
			x=x+100;
			if(x>4600){
				x=x-100;
			}
			moveTo = CCMoveTo.action(num * 0.15f, CGPoint.ccp(x , y));
			

			/*moveTo = CCMoveTo.action(num * 0.15f, CGPoint.ccp(x + 100, y));
			x = x + 100;*/

			spawn = CCSpawn.actions((CCFiniteTimeAction) action, moveTo);
			sequence = CCSequence.actions(spawn,
					CCCallFunc.action(this, "move"),
					CCCallFunc.action(this, "unLock"),
					CCCallFunc.action(this, "setPoint"));

			sprite.runAction(sequence);

			break;

		case 4:

			sprite = CCSprite.sprite(defaultFormat);
			sprite.setScale(2.5F);
			sprite.setPosition(x, y);

			judgeDirection();
			map.addChild(sprite, 1);
			action = Util.getAnimate(format, num, false, 0.15F);

			sequence = CCSequence.actions((CCFiniteTimeAction) action,
					CCCallFunc.action(this, "move"),
					CCCallFunc.action(this, "unLock"));
			sprite.runAction(sequence);

			break;
		case 5:

			sprite = CCSprite.sprite(defaultFormat);
			sprite.setScale(2.5F);
			sprite.setPosition(x, y);

			judgeDirection();
			map.addChild(sprite, 1);
			action = Util.getAnimate(format, num, false, 0.15F);

			sequence = CCSequence.actions((CCFiniteTimeAction) action,
					CCCallFunc.action(this, "move"),
					CCCallFunc.action(this, "unLock"));
			sprite.runAction(sequence);

			break;

		default:
			break;
		}

		random();

		return 0;
	}

}
