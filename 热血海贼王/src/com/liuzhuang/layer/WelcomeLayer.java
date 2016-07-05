package com.liuzhuang.layer;


import android.view.MotionEvent;


import com.liuzhuang.rxhzw.R;
import com.liuzhuang.util.Util;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCFiniteTimeAction;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;


public class WelcomeLayer extends CCLayer{
	
  //SoundEngine engine;
  CCSprite sprite;
  
  public WelcomeLayer(){
	  
    setIsTouchEnabled(true);
  //engine = SoundEngine.sharedEngine();
  //engine.playSound(CCDirector.theApp, R.raw.kaichangmusic, false);
    Util.startMusic(R.raw.kaichangmusic, false);
    sprite = CCSprite.sprite("kaichang/kaichang1.png");
    sprite.setScale(2.0f);
    CGSize winSize = CCDirector.sharedDirector().getWinSize();
    sprite.setPosition(winSize.width/2, winSize.height/2);
    this.addChild(this.sprite);
    CCAction action = Util.getAnimate("kaichang/kaichang%d.png", 8, false, 2.5f);
   
    CCSequence sequence = CCSequence.actions((CCFiniteTimeAction)action,CCCallFunc.action(this, "start"));
    this.sprite.runAction(sequence);
  }
  
  public boolean ccTouchesBegan(MotionEvent event){
	  
	//engine.pauseSound();
	Util.pauseMusic(1);
    CGPoint point = convertTouchToNodeSpace(event);
    CGRect box = sprite.getBoundingBox();
    if (CGRect.containsPoint(box,point)){
    	
      //engine.pauseSound();
    	Util.getMusic(R.raw.clickmusic, false);
    	Util.pauseMusic(1);
    	Util.changeLayer(new StartLayer());
    }
    return super.ccTouchesBegan(event);
  }
  
  public void start(){
	  
    Util.changeLayer(new StartLayer());

  }
}
