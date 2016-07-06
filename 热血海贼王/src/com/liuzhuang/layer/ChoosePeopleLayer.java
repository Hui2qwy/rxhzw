package com.liuzhuang.layer;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import com.liuzhuang.rxhzw.R;
import com.liuzhuang.util.Util;



import android.view.MotionEvent;

public class ChoosePeopleLayer extends CCLayer{

	//private CCSprite sprite;
	private CCSprite sprite1;
	private CCSprite sprite2;
	private CCSprite sprite3;
	private CCSprite sprite4;
	
	private CCSprite start;
	private CCSprite back;
	private CCSprite topSprite;
	private CCSprite bottomSprite;
	
	private boolean flag1;
	private boolean flag2;
	private boolean flag3;
	private boolean flag4;
	
	private int i;
	
	public ChoosePeopleLayer(){
		setIsTouchEnabled(true);
		init();
	}
	
	private void init(){
		
		start = CCSprite.sprite("menu/people/start.jpg");
		back = CCSprite.sprite("menu/people/back.jpg");
		bottomSprite = CCSprite.sprite("menu/people/boatbottom.jpg");
		topSprite = CCSprite.sprite("menu/people/boattop.jpg");
		sprite1 = CCSprite.sprite("menu/people/lufei.jpg");
		sprite2 = CCSprite.sprite("menu/people/suolong.jpg");
		sprite3 = CCSprite.sprite("menu/people/wusuopu.jpg");
		sprite4 = CCSprite.sprite("menu/people/namei.jpg");
		
		bottomSprite.setPosition(480,80);
		start.setPosition(480,80);
		back.setPosition(800,80);
		topSprite.setPosition(480,880);
		sprite1.setPosition(120,400);
		sprite2.setPosition(360,400);
		sprite3.setPosition(600,400);
		sprite4.setPosition(840,400);
		
		sprite1.setOpacity(250);
		sprite2.setOpacity(100);
		sprite3.setOpacity(100);
		sprite4.setOpacity(100);
		
		this.addChild(bottomSprite,1);
		this.addChild(topSprite,1);
		this.addChild(start,2);
		this.addChild(back,2);
		this.addChild(sprite1);
		this.addChild(sprite2);
		this.addChild(sprite3);
		this.addChild(sprite4);
		
		flag1=true;
		
	}
	
	private void initS(){
		if(flag1){
			flag1=false;
			sprite1.setOpacity(100);
		}else if(flag2){
			flag2=false;
			sprite2.setOpacity(100);
		}
		else if(flag3){
			flag3=false;
			sprite3.setOpacity(100);
		}
		else if(flag4){
			flag4=false;
			sprite4.setOpacity(100);
		}
	}
	
	public void moveTo(){
		
		//sprite1.setOpacity(250);
	//	sprite2.setOpacity(250);
		//sprite3.setOpacity(250);
		//sprite4.setOpacity(250);
		
		topSprite.setOpacity(250);
		CCMoveTo moveTo = CCMoveTo.action(2, ccp(480, 400));
		CCSequence sequence = CCSequence.actions(moveTo, CCCallFunc.action(this, "change"));
		topSprite.runAction(sequence);
	}
	
	public void change(){
		
		//topSprite.removeSelf();
		//bottomSprite.removeSelf();
		Util.setPeopleNum(i);
		
		Util.changeLayer(new ChooseCloseLayer(i));
	}
	
	public boolean ccTouchesBegan(MotionEvent event) {
		//setIsTouchEnabled(false);
		CGPoint point = this.convertTouchToNodeSpace(event);
		CGRect boundingstartBox = start.getBoundingBox();
		CGRect boundingbackBox = back.getBoundingBox();
		CGRect boundingsprite1Box = sprite1.getBoundingBox();
		CGRect boundingsprite2Box = sprite2.getBoundingBox();
		CGRect boundingsprite3Box = sprite3.getBoundingBox();
		CGRect boundingsprite4Box = sprite4.getBoundingBox();
		
			if(CGRect.containsPoint(boundingbackBox, point)){
				Util.getMusic(R.raw.clickmusic, false);
				Util.changeLayer(new StartLayer());
				
			}else if(CGRect.containsPoint(boundingstartBox, point)){
				
				if(sprite1.getOpacity()==250){
					Util.getMusic(R.raw.clickmusic, false);
					i=1;
					setIsTouchEnabled(false);
					moveTo();
					//Util.changeLayer(new MainLayer(i));
				}else if(sprite2.getOpacity()==250){
					Util.getMusic(R.raw.clickmusic, false);
					i=2;
					setIsTouchEnabled(false);
					moveTo();
					
					//Util.changeLayer(new MainLayer(i));
				}else if(sprite3.getOpacity()==250){
					Util.getMusic(R.raw.clickmusic, false);
					i=3;
					setIsTouchEnabled(false);
					moveTo();
					//Util.changeLayer(new MainLayer(i));
				}else if(sprite4.getOpacity()==250){
					Util.getMusic(R.raw.clickmusic, false);
					i=4;
					setIsTouchEnabled(false);
					moveTo();
					//Util.changeLayer(new MainLayer(i));
				}
				
			}else if(CGRect.containsPoint(boundingsprite1Box, point)){
				Util.getMusic(R.raw.clickmusic, false);
				initS();//把之前选中的设置成透明
				flag1=true;
				sprite1.setOpacity(250);
				
			}else if(CGRect.containsPoint(boundingsprite2Box, point)){
				Util.getMusic(R.raw.clickmusic, false);
				initS();
				flag2=true;
				sprite2.setOpacity(250);
					
			}else if(CGRect.containsPoint(boundingsprite3Box, point)){
				Util.getMusic(R.raw.clickmusic, false);
				initS();
				flag3=true;
				sprite3.setOpacity(250);
				
			}else if(CGRect.containsPoint(boundingsprite4Box, point)){
				Util.getMusic(R.raw.clickmusic, false);
				initS();
				flag4=true;
				sprite4.setOpacity(250);
		
			}
			
		return super.ccTouchesBegan(event);
	
	}
}
