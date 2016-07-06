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

public class LuFeiPeople extends BasePeople {
	
	public LuFeiPeople(CCTMXTiledMap map){
		
		this.map=map;
		sprite = CCSprite.sprite("lufei/stand/lf_zl01.png");
		head = CCSprite.sprite("bloodnumber/lufei.png");
		head.setPosition(75,560);
		map.getParent().addChild(head,2);
		
	}
	 public CGRect getBoundingBox(){

		//CGPoint point = map.convertToNodeSpace(x,y);
		 //下面改了,要看到哦!!!!!!!!!!!!!!傻逼
		CGPoint point = map.convertToNodeSpace(this.getSprites().getPosition().x,this.getSprites().getPosition().y);
		//CGPoint point = map.convertToNodeSpace(0, 0);
		//peopleBoundingBox = CGRect.make(point.x, point.y, 960, 480);
		peopleBoundingBox = CGRect.make(point.x-60, point.y-90 ,120, 180);//point.x-60, point.y-90 ,120, 180
		//peopleBoundingBox.
				
			return peopleBoundingBox;

	 }
	 
	public void getBeatMusic(){
		Util.getMusic(R.raw.manbeat, false);
	} 
	
	
	public void stand()
	{
		defaultFormat = "lufei/stand/lf_zl01.png";
		format = "lufei/stand/lf_zl%02d.png";
		
		num = 4;
		super.stand();
	}
	
	
	public int move()
	{
		flag=false;
		defaultFormat = "lufei/walk/lf_p01.png";
		format = "lufei/walk/lf_p%02d.png";
		num = 8;
		super.move();
		return num;
		
	}
	
	public void down(){
		
		flag=false;
		sprite.removeSelf();
		defaultFormat = "lufei/down/lf_jd01.png";
		format = "lufei/down/lf_jd%02d.png";
		num = 10;
		super.down();
	}
	
	public void beat(){
		
		flag=false;
		isBeat=true;
		defaultFormat = "lufei/beat/lf_ad01.png";
		format = "lufei/beat/lf_ad%02d.png";
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
		sprite = CCSprite.sprite("lufei/skill/lf_gj01.png");
		if(flagI==0){
			sprite.setFlipX(true);///////////////////
			sprite.setPosition(x-50,y);
			//x=x-50;
			//sprite.setPosition(x, y);
		}else if(flagI==1){
			sprite.setPosition(x+50,y);
			//x=x+50;
			//sprite.setPosition(x, y);
			
		}
		num = 6;
		format="lufei/skill/lf_gj%02d.png";
		
		CGPoint point = map.convertToNodeSpace(getX(),getY());
		rightAttackRect = CGRect.make(point.x-30, point.y, 100, 30);
		leftAttackRect = CGRect.make(point.x-80, point.y, 100, 30);
		
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
		sprite = CCSprite.sprite("lufei/skill1/lf_bws01.png");
		if(flagI==0){
			sprite.setFlipX(true);///////////////////
			sprite.setPosition(x-15,y+60);
			//x=x-15;y=y+60;
			//sprite.setPosition(x, y);
		}else if(flagI==1){
			sprite.setPosition(x+15,y+60);
			//x=x+15;y=y+60;
			//sprite.setPosition(x, y);
			
		}
		num = 18;
		format="lufei/skill1/lf_bws%02d.png";
		
		CGPoint point = map.convertToNodeSpace(getX(),getY());
		rightAttackRect = CGRect.make(point.x-230, point.y-150, 460, 300);
		leftAttackRect = CGRect.make(point.x-230, point.y-150, 460, 300);
		
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
		sprite = CCSprite.sprite("lufei/skill2/lf_jtpstr01.png");
		
		if(flagI==0){
			sprite.setFlipX(true);///////////////////
			sprite.setPosition(x-100,y+15);
			//x=x-100;y=y+15;
			//sprite.setPosition(x, y);
		}else if(flagI==1){
			sprite.setPosition(x+100,y+15);
			//x=x+100;y=y+15;
			//sprite.setPosition(x, y);
			
		}
		num = 19;
		format="lufei/skill2/lf_jtpstr%02d.png";
		
		CGPoint point = map.convertToNodeSpace(this.getSprites().getPosition().x,this.getSprites().getPosition().y);//因为上面设置改变了sprite的点,更新点是0.1s执行一次,所以会来不及更新
		rightAttackRect = CGRect.make(point.x-40, point.y-70, 320, 150);
		leftAttackRect = CGRect.make(point.x+280, point.y-70, 320, 150);
		
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
		sprite = CCSprite.sprite("lufei/skill3/lf_bfy01.png");
		
		if(flagI==0){
			sprite.setFlipX(true);///////////////////
			sprite.setPosition(x-120,y-15);
				x=x-90;
			//x=x-120;y=y-15;
			//sprite.setPosition(x, y);
		}
		else if(flagI==1){
			sprite.setPosition(x+120,y-15);
			x=x+90;
			//x=x+120;y=y-15;
			//sprite.setPosition(x, y);
		}
		num = 31;
		format="lufei/skill3/lf_bfy%02d.png";
		
		CGPoint point = map.convertToNodeSpace(getX(),getY());//因为上面设置改变了sprite的点,更新点是0.1s执行一次,所以会来不及更新
		rightAttackRect = CGRect.make(point.x-50, point.y-100, 280, 200);
		leftAttackRect = CGRect.make(point.x-230, point.y-100, 280, 200);
		
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
		sprite = CCSprite.sprite("lufei/skill4/lf_hqq01.png");
	
		if(flagI==0){
			sprite.setFlipX(true);///////////////////
			sprite.setPosition(x+10,y-10);
			//x=x+10;y=y-10;
			//sprite.setPosition(x, y);
		}else if(flagI==1){
			sprite.setPosition(x-10,y-10);
			//x=x-10;y=y-10;
			//sprite.setPosition(x, y);
		}
		num = 29;
		format="lufei/skill4/lf_hqq%02d.png";
		
		CGPoint point = map.convertToNodeSpace(getX(),getY());//因为上面设置改变了sprite的点,更新点是0.1s执行一次,所以会来不及更新
		rightAttackRect = CGRect.make(point.x+50, point.y-130, 150, 250);
		leftAttackRect = CGRect.make(point.x+100, point.y-130, 150, 250);
		
		super.attack();
		return num;
		
	}
}
