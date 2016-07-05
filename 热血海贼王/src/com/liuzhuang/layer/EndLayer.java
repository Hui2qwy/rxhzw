package com.liuzhuang.layer;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.view.MotionEvent;

import com.liuzhuang.rxhzw.R;
import com.liuzhuang.util.Util;

public class EndLayer extends CCLayer{
	
	private CCSprite backGround;
	private CCSprite sprite;
	private CCSprite back;
	private CCSprite restart;

	
	public EndLayer(){
		
		Util.pauseMusic(1);
		
		
		setIsTouchEnabled(true);
		backGround = CCSprite.sprite("menu/end/background.png");
		sprite = CCSprite.sprite("menu/end/kuangkuang.jpg");
		back = CCSprite.sprite("menu/end/back.jpg");
		restart = CCSprite.sprite("menu/end/restart.jpg");
		
		//backGround.setScale(2);
		backGround.setPosition(480, 320);
		sprite.setPosition(480,320);
		back.setPosition(480-100,320);
		restart.setPosition(480+100,320);
		
		
		this.addChild(backGround);
		this.addChild(sprite);
		this.addChild(back);
		this.addChild(restart);
	
	}
	
	public boolean ccTouchesBegan(MotionEvent event) {
		
		CGPoint point = convertTouchToNodeSpace(event);
		
		CGRect boundingBackBox = back.getBoundingBox();
		CGRect boundingRestartBox = restart.getBoundingBox();
		
		if(CGRect.containsPoint(boundingBackBox, point)){
			
			Util.getMusic(R.raw.clickmusic, false);
			Util.changeLayer(new StartLayer());
			
		}if(CGRect.containsPoint(boundingRestartBox, point)){
			
			Util.getMusic(R.raw.clickmusic, false);				
			Util.changeLayer(new ChoosePeopleLayer());
			
		}
		
		return super.ccTouchesBegan(event);
	}



	
	
		

		

	
}