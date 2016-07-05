package com.liuzhuang.layer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import org.cocos2d.actions.CCProgressTimer;
import org.cocos2d.actions.CCScheduler;
import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCFollow;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCTMXObjectGroup;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.particlesystem.CCParticleSystem;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.util.CGPointUtil;

import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.liuzhuang.base.Base;
import com.liuzhuang.base.BaseEnemy;
import com.liuzhuang.base.BasePeople;
import com.liuzhuang.enemy.Enemy1;
import com.liuzhuang.enemy.Enemy2;
import com.liuzhuang.enemy.Enemy3;
import com.liuzhuang.enemy.Enemy4;
import com.liuzhuang.enemy.Enemy5;
import com.liuzhuang.enemy.Enemy6;
import com.liuzhuang.enemy.EnemyBoss1;
import com.liuzhuang.enemy.EnemyBoss2;
import com.liuzhuang.enemy.EnemyBoss3;
import com.liuzhuang.people.LuFeiPeople;
import com.liuzhuang.people.NaMeiPeople;
import com.liuzhuang.people.TextPeople;
import com.liuzhuang.people.WuSuoPuPeople;
import com.liuzhuang.rxhzw.R;
import com.liuzhuang.rxhzw.R.raw;
import com.liuzhuang.util.Util;

import android.content.SharedPreferences;
import android.graphics.Rect;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.widget.BaseExpandableListAdapter;

public class MainLayer extends CCLayer {

	private CCSprite top;
	private CCSprite bottom;
	private CCSprite left;
	private CCSprite right;

	private CCSprite pause;
	private CCSprite leftkuang;
	private CCSprite rightkuang;

	private CCSprite skill;
	private CCSprite skill1;
	private CCSprite skill2;
	private CCSprite skill3;
	private CCSprite skill4;

	private CCSprite top1;
	private CCSprite bottom1;
	private CCSprite left1;
	private CCSprite right1;

	private int index = 0;
	private Random random;
	private CGPoint mapPoint;
	private BaseEnemy enemy;
	private int a = 0;
	private int b = 0;
	private int c = 0;
	private int d = 0;
	private int number = 0;
	
	private  int closeNum = 0;

	private CCTMXTiledMap map;

	// ArrayList<CCSprite> ccsprite = new ArrayList<CCSprite>();

	CopyOnWriteArrayList<BaseEnemy> enemysList = new CopyOnWriteArrayList<BaseEnemy>();

	ArrayList<CGPoint> pointsList = new ArrayList<CGPoint>();

	private boolean isLock = true;

	private int flagI = 1;

	private int num = 0;

	private BasePeople people;

	private int flagJ = 0;

	private CCParticleSystem system;

	//private SoundEngine engine;

	/*
	 * public SoundEngine getSoundEngine(){
	 * 
	 * return engine; }
	 */

	public MainLayer(int closeNum, int i) {


		this.closeNum = closeNum;
		//engine.playSound(CCDirector.theApp, R.raw.music, true);
		Util.startMusic(R.raw.music, true);
		//engine = new SoundEngine();

		//engine.playSound(CCDirector.theApp,R.raw.commomattack, false);
		
		loadMap(closeNum);
		switch (i) {
		case 1:
			people = new LuFeiPeople(map/* ,x,y */);
			break;
		case 2:
			people = new LuFeiPeople(map/* ,x,y */);
			break;
		case 3:
			people = new WuSuoPuPeople(map);
			break;
		case 4:
			people = new NaMeiPeople(map);
			break;

		default:
			break;
		}

		CCScheduler.sharedScheduler().schedule("getX", this, 0.1f, false);

		CCScheduler.sharedScheduler().schedule("unLock", this, 0.1f, false);

		CCScheduler.sharedScheduler().schedule("creatEnemy", this, 0.1f, false);

		CCScheduler.sharedScheduler().schedule("follow", this, 0.1f, false);

		CCScheduler.sharedScheduler().schedule("upDateSchdule", this, 0.1f,
				false);

		init();
		progress();
		
		/////////////////////////////
		
	/*	EnemyBoss1 enemy = new EnemyBoss1(map);
		enemysList.add(enemy);
		mapPoint = pointsList.get(++index);
		enemy.getSprites().setPosition(mapPoint);

		enemy.setX(mapPoint.x);
		enemy.setY(mapPoint.y);

		enemy.move();*/
		
		/////////////////////
	}

