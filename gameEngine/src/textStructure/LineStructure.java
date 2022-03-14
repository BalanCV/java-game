package textStructure;

import java.util.ArrayList;
import java.util.List;

public class LineStructure {

	private double maxLength;
	private double spaceSize;

	private List<WordStructure> words = new ArrayList<WordStructure>();
	private double currentLineLength = 0;

	protected LineStructure(double spaceWidth, double fontSize, double maxLength) {
		this.spaceSize = spaceWidth * fontSize;
		this.maxLength = maxLength;
	}

	
	protected boolean attemptToAddWord(WordStructure word) {
		double additionalLength = word.getWordWidth();
		additionalLength += !words.isEmpty() ? spaceSize : 0;
		if (currentLineLength + additionalLength <= maxLength) {
			words.add(word);
			currentLineLength += additionalLength;
			return true;
		} else {
			return false;
		}
	}

	
	protected double getMaxLength() {
		return maxLength;
	}

	
	protected double getLineLength() {
		return currentLineLength;
	}

	
	protected List<WordStructure> getWords() {
		return words;
	}

}
