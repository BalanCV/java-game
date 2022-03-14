package textRender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import renderer.Loader;
import textStructure.FontType;
import textStructure.RenderedText;
import textStructure.TextFormatData;

public class TextMain {
	
	private static Loader loader;
	private static Map<FontType, List<RenderedText>> texts = new HashMap<FontType, List<RenderedText>>();
	private static TextRenderer renderer;
	
	public static void init(Loader theLoader){
		renderer = new TextRenderer();
		loader = theLoader;
	}
	
	public static void render(){
		renderer.render(texts);
	}
	
	public static void loadText(RenderedText text){
		FontType font = text.getFont();
		TextFormatData data = font.loadText(text);
		int vao = loader.loadToVAO(data.getVertexPositions(), data.getTextureCoords());
		text.setMeshInfo(vao, data.getVertexCount());
		List<RenderedText> textBatch = texts.get(font);
		if(textBatch == null){
			textBatch = new ArrayList<RenderedText>();
			texts.put(font, textBatch);
		}
		textBatch.add(text);
	}
	
	public static void removeText(RenderedText text){
		List<RenderedText> textBatch = texts.get(text.getFont());
		textBatch.remove(text);
		if(textBatch.isEmpty()){
			texts.remove(texts.get(text.getFont()));
		}
	}
	
	public static void cleanUp(){
		renderer.cleanUp();
	}

}
