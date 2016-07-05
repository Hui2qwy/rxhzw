package com.liuzhuang.rxhzw;

import org.cocos2d.layers.CCScene;


import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.opengl.CCGLSurfaceView;

import com.liuzhuang.layer.ChooseCloseLayer;
import com.liuzhuang.layer.ChoosePeopleLayer;
import com.liuzhuang.layer.EndLayer;
import com.liuzhuang.layer.MainLayer;
import com.liuzhuang.layer.WelcomeLayer;
import com.liuzhuang.layer.WinLayer;
import com.liuzhuang.util.Util;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {
	private CCDirector ccDirector;
	
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		CCGLSurfaceView surfaceView = new CCGLSurfaceView(this);	
		setContentView(surfaceView);
		ccDirector = CCDirector.sharedDirector();
		ccDirector.attachInView(surfaceView);
		ccDirector.setDisplayFPS(true);
		ccDirector.setScreenSize(960, 640);
		ccDirector.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);

		CCScene scene=CCScene.node();
		
		scene.addChild(new WelcomeLayer());
		//scene.addChild(new ChooseCloseLayer(1));
		//scene.addChild(new MainLayer(1));
		//scene.addChild(new WinLayer());
		//scene.addChild(new EndLayer());
		////scene.addChild(new ChooseLayer());
		ccDirector.runWithScene(scene);
		
    }


    @Override
	protected void onResume() {
    	Util.resumeMusic(1);
		super.onResume();
		ccDirector.resume();
	}
	@Override
	protected void onPause() {
		Util.pauseMusic(1);
		super.onPause();
		ccDirector.pause();
	}
	@Override
	protected void onDestroy() {
		Util.purgeMusic(1);
		super.onDestroy();
		ccDirector.end();
	}
    
}