	/*
	 * public void getX(float t){ if(isMove==true){ if(people.getX()<300){
	 * moveMap(); num = people.move(); } if(people.getX()>650){ moveMap(); num =
	 * people.move(); } } if(people.getX()>winSize.width-200){ moveMap(); num =
	 * people.move(); }
	 * //System.out.println("------------------------"+people.getX
	 * ()+"-----------------------");
	 * 
	 * //System.out.println("------------------------"+x+"-----------------------"
	 * ); }
	 */

	// private ArrayList<CGPoint> pointList = new ArrayList<CGPoint>();
	private BaseEnemy enemyList;
	private BaseEnemy enemyBoss;

	// 可能是因为动作太快,没来的及做动作,就被移除了,被虚拟机垃圾回收器自动回收了,因为是靠enemysList集合查找的
	// 可能是因为动作太快,没来的及做动作,就被移除了,因为是靠enemysList集合查找的
	// 可能是因为动作太快,没来的及做动作,就被移除了,因为是靠enemysList集合查找的
	// 可能是因为动作太快,没来的及做动作,就被移除了,因为是靠enemysList集合查找的
	// 可能是因为动作太快,没来的及做动作,就被移除了,因为是靠enemysList集合查找的
	public void follow(float t) {

		// 就他妈是你小子了
		// 主角的坐标点

		for (int i = 0; i < enemysList.size(); i++) {// ////////pointsList.size()
			/*
			 * System.out.println("456dsf46asd4fasd5d6f4sa65df41d5f6asd4f56asd4fsf"
			 * + "4sf56sdd4f56sd4f65+++++++++++++++++++" +
			 * enemyNum+"++++++++++++++++dfgasfhsadkfhasdjfkadsfads" +
			 * "fsfadsnfjkasfadskjfshfkladshfadsjkfhadasd" +
			 * "sdfbsdkjghsgjklfhsklfsnkjgl");
			 */
			CGPoint peoplePoint = map.convertToNodeSpace(people.getX(),
					people.getY());
			// CGPoint enemyPoint =
			// enemysList.get(i).getSprites().getPosition();
			CGPoint enemyPoint = enemysList.get(i).getSprites().getPosition();
			// System.out.println("主角的坐标点:" + peoplePoint);
			// System.out.println("敌人" + i + "坐标点:" + enemyPoint);

			CGPoint ToPeoplePoint = map.convertToNodeSpace(people.getX()
					+ enemysList.get(i).getAttackPointX(peoplePoint),
					people.getY() + enemysList.get(i).getAttackPointY());

			// CGPoint peoplePoint =
			// map.convertToNodeSpace(people.randomPoint(enemysList.get(i).getAttackPointX(),
			// enemysList.get(i).getAttackPointY(), enemyPoint));

			// System.out.print("---peoplePoint"+peoplePoint+"----enemyPoint"+enemyPoint+"-------isPeoplePoint"+isPeoplePoint+"---------------------------------");

			float toDistance = CGPointUtil.distance(ToPeoplePoint, enemyPoint);
			float distance = CGPointUtil.distance(peoplePoint, enemyPoint);

			// //////////////////////////////////
			// enemysList.get(i).makeRect(people.getX(), people.getY());

			// CGRect leftRect = enemysList.get(i).getLeftRect();
			// CGRect rightRect = enemysList.get(i).getLeftRect();
			// System.out.print("---leftRect"+leftRect+"----rightRect"+rightRect+"----------------------------------------");

			// if(distance<winSize.height/4){
			if (distance < enemysList.get(i).getAttackDistance()) {
				// if(distance<enemysList.get(i).getAttackDistance()){

				// if(CGRect.containsPoint(leftRect, enemyPoint)){
				// if(isDistance>enemysList.get(i).getAttackDistance()){

				if (enemysList.get(i).getFlag() == true) {

					enemysList.get(i).attack();
					// 尚未解决的问题,就是敌人一同攻击时候,只能有一次受伤,
					// 解决办法,增加一个方法.

					/**
					 * 
					 * 最新注释的一个语句
					 */
					// if (people.getFlag() == true) {

					// System.out.print("gjkgjjjjjjjjjjjjjjjjjjjjjjjjftydrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrhj");

					if (num == 8) {
						// map.stopAction(sequence);///////////此处注视了
					}
					if (enemysList.get(i).getFlagI() == 0) {
						// if(people.getX()<enemysList.get(i).getX()){
						// CGSize size = people.getBoundingBox().size;

						// System.out.print("zuobian=============================================================================================左边"+size.width+"sdfsfs"+size.height);
						// size =
						// enemysList.get(i).getAttackLeftRect().size;
						// System.out.print("zuobian=============================================================================================左边"+size.width+"sdfsfs"+size.height);

						if (CGRect.intersects(people.getBoundingBox(),
								enemysList.get(i).getAttackLeftRect())) {
							// if(enemysList.get(i).getAttackDistance()<distance+200){
							people.getBoundingBox();
							// CGRect.intersects(a, b)
							// people.getBoundingBox().

							if (people.getFlag() == true) {
								people.getBeatMusic();
								people.beat();
								
							}
							people.bruise(enemysList.get(i).getAttackPower());
							//把这个减血的东西放里面了
							System.out.print("敌人在左边攻击到我");

						}
					} else if (enemysList.get(i).getFlagI() == 1) {
						// if(people.getX()>enemysList.get(i).getX()){

						// CGSize size = people.getBoundingBox().size;

						// System.out.print("youbian=============================================================================================右边"+size.width+"sdfsfs"+size.height);
						// size =
						// enemysList.get(i).getAttackRightRect().size;
						// System.out.print("youbian=============================================================================================右边"+size.width+"sdfsfs"+size.height);

						if (CGRect.intersects(people.getBoundingBox(),
								enemysList.get(i).getAttackRightRect())) {
							// if(enemysList.get(i).getAttackDistance()<distance+200){
							people.getBoundingBox();

							if (people.getFlag() == true) {
								people.getBeatMusic();
								people.beat();
							    
							
							}
								people.bruise(enemysList.get(i).getAttackPower());
							//把这个减血的东西放里面了
							System.out.print("敌人在右边攻击到我");
						}
					}
					/**
					 * 
					 * 
					 * 新加的
					 */
					/*
					 * if (people.getBloodNumber() <= 0) { Util.changeLayer(new
					 * EndLayer()); }
					 */
					// }
				}

				// }else
				// if(distance>winSize.height/4&&distance<winSize.height/2){

			} else if (distance > enemysList.get(i).getAttackDistance()
					&& toDistance < winSize.height / 2) {
				// pointList.add(enemyPoint);
				if (enemysList.get(i).getFlag() == true) {

					// 少了一步吧peoplePoint换成point
					// 少了一步,更新点......点
					// CGPoint point =
					// map.convertToNodeSpace(people.randomPoint(enemysList.get(i).getAttackPointX(),
					// enemysList.get(i).getAttackPointY(), enemyPoint));

					// enemysList.get(i).moveToPeople(point, distance,
					// enemyPoint);
					enemysList.get(i).moveToPeople(peoplePoint, ToPeoplePoint,
							toDistance, enemyPoint);

				}
			}
			if (toDistance < winSize.height / 2) {

				// enemyList.setIsVisable(false);
				enemyList = enemysList.get(i);
				
				
				//enemysList.get(i).setIsVisable(true);
				if (people.getisAttack() == true) {

					// enemysList.get(i).setFlag(true);
					//

					if (people.getFlagI() == 0) {
						if (CGRect.intersects(enemysList.get(i).getBoundingBox(),
								people.getAttackLeftRect())) {
								if (enemysList.get(i).getIsBeat() == false) {
									enemysList.get(i).setIsVisable(true);
									enemysList.get(i).beat(people.getAttackPower());
								}

							}
					

						/*if (CGRect.intersects(people.getBoundingBox(),
								enemysList.get(i).getAttackLeftRect())) {
							if (enemysList.get(i).getIsBeat() == false) {
								enemysList.get(i).setIsVisable(true);
								enemysList.get(i).beat(people.getAttackPower());
							}

						}*/
					} else if (people.getFlagI() == 1) {

						if (CGRect.intersects(enemysList.get(i).getBoundingBox(),
								people.getAttackRightRect())) {
							if (enemysList.get(i).getIsBeat() == false) {
								enemysList.get(i).setIsVisable(true);
								enemysList.get(i).beat(people.getAttackPower());
							}
						}
					}

					if (enemysList.get(i).getBloodNumber() > 0) {

						progressTimer2.setPercentage(enemysList.get(i)
								.getBloodNumber());

					}
					if (enemysList.get(i).getBloodNumber() <= 0) {// //////////////////
						
						enemysList.get(i).down();// /////这五行 是新加的
						enemysList.get(i).setIsVisable(false);
						enemysList.remove(enemysList.get(i));
						enemyNum--;

					}
				}

				// 可能是因为动作太快,没来的及做动作,就被移除了,因为是靠enemysList集合查找的,没准备虚拟机回收了
			} else if (toDistance > winSize.height / 2) {

				if (enemysList.get(i).getFlag() == true) {
					// 此处设置visbale=false;
					enemysList.get(i).move();

				}
			}

		}

		/*
		 * if (enemysList.get(40).getBloodNumber() <= 30) {
		 * 
		 * Util.changeLayer(new EndLayer());
		 * 
		 * }
		 */

	}

