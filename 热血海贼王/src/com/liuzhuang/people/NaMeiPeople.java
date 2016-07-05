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

public class NaMeiPeople extends BasePeople {

	public NaMeiPeople(CCTMXTiledMap map){
		this.map=map;
		sprite = CCSprite.sprite("namei/stand/nm_zl01.png");
		head = CCSprite.sprite("bloodnumber/namei.png");
		head.setPosition(75,560);
		map.getParent().addChild(head,2);
		

	}
	
	 public CGRect getBoundingBox(){
		 
		CGPoint point = map.convertToNodeSpace(x,y);

		peopleBoundingBox = CGRect.make(point.x-52, point.y-82 ,105, 165);
		//peopleBoundingBox = CGRect.make(point.x-92, point.y-102 ,185, 205);	
			return peopleBoundingBox;

	 }
	 
	 public void getBeatMusic() {
			Util.getMusic(R.raw.womanbeat, false);
		}
	
	public CGPoint randomPoint(int pointX,int pointY){
		 Random random = new Random();
		 float x = this.x+random.nextInt(pointX)-pointX/2 ;
		 float y = this.y+random.nextInt(pointY)-pointY/2;
		 return CGPoint.ccp(x, y);
 	}
	
	public void stand()
	{
		defaultFormat = "namei/stand/nm_zl01.png";
		format = "namei/stand/nm_zl%02d.png";
		num = 3;
		super.stand();
	}
	
	
	public int move()
	{
		flag=false;
		defaultFormat = "namei/walk/nm_p01.png";
		format = "namei/walk/nm_p%02d.png";
		num = 8;
		super.move();
		return num;
	}
	
	
	public void down(){
		
		flag=false;
		sprite.removeSelf();
		defaultFormat = "namei/down/nm_bdf01.png";
		format = "namei/down/nm_bdf%02d.png";
		num = 4;
		super.down();
	}
	
	public void beat(){
		
		flag=false;
		isBeat=true;
		defaultFormat = "namei/beat/nm_ad01.png";
		format = "namei/beat/nm_ad%02d.png";
		num = 2;
		super.beat();
	}

	
	public int commomAttack(){
		
		flag=false;
		isAttack=true;
		//isLock = false;
		//setIsTouchEnabled(false);///////////////////////////
		//point = sprites.getPosition();
		sprite.removeSelf();
		sprite = CCSprite.sprite("namei/skill/nm_gj201.png");
		if(flagI==0){
			sprite.setFlipX(true);
			sprite.setPosition(x-10,y+5);
			
		}else if(flagI==1){
			sprite.setPosition(x+10,y+5);
		
		}																																									
		num = 8;
		format="namei/skill/nm_gj2%02d.png";
		
		CGPoint point = map.convertToNodeSpace(getX(),getY());
		rightAttackRect = CGRect.make(point.x, point.y, 240, 180);
		leftAttackRect = CGRect.make(point.x, point.y, 240, 180);
		
		super.attack();
		return num;
	}
	
	public int attack1() {
		
		flag=false;
		isAttack=true;
		//isLock = false;
		//setIsTouchEnabled(false);///////////////////////////
		//point = sprites.getPosition();
		sprite.removeSelf();
		sprite = CCSprite.sprite("namei/skill1/nm_fsj01.png");
		if(flagI==0){
			sprite.setFlipX(true);
			sprite.setPosition(x-50,y+20);
			
		}else if(flagI==1){
			sprite.setPosition(x+50,y+20);
		
		}																																									
		num = 21;
		format="namei/skill1/nm_fsj%02d.png";
		
		CGPoint point = map.convertToNodeSpace(getX(),getY());
		rightAttackRect = CGRect.make(point.x-40, point.y-80, 200, 180);
		leftAttackRect = CGRect.make(point.x-200, point.y-80, 200, 180);
		
		super.attack();
		return num;
	}
	

	public int attack2() {
		
		flag=false;
		isAttack=true;
		//isLock = false;
		//setIsTouchEnabled(false);/////////////////////////
		//point = sprites.getPosition();
		sprite.removeSelf();
		sprite = CCSprite.sprite("namei/skill2/nm_hx01.png");
		
		if(flagI==0){
			sprite.setFlipX(true);///////////////////
			sprite.setPosition(x-110,y+10);
			x=x+60;
		}else if(flagI==1){
			sprite.setPosition(x+110,y+10);
			x=x-60;
		}
		num = 40;
		format="namei/skill2/nm_hx%02d.png";
		
		CGPoint point = map.convertToNodeSpace(getX(),getY());
		rightAttackRect = CGRect.make(point.x-300, point.y-50, 600, 100);
		leftAttackRect = CGRect.make(point.x-300, point.y-50, 600, 100);
		
		
		super.attack();
		return num;
	}
	

	public int attack3() {
		
		flag=false;
		isAttack=true;
		//isLock = false;
		//setIsTouchEnabled(false);////////////////////////
		//point = sprite.getPosition();
		sprite.removeSelf();
		sprite = CCSprite.sprite("namei/skill3/nm_bs101.png");
		
		if(flagI==0){
			sprite.setFlipX(true);///////////////////
			sprite.setPosition(x-80,y+45);
		}
		else if(flagI==1){
			sprite.setPosition(x+80,y+45);
			
		}
		num = 23;
		format="namei/skill3/nm_bs1%02d.png";
		
		CGPoint point = map.convertToNodeSpace(getX(),getY());
		rightAttackRect = CGRect.make(point.x-50, point.y-100, 360, 180);
		leftAttackRect = CGRect.make(point.x-250, point.y-100, 360, 180);
		
		super.attack();
		return num;
	}
	

	public int attack4() {
		
		flag=false;
		isAttack=true;
		//isLock = false;
		//setIsTouchEnabled(false);/////////////////////
		//point = sprite.getPosition();
		sprite.removeSelf();
		sprite = CCSprite.sprite("namei/skill4/nm_bs201.png");
	
		if(flagI==0){
			sprite.setFlipX(true);///////////////////
			sprite.setPosition(x-70,y+135);
			x=x-10;
		}else if(flagI==1){
			sprite.setPosition(x+70,y+135);
			x=x+10;
		}
		
		num = 31;
		format="namei/skill4/nm_bs2%02d.png";
		
		CGPoint point = map.convertToNodeSpace(getX(),getY());
		rightAttackRect = CGRect.make(point.x-250, point.y-200, 500, 300);
		leftAttackRect = CGRect.make(point.x-250, point.y-200, 500, 300);
		
		
		super.attack();
		return num;
	}

}
