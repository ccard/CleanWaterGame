/**
 * Authors: Chris Card, Tony Nguyen, Gurpreet Nanda, Dylan Chau, Dustin Liang, Maria Deslis
 * Date: 05/22/13
 * Version: 1.0
 * Description: Level selection for act 1. This scene will display the levels that the user can choose to play.
 * 
 * History:
 *  05/22/13 Original 1.0
 */


package csci307.theGivingChild.CleanWaterGame.scene;

import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

import csci307.theGivingChild.CleanWaterGame.CleanWaterGame;
import csci307.theGivingChild.CleanWaterGame.GameLauncher;
import csci307.theGivingChild.CleanWaterGame.manager.ResourceManager;
import csci307.theGivingChild.CleanWaterGame.manager.SceneManager;
import csci307.theGivingChild.CleanWaterGame.manager.SceneManager.SceneType;
import csci307.theGivingChild.CleanWaterGame.scene.AnimationScene.Animation;

public class LevelSelectScene extends BaseScene implements IOnMenuItemClickListener {

    public static final String LEVEL_PREFERENCE = "csci370.theGivingChild.CleanWaterGame.LEVEL_SELECT";
    private MenuScene menuChildScene;

	private final int SCENE_ONE = 0;
	private final int SCENE_TWO = 1;
	private final int SCENE_THREE = 2;
	private final int SCENE_FOUR = 3;
	private final int SCENE_FIVE = 4;


	@Override
	public boolean onMenuItemClicked(org.andengine.entity.scene.menu.MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
		switch (pMenuItem.getID()) {
			case SCENE_ONE:
				SceneManager.getInstance().createAnimationScene(Animation.SCENE_ONE);
				SceneManager.getInstance().loadAnimationScene(engine);
				CleanWaterGame.getInstance().pauseMenuMusic();
				CleanWaterGame.getInstance().getSharedPreferences(GameLauncher.PREFERENCE_KEY_INGAME, ResourceManager.getInstance().activity.MODE_MULTI_PROCESS).edit().putBoolean(GameLauncher.PREFERENCE_KEY_INGAME_MUTE, true).commit();
				return true;
			case SCENE_TWO:
				SceneManager.getInstance().createAnimationScene(Animation.SCENE_TWO);
				SceneManager.getInstance().loadAnimationScene(engine);
				CleanWaterGame.getInstance().getSharedPreferences(GameLauncher.PREFERENCE_KEY_INGAME, ResourceManager.getInstance().activity.MODE_MULTI_PROCESS).edit().putBoolean(GameLauncher.PREFERENCE_KEY_INGAME_MUTE, true).commit();
				CleanWaterGame.getInstance().pauseMenuMusic();
				return true;
			case SCENE_THREE:
				SceneManager.getInstance().createAnimationScene(Animation.SCENE_THREE);
				SceneManager.getInstance().loadAnimationScene(engine);
				CleanWaterGame.getInstance().getSharedPreferences(GameLauncher.PREFERENCE_KEY_INGAME, ResourceManager.getInstance().activity.MODE_MULTI_PROCESS).edit().putBoolean(GameLauncher.PREFERENCE_KEY_INGAME_MUTE, true).commit();
				CleanWaterGame.getInstance().pauseMenuMusic();
				return true;
			case SCENE_FOUR:
				SceneManager.getInstance().loadGameScene(engine, "act1scene4", "act1scene5");
				CleanWaterGame.getInstance().getSharedPreferences(GameLauncher.PREFERENCE_KEY_INGAME, ResourceManager.getInstance().activity.MODE_MULTI_PROCESS).edit().putBoolean(GameLauncher.PREFERENCE_KEY_INGAME_MUTE, true).commit();
				CleanWaterGame.getInstance().pauseMenuMusic();
				return true;
			case SCENE_FIVE:
				SceneManager.getInstance().loadGameScene(engine, "act1scene5", null);
				CleanWaterGame.getInstance().getSharedPreferences(GameLauncher.PREFERENCE_KEY_INGAME, ResourceManager.getInstance().activity.MODE_MULTI_PROCESS).edit().putBoolean(GameLauncher.PREFERENCE_KEY_INGAME_MUTE, true).commit();
				CleanWaterGame.getInstance().pauseMenuMusic();
				return true;
			default:
				return false;
		}
	}


