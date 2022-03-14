package textStructure;

import java.util.ArrayList;
import java.util.List;

public class WordStructure {
	
	private List<CharacterStructure> characters = new ArrayList<CharacterStructure>();
	private double width = 0;
	private double fontSize;
	
	
	protected WordStructure(double fontSize){
		this.fontSize = fontSize;
	}
	
	protected void addCharacter(CharacterStructure character){
		characters.add(character);
		width += character.getxAdvance() * fontSize;
	}
	
	
	protected List<CharacterStructure> getCharacters(){
		return characters;
	}
	
	
	protected double getWordWidth(){
		return width;
	}

}