	// people的属性

	private int enemyNum = 42;

	public void upDateSchdule(float t) {

		if (people.getBloodNumber() > 0) {

			progressTimer.setPercentage(people.getBloodNumber() / 10);

		} else if (people.getBloodNumber() <= 0) {

			Util.changeLayer(new EndLayer());
			// people.setFlag(false);
			// people.down();
		}

		if (enemyList.getBloodNumber() > 0) {
			
			
			if(enemyList.isBoss()){
				progressTimer2.setPercentage(enemyList.getBloodNumber()/10);
				
			}else{
				progressTimer2.setPercentage(enemyList.getBloodNumber());
			}
				
				
		}	
		if (enemyNum == 0) {
			if(closeNum==1){
				Util.setSharedPreferences("close2Lock", true);
			}else if(closeNum==2){
				Util.setSharedPreferences("close3Lock", true);
			}
			
			Util.pauseMusic(1);
			Util.changeLayer(new WinLayer());
			
		}
		if(enemyList.isBoss()){
			if(closeNum==1){
				Util.setSharedPreferences("close2Lock", true);
			}else if(closeNum==2){
				Util.setSharedPreferences("close3Lock", true);
			}
			if(enemyList.getBloodNumber()<=0){
				Util.pauseMusic(1);
				Util.changeLayer(new WinLayer());
			}
			
			
		}

		/*
		 * if (enemysList.size() == 0) {
		 * 
		 * Util.changeLayer(new EndLayer());
		 * 
		 * }
		 */
		/*
		 * if (index == 40) { if (enemysList.get(40)==null) {
		 * 
		 * System.exit(0); Util.changeLayer(new EndLayer());
		 * 
		 * } }
		 */

		/*
		 * else if(enemyList.getbloodNumber()<=0){ //enemyList.setFlag(flag);
		 * enemyList.down(); enemysList.remove(enemyList); }
		 */
		// (enemyList.getbloodNumber());
		// CGSize size = CGSize.make(200, 50);
		// CGRect rect = CGRect.make(ccp(winSize.width/2, winSize.height/2),
		// size);

	}

