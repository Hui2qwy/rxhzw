package com.liuzhuang.base;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

public class Base {
	protected float x;
	protected float y;
	protected int num;

	protected CCAction action;
	protected String format;
	protected String defaultFormat;
	protected boolean flag = true;
	protected CCSprite sprite;
	protected CGPoint point;
	protected CCMoveTo moveTo;
	protected CCTMXTiledMap map;
	protected CCSprite head;
	protected int bloodNumber;
	protected CGRect leftAttackRect;
	protected CGRect rightAttackRect;
	protected CGSize winSize = CCDirector.sharedDirector().getWinSize();
	protected int attackPower;

	public float getX() {

		return x;
	}

	public float getY() {

		return y;
	}

	public CCSprite getSprites() {

		return sprite;
	}

	public boolean getFlag() {

		return flag;
	}

	public CGRect getAttackLeftRect() {

		return leftAttackRect;
	}

	public CGRect getAttackRightRect() {

		return rightAttackRect;

	}

	public int getAttackPower() {

		return attackPower;
	}

	public float getBloodNumber() {

		return bloodNumber;
	}

	public void unLock() {

		flag = true;
	}

	/*
	 * public void stop(int num){}
	 * 
	 * public void init(int i, int j){}
	 */
}
