package com.liuzhuang.layer;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.view.MotionEvent;

import com.liuzhuang.rxhzw.R;
import com.liuzhuang.util.Util;

public class WinLayer extends CCLayer{
	
	private CCSprite backGround;
	private CCSprite sprite;
	private CCSprite back;
	private CCSprite last;

	
	public WinLayer(){
		
		//Util.pauseMusic(1);
		
		
		setIsTouchEnabled(true);
		backGround = CCSprite.sprite("menu/win/beidabai.png");
		sprite = CCSprite.sprite("menu/win/kuangkuang.jpg");
		back = CCSprite.sprite("menu/win/back.jpg");
		last = CCSprite.sprite("menu/win/last.jpg");
		
		//backGround.setScale(2);
		backGround.setPosition(480, 320);
		sprite.setPosition(480,320);
		back.setPosition(480-100,320);
		last.setPosition(480+100,320);
		
		
		this.addChild(backGround);
		this.addChild(sprite);
		this.addChild(back);
		this.addChild(last);
	
	}
	
	public boolean ccTouchesBegan(MotionEvent event) {
		
		CGPoint point = convertTouchToNodeSpace(event);
		
		CGRect boundingBackBox = back.getBoundingBox();
		CGRect boundinglastBox = last.getBoundingBox();
		
		if(CGRect.containsPoint(boundingBackBox, point)){
			
			Util.getMusic(R.raw.clickmusic, false);
			Util.changeLayer(new StartLayer());
			
		}if(CGRect.containsPoint(boundinglastBox, point)){
				
			
			Util.getMusic(R.raw.clickmusic, false);
			/**
			 * 
			 * 在这里进行下一关
			 * 
			 */
			Util.changeLayer(new ChooseCloseLayer(Util.getPeopleNum()));
			
		}
		
		return super.ccTouchesBegan(event);
	}



	
	
}