	private CCProgressTimer progressTimer;

	private CCProgressTimer progressTimer2;

	public void progress() {

		progressTimer = CCProgressTimer
				.progressWithFile("bloodnumber/bloodnumber.png");
		progressTimer.setPosition(245, 560);
		this.addChild(progressTimer, 2);
		progressTimer.setPercentage(0);

		progressTimer
				.setType(CCProgressTimer.kCCProgressTimerTypeHorizontalBarLR);

		// bloodNumber.setPosition();

		progressTimer2 = CCProgressTimer
				.progressWithFile("bloodnumber/bloodnumber.png");
		progressTimer2.setPosition(715, 560);
		this.addChild(progressTimer2, 2);
		progressTimer2.setPercentage(0);

		progressTimer2
				.setType(CCProgressTimer.kCCProgressTimerTypeHorizontalBarRL);
	}

	public void creatEnemy(float t) {
		if (enemysList.size() < 5) {

			random = new Random();

			int i = random.nextInt(6);

			switch (i) {
			case 0:
				//enemy = new EnemyBoss2(map);
				enemy = new Enemy1(map);

				break;
			case 1:
				enemy = new Enemy3(map);

				break;
			case 2:
				enemy = new Enemy3(map);

				break;
			case 3:
				enemy = new Enemy4(map);

				break;
			case 4:
				enemy = new Enemy5(map);

				break;
			case 5:
				enemy = new Enemy6(map);

				break;

			default:
				break;
			}

			
			//2016.7.3修改
			if(index<pointsList.size()){
				enemysList.add(enemy);
				mapPoint = pointsList.get(++index);
				enemy.getSprites().setPosition(mapPoint);

				enemy.setX(mapPoint.x);
				enemy.setY(mapPoint.y);
	
				enemy.move();
			}
		

		}
		if (index == 39) {
			if(closeNum==1){
				enemy = new EnemyBoss1(map);
			}else if(closeNum==2){
				enemy = new EnemyBoss2(map);
			}else if(closeNum==3){
				enemy = new EnemyBoss3(map);
			}

			enemysList.add(enemy);
			mapPoint = pointsList.get(++index);

			enemy.getSprites().setPosition(mapPoint);

			enemy.setX(mapPoint.x);
			enemy.setY(mapPoint.y);

			enemy.move();
		}

		/*
		 * if(enemysList.size()==0){
		 * 
		 * Util.changeLayer(new EndLayer()); }
		 */

	}

