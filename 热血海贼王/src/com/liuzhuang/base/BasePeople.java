package com.liuzhuang.base;

import java.io.SequenceInputStream;
import java.security.cert.CollectionCertStoreParameters;
import java.util.Random;

import org.cocos2d.actions.CCScheduler;
import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCFiniteTimeAction;
import org.cocos2d.actions.base.CCFollow;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteSheet;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import android.R.bool;

import com.liuzhuang.layer.EndLayer;
import com.liuzhuang.util.Util;

public class BasePeople extends Base {

	protected int flagI = 1;
	protected int flagJ = 1;

	protected boolean isAttack;

	// ////////////////////////
	// ////////////////////////
	protected boolean isBeat;

	public boolean getisAttack() {
		return isAttack;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	private CCScheduler scheduler;

	public BasePeople() {
		attackPower = 10;
		bloodNumber = 1000;

		x = winSize.width / 2;
		y = winSize.height / 2;
		scheduler = CCScheduler.sharedScheduler();

		scheduler.schedule("upDataPoint", this, 0.1f, true);

		scheduler.pause(this);

		// CCScheduler.sharedScheduler().schedule("Listener", this, 0.1f,
		// false);

	}
	
	public void getBeatMusic(){
		
	}

/*	public void Listener(float t) {

		if (mapX < 10) {
			map.stopAllActions();

			moveTo = CCMoveTo.action(0.8f, CGPoint.ccp(0, winSize.height / 2));
			sprite.runAction(moveTo);

		}
	}

	public CGPoint randomPoint(int pointX, int pointY, CGPoint enemyPoint) {
		Random random = new Random();
		float x;
		float y;
		if (this.x > enemyPoint.x) {
			x = this.x - random.nextInt(pointX)-pointX/2;
			y = this.y + random.nextInt(pointY)-pointY/2;
		} else {
			x = this.x + random.nextInt(pointX)-pointX/2;
			y = this.y + random.nextInt(pointY)-pointY/2;
		}
		return CGPoint.ccp(x, y);
	}*/

	public float getX() {

		CGPoint point = map.convertToNodeSpace(CGPoint.ccp(x, y));
		// return point.x;
		return sprite.getPosition().x;
	}

	public void upDataPoint(float t) {

		x = sprite.getPosition().x;
		y = sprite.getPosition().y;
		mapX = map.getPosition().x;
		mapY = map.getPosition().y;
	}

	public void stand() {

		flag = true;
		sprite.removeSelf();// ////////////
		sprite = CCSprite.sprite(defaultFormat);
		sprite.setScale(2.5f);

		if (flagI == 0) {
			sprite.setFlipX(true);// /////////////////
		}

		sprite.setPosition(x, y);

		map.getParent().addChild(sprite, 3);// 少了这个运行follow();
		action = Util.getAnimate(format, num, true, 0.1f);
		sprite.runAction(action);

	}

	/*
	 * public void stop(int number){
	 * 
	 * flag=true; this.number=number; if(number<8){ if(flagJ==1){
	 * y=y-(float)(8-number)/8*100; }else if(flagJ==2){
	 * y=y+(float)(8-number)/8*100; } }
	 * 
	 * sprite.removeSelf();//////////////
	 * 
	 * }
	 */

	public void init(int i, int j) {

		this.flagI = i;
		this.flagJ = j;
	}

	public void unUpDate(){

		scheduler.pause(this);
	}

	public int getFlagI() {

		return flagI;
	}

	public int move() {
		// /////////////////////加上一个锁
		scheduler.resume(this);
		sprite.removeSelf();
		// follow();/////////////////////////////////////////
		sprite = CCSprite.sprite(defaultFormat);
		sprite.setScale(2.5f);
		sprite.setPosition(x, y);

		if (flagI == 0) {// ////
			sprite.setFlipX(true);// /////////////////
		}
		map.getParent().addChild(sprite, 1);// ///////////多了这行
		// map.addChild(sprites);//少了这个运行/////////////////////
		action = Util.getAnimate(format, num, false, 0.1f);// /////////

		// /////////////////////////////////////////这两个方向的问题

		switch (flagJ) {

		case 1:
			y = y + 100;

			break;
		case 2:
			y = y - 100;

			break;

		case 3:
			x = x - 100;
			break;
		case 4:
			x = x + 100;
			break;

		default:
			break;
		}
		point = CGPoint.ccp(x, y);
		moveTo = CCMoveTo.action(0.8f, point);
		if (flagJ == 1 || flagJ == 2) {

			// System.out.println("45648979484894897984649/7/94649/79/784987/97864");

			if (y <= 50 || y >= 450) {

				if (flagJ == 1) {
					y = y - 100;
				} else {
					y = y + 100;
				}

				CCSequence sequence = CCSequence.actions(
						(CCFiniteTimeAction) action,
						CCCallFunc.action(this, "move"),
						CCCallFunc.action(this, "unLock"),
						CCCallFunc.action(this, "unUpDate"));
				sprite.runAction(sequence);

			} else {

				CCSpawn spawn = CCSpawn.actions((CCFiniteTimeAction) action,
						moveTo);
				CCSequence sequence = CCSequence.actions(spawn,
						CCCallFunc.action(this, "move"),
						CCCallFunc.action(this, "unLock"),
						CCCallFunc.action(this, "unUpDate"));
				sprite.runAction(sequence);

			}

		}

		else if (flagJ == 3 || flagJ == 4) {
			// System.out.println("______这是BasePeople上面的Move"
			// +"________"+x+"__________________");
			
			/*if(x<200&&x>0||x<960&&x>760){
			
				if(mapX>=0&&mapX<200||mapX>=3440&&mapX<3640){
				CCSpawn spawn = CCSpawn.actions((CCFiniteTimeAction) action, moveTo);
				CCSequence sequence = CCSequence.actions(spawn,
						CCCallFunc.action(this, "move"),
						CCCallFunc.action(this, "unLock"),
						CCCallFunc.action(this, "unUpDate"));
				sprite.runAction(sequence);
				}*/
			//条件挺苛刻,必须四个全部符合才能运行
			if(x<200&&x>0&&mapX>-100&&mapX<=0||x<960&&x>760&&mapX>=-3640&&mapX<=-3540){
			
				
				System.out.println("+++++++++++++++++++++++++++++++++++++++" +
						"++++++++++++++++++++++++++++++++++++++++++" +
						x+"--------------------------------------------" +
								"------------------------------------" +
								mapX);
								
			//if(mapX>=0&&mapX<200||mapX>=3440&&mapX<3640){
			CCSpawn spawn = CCSpawn.actions((CCFiniteTimeAction) action, moveTo);
			CCSequence sequence = CCSequence.actions(spawn,
					CCCallFunc.action(this, "move"),
					CCCallFunc.action(this, "unLock"),
					CCCallFunc.action(this, "unUpDate"));
			sprite.runAction(sequence);
			
			}
			else if(x < 200 || x > 760) {
				// /////////////////////
			
				moveMap();
				if (flagJ == 3) {
					x = x + 100;
				} else {
					x = x - 100;
				}
				// System.out.println("______这是BasePeople下面的Move"
				// +"________"+x+"__________________");

				CCSequence sequence = CCSequence.actions(
						(CCFiniteTimeAction) action,
						CCCallFunc.action(this, "move"),
						CCCallFunc.action(this, "unLock"),
						CCCallFunc.action(this, "unUpDate"));
				// follow();/////////////////////////////////////////
				sprite.runAction(sequence);
				// }
			} else if( x > 200 || x < 760){
			
				CCSpawn spawn = CCSpawn.actions((CCFiniteTimeAction) action,
						moveTo);
				CCSequence sequence = CCSequence.actions(spawn,
						CCCallFunc.action(this, "move"),
						CCCallFunc.action(this, "unLock"),
						CCCallFunc.action(this, "unUpDate"));
				sprite.runAction(sequence);
			}

		}

		return 0;
	}

	protected CGRect peopleBoundingBox;

	public CGRect getBoundingBox() {

		return peopleBoundingBox;

	}

	// ///毒瘤

	private CCSequence CCsequence;

	public void moveMap() {
		scheduler.resume(this);
		CCMoveTo moveTo;
		switch (flagJ) {
		/*
		 * case 1: y=y-10; break; case 2: y=y+10; break;
		 */
		case 3:
			mapX = mapX + 100;
			if (mapX > 0) {
				mapX = mapX - 100;
				// confineMove();
			}

			moveTo = CCMoveTo.action(0.8f, CGPoint.ccp(mapX, mapY));
			CCsequence = CCSequence.actions(moveTo,
					CCCallFunc.action(this, "unUpDate"));
			break;
		case 4:
			mapX = mapX - 100;
			if (mapX < -3640) {
				mapX = mapX + 100;

				confineMove();

			}

			moveTo = CCMoveTo.action(0.8f, CGPoint.ccp(mapX, mapY));
			CCsequence = CCSequence.actions(moveTo,
					CCCallFunc.action(this, "unUpDate"));
			break;
		default:
			break;
		}
		// System.out.println("______Movemap"
		// +"________"+x+"__________________");

		// CGPoint point = CGPoint.ccp(x, y);

		// moveTo = CCMoveTo.action(0.8f,CGPoint.ccp(mapX,mapY));

		// moveTo.setTag(2);

		// CCsequence = CCSequence.actions(moveTo/*,CCCallFunc.action(this,
		// "moveMap")*/);
	
		map.runAction(CCsequence);

	}
	


	public void confineMove() {

		// if(x<200&&x>0||x<960&&x>760){
		if (mapX < 200 && mapX > 0 || mapX < 3440 && mapX > 3640)

			switch (flagJ) {

			case 3: // if(mapX<0&&mapX>-200){
				if (x < 200 && x > 0) {
					x = x - 100;
				}

				break;
			case 4: // if(mapX<3440&&mapX>3640){
				if (x < 960 && x > 760) {
					x = x + 100;
				}
				break;

			default:
				break;
			}

		point = CGPoint.ccp(x, y);
		moveTo = CCMoveTo.action(0.8f, point);
		action = Util.getAnimate(format, num, false, 0.1f);// /////////

		CCSpawn spawn = CCSpawn.actions((CCFiniteTimeAction) action, moveTo);
		CCSequence sequence = CCSequence.actions(spawn,
				CCCallFunc.action(this, "move"),
				CCCallFunc.action(this, "unLock"),
				CCCallFunc.action(this, "unUpDate"));
		sprite.runAction(sequence);

	}

	public void setMapPoint(float mapX, float mapY) {
		this.mapX=mapX;
		this.mapY=mapY;
	
	}

	float mapX = 0;
	float mapY = 0;

	public void beat() {

		// bloodNumber = bloodNumber - attackPower;
		sprite.removeSelf();

		// sprite.getParent().getChildByTag(1).stopAllActions();
		// sprite.getParent().getChildByTag(1).stopAction(2);

		sprite.setScale(2.5f);
		map.getParent().addChild(sprite, 1);

		action = Util.getAnimate(format, num, false, 0.3f);

		CCSequence sequence = CCSequence.actions((CCFiniteTimeAction) action,
				CCCallFunc.action(this, "stand"),
				CCCallFunc.action(this, "unLock"),
				CCCallFunc.action(this, "setIsBeat"));
		sprite.runAction(sequence);

	}

	public void bruise(int attackPower) {
		bloodNumber = bloodNumber - attackPower;
	}

	public void removeSelf() {

		Util.changeLayer(new EndLayer());

	}

	public int commomAttack() {
		return 0;
	}

	public int attack1() {

		attack();

		return 0;
	}

	public int attack2() {

		attack();

		return 0;
	}

	public int attack3() {
		attack();

		return 0;
	}

	public int attack4() {

		attack();

		return 0;
	}

	protected void attack() {
	
		//scheduler.resume(this);
		sprite.setScale(2.5f);
		map.getParent().addChild(sprite, 1);

		action = Util.getAnimate(format, num, false, 0.1f);

		CCSequence sequence = CCSequence.actions((CCFiniteTimeAction) action,
				CCCallFunc.action(this, "stand"),
				CCCallFunc.action(this, "unLock"),
				CCCallFunc.action(this, "setIsAttack")/*,
				CCCallFunc.action(this, "unUpDate")*/);
		sprite.runAction(sequence);
	}

	public void down() {
		

		flag = false;
		sprite.setScale(2.5f);
		map.getParent().addChild(sprite, 1);

		action = Util.getAnimate(format, num, false, 0.3f);

		CCSequence sequence = CCSequence.actions((CCFiniteTimeAction) action,/*
																	 */
				CCCallFunc.action(this, "removeSelf"));
		sprite.runAction(sequence);

	}

	/*
	 * public void end(){ Util.changeLayer(new EndLayer()); }
	 */

	public void setIsAttack() {

		isAttack = false;
	}

	public void setIsBeat(boolean isBeat) {

		this.isBeat = isBeat;
	}

	public boolean getIsBeat() {

		return isBeat;
	}


}
