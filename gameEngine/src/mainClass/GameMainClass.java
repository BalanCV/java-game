package mainClass;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.BaseModel;
import models.TexturedModel;
import objs.ModelData;
import objs.OBJFileLoader;
import particles.Particle;
import particles.ParticleMain;
import particles.AdvancedParticle;
import particles.ParticleTexture;
import renderer.DisplayWindow;
import renderer.Loader;
import renderer.RendererMain;
import renderer.OBJLoader;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL10;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import audio.Audio;
import audio.SourceAudio;
import skybox.SkyboxRenderer;
import terrains.Terrain;
import textRender.TextMain;
import textStructure.FontType;
import textStructure.RenderedText;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTextureMaps;
import water.WaterFrameBuffers;
import water.WaterRenderer;
import water.WaterShader;
import water.WaterUnits;
import entities.Camera;
import entities.EntityStructure;
import entities.Light;
import entities.Player;
import guis.GuiRenderer;
import guis.GuiTexture;

public class GameMainClass {

	public static void main(String[] args) {
		Audio.init();
		Audio.setListenerData(0, 0, 0);
		
		int stream = Audio.loadSound("audio/town.wav");
		SourceAudio pt = new SourceAudio();
		pt.setPosition(400, 20, -400);
		pt.setLooping(true);
		pt.play(stream);
		
		

		DisplayWindow.createDisplay();
		Loader loader = new Loader();
		TextMain.init(loader);
		BaseModel bunnyModel = OBJLoader.loadObjModel("sportyTpose", loader);
		TexturedModel sportsbunny = new TexturedModel(bunnyModel, new ModelTexture(loader.loadTexture("sportytexture")));


		Player player = new Player(sportsbunny, new Vector3f(744.41315f,4.51f,-79.255f),0,0,0,1.75f);
		Camera camera = new Camera(player);
		
		RendererMain renderer = new RendererMain(loader,camera);
		ParticleMain.init(loader, renderer.getProjectionMatrix());
		
		FontType font = new FontType(loader.loadTexture("tahoma"), new File("res/tahoma.fnt"));
		RenderedText text = new RenderedText("Daytime!", 1, font, new Vector2f(0.35f,0.12f), 1f, true);
		text.setColour(1, 1, 0.75f);
		RenderedText textnight = new RenderedText("Night time!", 1, font, new Vector2f(0.35f,0.12f), 1f, true);
		textnight.setColour(1, 1, 0.75f);
		
		RenderedText maintext = new RenderedText("Hey there, bunny! Thieves have stolen our easter eggs! The three paths out of town will lead you to their hideouts. Other elders like me along the path will give you hints about your challenge!", 1, font, new Vector2f(0,0), 1f, true);
		maintext.setColour(1, 1, 0.75f);
		RenderedText mazetext = new RenderedText("A fox stole our red egg, must be because it's obsessed with the same colour as it's fur!", 1, font, new Vector2f(0,0), 1f, true);
		mazetext.setColour(1, 1, 0.75f);
		RenderedText laketext = new RenderedText("Turtles are slow but many, so avoid group confrontation. The swamp here also makes you as slow as them!", 1, font, new Vector2f(0,0), 1f, true);
		laketext.setColour(1, 1, 0.75f);
		RenderedText firetext1 = new RenderedText("I wouldn't fight these wolves during the night since the moon empowers them. Actually, I would not fight them at all!", 1, font, new Vector2f(0,0), 1f, true);
		firetext1.setColour(1, 1, 0.75f);
		RenderedText firetext2 = new RenderedText("That volcano there will burn everything in sight. Maybe the wolf boss will fall in by accident.", 1, font, new Vector2f(0,0), 1f, true);
		firetext2.setColour(1, 1, 0.75f);
		
		
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("hdgrass2"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("lava"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("hdgrass"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("brickpath"));
		TerrainTextureMaps texturePack = new TerrainTextureMaps(backgroundTexture, rTexture, gTexture, bTexture);
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
		
		
		 
		
		
		
		
		BaseModel model = OBJLoader.loadObjModel("pine", loader);
		BaseModel rockmodel = OBJLoader.loadObjModel("rock", loader);
		BaseModel housemodel = OBJLoader.loadObjModel("house", loader);
		BaseModel fencemodel = OBJLoader.loadObjModel("fence", loader);
		BaseModel windmilmodel = OBJLoader.loadObjModel("windmil", loader);
		BaseModel benchmodel = OBJLoader.loadObjModel("bench", loader);
		BaseModel cratemodel = OBJLoader.loadObjModel("box", loader);
		BaseModel villagers = OBJLoader.loadObjModel("stanfordBunny", loader);
		
	
		
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("pine")));
		TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader),new ModelTexture(loader.loadTexture("grassTexture")));
		TexturedModel lamp = new TexturedModel(OBJLoader.loadObjModel("lamp", loader),new ModelTexture(loader.loadTexture("lamp")));
		TexturedModel flower = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader),new ModelTexture(loader.loadTexture("flower")));
		TexturedModel bushmodel = new TexturedModel(OBJLoader.loadObjModel("bush", loader),new ModelTexture(loader.loadTexture("bush")));
		TexturedModel eldermodel = new TexturedModel(OBJLoader.loadObjModel("elder", loader),new ModelTexture(loader.loadTexture("wh")));
		TexturedModel foxmodel = new TexturedModel(OBJLoader.loadObjModel("fox1", loader),new ModelTexture(loader.loadTexture("fox")));
		TexturedModel wolfmodel = new TexturedModel(OBJLoader.loadObjModel("wolfy2", loader),new ModelTexture(loader.loadTexture("wolfytex")));
		TexturedModel testosmodel = new TexturedModel(OBJLoader.loadObjModel("testos1", loader),new ModelTexture(loader.loadTexture("testos")));
		TexturedModel redeggmodel = new TexturedModel(OBJLoader.loadObjModel("egg1", loader),new ModelTexture(loader.loadTexture("redegg")));
		TexturedModel barrelmodel = new TexturedModel(OBJLoader.loadObjModel("barrel", loader),new ModelTexture(loader.loadTexture("barrel")));
		TexturedModel whiteeggmodel = new TexturedModel(OBJLoader.loadObjModel("egg1", loader),new ModelTexture(loader.loadTexture("whiteegg")));
		
		
		ModelTexture fernTextureAtlas = new ModelTexture(loader.loadTexture("fern"));
		fernTextureAtlas.setNumberOfRows(2);
		TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern", loader),fernTextureAtlas);
		
		redeggmodel.getTexture().setUseFakeLighting(true);
		whiteeggmodel.getTexture().setUseFakeLighting(true);
		bushmodel.getTexture().setHasTransparency(true);
		bushmodel.getTexture().setUseFakeLighting(true);
		grass.getTexture().setHasTransparency(true);
		grass.getTexture().setUseFakeLighting(true);
		fern.getTexture().setHasTransparency(true);
		flower.getTexture().setHasTransparency(true);
		flower.getTexture().setUseFakeLighting(true);
		lamp.getTexture().setUseFakeLighting(true);
		
		Terrain terrain = new Terrain(0,-1,loader,texturePack,blendMap, "heightmap");
		
		List<EntityStructure> entities = new ArrayList<EntityStructure>();
		
		List<EntityStructure> normalMapEntities = new ArrayList<EntityStructure>();
		

		
		
		//asset bundle
		//firelands
		entities.add(new EntityStructure(new TexturedModel(model,new ModelTexture(loader.loadTexture("pinedark"))), new Vector3f(135.98723f,-3.33f,-587.78546f),0,0,0,4f));
		entities.add(new EntityStructure(new TexturedModel(model,new ModelTexture(loader.loadTexture("pinedark"))), new Vector3f(39.60845f,-5.507f,-553.919f),0,0,0,4f));
		entities.add(new EntityStructure(new TexturedModel(model,new ModelTexture(loader.loadTexture("pinedark"))), new Vector3f(218.47176f,-9.85339f,-531.9131f),0,0,0,4f));
		entities.add(new EntityStructure(new TexturedModel(rockmodel,new ModelTexture(loader.loadTexture("rock"))), new Vector3f(111.480156f,-4.7f,-639.318f),0,0,0,4f));
		entities.add(new EntityStructure(new TexturedModel(rockmodel,new ModelTexture(loader.loadTexture("rock"))), new Vector3f(640.558f,-3.33f,-297.2122f),0,0,0,4f));
		
		//village
		entities.add(new EntityStructure(barrelmodel, new Vector3f(764.1348f,23,-109.69f),0,0,0,1f));
		entities.add(new EntityStructure(new TexturedModel(housemodel,new ModelTexture(loader.loadTexture("house"))), new Vector3f(699.2568f,17.06f,-168.94f),0,0,0,6f));
		entities.add(new EntityStructure(new TexturedModel(housemodel,new ModelTexture(loader.loadTexture("house"))), new Vector3f(596.2568f,17.06f,-78.94f),0,0,0,6f));
		entities.add(new EntityStructure(new TexturedModel(fencemodel,new ModelTexture(loader.loadTexture("fence"))), new Vector3f(473.1645f,-2.087f,-186.639f),0,0,0,5f));
		entities.add(new EntityStructure(new TexturedModel(windmilmodel,new ModelTexture(loader.loadTexture("windmil"))), new Vector3f(440.7674f,-3f,-240.175312f),0,0,0,5.5f));
		entities.add(new EntityStructure(new TexturedModel(benchmodel,new ModelTexture(loader.loadTexture("bench"))), new Vector3f(678.7674f,17.06f,-167.175312f),0,0,0,5.5f));
		entities.add(new EntityStructure(bushmodel, new Vector3f(650.175f,0.6166664f,-60.380653f),0,0,0,5.5f));
		entities.add(new EntityStructure(bushmodel, new Vector3f(718.175f,0.6166664f,-50.380653f),0,0,0,2f));
		entities.add(new EntityStructure(bushmodel, new Vector3f(489.175f,-3f,-214.380653f),0,0,0,5f));
		entities.add(new EntityStructure(new TexturedModel(cratemodel,new ModelTexture(loader.loadTexture("box"))), new Vector3f(555.8868f,22.06f,-142.94f),0,0,0,4f));
		entities.add(new EntityStructure(new TexturedModel(cratemodel,new ModelTexture(loader.loadTexture("box"))), new Vector3f(545.8868f,22.06f,-152.94f),0,0,0,4f));
		entities.add(new EntityStructure(new TexturedModel(cratemodel,new ModelTexture(loader.loadTexture("box"))), new Vector3f(532.8868f,22.06f,-111.94f),0,0,0,4f));
		entities.add(new EntityStructure(new TexturedModel(cratemodel,new ModelTexture(loader.loadTexture("box"))), new Vector3f(520.8868f,22.06f,-105.94f),0,0,0,4f));
		entities.add(new EntityStructure(new TexturedModel(villagers,new ModelTexture(loader.loadTexture("wh"))), new Vector3f(689.8868f,17.06f,-170.94f),0,0,0,0.75f));
		entities.add(new EntityStructure(new TexturedModel(villagers,new ModelTexture(loader.loadTexture("wh"))), new Vector3f(612.8868f,7.76f,-227.94f),0,0,0,0.75f));
		entities.add(new EntityStructure(new TexturedModel(villagers,new ModelTexture(loader.loadTexture("wh"))), new Vector3f(657.8868f,17.06f,-223.94f),0,0,0,0.75f));
		entities.add(new EntityStructure(new TexturedModel(villagers,new ModelTexture(loader.loadTexture("wh"))), new Vector3f(595.8868f,17.06f,-74.94f),0,0,0,0.75f));
		
		//elders
		EntityStructure elderTown = new EntityStructure(eldermodel, new Vector3f(687.4627f,17.06f,-122.56495f),0,0,0,3.5f);
		entities.add(elderTown);
		EntityStructure elderMaze = new EntityStructure(eldermodel, new Vector3f(730.953f,17.06f,-388.60703f),0,0,0,3.5f);
		entities.add(elderMaze);
		EntityStructure elderLake = new EntityStructure(eldermodel, new Vector3f(363.05402f,17.06f,-141.52122f),0,0,0,3.5f);
		entities.add(elderLake);
		EntityStructure elderFire1 = new EntityStructure(eldermodel, new Vector3f(168.27415f,-3.3f,-361.1442f),0,0,0,3.5f);
		entities.add(elderFire1);
		EntityStructure elderFire2 = new EntityStructure(eldermodel, new Vector3f(294.7997f,-3.3f,-411.35822f),0,0,0,3.5f);
		entities.add(elderFire2);
		
		
		//mobs
		
		EntityStructure FoxBoss = new EntityStructure(foxmodel, new Vector3f(391.90506f,4.12f,-756.7f),0,0,0,13.5f);
		entities.add(FoxBoss);
		FoxBoss.setHp(1);
		FoxBoss.increaseRotation(0, 90, 0);
		
		
		EntityStructure testos1 = new EntityStructure(testosmodel, new Vector3f(173.90506f,-3.3f,-115.7f),0,0,0,13.5f);
		entities.add(testos1);
		testos1.setHp(2);
		EntityStructure testos2 = new EntityStructure(testosmodel, new Vector3f(131.90506f,-3.3f,-102.7f),0,0,0,13.5f);
		entities.add(testos2);
		testos2.setHp(2);
		EntityStructure testos3 = new EntityStructure(testosmodel, new Vector3f(137.90506f,-3.3f,-43.7f),0,0,0,13.5f);
		entities.add(testos3);
		testos3.setHp(2);
		EntityStructure testos4 = new EntityStructure(testosmodel, new Vector3f(106.90506f,-3.3f,-118.7f),0,0,0,13.5f);
		entities.add(testos4);
		testos4.setHp(2);
		
		EntityStructure wolfy1 = new EntityStructure(wolfmodel, new Vector3f(187.90506f,-3.3f,-598.7f),0,0,0,23.5f);
		entities.add(wolfy1);
		wolfy1.setHp(4);
		EntityStructure wolfy2 = new EntityStructure(wolfmodel, new Vector3f(78.90506f,-3.3f,-645.7f),0,0,0,23.5f);
		entities.add(wolfy2);
		wolfy2.setHp(4);
		EntityStructure WolfBoss = new EntityStructure(wolfmodel, new Vector3f(97.90506f,-3.3f,-724.7f),0,0,0,33.5f);
		entities.add(WolfBoss);
		WolfBoss.setHp(8);
		
		//eggs
		EntityStructure turtleEgg = new EntityStructure(whiteeggmodel, new Vector3f(52.3834f,23.67f,-28.460686f),0,0,0,8.5f);
		entities.add(turtleEgg);
		EntityStructure whiteEgg = new EntityStructure(whiteeggmodel, new Vector3f(60.3515f,11.07f,-765.0599f),0,0,0,8.5f);
		entities.add(whiteEgg);
		
		Random random = new Random(676452);
		for(int i=0;i<400;i++) {
			if(i % 2 == 0) {
				float x = random.nextFloat()*800-400;
				float z = random.nextFloat()*-600;
				float y = terrain.getHeightOfTerrain(x,z);
				
				entities.add(new EntityStructure(fern, random.nextInt(4), new Vector3f(x,y,z),0,random.nextFloat()*360,0,0.9f));
				
			}
			if(i%5==0) {
				float x = random.nextFloat()*800-400;
				float z = random.nextFloat()*-600;
				float y = terrain.getHeightOfTerrain(x,z);
				
				
				
				entities.add(new EntityStructure(staticModel, new Vector3f(x,y,z),0,0,0,random.nextFloat()*1+2));
			}
			
			
		}
		
		
		
		Light light = new Light(new Vector3f(70000,100000,-70000),new Vector3f(0.85f,0.85f,0.85f));
		List<Light> lights = new ArrayList<Light>();
		lights.add(light);
		lights.add(new Light(new Vector3f(783.411f,12.5f,-680.38f),new Vector3f(2,0,0), new Vector3f(1,0.01f,0.002f)));
		lights.add(new Light(new Vector3f(382.74f,12.5f,-442.501f),new Vector3f(2,0,0), new Vector3f(1,0.01f,0.002f)));
		
		
		entities.add(new EntityStructure (lamp, new Vector3f(783.33f,5,-680.37f),0,0,0,1));
		entities.add(new EntityStructure (lamp, new Vector3f(381.74f,5,-441.58f),0,0,0,1));
		
			
		
		

		
		List<GuiTexture> guis = new ArrayList<GuiTexture>();

		GuiTexture gui = new GuiTexture(loader.loadTexture("logo2"),new Vector2f(0.7f,0.75f),new Vector2f(0.25f,0.25f));
		GuiTexture guitutorial = new GuiTexture(loader.loadTexture("tutframe"),new Vector2f(0,0),new Vector2f(1,1));
		GuiTexture guiend = new GuiTexture(loader.loadTexture("endscreen"),new Vector2f(0,0),new Vector2f(1,1));
		
		guis.add(guitutorial);
		guis.add(gui);
		
		GuiRenderer guiRenderer = new GuiRenderer(loader);
		
		//MousePicker picker = new MousePicker(camera, renderer.getProjectionMatrix(), terrain);
		EntityStructure lampEntity = (new EntityStructure(lamp, new Vector3f(488,5,-696.5f),0,0,0,1));
		entities.add(lampEntity);
		Light lit =(new Light(new Vector3f(489.40628f,13,-696.4544f),new Vector3f(2,0,0), new Vector3f(1,0.01f,0.002f)));
		lights.add(lit);
		
		//water test
		WaterFrameBuffers buffers=new WaterFrameBuffers();
		WaterShader waterShader = new WaterShader();
		WaterRenderer waterRenderer = new WaterRenderer(loader, waterShader,renderer.getProjectionMatrix(),buffers);
		List<WaterUnits> waters = new ArrayList<WaterUnits>();
		WaterUnits water=new WaterUnits(729.86f,-61.68f,4);
		waters.add(water);
		waters.add(new WaterUnits(186.476f,-34.48f,-0.9554f));
		waters.add(new WaterUnits(179.476f,-110.38f,-0.9554f));
		waters.add(new WaterUnits(61.76f,-77.83f,-0.9554f));
		waters.add(new WaterUnits(56.76f,-159.83f,-0.9554f));
		waters.add(new WaterUnits(135.76f,-146.83f,-0.9554f));
		
		//water end
		
		//particles start
		ParticleTexture particleTexture = new ParticleTexture(loader.loadTexture("particleAtlas"),4);
		ParticleTexture particleTexture2 = new ParticleTexture(loader.loadTexture("particleStar"),1);
		
		AdvancedParticle system = new AdvancedParticle(particleTexture,50,25,0.3f,4,2);
		system.randomizeRotation();
		system.setDirection(new Vector3f(0,1,0), 0.1f);
		system.setLifeError(0.1f);
		system.setSpeedError(0.4f);
		system.setScaleError(0.8f);
		
		
		// Game Runtime loop
		
		while(!Display.isCloseRequested()){
			// Time transition
			if(SkyboxRenderer.time >= 0 && SkyboxRenderer.time < 5000){
			    light.setColour(new Vector3f(0.3f,0.3f,0.3f));
			    text.remove();
			    maintext.remove();
			    mazetext.remove();
			    laketext.remove();
			    firetext1.remove();
			    firetext2.remove();
			    textnight.remove();
			    RendererMain.RED = 0.01f;
			    RendererMain.GREEN = 0.01f;
			    RendererMain.BLUE = 0.01f;
			   }else if(SkyboxRenderer.time >= 5000 && SkyboxRenderer.time < 8000){
			    light.increaseColor(new Vector3f(0.0001f,0.0001f,0.0001f));
			    text.remove();
			    textnight.remove();
			    TextMain.loadText(text);
			    WolfBoss.hp=8;
			    wolfy1.hp=4;
			    wolfy2.hp=4;
			    RendererMain.RED += 0.00157f;
			    RendererMain.GREEN += 0.00157f;
			    RendererMain.BLUE += 0.0018f;
			   }else if(SkyboxRenderer.time >= 8000 && SkyboxRenderer.time < 200000){
			    light.setColour(new Vector3f(1f,1f,1f));
			    RendererMain.RED = 0.5444f;
			    RendererMain.GREEN = 0.62f;
			    RendererMain.BLUE = 0.69f;
			   }else{
			    light.decreaseColor(new Vector3f(0.0001f,0.0001f,0.0001f));
			    text.remove();
			    textnight.remove();
			    TextMain.loadText(textnight);
			    WolfBoss.hp=200;
			    wolfy1.hp=100;
			    wolfy2.hp=100;
			    RendererMain.RED -= 0.002f;
			    RendererMain.GREEN -= 0.002f;
			    RendererMain.BLUE -= 0.002f;
			   }
			
			//Gui display
			if(Keyboard.isKeyDown(Keyboard.KEY_U)) {
				guis.remove(guitutorial);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_T)) {
				guis.add(guitutorial);
			}
			if(player.hasWhiteEgg==1 && player.hasTurtleEgg==1 && player.hasRedEgg==1) {
				guis.add(guiend);
			}
		
			//Projectile combat & misc.
			if(player.getPosition().x >= turtleEgg.getPosition().x-5 && player.getPosition().x < turtleEgg.getPosition().x+5  && player.getPosition().z >= turtleEgg.getPosition().z-5 && player.getPosition().z < turtleEgg.getPosition().z+5 ){
				player.hasTurtleEgg=1;
				entities.remove(turtleEgg);
				}
			if(player.getPosition().x >= whiteEgg.getPosition().x-5 && player.getPosition().x < whiteEgg.getPosition().x+5  && player.getPosition().z >= whiteEgg.getPosition().z-5 && player.getPosition().z < whiteEgg.getPosition().z+5 ){
				player.hasWhiteEgg=1;
				entities.remove(whiteEgg);
				}
			if(player.getPosition().x >= FoxBoss.getPosition().x-30 && player.getPosition().x < FoxBoss.getPosition().x+30  && player.getPosition().z >= FoxBoss.getPosition().z-30 && player.getPosition().z < FoxBoss.getPosition().z+30 ){
				
				
				
				float dx=(float) ((20* DisplayWindow.getFrameTimeSeconds())*Math.sin(Math.toRadians(FoxBoss.getRotY())));
				float dz=(float) ((20* DisplayWindow.getFrameTimeSeconds())*Math.cos(Math.toRadians(FoxBoss.getRotY())));
				FoxBoss.increasePosition(dx, 0, dz);
				}
			
			if(Mouse.isButtonDown(1)) {
				Particle bullet = new Particle(particleTexture2,new Vector3f(player.getPosition()), new Vector3f(0,30,0), -1, 4, 0.5f , 3);
				if(bullet.getPosition().x >= FoxBoss.getPosition().x-15 && bullet.getPosition().x < FoxBoss.getPosition().x+15  && bullet.getPosition().z >= FoxBoss.getPosition().z-15 && bullet.getPosition().z < FoxBoss.getPosition().z+15 ){
				FoxBoss.hp-=1;
				if(FoxBoss.hp<=0) {
				EntityStructure redegg = new EntityStructure(redeggmodel, FoxBoss.getPosition(),0,0,0,8.5f);
				entities.add(redegg);
				entities.remove(FoxBoss);
				player.hasRedEgg=1;}
				
				}
				
				
				if(bullet.getPosition().x >= wolfy1.getPosition().x-15 && bullet.getPosition().x < wolfy1.getPosition().x+15  && bullet.getPosition().z >= wolfy1.getPosition().z-15 && bullet.getPosition().z < wolfy1.getPosition().z+15 ){
					wolfy1.hp-=1;
					if(wolfy1.hp<=0) {entities.remove(wolfy1);}
					
					}
				if(bullet.getPosition().x >= wolfy2.getPosition().x-15 && bullet.getPosition().x < wolfy2.getPosition().x+15  && bullet.getPosition().z >= wolfy2.getPosition().z-15 && bullet.getPosition().z < wolfy2.getPosition().z+15 ){
					wolfy2.hp-=1;
					if(wolfy2.hp<=0) {entities.remove(wolfy2);}
					
					}
				if(bullet.getPosition().x >= WolfBoss.getPosition().x-15 && bullet.getPosition().x < WolfBoss.getPosition().x+15  && bullet.getPosition().z >= WolfBoss.getPosition().z-15 && bullet.getPosition().z < WolfBoss.getPosition().z+15 ){
					WolfBoss.hp-=1;
					if(WolfBoss.hp<=0) {entities.remove(WolfBoss);}
					
					}
				if(bullet.getPosition().x >= testos1.getPosition().x-15 && bullet.getPosition().x < testos1.getPosition().x+15  && bullet.getPosition().z >= testos1.getPosition().z-15 && bullet.getPosition().z < testos1.getPosition().z+15 ){
					testos1.hp-=1;
					if(testos1.hp<=0) {entities.remove(testos1);}
					
					}
				if(bullet.getPosition().x >= testos2.getPosition().x-15 && bullet.getPosition().x < testos2.getPosition().x+15  && bullet.getPosition().z >= testos2.getPosition().z-15 && bullet.getPosition().z < testos2.getPosition().z+15 ){
					testos2.hp-=1;
					if(testos2.hp<=0) {entities.remove(testos2);}
					
					}
				if(bullet.getPosition().x >= testos3.getPosition().x-15 && bullet.getPosition().x < testos3.getPosition().x+15  && bullet.getPosition().z >= testos3.getPosition().z-15 && bullet.getPosition().z < testos3.getPosition().z+15 ){
					testos3.hp-=1;
					if(testos3.hp<=0) {entities.remove(testos3);}
					
					}
				if(bullet.getPosition().x >= testos4.getPosition().x-15 && bullet.getPosition().x < testos4.getPosition().x+15  && bullet.getPosition().z >= testos4.getPosition().z-15 && bullet.getPosition().z < testos4.getPosition().z+15 ){
					testos4.hp-=1;
					if(testos4.hp<=0) {entities.remove(testos4);}
					
					}
			
			
			}
			
			//Mob follows
			if(player.getPosition().x >= wolfy1.getPosition().x-40 && player.getPosition().x < wolfy1.getPosition().x+40  && player.getPosition().z >= wolfy1.getPosition().z-40 && player.getPosition().z < wolfy1.getPosition().z+40 ){
				float dx=(float) ((20* DisplayWindow.getFrameTimeSeconds())*Math.sin(Math.toRadians(player.getRotY())));
				float dz=(float) ((20* DisplayWindow.getFrameTimeSeconds())*Math.cos(Math.toRadians(player.getRotY())));
				wolfy1.increasePosition(-dx, 0, -dz);
				wolfy1.setRotY(-player.getRotY());
				}
			if(player.getPosition().x >= wolfy2.getPosition().x-40 && player.getPosition().x < wolfy2.getPosition().x+40  && player.getPosition().z >= wolfy2.getPosition().z-40 && player.getPosition().z < wolfy2.getPosition().z+40 ){
				float dx=(float) ((20* DisplayWindow.getFrameTimeSeconds())*Math.sin(Math.toRadians(player.getRotY())));
				float dz=(float) ((20* DisplayWindow.getFrameTimeSeconds())*Math.cos(Math.toRadians(player.getRotY())));
				wolfy2.increasePosition(-dx, 0, -dz);
				wolfy2.setRotY(-player.getRotY());
				}
			if(player.getPosition().x >= WolfBoss.getPosition().x-40 && player.getPosition().x < WolfBoss.getPosition().x+40  && player.getPosition().z >= WolfBoss.getPosition().z-40 && player.getPosition().z < WolfBoss.getPosition().z+40 ){
				float dx=(float) ((25* DisplayWindow.getFrameTimeSeconds())*Math.sin(Math.toRadians(player.getRotY())));
				float dz=(float) ((25* DisplayWindow.getFrameTimeSeconds())*Math.cos(Math.toRadians(player.getRotY())));
				WolfBoss.increasePosition(-dx, 0, -dz);
				WolfBoss.setRotY(-player.getRotY());
				}
			if(player.getPosition().x >= testos1.getPosition().x-40 && player.getPosition().x < testos1.getPosition().x+40  && player.getPosition().z >= testos1.getPosition().z-40 && player.getPosition().z < testos1.getPosition().z+40 ){
				float dx=(float) ((15* DisplayWindow.getFrameTimeSeconds())*Math.sin(Math.toRadians(player.getRotY())));
				float dz=(float) ((15* DisplayWindow.getFrameTimeSeconds())*Math.cos(Math.toRadians(player.getRotY())));
				testos1.increasePosition(-dx, 0, -dz);
				testos1.setRotY(-player.getRotY());
				}
			if(player.getPosition().x >= testos2.getPosition().x-40 && player.getPosition().x < testos2.getPosition().x+40  && player.getPosition().z >= testos2.getPosition().z-40 && player.getPosition().z < testos2.getPosition().z+40 ){
				float dx=(float) ((15* DisplayWindow.getFrameTimeSeconds())*Math.sin(Math.toRadians(player.getRotY())));
				float dz=(float) ((15* DisplayWindow.getFrameTimeSeconds())*Math.cos(Math.toRadians(player.getRotY())));
				testos2.increasePosition(-dx, 0, -dz);
				testos2.setRotY(-player.getRotY());
				}
			if(player.getPosition().x >= testos3.getPosition().x-40 && player.getPosition().x < testos3.getPosition().x+40  && player.getPosition().z >= testos3.getPosition().z-40 && player.getPosition().z < testos3.getPosition().z+40 ){
				float dx=(float) ((15* DisplayWindow.getFrameTimeSeconds())*Math.sin(Math.toRadians(player.getRotY())));
				float dz=(float) ((15* DisplayWindow.getFrameTimeSeconds())*Math.cos(Math.toRadians(player.getRotY())));
				testos3.increasePosition(-dx, 0, -dz);
				testos3.setRotY(-player.getRotY());
				}
			if(player.getPosition().x >= testos4.getPosition().x-40 && player.getPosition().x < testos4.getPosition().x+40  && player.getPosition().z >= testos4.getPosition().z-40 && player.getPosition().z < testos4.getPosition().z+40 ){
				float dx=(float) ((15* DisplayWindow.getFrameTimeSeconds())*Math.sin(Math.toRadians(player.getRotY())));
				float dz=(float) ((15* DisplayWindow.getFrameTimeSeconds())*Math.cos(Math.toRadians(player.getRotY())));
				testos4.increasePosition(-dx, 0, -dz);
				testos4.setRotY(-player.getRotY());
				}
			
			//player damage
			if(player.getPosition().x >= wolfy1.getPosition().x-6 && player.getPosition().x < wolfy1.getPosition().x+6  && player.getPosition().z >= wolfy1.getPosition().z-6 && player.getPosition().z < wolfy1.getPosition().z+6 && player.getPosition().y >= wolfy1.getPosition().y-1 && player.getPosition().y < wolfy1.getPosition().y+1){
				player.getPosition().x=713.35966f;player.getPosition().z=-96.35f;player.getPosition().y=17.06f;
				}
			if(player.getPosition().x >= wolfy2.getPosition().x-6 && player.getPosition().x < wolfy2.getPosition().x+6  && player.getPosition().z >= wolfy2.getPosition().z-6 && player.getPosition().z < wolfy2.getPosition().z+6 && player.getPosition().y >= wolfy1.getPosition().y-1 && player.getPosition().y < wolfy1.getPosition().y+1 ){
				player.getPosition().x=713.35966f;player.getPosition().z=-96.35f;player.getPosition().y=17.06f;
				}
			if(player.getPosition().x >= WolfBoss.getPosition().x-6 && player.getPosition().x < WolfBoss.getPosition().x+6  && player.getPosition().z >= WolfBoss.getPosition().z-6 && player.getPosition().z < WolfBoss.getPosition().z+6 && player.getPosition().y >= wolfy1.getPosition().y-1 && player.getPosition().y < wolfy1.getPosition().y+1){
				player.getPosition().x=713.35966f;player.getPosition().z=-96.35f;player.getPosition().y=17.06f;
				}
			if(player.getPosition().x >= testos1.getPosition().x-6 && player.getPosition().x < testos1.getPosition().x+6  && player.getPosition().z >= testos1.getPosition().z-6 && player.getPosition().z < testos1.getPosition().z+6 && player.getPosition().y >= wolfy1.getPosition().y-1 && player.getPosition().y < wolfy1.getPosition().y+1){
				player.getPosition().x=713.35966f;player.getPosition().z=-96.35f;player.getPosition().y=17.06f;
				}
			if(player.getPosition().x >= testos2.getPosition().x-6 && player.getPosition().x < testos2.getPosition().x+6  && player.getPosition().z >= testos2.getPosition().z-6 && player.getPosition().z < testos2.getPosition().z+6 && player.getPosition().y >= wolfy1.getPosition().y-1 && player.getPosition().y < wolfy1.getPosition().y+1){
				player.getPosition().x=713.35966f;player.getPosition().z=-96.35f;player.getPosition().y=17.06f;
				}
			if(player.getPosition().x >= testos3.getPosition().x-6 && player.getPosition().x < testos3.getPosition().x+6  && player.getPosition().z >= testos3.getPosition().z-6 && player.getPosition().z < testos3.getPosition().z+6 && player.getPosition().y >= wolfy1.getPosition().y-1 && player.getPosition().y < wolfy1.getPosition().y+1){
				player.getPosition().x=713.35966f;player.getPosition().z=-96.35f;player.getPosition().y=17.06f;
				}
			if(player.getPosition().x >= testos4.getPosition().x-6 && player.getPosition().x < testos4.getPosition().x+6  && player.getPosition().z >= testos4.getPosition().z-6 && player.getPosition().z < testos4.getPosition().z+6 && player.getPosition().y >= wolfy1.getPosition().y-1 && player.getPosition().y < wolfy1.getPosition().y+1 ){
				player.getPosition().x=713.35944f;player.getPosition().z=-96.35f;player.getPosition().y=17.06f;
				}
			
			
			
			
			//Dialogues
			if(player.getPosition().x >= 684f && player.getPosition().x < 692f  && player.getPosition().z >= -125f && player.getPosition().z < -118f ){
			
				if(Keyboard.isKeyDown(Keyboard.KEY_E)) {
					TextMain.loadText(maintext);
				}else if(Keyboard.isKeyDown(Keyboard.KEY_Q)) {maintext.remove();}
				
			};
			
			if(player.getPosition().x >= 725f && player.getPosition().x < 735f  && player.getPosition().z >= -393f && player.getPosition().z < -383f ){
				
				if(Keyboard.isKeyDown(Keyboard.KEY_E)) {
					TextMain.loadText(mazetext);
				}else if(Keyboard.isKeyDown(Keyboard.KEY_Q)) {mazetext.remove();}
				
			};
			
			if(player.getPosition().x >= 357f && player.getPosition().x < 370f  && player.getPosition().z >= -146f && player.getPosition().z < -135f ){
				
				if(Keyboard.isKeyDown(Keyboard.KEY_E)) {
					TextMain.loadText(laketext);
				}else if(Keyboard.isKeyDown(Keyboard.KEY_Q)) {laketext.remove();}
				
			};
			
			if(player.getPosition().x >= 162f && player.getPosition().x < 173f  && player.getPosition().z >= -366f && player.getPosition().z < -353f ){
				
				if(Keyboard.isKeyDown(Keyboard.KEY_E)) {
					TextMain.loadText(firetext1);
				}else if(Keyboard.isKeyDown(Keyboard.KEY_Q)) {firetext1.remove();}
				
			};
			
			if(player.getPosition().x >= 290f && player.getPosition().x < 300f  && player.getPosition().z >= -416f && player.getPosition().z < -405f ){
				
				if(Keyboard.isKeyDown(Keyboard.KEY_E)) {
					TextMain.loadText(firetext2);
				}else if(Keyboard.isKeyDown(Keyboard.KEY_Q)) {firetext2.remove();}
				
			};
			
			player.move(terrain);
			camera.move();
			if(player.getPosition().y<=-40) {player.getPosition().x=713.35944f;player.getPosition().z=-96.35f;player.getPosition().y=17.06f;}
			
			renderer.renderShadowMap(entities, light);
			
			system.generateParticles(new Vector3f(230,33,-698));
			
			ParticleMain.update(camera);
			
			GL11.glEnable(GL30.GL_CLIP_DISTANCE0);
			
			
			
			//water rendering setup
			buffers.bindReflectionFrameBuffer();
			float distance = 2*(camera.getPosition().y-water.getHeight());
			camera.getPosition().y-=distance;
			camera.invertPitch();
			renderer.renderScene(player,entities,normalMapEntities, terrain, lights, camera, new Vector4f(0,1,0,-water.getHeight()+1f));
			camera.getPosition().y+=distance;
			camera.invertPitch();
			buffers.unbindCurrentFrameBuffer();
			buffers.bindRefractionFrameBuffer();
			renderer.renderScene(player,entities,normalMapEntities, terrain, lights, camera, new Vector4f(0,-1,0,water.getHeight()));
			GL11.glDisable(GL30.GL_CLIP_DISTANCE0);
			buffers.unbindCurrentFrameBuffer();
			
			
			
			renderer.renderScene(player,entities,normalMapEntities, terrain, lights, camera, new Vector4f(0,-1,0,1111110));
			waterRenderer.render(waters, camera, light);
			
			ParticleMain.renderParticles(camera);
			
			guiRenderer.render(guis);
			TextMain.render();
			
			
			DisplayWindow.updateDisplay();
		}
		
		pt.delete();
		Audio.cleanUp();
		ParticleMain.cleanUp();
		TextMain.cleanUp();
		buffers.cleanUp();
		waterShader.cleanUp();
		guiRenderer.cleanUp();
		renderer.cleanUp();
		loader.cleanUp();
		DisplayWindow.closeDisplay();

	}

}