	public void unLock(float t) {
		if (num != 0) {
			number++;
			if (number == num) {
				if (flagJ == 0) {
					Util.pauseMusic(2);
					setIsTouchEnabled(true);
					isLock = true;
				} else if (flagJ != 0) {
					isLock = true;
				}
				num = 0;
				number = 0;
			}
		}
		
		// skill1.setBackgroundMusicVolume(volume);

		if (a != 0) {
			skill1.setOpacity(a = a + 4);
			if (skill1.getOpacity() > 250) {
				a = 0;
			}
		}
		if (b != 0) {
			skill2.setOpacity(b = b + 3);
			if (skill2.getOpacity() > 250) {
				b = 0;
			}
		}
		if (c != 0) {
			skill3.setOpacity(c = c + 2);
			if (skill3.getOpacity() > 250) {
				c = 0;
			}
		}
		if (d != 0) {
			skill4.setOpacity(d = d + 1);
			if (skill4.getOpacity() > 250) {
				d = 0;
			}
		}

	}

	/*
	 * private List<CGPoint> getMapPoints(CCTMXTiledMap map,String name){
	 * List<CGPoint> points = new ArrayList<CGPoint>(); // 解析地图 CCTMXObjectGroup
	 * objectGroupNamed = map.objectGroupNamed(name); ArrayList<HashMap<String,
	 * String>> objects = objectGroupNamed.objects; for (HashMap<String, String>
	 * hashMap : objects) { int x = Integer.parseInt(hashMap.get("x")); int y =
	 * Integer.parseInt(hashMap.get("y")); CGPoint cgPoint = CCNode.ccp(x, y);
	 * points.add(cgPoint); } return points; }
	 */

	/*
	 * private void moveMap(){
	 * 
	 * float mapx = map.getPosition().x; float map2x = map.getPosition().x;
	 * float mapy = map.getPosition().y; float map2y = map.getPosition().y; int
	 * speed = 1; mapx = mapx-speed; mapx = mapx-speed; CGSize mapSize =
	 * map.getContentSize();
	 * 
	 * if(mapx<=-mapSize.width/2) mapx=mapSize.width+mapSize.width/2;
	 * if(map2x<=-mapSize.width/2) map2x=mapSize.width+mapSize.width/2;
	 * map.setPosition(mapx, mapy);
	 * System.out.print("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
	 * this.addChild(map);
	 * 
	 * }
	 */

	/*
	 * private void loadParticle() { system = CCParticleSnow.node(); 
	 * system.setTexture(CCTextureCache.sharedTextureCache().addImage(
	 * "801162c85f522f733d9ff4e459bdd2ce.jpg")); .addChild(system, 1); }
	 */

	private void loadMap(int closeNum) {
		
		if(closeNum==1){
			map = CCTMXTiledMap.tiledMap("map1.tmx");
		}else if(closeNum==2){
			map = CCTMXTiledMap.tiledMap("map2.tmx");
		}else if(closeNum==3){
			map = CCTMXTiledMap.tiledMap("map3.tmx");
		}
		
	
		this.addChild(map, -1, 1);

		// map.setBackgroundMusicVolume(volume);

		pointsList = Util.getMapPoints(map, "road");

	}

