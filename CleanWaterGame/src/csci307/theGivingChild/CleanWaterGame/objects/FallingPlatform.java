package csci307.theGivingChild.CleanWaterGame.objects;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import csci307.theGivingChild.CleanWaterGame.scene.GameScene;

public class FallingPlatform extends Sprite {
	
	public Body body;
	private Engine mEngine;
	
	public FallingPlatform(float pX, float pY, VertexBufferObjectManager vbom, Camera camera, PhysicsWorld physicsWorld, ITextureRegion region, Engine engine) {
		super(pX, pY, region, vbom);
		createPhysics(camera, physicsWorld);
		mEngine = engine;
	}
	
	private void createPhysics(final Camera camera, PhysicsWorld physicsWorld) {
		body = PhysicsFactory.createBoxBody(physicsWorld, this, BodyType.StaticBody, GameScene.FALLING_FIX);
		body.setUserData("fallingPlatform");
		body.setFixedRotation(true);
		
		physicsWorld.registerPhysicsConnector(new PhysicsConnector(this, body, true, false));
	}
	
	public void platformFall() {
		mEngine.registerUpdateHandler(new TimerHandler(.35f, new ITimerCallback() {

			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				pTimerHandler.reset();
				mEngine.unregisterUpdateHandler(pTimerHandler);
				body.setType(BodyType.DynamicBody);
				
			}
		}));
	}
}