	@Override
	public void createScene() {
		createBackground();
		createMenuChildScene();
	}

	@Override
	public void onBackKeyPressed() {
		SceneManager.getInstance().loadActSelectScene(engine);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_LEVEL_SELECT;
	}

	@Override
	public void disposeScene() {
		this.clearChildScene();
		this.detachChildren();
		this.reset();
		this.detachSelf();

	}

	private void createBackground() {
		AutoParallaxBackground autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 5);
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(0.0f, new Sprite(.5f*camera.getWidth() , .5f*camera.getHeight(), resourcesManager.menu_background_TR, vbom)));
		setBackground(autoParallaxBackground);
		attachChild(new Text(400, camera.getHeight() - 40, resourcesManager.font, "Act I", vbom));
	}

	//create buttons here
	private void createMenuChildScene() {

		menuChildScene = new MenuScene(camera);
		menuChildScene.setPosition(0, 0);

		IMenuItem levelOneMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(SCENE_ONE, resourcesManager.scene_one_TR, vbom), 1.2f, 1);
		IMenuItem levelTwoMenuItem;
		IMenuItem levelThreeMenuItem;
		IMenuItem levelFourMenuItem;
		IMenuItem levelFiveMenuItem;

        if (CleanWaterGame.getInstance().getSharedPreferences(LEVEL_PREFERENCE, ResourceManager.getInstance().activity.MODE_MULTI_PROCESS).getBoolean("act1scene1_done", false))
            levelTwoMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(SCENE_TWO, resourcesManager.scene_two_TR, vbom), 1.2f, 1);
        else
            levelTwoMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(-1, resourcesManager.locked_scene_TR, vbom), 1.2f, 1);

        if (CleanWaterGame.getInstance().getSharedPreferences(LEVEL_PREFERENCE, ResourceManager.getInstance().activity.MODE_MULTI_PROCESS).getBoolean("act1scene2_done", false))
            levelThreeMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(SCENE_THREE, resourcesManager.scene_three_TR, vbom), 1.2f, 1);
        else
            levelThreeMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(-1, resourcesManager.locked_scene_TR, vbom), 1.2f, 1);

        if (CleanWaterGame.getInstance().getSharedPreferences(LEVEL_PREFERENCE, ResourceManager.getInstance().activity.MODE_MULTI_PROCESS).getBoolean("act1scene3_done", false))
            levelFourMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(SCENE_FOUR, resourcesManager.scene_four_TR, vbom), 1.2f, 1);
        else
            levelFourMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(-1, resourcesManager.locked_scene_TR, vbom), 1.2f, 1);

        if (CleanWaterGame.getInstance().getSharedPreferences(LEVEL_PREFERENCE, ResourceManager.getInstance().activity.MODE_MULTI_PROCESS).getBoolean("act1scene4_done", false))
            levelFiveMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(SCENE_FIVE, resourcesManager.scene_five_TR, vbom), 1.2f, 1);
        else
            levelFiveMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(-1, resourcesManager.locked_scene_TR, vbom), 1.2f, 1);

		menuChildScene.buildAnimations();
		menuChildScene.setBackgroundEnabled(false);

        menuChildScene.addMenuItem(levelOneMenuItem);
		menuChildScene.addMenuItem(levelTwoMenuItem);
		menuChildScene.addMenuItem(levelThreeMenuItem);
		menuChildScene.addMenuItem(levelFourMenuItem);
		menuChildScene.addMenuItem(levelFiveMenuItem);
		
		levelOneMenuItem.setPosition(100, 100);
        levelTwoMenuItem.setPosition(250, 100);
        levelThreeMenuItem.setPosition(400, 100);
        levelFourMenuItem.setPosition(550,100);
        levelFiveMenuItem.setPosition(700,100);

		menuChildScene.setOnMenuItemClickListener(this);

		setChildScene(menuChildScene);
	}
}