	private void init() {

		CGSize winSize = CCDirector.sharedDirector().getWinSize();
		/*
		 * x=winSize.width/2; y=winSize.height/2;
		 */
		// points=getMapPoints(map, "road");
		// CGPoint point = points.get(0);

		top = CCSprite.sprite("menu/direction/top_default.png");
		bottom = CCSprite.sprite("menu/direction/bottom_default.png");
		left = CCSprite.sprite("menu/direction/left_default.png");
		right = CCSprite.sprite("menu/direction/right_default.png");

		pause = CCSprite.sprite("menu/pause/zanting.png");
		leftkuang = CCSprite.sprite("bloodnumber/leftkuang.png");
		rightkuang = CCSprite.sprite("bloodnumber/rightkuang.png");

		skill = CCSprite.sprite("menu/attack/button.png");
		skill1 = CCSprite.sprite("menu/attack/button1.png");
		skill2 = CCSprite.sprite("menu/attack/button2.png");
		skill3 = CCSprite.sprite("menu/attack/button3.png");
		skill4 = CCSprite.sprite("menu/attack/button4.png");

		skill.setScale(0.75f);
		skill1.setScale(0.75f);
		skill2.setScale(0.75f);
		skill3.setScale(0.75f);
		skill4.setScale(0.75f);

		top1 = CCSprite.sprite("menu/direction/top_press.png");
		bottom1 = CCSprite.sprite("menu/direction/bottom_press.png");
		left1 = CCSprite.sprite("menu/direction/left_press.png");
		right1 = CCSprite.sprite("menu/direction/right_press.png");

		top1.setPosition(100, 150);
		bottom1.setPosition(100, 50);
		left1.setPosition(50, 100);
		right1.setPosition(150, 100);

		pause.setPosition(480, 560);
		leftkuang.setPosition(250, 560);
		rightkuang.setPosition(710, 560);

		top.setPosition(100, 150);
		bottom.setPosition(100, 50);
		left.setPosition(50, 100);
		right.setPosition(150, 100);

		skill.setPosition(winSize.width - 125, 125);
		skill1.setPosition(winSize.width - 50, 50);
		skill2.setPosition(winSize.width - 50, 200);
		skill3.setPosition(winSize.width - 200, 50);
		skill4.setPosition(winSize.width - 200, 200);

		this.addChild(top1, 1);
		this.addChild(bottom1, 1);
		this.addChild(left1, 1);
		this.addChild(right1, 1);

		this.addChild(pause, 1);
		this.addChild(leftkuang, 1);
		this.addChild(rightkuang, 1);

		this.addChild(skill, 1);
		this.addChild(skill1, 1);
		this.addChild(skill2, 1);
		this.addChild(skill3, 1);
		this.addChild(skill4, 1);

		this.addChild(top, 2);
		this.addChild(bottom, 2);
		this.addChild(left, 2);
		this.addChild(right, 2);

		CCSprite head;
		head = CCSprite.sprite("bloodnumber/ll.png");
		head.setPosition(885, 560);
		this.addChild(head,1);

		people.stand();
		System.out.println("1");
		setIsTouchEnabled(true);

	}

	/*
	 * public void delayTime(){ CCDelayTime delayTime=CCDelayTime.action(1);
	 * sprites.runAction(delayTime); }
	 */
	// zCCTouchHandler ccTouchHandler = CCTouchHandler.makeHandler(delegate,
	// priority)

