package com.liuzhuang.layer;

import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.view.MotionEvent;

import com.liuzhuang.rxhzw.R;
import com.liuzhuang.util.Util;

public class ChooseCloseLayer extends CCLayer{
	
	
	private CCSprite backGround;
	private CCSprite back;
	private CCSprite start;
	private CCSprite close1;
	private CCSprite close2;
	private CCSprite close3;
	private CCSprite close2Lock;
	private CCSprite close3Lock;
	
	//private CCSprite unLock;
	
	private int closeNum=1;
	
	private int i;
	
	/**
	 * 关卡数
	 * 人数
	 * 是哪一个boos
	 * 地图
	 */
	
	public ChooseCloseLayer(int i){
		
		this.i=i;
		setIsTouchEnabled(true);
		
		backGround = CCSprite.sprite("menu/chooseclose/background.png");
		back = CCSprite.sprite("menu/chooseclose/back.jpg");
		start = CCSprite.sprite("menu/chooseclose/start.jpg");
		close1 = CCSprite.sprite("menu/chooseclose/close1.png");
		close2 = CCSprite.sprite("menu/chooseclose/close2.jpg");
		close3 = CCSprite.sprite("menu/chooseclose/close3.jpg");
		
		close2Lock = CCSprite.sprite("menu/chooseclose/lock.png");
		close3Lock = CCSprite.sprite("menu/chooseclose/lock.png");
		
		//unLock = CCSprite.sprite("menu/chooseclose/unLock.jpg");
		
		backGround.setPosition(480, 320);
		back.setPosition(100,100);
		start.setPosition(860,100);
		close1.setPosition(160,480);
		close2.setPosition(480,480);
		close3.setPosition(800,480);
		close2Lock.setPosition(480, 510);
		close3Lock.setPosition(800, 510);
		
	//	unLock.setPosition(480, 100);
				
		this.addChild(backGround);
		this.addChild(back);
		this.addChild(start);
		this.addChild(close1);
		this.addChild(close2);
		this.addChild(close3);
		this.addChild(close2Lock);
		this.addChild(close3Lock);
		
	//	this.addChild(unLock);
		
		close2.setOpacity(100);
		close3.setOpacity(100);
		
		
		
		
		
		if((Util.getSharedPreferences("close2Lock"))==true){
			close2Lock.setVisible(false);
			close2.setOpacity(255);
		}
		if((Util.getSharedPreferences("close3Lock"))==true){
			close3Lock.setVisible(false);
			close3.setOpacity(255);
		}
	}
	
/*	public void unLock(){
		close2Lock.setVisible(false);
		close2.setOpacity(255);
		close3Lock.setVisible(false);
		close3.setOpacity(255);
		
			Util.setSharedPreferences("close2Lock", true);
		
			Util.setSharedPreferences("close3Lock", true);
		
	}
	*/
	
	public boolean ccTouchesBegan(MotionEvent event) {
		
		CGPoint point = convertTouchToNodeSpace(event);
		
		CGRect boundingBackBox = back.getBoundingBox();
		CGRect boundingStartBox = start.getBoundingBox();
		CGRect boundingClose1Box = close1.getBoundingBox();
		CGRect boundingClose2Box = close2.getBoundingBox();
		CGRect boundingClose3Box = close3.getBoundingBox();
		
		/*CGRect boundingunLockBox = unLock.getBoundingBox();
		
		if(CGRect.containsPoint(boundingunLockBox, point)){
			unLock();
		}*/
		
		
		if(CGRect.containsPoint(boundingClose1Box, point)){
			Util.getMusic(R.raw.clickmusic, false);
			closeNum=1;
			
		}
		if((Util.getSharedPreferences("close2Lock"))==true){
			if(CGRect.containsPoint(boundingClose2Box, point)){
				/*close2.setOpacity(255);
				close2.setVisible(false);*/
				Util.getMusic(R.raw.clickmusic, false);
				closeNum=2;
				
			}
		}
		if((Util.getSharedPreferences("close3Lock"))==true){
			if(CGRect.containsPoint(boundingClose3Box, point)){
				/*close3.setOpacity(255);
				close3Lock.setVisible(false);*/
				Util.getMusic(R.raw.clickmusic, false);
				closeNum=3;
			}
		}
		if(CGRect.containsPoint(boundingBackBox, point)){
			Util.getMusic(R.raw.clickmusic, false);
			Util.changeLayer(new StartLayer());
			
		}if(CGRect.containsPoint(boundingStartBox, point)){
			Util.getMusic(R.raw.clickmusic, false);		
			Util.changeLayer(new MainLayer(closeNum, i));
		}
	
		return super.ccTouchesBegan(event);
	}

	
	
}
