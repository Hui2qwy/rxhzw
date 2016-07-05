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

public class Enemy5 extends BaseEnemy {

	public Enemy5(CCTMXTiledMap map) {

		super(1);
		this.map = map;
		sprite = CCSprite.sprite("enmey/enemy5/walk/xmr_p01.png");

		head = CCSprite.sprite("bloodnumber/ll5.png");
		head.setPosition(885, 560);
		map.getParent().addChild(head, 1);
		head.setVisible(false);
		pointX = 170;
		pointY = 30;

		attackPower = 5;
		bloodNumber = 100;
		attackDistance = Math.sqrt(pointX * pointX + pointY * pointY);
		// CCScheduler.sharedScheduler().schedule("upDataRect", this, 0.1f,
		// false);

	}

	/*
	 * 
	 * public void makeRect(){ leftRect.set(x-310, y-100, 280, 140);
	 * rightRect.set(x+30, y-100, 280, 140); }
	 */

	public CGRect getBoundingBox() {
		EnemyBoundingBox = CGRect.make(this.getSprites().getPosition().x, this
				.getSprites().getPosition().y , 80, 180);
		return EnemyBoundingBox;

	}

	public void down() {

		sprite.removeSelf();

	}

	public void beat(int attackPower) {

		sprite.removeSelf();
		defaultFormat = "enmey/enemy5/beat/xmr_ad01.png";

		num = 2;
		format = "enmey/enemy5/beat/xmr_ad%02d.png";
		super.beat(attackPower);
	}

	/*
	 * public void upDataRect(float t){ leftAttackRect=CGRect.make(x-305, y-80,
	 * 250, 120); rightAttackRect=CGRect.make(x+305, y-80, 250, 120); }
	 */

	public void attack() {
		leftAttackRect = CGRect.make(x - 305, y - 80, 250, 120);
		rightAttackRect = CGRect.make(x + 55, y - 80, 250, 120);
		sprite.removeSelf();
		defaultFormat = "enmey/enemy5/attack/xmr_gx01.png";

		num = 17;
		format = "enmey/enemy5/attack/xmr_gx%02d.png";
		sprite.setPosition(x, y);
		super.attack();

	}

	public int moveToPeople(CGPoint isPeoplePoint, CGPoint peoplePoint,
			float distance, CGPoint enemyPoint) {

		sprite.removeSelf();
		defaultFormat = "enmey/enemy5/walk/xmr_p01.png";

		format = "enmey/enemy5/walk/xmr_p%02d.png";
		num = 8;

		super.moveToPeople(isPeoplePoint, peoplePoint, distance, enemyPoint);

		return 0;
	}

	public int move() {

		number = random();
		// Random random = new Random();
		// number = random.nextInt(6);
		switch (number) {
		case 0:

		case 1:

		case 2:

		case 3:

			sprite.removeSelf();
			defaultFormat = "enmey/enemy5/walk/xmr_p01.png";

			format = "enmey/enemy5/walk/xmr_p%02d.png";
			num = 8;

			break;

		case 4:

			sprite.removeSelf();
			defaultFormat = "enmey/enemy5/stand/xmr_zl01.png";

			num = 2;
			format = "enmey/enemy5/stand/xmr_zl%02d.png";

			break;
		case 5:

			sprite.removeSelf();
			defaultFormat = "enmey/enemy5/attack/xmr_gx01.png";

			num = 17;
			format = "enmey/enemy5/attack/xmr_gx%02d.png";

			break;

		default:
			break;
		}

		super.move();

		return num;
	}

}