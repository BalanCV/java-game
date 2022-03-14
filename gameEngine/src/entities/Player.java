package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import models.BaseModel;
import models.TexturedModel;
import renderer.DisplayWindow;
import renderer.Loader;
import renderer.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;

public class Player extends EntityStructure {
	Loader loader = new Loader();
	BaseModel walkmodel1 = OBJLoader.loadObjModel("sportywalkx", loader);
	TexturedModel walkmodelx = new TexturedModel(walkmodel1,new ModelTexture(loader.loadTexture("sportytexture")));
	BaseModel walkmodel2 = OBJLoader.loadObjModel("sportywalky", loader);
	TexturedModel walkmodely = new TexturedModel(walkmodel2,new ModelTexture(loader.loadTexture("sportytexture")));
	BaseModel endmodel = OBJLoader.loadObjModel("sportywave", loader);
	TexturedModel endmodel1 = new TexturedModel(endmodel,new ModelTexture(loader.loadTexture("sportytexture")));
	BaseModel tpose = OBJLoader.loadObjModel("sportyTpose", loader);
	TexturedModel jumpmodel = new TexturedModel(tpose,new ModelTexture(loader.loadTexture("sportytexture")));
	
	private static final float RUN_SPEED = 20;
	private static final float TURN_SPEED = 160;
	public static final float GRAVITY = -55;
	private static final float JUMP_POWER=35;
	
	private static final float TERRAIN_HEIGHT = 0;
	public static float time=0;
	public float hasRedEgg=0;
	public float hasWhiteEgg=0;
	public float hasTurtleEgg=0;
	
	
	private float upwardsSpeed=0;
	
	private float currentSpeed = 0;
	public float currentTurnSpeed=0;
	
	private boolean isInAir=false;

	public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
		
	}
	
	public void move(Terrain terrain) {
		checkInputs();
		super.increaseRotation(0, currentTurnSpeed*DisplayWindow.getFrameTimeSeconds(), 0);
		float distance=currentSpeed* DisplayWindow.getFrameTimeSeconds();
		float dx=(float) (distance*Math.sin(Math.toRadians(super.getRotY())));
		float dz=(float) (distance*Math.cos(Math.toRadians(super.getRotY())));
		super.increasePosition(dx, 0, dz);
		upwardsSpeed+=GRAVITY*DisplayWindow.getFrameTimeSeconds();
		super.increasePosition(0, upwardsSpeed*DisplayWindow.getFrameTimeSeconds(), 0);
		float terrainHeight = terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);
		if(super.getPosition().y<terrainHeight) {
			upwardsSpeed=0;
			isInAir=false;
			super.getPosition().y=terrainHeight;
		}
		
	}
	
	private void jump() {
		if(!isInAir) {
		this.upwardsSpeed=JUMP_POWER;
		isInAir=true;}
	}
	
	private void checkInputs() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			time += DisplayWindow.getFrameTimeSeconds()*1000;
			time %= 2000;
			if(time >= 0 && time < 1000){
				super.setModel(walkmodelx);
			} else {super.setModel(walkmodely);}
			if(super.getPosition().y>=40) {super.getPosition().x=713.35944f;super.getPosition().z=-96.35f;super.getPosition().y=17.06f;}
			if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				this.currentSpeed=RUN_SPEED+5;
			}
			else{this.currentSpeed=RUN_SPEED;}
		}else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			
			this.currentSpeed=-RUN_SPEED+10;
			if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				this.currentSpeed=-RUN_SPEED+5;
			}
		}else {
			this.currentSpeed=0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.currentTurnSpeed=-TURN_SPEED*2;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.currentTurnSpeed=TURN_SPEED*2;
		}else {
			this.currentTurnSpeed=0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			jump();
			super.setModel(jumpmodel);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_Y)) {
			super.setModel(endmodel1);
		}
		
		
		
	}

}
