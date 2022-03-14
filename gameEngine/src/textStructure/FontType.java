package textStructure;

import java.io.File;


public class FontType {

	private int textureAtlas;
	private TextFormat loader;

	public FontType(int textureAtlas, File fontFile) {
		this.textureAtlas = textureAtlas;
		this.loader = new TextFormat(fontFile);
	}

	
	public int getTextureAtlas() {
		return textureAtlas;
	}

	public TextFormatData loadText(RenderedText text) {
		return loader.createTextMesh(text);
	}

}