	public boolean ccTouchesEnded(MotionEvent event) {
		CGPoint point = this.convertTouchToNodeSpace(event);
		CGRect boundingTopBox = top.getBoundingBox();
		CGRect boundingBottomBox = bottom.getBoundingBox();
		CGRect boundingLeftBox = left.getBoundingBox();
		CGRect boundingRightBox = right.getBoundingBox();
		if (CGRect.containsPoint(boundingTopBox, point)) {
			isLock = true;
			top.setVisible(true);
			// ///////////////新加的
			people.unUpDate();
			people.stand();

			// CCSequence sequence =
			// CCSequence.actions(CCCallFunc.action(this,"delayTime"),CCCallFunc.action(this,"stop"));
			// top.runAction(sequence);
		} else if (CGRect.containsPoint(boundingBottomBox, point)) {
			isLock = true;
			bottom.setVisible(true);
			// ///////////////新加的
			people.unUpDate();
			people.stand();

			// CCSequence sequence =
			// CCSequence.actions(CCCallFunc.action(this,"delayTime"),CCCallFunc.action(this,"stop"));
			// bottom.runAction(sequence);
		} else if (CGRect.containsPoint(boundingLeftBox, point)) {
			// isMove=false;
			isLock = true;
			left.setVisible(true);
			people.unUpDate();
			people.stand();
			// map.stopAction(sequence);
			map.stopAllActions();

			// CCSequence sequence =
			// CCSequence.actions(CCCallFunc.action(this,"delayTime"),CCCallFunc.action(this,"stop"));
			// left.runAction(sequence);
		} else if (CGRect.containsPoint(boundingRightBox, point)) {
			// isMove=false;
			isLock = true;
			right.setVisible(true);
			people.unUpDate();
			people.stand();
			// map.stopAction(sequence);
			map.stopAllActions();

			// CCSequence sequence =
			// CCSequence.actions(CCCallFunc.action(this,"delayTime"),CCCallFunc.action(this,"stop"));
			// right.runAction(sequence);
		}

		return super.ccTouchesEnded(event);
	}

	public boolean ccTouchesBegan(MotionEvent event) {
		// setIsTouchEnabled(false);
		CGPoint point = this.convertTouchToNodeSpace(event);
		// CGRect boundingBox = CGRect.make(point.x, point.y, 1, 1);
		CGRect boundingTopBox = top.getBoundingBox();
		CGRect boundingBottomBox = bottom.getBoundingBox();
		CGRect boundingLeftBox = left.getBoundingBox();
		CGRect boundingRightBox = right.getBoundingBox();

		CGRect boundingSkillBox = skill.getBoundingBox();
		CGRect boundingSkill1Box = skill1.getBoundingBox();
		CGRect boundingSkill2Box = skill2.getBoundingBox();
		CGRect boundingSkill3Box = skill3.getBoundingBox();
		CGRect boundingSkill4Box = skill4.getBoundingBox();

		CGRect boundingPauseBox = pause.getBoundingBox();

		if (CGRect.containsPoint(boundingPauseBox, point)) {

			this.onExit();

			Util.pauseMusic(1);
			Util.getMusic(R.raw.clickmusic, false);
			this.getParent().addChild(new PauseLayer());
			// Util.changeLayer(new PauseLayer());
		}

		if (isLock) {
			if (CGRect.containsPoint(boundingSkillBox, point)) {
				flagJ = 0;
				isLock = false;
				num = people.commomAttack();
				Util.getMusic(R.raw.commomattack, false);
				// setIsTouchEnabled(false);

			} else if (CGRect.containsPoint(boundingSkill1Box, point)) {
				if (skill1.getOpacity() > 250) {
					flagJ = 0;
					isLock = false;
					a = 1;
					num = people.attack1();// ///////////
					Util.getMusic(R.raw.commomattack, true);
					setIsTouchEnabled(false);
				}
			} else if (CGRect.containsPoint(boundingSkill2Box, point)) {
				if (skill2.getOpacity() > 250) {
					flagJ = 0;
					isLock = false;
					b = 1;
					num = people.attack2();
					Util.getMusic(R.raw.commomattack, true);
					setIsTouchEnabled(false);
				}
			} else if (CGRect.containsPoint(boundingSkill3Box, point)) {
				if (skill3.getOpacity() > 250) {
					flagJ = 0;
					isLock = false;
					c = 1;
					num = people.attack3();
					Util.getMusic(R.raw.commomattack, true);
					setIsTouchEnabled(false);
				}
			} else if (CGRect.containsPoint(boundingSkill4Box, point)) {
				if (skill4.getOpacity() > 250) {
					flagJ = 0;
					isLock = false;
					d = 1;
					num = people.attack4();
					Util.getMusic(R.raw.commomattack, true);
					setIsTouchEnabled(false);
				}
			}
			if (CGRect.containsPoint(boundingTopBox, point)) {
				// CGRect.containsRect(aRect, bRect) 得来毫不费工夫,真他妈有.
				// CGRect.equalToRect(r1, r2)
				isLock = false;
				top.setVisible(false);
				flagJ = 1;
				people.init(flagI, flagJ);
				/* num = */people.move();
				// setIsTouchEnabled(false);
				// moveMap();///////////////
			} else if (CGRect.containsPoint(boundingBottomBox, point)) {
				bottom.setVisible(false);
				flagJ = 2;
				people.init(flagI, flagJ);
				/* num = */people.move();
				// setIsTouchEnabled(false);

			} else if (CGRect.containsPoint(boundingLeftBox, point)) {
				// isMove=true;
				isLock = false;
				left.setVisible(false);
				flagI = 0;
				flagJ = 3;
				people.init(flagI, flagJ);
				if (people.getX() < 300) {
					// moveMap();
					/* num = */people.move();
				} else {
					System.out.println("______这是CCTouchGegin________"
							+ people.getX() + "__________________");

					/* num = */people.move();
				}

				// num = people.move();
				// if(x>480&&x<map.getScaleX()-480){
				// if(people.getIsBeat()==false){
				// moveMap();
				// }
				// }
				// setIsTouchEnabled(false);

			} else if (CGRect.containsPoint(boundingRightBox, point)) {

				// isMove=true;
				isLock = false;
				right.setVisible(false);
				flagI = 1;
				flagJ = 4;
				people.init(flagI, flagJ);

				if (people.getX() > 660) {
					// moveMap();
					/* num = */people.move();
				} else {

					/* num = */people.move();
				}

				// num = people.move();
				// if(x>200&&x<map.getScaleX()-200){
				// if(people.getIsBeat()==false){
				// moveMap();

				// }
				// ////////////////////此处和上面坐了改动
				// }
				// setIsTouchEnabled(false);

			}
		}

		return super.ccTouchesBegan(event);

	}

