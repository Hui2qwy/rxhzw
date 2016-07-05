package com.liuzhuang.people;

import java.util.ArrayList;
import java.util.Random;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCFiniteTimeAction;
import org.cocos2d.actions.base.CCFollow;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import com.liuzhuang.base.BasePeople;
import com.liuzhuang.rxhzw.R;
import com.liuzhuang.util.Util;

public class WuSuoPuPeople extends BasePeople {

	public WuSuoPuPeople(CCTMXTiledMap map) {
		this.map = map;
		sprite = CCSprite.sprite("wusuopu/stand/wsp_zl01.png");
		head = CCSprite.sprite("bloodnumber/wusuopu.png");
		head.setPosition(75, 560);
		map.getParent().addChild(head, 2);

	}

	public CGRect getBoundingBox() {
		CGPoint point = map.convertToNodeSpace(x, y);

		peopleBoundingBox = CGRect.make(point.x - 65, point.y - 90, 130, 180);
		return peopleBoundingBox;

	}

	public void getBeatMusic() {
		Util.getMusic(R.raw.manbeat, false);
	}

	public CGPoint randomPoint(int pointX, int pointY) {
		Random random = new Random();
		float x = this.x + random.nextInt(pointX) - pointX / 2;
		float y = this.y + random.nextInt(pointY) - pointY / 2;
		return CGPoint.ccp(x, y);
	}

	public void stand() {
		defaultFormat = "wusuopu/stand/wsp_zl01.png";
		format = "wusuopu/stand/wsp_zl%02d.png";
		num = 4;
		super.stand();
	}

	public int move() {
		flag = false;
		defaultFormat = "wusuopu/walk/wsp_p01.png";
		format = "wusuopu/walk/wsp_p%02d.png";
		num = 8;
		super.move();
		return num;

	}

	public void down() {

		flag = false;
		sprite.removeSelf();
		defaultFormat = "wusuopu/down/wsp_bdf01.png";
		format = "wusuopu/down/wsp_bdf%02d.png";
		num = 4;
		super.down();
	}

	public void beat() {

		flag = false;
		isBeat = true;
		defaultFormat = "wusuopu/beat/wsp_ad01.png";
		format = "wusuopu/beat/wsp_ad%02d.png";
		num = 2;
		super.beat();
	}

	public int commomAttack() {

		flag = false;
		isAttack = true;
		// isLock = false;
		// setIsTouchEnabled(false);///////////////////////////
		// point = sprites.getPosition();
		sprite.removeSelf();
		sprite = CCSprite.sprite("wusuopu/skill/wsp_cz01.png");
		if (flagI == 0) {
			sprite.setFlipX(true);// /////////////////
			sprite.setPosition(x + 30, y);
			// x=x-10;
		} else if (flagI == 1) {
			sprite.setPosition(x - 30, y);
			// x=x+10;

		}
		num = 7;
		format = "wusuopu/skill/wsp_cz%02d.png";
		
		CGPoint point = map.convertToNodeSpace(getX(),getY());
		rightAttackRect = CGRect.make(point.x-60, point.y-50, 160, 160);
		leftAttackRect = CGRect.make(point.x-100, point.y-50, 160, 160);
		
		super.attack();
		return num;
	}

	public int attack1() {

		flag = false;
		isAttack = true;
		// isLock = false;
		// setIsTouchEnabled(false);///////////////////////////
		// point = sprites.getPosition();
		sprite.removeSelf();
		sprite = CCSprite.sprite("wusuopu/skill1/wsp_gj01.png");
		if (flagI == 0) {
			sprite.setFlipX(true);// /////////////////
			sprite.setPosition(x - 150, y + 70);
			x = x - 10;
		} else if (flagI == 1) {
			sprite.setPosition(x + 150, y + 70);
			x = x + 10;

		}
		num = 44;
		format = "wusuopu/skill1/wsp_gj%02d.png";

		CGPoint point = map.convertToNodeSpace(getX(), getY());
		rightAttackRect = CGRect.make(point.x - 70, point.y - 65, 320, 40);
		leftAttackRect = CGRect.make(point.x - 250, point.y - 65, 320, 40);

		super.attack();
		return num;
	}

	public int attack2() {

		flag = false;
		isAttack = true;
		// isLock = false;
		// setIsTouchEnabled(false);/////////////////////////
		// point = sprites.getPosition();
		sprite.removeSelf();
		sprite = CCSprite.sprite("wusuopu/skill2/wsp_bs301.png");

		if (flagI == 0) {
			sprite.setFlipX(true);// /////////////////
			sprite.setPosition(x - 150, y + 20);
		} else if (flagI == 1) {
			sprite.setPosition(x + 150, y + 20);

		}
		num = 13;
		format = "wusuopu/skill2/wsp_bs3%02d.png";

		CGPoint point = map.convertToNodeSpace(getX(), getY());
		rightAttackRect = CGRect.make(point.x - 100, point.y, 250, 40);
		leftAttackRect = CGRect.make(point.x - 200, point.y, 250, 40);

		super.attack();
		return num;
	}

	public int attack3() {

		flag = false;
		isAttack = true;
		// isLock = false;
		// setIsTouchEnabled(false);////////////////////////
		// point = sprite.getPosition();
		sprite.removeSelf();
		sprite = CCSprite.sprite("wusuopu/skill3/wsp_bs201.png");

		if (flagI == 0) {
			sprite.setFlipX(true);// /////////////////
			sprite.setPosition(x - 50, y + 20);
			x = x + 30;
		} else if (flagI == 1) {
			sprite.setPosition(x + 50, y + 20);
			x = x - 30;

		}
		num = 12;
		format = "wusuopu/skill3/wsp_bs2%02d.png";

		CGPoint point = map.convertToNodeSpace(getX(), getY());
		rightAttackRect = CGRect.make(point.x, point.y - 110, 320, 220);
		leftAttackRect = CGRect.make(point.x - 160, point.y - 110, 320, 220);

		super.attack();
		return num;
	}

	public int attack4() {

		flag = false;
		isAttack = true;
		// isLock = false;
		// setIsTouchEnabled(false);/////////////////////
		// point = sprite.getPosition();
		sprite.removeSelf();
		sprite = CCSprite.sprite("wusuopu/skill4/wsp_bs101.png");

		if (flagI == 0) {
			sprite.setFlipX(true);// /////////////////
			sprite.setPosition(x - 20, y + 80);
		} else if (flagI == 1) {
			sprite.setPosition(x + 20, y + 80);
		}
		num = 15;
		format = "wusuopu/skill4/wsp_bs1%02d.png";

		CGPoint point = map.convertToNodeSpace(getX(), getY());
		rightAttackRect = CGRect.make(point.x, point.y - 200, 280, 340);
		leftAttackRect = CGRect.make(point.x - 280, point.y - 200, 280, 340);

		super.attack();
		return num;
	}

}
