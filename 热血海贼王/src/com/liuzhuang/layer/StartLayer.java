package com.liuzhuang.layer;

import android.graphics.Rect;
import android.view.MotionEvent;

import com.liuzhuang.rxhzw.R;
import com.liuzhuang.util.Util;
import java.io.PrintStream;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

public class StartLayer extends CCLayer{
	
  CCSprite sprite2;
  
  public StartLayer(){
	  
    setIsTouchEnabled(true);
    CCSprite sprite = CCSprite.sprite("menu/welcome/welcome.png");
    this.sprite2 = CCSprite.sprite("menu/welcome/kaishi.jpg");
    CGSize size = CCDirector.sharedDirector().getWinSize();
    sprite.setPosition(size.width/2, size.height/2);
    this.sprite2.setPosition(size.width /2-10, 150);
    addChild(sprite);
    addChild(this.sprite2, 1);
  }
  
  public boolean ccTouchesBegan(MotionEvent event){
	  
    CGPoint point = convertTouchToNodeSpace(event);
  
    CGRect box = sprite2.getBoundingBox();
    if (CGRect.containsPoint(box, point)){
    	
    	Util.getMusic(R.raw.clickmusic, false);
      Util.changeLayer(new ChoosePeopleLayer());
      
    }
    return super.ccTouchesBegan(event);
  }
}