	// private boolean isMove;

	CGSize winSize = CCDirector.sharedDirector().getWinSize();

	// private float x=0;
	// private float y=0;
	/*
	 * public void moveMap() { switch (flagJ) { case 1: y=y-10; break; case 2:
	 * y=y+10; break; case 3: x=x+10;
	 * 
	 * break; case 4: x=x-10;
	 * 
	 * break; default: break; }
	 * 
	 * // CGPoint point = CGPoint.ccp(x, y);
	 * 
	 * CCMoveTo moveTo = CCMoveTo.action(0.08f,ccp(x,y));
	 * 
	 * //moveTo.setTag(2);
	 * 
	 * sequence = CCSequence.actions(moveTo,CCCallFunc.action(this, "moveMap"));
	 * 
	 * map.runAction(sequence);
	 * 
	 * } CCSequence sequence;
	 */
	public void isEnable() {
		setIsTouchEnabled(true);
	}

	public class PauseLayer extends CCLayer {

		private CCSprite sprite;
		private CCSprite back;
		private CCSprite goOn;

		public PauseLayer() {

			setIsTouchEnabled(true);
			sprite = CCSprite.sprite("menu/pause/kuangkuang.jpg");
			back = CCSprite.sprite("menu/pause/back.jpg");
			goOn = CCSprite.sprite("menu/pause/goon.jpg");
			sprite.setPosition(480, 320);
			back.setPosition(480 - 100, 320);
			goOn.setPosition(480 + 100, 320);
			this.addChild(sprite);
			this.addChild(back);
			this.addChild(goOn);
		}

		@Override
		public boolean ccTouchesBegan(MotionEvent event) {

			CGPoint point = convertTouchToNodeSpace(event);

			CGRect boundingBackBox = back.getBoundingBox();
			CGRect boundingGoOnBox = goOn.getBoundingBox();

			if (CGRect.containsPoint(boundingBackBox, point)) {
				
				Util.getMusic(R.raw.clickmusic, false);
				Util.changeLayer(new StartLayer());

			}
			if (CGRect.containsPoint(boundingGoOnBox, point)) {

				MainLayer.this.onEnter();
				Util.getMusic(R.raw.clickmusic, false);
				Util.resumeMusic(1);
				this.removeSelf();
				// MainLayer.this.setIsTouchEnabled(true);

			}

			return super.ccTouchesBegan(event);
		}

	}

}
