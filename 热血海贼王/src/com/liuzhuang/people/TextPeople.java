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

public class TextPeople extends BasePeople {

	public TextPeople(CCTMXTiledMap map){
		this.map=map;
	    sprite = CCSprite.sprite("enmey/enemyboss/walk/bj_p01.png");
		head = CCSprite.sprite("bloodnumber/boss.png");
		head.setPosition(75,560);
		map.getParent().addChild(head,2);
		

	}
	
	 public CGRect getBoundingBox(){
		 
		CGPoint point = map.convertToNodeSpace(x,y);

		peopleBoundingBox = CGRect.make(point.x-52, point.y-82 ,105, 165);	
			return peopleBoundingBox;

	 }
	
	public CGPoint randomPoint(int pointX,int pointY){
		 Random random = new Random();
		 float x = this.x+random.nextInt(pointX)-pointX/2 ;
		 float y = this.y+random.nextInt(pointY)-pointY/2;
		 return CGPoint.ccp(x, y);
 	}
	
	public void stand()
	{
		sprite.removeSelf();
		defaultFormat = "enmey/enemyboss/stand/bj_zl01.png";
	    
	    num = 4;
	    format = "enmey/enemyboss/stand/bj_zl%02d.png";
		super.stand();
	}
	
	
	public int move()
	{
		sprite.removeSelf();
		defaultFormat = "enmey/enemyboss/walk/bj_p01.png";
	    
		format = "enmey/enemyboss/walk/bj_p%02d.png";
	    num = 8;
		super.move();
		return num;
	}
	
	
	public void down(){
		
		sprite.removeSelf();
		  defaultFormat = "enmey/enemyboss/down/bj_qs01.png";
			
		  num = 10;
		  format = "enmey/enemyboss/down/bj_qs%02d.png";
		super.down();
	}
	
	public void beat(){
		
		 sprite.removeSelf();
		  defaultFormat = "enmey/enemyboss/beat/bj_ad01.png";
			
		  num = 2;
		  format = "enmey/enemyboss/beat/bj_ad%02d.png";
		super.beat();
	}

	
	public int attack1() {
		
		flag=false;
		isAttack=true;
		//isLock = false;
		//setIsTouchEnabled(false);///////////////////////////
		//point = sprites.getPosition();
		defaultFormat = "enmey/enemyboss/attack1/bj_mjd01.png";
		sprite.removeSelf();
			  
		num = 14;
		format = "enmey/enemyboss/attack1/bj_mjd%02d.png";
		if(flagI==1){
			sprite.setFlipX(true);
			sprite.setPosition(x+200,y);
			
		}else if(flagI==0){
			sprite.setPosition(x+200,y);
		
		}																																									
		
		super.attack();
		return num;
	}
	

	public int attack2() {
		
		flag=false;
		isAttack=true;
		//isLock = false;
		//setIsTouchEnabled(false);/////////////////////////
		//point = sprites.getPosition();
		defaultFormat = "enmey/enemyboss/attack2/bj_lz01.png";
		sprite.removeSelf();
			  
		num = 31;
		format = "enmey/enemyboss/attack2/bj_lz%02d.png";
		if(flagI==1){
			sprite.setFlipX(true);///////////////////
			sprite.setPosition(x+170,y-15);
			//x=x+60;
		}else if(flagI==0){
			sprite.setPosition(x-170,y-15);
			//x=x-60;
		}
		
		super.attack();
		return num;
	}
	

	public int attack3() {
		
		flag=false;
		isAttack=true;
		//isLock = false;
		//setIsTouchEnabled(false);////////////////////////
		//point = sprite.getPosition();
		defaultFormat = "enmey/enemyboss/attack3/bj_fb01.png";
		sprite.removeSelf();
			  
		num = 24;
		format = "enmey/enemyboss/attack3/bj_fb%02d.png";
		if(flagI==1){
			sprite.setFlipX(true);///////////////////
			sprite.setPosition(x+230,y-15);
		}
		else if(flagI==0){
			sprite.setPosition(x-230,y-15);
			
		}
	
		super.attack();
		return num;
	}
	

	public int attack4() {
		
		flag=false;
		isAttack=true;
		//isLock = false;
		//setIsTouchEnabled(false);/////////////////////
		//point = sprite.getPosition();
		defaultFormat = "enmey/enemyboss/attack4/bj_pc01.png";
		sprite.removeSelf();
		  
		num = 14;
		format = "enmey/enemyboss/attack4/bj_pc%02d.png";
		if(flagI==1){
			sprite.setFlipX(true);///////////////////
			sprite.setPosition(x+215,y-15);
			x=x+230;
		}else if(flagI==0){
			sprite.setPosition(x-215,y-15);
			x=x-230;
		}
		
		
		super.attack();
		return num;
	}

}