package com.liuzhuang.enemy;

import com.liuzhuang.base.BaseEnemy;
import com.liuzhuang.util.Util;

import java.io.PrintStream;
import java.io.SequenceInputStream;
import java.util.Random;

import org.cocos2d.actions.CCScheduler;
import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCFiniteTimeAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCIntervalAction;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

public class EnemyBoss3 extends BaseEnemy {

	public EnemyBoss3(CCTMXTiledMap map) {

		super(1);
		isBoss=true;
		this.map = map;
		sprite = CCSprite.sprite("enmey/enemyboss3/walk/bj_p01.png");

		head = CCSprite.sprite("bloodnumber/boss3.png");
		head.setPosition(885, 560);
		map.getParent().addChild(head, 1);
		map.getParent().addChild(head, 1);
		head.setVisible(false);
		pointX = 200;
		pointY = 0;
		attackDistance = Math.sqrt(pointX * pointX + pointY * pointY);

		attackPower = 10;
		bloodNumber = 1000; 
		// CCScheduler.sharedScheduler().schedule("upDataRect", this, 0.1f,
		// false);

	}

	/*
	 * public void makeRect(){ leftRect.set(x-60, y-100, 60, 30);
	 * rightRect.set(x+0, y-100, 60, 30); }
	 */

	public CGRect getBoundingBox() {
		EnemyBoundingBox = CGRect.make(this.getSprites().getPosition().x-60, this
				.getSprites().getPosition().y - 90, 120, 180);
		return EnemyBoundingBox;

	}

	public void down() {

		sprite.removeSelf();
		defaultFormat = "enmey/enemyboss3/down/bj_qs01.png";

		num = 10;
		format = "enmey/enemyboss3/down/bj_qs%02d.png";
		super.down();
	}

	public void beat(int attackPower) {

		sprite.removeSelf();
		defaultFormat = "enmey/enemyboss3/beat/bj_ad01.png";

		num = 2;
		format = "enmey/enemyboss3/beat/bj_ad%02d.png";
		super.beat(attackPower);
	}

