package models;

import textures.ModelTexture;

public class TexturedModel {
	
	private BaseModel rawModel;
	private ModelTexture texture;

	
	public TexturedModel(BaseModel model, ModelTexture texture){
		this.rawModel = model;
		this.texture = texture;
	}

	public BaseModel getRawModel() {
		return rawModel;
	}

	public ModelTexture getTexture() {
		return texture;
	}

}
