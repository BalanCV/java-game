package renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.TexturedModel;
import shadersET.FirstShader;
import shadersET.TerrainShader;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import shadows.ShadowRenderMain;
import skybox.SkyboxRenderer;
import terrains.Terrain;
import entities.Camera;
import entities.EntityStructure;
import entities.Light;

public class RendererMain {
	
	public static final float FOV = 70;
	public static final float NEAR_PLANE = 0.1f;
	public static final float FAR_PLANE = 1000;
	
	public static float RED = 0.5444f;
	public static float GREEN = 0.62f;
	public static float BLUE = 0.69f;
	
	private Matrix4f projectionMatrix;
	
	private FirstShader shader = new FirstShader();
	private EntityRenderer renderer;
	
	private TerrainRenderer terrainRenderer;
	private TerrainShader terrainShader = new TerrainShader();
	
	private SkyboxRenderer skyboxRenderer;
	private ShadowRenderMain shadowMapRenderer;
	
	private Map<TexturedModel,List<EntityStructure>> entities = new HashMap<TexturedModel,List<EntityStructure>>();
	
	private List<Terrain> terrains = new ArrayList<Terrain>();
	
	
	
	public RendererMain(Loader loader, Camera camera){
		enableCulling();
		createProjectionMatrix();
		renderer = new EntityRenderer(shader,projectionMatrix);
		terrainRenderer = new TerrainRenderer(terrainShader,projectionMatrix);
		skyboxRenderer = new SkyboxRenderer(loader,projectionMatrix);
		this.shadowMapRenderer=new ShadowRenderMain(camera);
	}
	
	public Matrix4f getProjectionMatrix(){
		return projectionMatrix;
	}
	
	public void renderScene(EntityStructure player,List<EntityStructure> entities, List<EntityStructure> normalEntities, Terrain terrains, List<Light> lights, Camera camera, Vector4f clipPlane) {
		processEntity(player);
		
			processTerrain(terrains);
		
		for(EntityStructure entity:entities) {
			processEntity(entity);
		}
	
		render(lights,camera, clipPlane);
	}
	
	public static void enableCulling() {
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	
	public static void disableCulling() {
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	
	public void render(List<Light> lights,Camera camera, Vector4f clipPlane){
		prepare();
		shader.start();
		shader.loadClipPlane(clipPlane);
		shader.loadSkyColour(RED, GREEN, BLUE);
		shader.loadLights(lights);
		shader.loadViewMatrix(camera);
		renderer.render(entities, shadowMapRenderer.getToShadowMapSpaceMatrix());
		shader.stop();
		terrainShader.start();
		terrainShader.loadClipPlane(clipPlane);
		terrainShader.loadSkyColour(RED, GREEN, BLUE);
		terrainShader.loadLights(lights);
		terrainShader.loadViewMatrix(camera);
		terrainRenderer.render(terrains, shadowMapRenderer.getToShadowMapSpaceMatrix());
		terrainShader.stop();
		skyboxRenderer.render(camera,RED, GREEN, BLUE);
		terrains.clear();
		entities.clear();
	}
	
	public void processTerrain(Terrain terrain){
		terrains.add(terrain);
	}
	
	public void processEntity(EntityStructure entity){
		TexturedModel entityModel = entity.getModel();
		List<EntityStructure> batch = entities.get(entityModel);
		if(batch!=null){
			batch.add(entity);
		}else{
			List<EntityStructure> newBatch = new ArrayList<EntityStructure>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);		
		}
	}
	

	
	public void renderShadowMap(List<EntityStructure> entityList, Light sun) {
		for(EntityStructure entity : entityList) {
			processEntity(entity);
		}
		shadowMapRenderer.render(entities, sun);
		entities.clear();
	}
	
	public int getShadowMapTexture() {
		return shadowMapRenderer.getShadowMap();
	}
	
	public void cleanUp(){
		shader.cleanUp();
		terrainShader.cleanUp();
		shadowMapRenderer.cleanUp();
	}
	
	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(RED, GREEN, BLUE, 1);
		GL13.glActiveTexture(GL13.GL_TEXTURE5);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, getShadowMapTexture());
	}
	
	 private void createProjectionMatrix(){
	    	projectionMatrix = new Matrix4f();
			float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
			float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))));
			float x_scale = y_scale / aspectRatio;
			float frustum_length = FAR_PLANE - NEAR_PLANE;

			projectionMatrix.m00 = x_scale;
			projectionMatrix.m11 = y_scale;
			projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
			projectionMatrix.m23 = -1;
			projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
			projectionMatrix.m33 = 0;
	    }
	

}
