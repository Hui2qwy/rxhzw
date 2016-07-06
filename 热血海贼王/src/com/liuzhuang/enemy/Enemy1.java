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

public class Enemy1 extends BaseEnemy {

	public Enemy1(CCTMXTiledMap map) {

		super(1);
		this.map = map;
		sprite = CCSprite.sprite("enmey/enemy1/walk/ll1_z01.png");

		head = CCSprite.sprite("bloodnumber/ll1.png");
		head.setPosition(885, 560);
		map.getParent().addChild(head, 1);
		head.setVisible(false);

		pointX = 90;// 150;
		pointY = 100;
		attackDistance = Math.sqrt(pointX * pointX + pointY * pointY);

		attackPower = 5;
		bloodNumber = 100;

		// CCScheduler.sharedScheduler().schedule("upDataRect", this, 0.1f,
		// false);

	}

	/*
	 * public void makeRect(float x,float y){ leftRect.set(x+100, y+75, 100,
	 * 50); rightRect.set(x-175, y+75, 100, 50); }
	 */

	public CGRect getBoundingBox() {
		EnemyBoundingBox = CGRect.make(this.getSprites().getPosition().x, this.getSprites().getPosition().y-90, 120, 120);
		return EnemyBoundingBox;

	}

	public void down() {

		sprite.removeSelf();
		defaultFormat = "enmey/enemy1/down/ll1_jd01.png";

		num = 5;
		format = "enmey/enemy1/down/ll1_jd%02d.png";
		super.down();
	}

	public void beat(int attackPower) {

		flag = false;
		sprite.removeSelf();
		defaultFormat = "enmey/enemy1/beat/ll1_ad01.png";

		num = 2;
		format = "enmey/enemy1/beat/ll1_ad%02d.png";
		super.beat(attackPower);
	}

	/*
	 * public void upDataRect(float t){ // leftAttackRect=CGRect.make(x-160,
	 * y-110, 80, 20);
	 * 
	 * //rightAttackRect=CGRect.make(x+160, y-110, 80, 20);
	 * 
	 * }
	 */
	

	
	public void attack() {
		
		leftAttackRect = CGRect.make(this.getSprites().getPosition().x - 170,
				this.getSprites().getPosition().y - 110, 150, 20);// x-120,
																	// y-100,
																	// 80, 20

		rightAttackRect = CGRect.make(this.getSprites().getPosition().x + 40,
				this.getSprites().getPosition().y - 110, 150, 20);// x+40,,
			
		sprite.removeSelf();
		defaultFormat = "enmey/enemy1/attack/ll1_gj01.png";

		num = 6;
		format = "enmey/enemy1/attack/ll1_gj%02d.png";
		sprite.setPosition(x, y);
		super.attack();
	}

	public int moveToPeople(CGPoint isPeoplePoint, CGPoint peoplePoint,
			float distance, CGPoint enemyPoint) {

		sprite.removeSelf();
		defaultFormat = "enmey/enemy1/walk/ll1_z01.png";

		format = "enmey/enemy1/walk/ll1_z%02d.png";
		num = 6;

		super.moveToPeople(isPeoplePoint, peoplePoint, distance, enemyPoint);

		return 0;
	}

	public int move() {

		// Random random = new Random();
		// number = random.nextInt(6);
		number = random();

		switch (number) {
		case 0:

		case 1:

		case 2:

		case 3:
			sprite.removeSelf();
			defaultFormat = "enmey/enemy1/walk/ll1_z01.png";

			format = "enmey/enemy1/walk/ll1_z%02d.png";
			num = 6;

			break;

		case 4:

			sprite.removeSelf();
			defaultFormat = "enmey/enemy1/stand/ll1_zl01.png";

			num = 3;
			format = "enmey/enemy1/stand/ll1_zl%02d.png";

			break;
		case 5:

			sprite.removeSelf();
			defaultFormat = "enmey/enemy1/attack/ll1_gj01.png";

			num = 6;
			format = "enmey/enemy1/attack/ll1_gj%02d.png";

			break;

		default:
			break;
		}
		super.move();

		return num;
	}

}