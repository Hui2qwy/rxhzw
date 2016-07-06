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

public class Enemy2 extends BaseEnemy {

	public Enemy2(CCTMXTiledMap map) {
		
		super(1);
		this.map = map;
		sprite = CCSprite.sprite("enmey/enemy2/walk/ll2_z01.png");

		head = CCSprite.sprite("bloodnumber/ll2.png");
		head.setPosition(885, 560);
		map.getParent().addChild(head, 1);
		map.getParent().addChild(head, 1);
		head.setVisible(false);
		pointX = 30;
		pointY = 70;
		attackDistance = Math.sqrt(pointX * pointX + pointY * pointY);

		attackPower = 5;
		bloodNumber = 100;
		// CCScheduler.sharedScheduler().schedule("upDataRect", this, 0.1f,
		// false);

	}

	public CGRect getBoundingBox() {
		EnemyBoundingBox = CGRect.make(this.getSprites().getPosition().x, this
				.getSprites().getPosition().y - 90, 120, 120);
		return EnemyBoundingBox;

	}

	/*
	 * public void makeRect(){ leftRect.set(x-60, y-100, 60, 30);
	 * rightRect.set(x+0, y-100, 60, 30); }
	 */

	public void down() {

		sprite.removeSelf();
		defaultFormat = "enmey/enemy2/down/ll2_jd01.png";

		num = 3;
		format = "enmey/enemy2/down/ll2_jd%02d.png";
		super.down();
	}

	public void beat(int attackPower) {

		sprite.removeSelf();
		defaultFormat = "enmey/enemy2/beat/ll2_ad01.png";

		num = 2;
		format = "enmey/enemy2/beat/ll2_ad%02d.png";
		super.beat(attackPower);
	}

	/*
	 * public void upDataRect(float t){ leftAttackRect=CGRect.make(x-60, y-100,
	 * 50, 40); rightAttackRect=CGRect.make(x+10, y-100, 50, 40);
	 * 
	 * }
	 */
	
	

	public void attack() {
		leftAttackRect = CGRect.make(this.getSprites().getPosition().x - 100, this.getSprites().getPosition().y - 100, 130, 40);// (x-60, y-100,
																// 50, 40);
		rightAttackRect = CGRect.make(this.getSprites().getPosition().x + 50, this.getSprites().getPosition().y - 100, 130, 40);// (x+10, y-100,
																// 50, 40);

		defaultFormat = "enmey/enemy2/attack/ll2_gj01.png";
		sprite.removeSelf();

		num = 6;
		format = "enmey/enemy2/attack/ll2_gj%02d.png";
		sprite.setPosition(x, y);
		super.attack();
	}

	public int moveToPeople(CGPoint isPeoplePoint, CGPoint peoplePoint,
			float distance, CGPoint enemyPoint) {

		sprite.removeSelf();
		defaultFormat = "enmey/enemy2/walk/ll2_z01.png";

		format = "enmey/enemy2/walk/ll2_z%02d.png";
		num = 6;

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
			defaultFormat = "enmey/enemy2/walk/ll2_z01.png";

			format = "enmey/enemy2/walk/ll2_z%02d.png";
			num = 6;

			break;

		case 4:

			sprite.removeSelf();
			defaultFormat = "enmey/enemy2/stand/ll2_zl01.png";

			num = 3;
			format = "enmey/enemy2/stand/ll2_zl%02d.png";

			break;
		case 5:

			defaultFormat = "enmey/enemy2/attack/ll2_gj01.png";
			sprite.removeSelf();

			num = 6;
			format = "enmey/enemy2/attack/ll2_gj%02d.png";

			break;

		default:
			break;
		}
		super.move();

		return num;
	}

}