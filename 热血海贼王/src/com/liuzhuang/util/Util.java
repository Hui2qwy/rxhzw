package com.liuzhuang.util;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.prefs.PreferencesFactory;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.tile.CCFadeOutDownTiles;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.layers.CCTMXObjectGroup;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.transitions.CCFadeBLTransition;
import org.cocos2d.transitions.CCFadeDownTransition;
import org.cocos2d.transitions.CCFadeTRTransition;
import org.cocos2d.transitions.CCFlipAngularTransition;
import org.cocos2d.transitions.CCFlipXTransition;
import org.cocos2d.types.CGPoint;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Util {
	// private static CCScene scene=CCScene.node();

	public static void changeLayer(CCLayer newLayer) {
		CCScene scene = CCScene.node();
		scene.addChild(newLayer);
		// CCFlipAngularTransition
		// angularTransition=CCFlipAngularTransition.transition(1, scene, 0);
		// CCDirector.sharedDirector().replaceScene(angularTransition);

		CCFlipXTransition transition = CCFlipXTransition
				.transition(1, scene, 0);
		CCDirector.sharedDirector().replaceScene(transition);
	}

	public static ArrayList<CGPoint> getMapPoints(CCTMXTiledMap map, String name) {
		ArrayList<CGPoint> points = new ArrayList<CGPoint>();
		CCTMXObjectGroup objectGroupNamed = map.objectGroupNamed(name);
		ArrayList<HashMap<String, String>> objects = objectGroupNamed.objects;
		for (HashMap<String, String> hashMap : objects) {
			int x = Integer.parseInt(hashMap.get("x"));
			int y = Integer.parseInt(hashMap.get("y"));
			CGPoint cgPoint = CCNode.ccp(x, y);
			points.add(cgPoint);
		}
		return points;
	}

	public static CCAction getAnimate(String format, int num,
			boolean isForerver, float time) {
		ArrayList<CCSpriteFrame> frames = new ArrayList<CCSpriteFrame>();

		for (int i = 1; i <= num; i++) {
			// CCSprite sprite = CCSprite.sprite(String.format(format, i));
			CCSpriteFrame spriteFrame = CCSprite.sprite(
					String.format(format, i)).displayedFrame();
			frames.add(spriteFrame);
			// spriteFrame.getRect();
		}
		CCAnimation anim = CCAnimation.animation("", time, frames);
		if (isForerver) {
			CCAnimate animate = CCAnimate.action(anim);
			CCRepeatForever forever = CCRepeatForever.action(animate);
			return forever;
		} else {
			CCAnimate animate = CCAnimate.action(anim, false);
			return animate;
		}

	}

	private static SoundEngine engine = new SoundEngine();
	private static SoundEngine engine2 = new SoundEngine();

	public static void getMusic(int resId, boolean loop) {
		engine2.playSound(CCDirector.theApp, resId, loop);
	}

	public static void startMusic(int resId, boolean loop) {

		// engine = new SoundEngine();
		engine.playSound(CCDirector.theApp, resId, loop);

	}

	public static void pauseMusic(int id) {
		switch (id) {
		case 1:
			engine.pauseSound();
			break;
		case 2:
			engine2.pauseSound();
			break;

		default:
			break;
		}
	}

	public static void purgeMusic(int id) {
		switch (id) {
		case 1:
			engine.purgeSharedEngine();
			break;
		case 2:
			engine2.purgeSharedEngine();
			break;

		default:
			break;
		}
	}

	public static void resumeMusic(int id) {
		switch (1) {
		case 1:
			engine.resumeSound();
			break;
		case 2:
			engine2.resumeSound();
			break;

		default:
			break;
		}

	}
	
	public static void setSharedPreferences(String closeLock, boolean lock){
		
		SharedPreferences preferences = CCDirector.theApp.getSharedPreferences("lock", CCDirector.theApp.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putBoolean(closeLock, lock);
		editor.commit();
		
	}
	
	public static boolean getSharedPreferences(String closeLock){
		SharedPreferences preferences = CCDirector.theApp.getSharedPreferences("lock", CCDirector.theApp.MODE_PRIVATE);
		boolean lock = preferences.getBoolean(closeLock, false);
		return lock;
		
	}
	
	private static int peopleNum;
	
	public static void setPeopleNum(int people_Num){
		peopleNum = people_Num;
	}
	
	public static int getPeopleNum(){
		return peopleNum;
	}
	
	
	

}