	/*
	 * public void upDataRect(float t){ leftAttackRect=CGRect.make(x-60, y-100,
	 * 50, 40); rightAttackRect=CGRect.make(x+10, y-100, 50, 40);
	 * 
	 * }
	 */
	public void makeAttackBoundingBox(float t){}
	public void attack() {
		
		scheduler.resume(this);
		//scheduler.pause(this);
		Random random = new Random();
		int i = random.nextInt(4);
		switch (i) {
		case 0:

			defaultFormat = "enmey/enemyboss3/attack1/bj_mjd01.png";
			sprite.removeSelf();

			num = 14;
			format = "enmey/enemyboss3/attack1/bj_mjd%02d.png";

			if (flagI == 1) {
				sprite = CCSprite.sprite(defaultFormat);
				sprite.setFlipX(true);
				sprite.setPosition(x + 200, y);
				// x=x-200;
			} else if (flagI == 0) {
				sprite = CCSprite.sprite(defaultFormat);
				sprite.setPosition(x - 200, y);
				// x=x+200;
			}
			
			leftAttackRect=CGRect.make(this.getSprites().getPosition().x-100, this.getSprites().getPosition().y-30, 400, 40);
			rightAttackRect=CGRect.make(this.getSprites().getPosition().x-310, this.getSprites().getPosition().y-30, 400, 40);
			break;
		case 1:

			defaultFormat = "enmey/enemyboss3/attack2/bj_lz01.png";
			sprite.removeSelf();

			num = 31;
			format = "enmey/enemyboss3/attack2/bj_lz%02d.png";
			if (flagI == 1) {
				sprite = CCSprite.sprite(defaultFormat);
				sprite.setFlipX(true);// /////////////////
				sprite.setPosition(x + 170, y);
				// x=x-170;
			} else if (flagI == 0) {
				sprite = CCSprite.sprite(defaultFormat);
				sprite.setPosition(x - 170, y);
				// x=x+170;
			}

			leftAttackRect=CGRect.make(this.getSprites().getPosition().x-150, this.getSprites().getPosition().y-60, 300, 100);
			rightAttackRect=CGRect.make(this.getSprites().getPosition().x-150, this.getSprites().getPosition().y-60, 300, 100);
			
			break;
		case 2:

			defaultFormat = "enmey/enemyboss3/attack3/bj_fb01.png";
			sprite.removeSelf();

			num = 24;
			format = "enmey/enemyboss3/attack3/bj_fb%02d.png";
			if (flagI == 1) {
				sprite.setFlipX(true);// /////////////////
				sprite = CCSprite.sprite(defaultFormat);
				sprite.setPosition(x + 230, y);
				// x=x-230;
			} else if (flagI == 0) {
				sprite = CCSprite.sprite(defaultFormat);
				sprite.setPosition(x - 230, y);
				// x=x+230;
			}
			
			leftAttackRect=CGRect.make(this.getSprites().getPosition().x-150, this.getSprites().getPosition().y-80, 500, 130);
			rightAttackRect=CGRect.make(this.getSprites().getPosition().x-330, this.getSprites().getPosition().y-80, 500, 130);
			
			
			break;
		case 3:

			defaultFormat = "enmey/enemyboss3/attack4/bj_pc01.png";
			sprite.removeSelf();

			num = 14;
			format = "enmey/enemyboss3/attack4/bj_pc%02d.png";
			if (flagI == 1) {
				sprite = CCSprite.sprite(defaultFormat);
				sprite.setFlipX(true);// /////////////////
				sprite.setPosition(x + 215, y);// 230
				x = x + 430;
			} else if (flagI == 0) {
				sprite = CCSprite.sprite(defaultFormat);
				sprite.setPosition(x - 215, y);
				x = x - 430;
			}
			
			leftAttackRect=CGRect.make(this.getSprites().getPosition().x-200, this.getSprites().getPosition().y-70, 500, 100);
			rightAttackRect=CGRect.make(this.getSprites().getPosition().x-300, this.getSprites().getPosition().y-70, 500, 100);
			
			
			break;

		default:
			break;
		}
		flag = false;
		// sprite = CCSprite.sprite(defaultFormat);
		sprite.setScale(2.5F);
		// sprite.setPosition(x,y);
		judgeDirection();

		map.addChild(sprite, 1);
		action = Util.getAnimate(format, num, false, 0.15F);

		sequence = CCSequence.actions((CCFiniteTimeAction) action,
				CCCallFunc.action(this, "move"), CCCallFunc.action(this, "unUpDate"));
		sprite.runAction(sequence);
	}

	public int moveToPeople(CGPoint isPeoplePoint, CGPoint peoplePoint,
			float distance, CGPoint enemyPoint) {

		sprite.removeSelf();
		defaultFormat = "enmey/enemyboss3/walk/bj_p01.png";

		format = "enmey/enemyboss3/walk/bj_p%02d.png";
		num = 8;

		super.moveToPeople(isPeoplePoint, peoplePoint, distance, enemyPoint);

		return 0;
	}

	public int move() {
		flag = false;
		number = random();
		// Random random = new Random();
		// number = random.nextInt(6);
		switch (number) {
		case 0:

		case 1:

		case 2:

		case 3:
			sprite.removeSelf();
			defaultFormat = "enmey/enemyboss3/walk/bj_p01.png";

			format = "enmey/enemyboss3/walk/bj_p%02d.png";
			num = 8;

			break;

		case 4:

			sprite.removeSelf();
			defaultFormat = "enmey/enemyboss3/stand/bj_zl01.png";

			num = 4;
			format = "enmey/enemyboss3/stand/bj_zl%02d.png";

			break;
		case 5:

			sprite.removeSelf();
			defaultFormat = "enmey/enemyboss3/stand/bj_zl01.png";

			num = 4;
			format = "enmey/enemyboss3/stand/bj_zl%02d.png";

			break;

		default:
			break;
		}
		super.move();

		return num;
	}

}