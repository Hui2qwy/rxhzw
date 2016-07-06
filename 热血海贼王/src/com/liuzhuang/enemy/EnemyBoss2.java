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

public class EnemyBoss2 extends BaseEnemy {

	public EnemyBoss2(CCTMXTiledMap map) {

		super(0);
		isBoss=true;
		this.map = map;
		sprite = CCSprite.sprite("enmey/enemyboss2/walk/jsz_z01.png");

		head = CCSprite.sprite("bloodnumber/boss2.png");
		head.setPosition(885, 560);
		map.getParent().addChild(head, 1);
		map.getParent().addChild(head, 1);
		head.setVisible(false);
		pointX = 50;
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
		defaultFormat = "enmey/enemyboss2/down/jsz_qs01.png";

		num = 10;
		format = "enmey/enemyboss2/down/jsz_qs%02d.png";
		super.down();
	}

	public void beat(int attackPower) {

		sprite.removeSelf();
		defaultFormat = "enmey/enemyboss2/beat/jsz_ad01.png";

		num = 2;
		format = "enmey/enemyboss2/beat/jsz_ad%02d.png";
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

			defaultFormat = "enmey/enemyboss2/attack1/jsz_sy01.png";
			sprite.removeSelf();

			num = 7;
			format = "enmey/enemyboss2/attack1/jsz_sy%02d.png";

			if (flagI == 1) {
				sprite = CCSprite.sprite(defaultFormat);
				sprite.setFlipX(true);
				sprite.setPosition(x + 70, y);
				// x=x-200;
			} else if (flagI == 0) {
				sprite = CCSprite.sprite(defaultFormat);
				sprite.setPosition(x - 70, y);
				// x=x+200;
			}
			
			leftAttackRect=CGRect.make(this.getSprites().getPosition().x-150, this.getSprites().getPosition().y-100, 300,160);
			rightAttackRect=CGRect.make(this.getSprites().getPosition().x-150, this.getSprites().getPosition().y-100, 300, 160);
			break;
		case 1:

			defaultFormat = "enmey/enemyboss2/attack2/jsz_tj01.png";
			sprite.removeSelf();

			num = 8;
			format = "enmey/enemyboss2/attack2/jsz_tj%02d.png";
			if (flagI == 1) {
				sprite = CCSprite.sprite(defaultFormat);
				sprite.setFlipX(true);// /////////////////
				sprite.setPosition(x, y);
				// x=x-170;
			} else if (flagI == 0) {
				sprite = CCSprite.sprite(defaultFormat);
				sprite.setPosition(x, y);
				// x=x+170;
			}

			leftAttackRect=CGRect.make(this.getSprites().getPosition().x, this.getSprites().getPosition().y ,140,140);
			rightAttackRect=CGRect.make(this.getSprites().getPosition().x, this.getSprites().getPosition().y, 140,140);
			
			break;
		case 2:

			defaultFormat = "enmey/enemyboss2/attack3/jsz_zh01.png";
			sprite.removeSelf();

			num = 9;
			format = "enmey/enemyboss2/attack3/jsz_zh%02d.png";
			if (flagI == 1) {
				sprite.setFlipX(true);// /////////////////
				sprite = CCSprite.sprite(defaultFormat);
				sprite.setPosition(x , y);
				// x=x-230;
			} else if (flagI == 0) {
				sprite = CCSprite.sprite(defaultFormat);
				sprite.setPosition(x , y);
				// x=x+230;
			}
			
			leftAttackRect=CGRect.make(this.getSprites().getPosition().x-110, this.getSprites().getPosition().y-40, 180, 180);
			rightAttackRect=CGRect.make(this.getSprites().getPosition().x-90, this.getSprites().getPosition().y-40, 180, 180);
			
			
			break;
		case 3:

			defaultFormat = "enmey/enemyboss2/attack4/jsz_bs01.png";
			sprite.removeSelf();

			num = 28;
			format = "enmey/enemyboss2/attack4/jsz_bs%02d.png";
			if (flagI == 1) {
				sprite = CCSprite.sprite(defaultFormat);
				sprite.setFlipX(true);// /////////////////
				sprite.setPosition(x + 80, y);// 230
			
			} else if (flagI == 0) {
				sprite = CCSprite.sprite(defaultFormat);
				sprite.setPosition(x - 80, y);
			
			}
			
			leftAttackRect=CGRect.make(this.getSprites().getPosition().x-450, this.getSprites().getPosition().y-150, 900, 300);
			rightAttackRect=CGRect.make(this.getSprites().getPosition().x-450, this.getSprites().getPosition().y-150, 900, 300);
			
			
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
		defaultFormat = "enmey/enemyboss2/walk/jsz_z01.png";

		format = "enmey/enemyboss2/walk/jsz_z%02d.png";
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
			defaultFormat = "enmey/enemyboss2/walk/jsz_z01.png";

			format = "enmey/enemyboss2/walk/jsz_z%02d.png";
			num = 8;

			break;

		case 4:

			sprite.removeSelf();
			defaultFormat = "enmey/enemyboss2/stand/jsz_zl01.png";

			num = 6;
			format = "enmey/enemyboss2/stand/jsz_zl%02d.png";

			break;
		case 5:

			sprite.removeSelf();
			defaultFormat = "enmey/enemyboss2/stand/jsz_zl01.png";

			num = 6;
			format = "enmey/enemyboss2/stand/jsz_zl%02d.png";

			break;

		default:
			break;
		}
		super.move();

		return num;
	}

}