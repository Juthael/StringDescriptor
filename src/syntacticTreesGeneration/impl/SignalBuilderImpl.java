package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.SynTreeGenerationException;
import model.copycatModel.signal.ICopycatSignal;
import model.copycatModel.signal.impl.CopycatSignal;
import model.copycatModel.synTreeGrammar.Frame;
import model.copycatModel.synTreeGrammar.Letter;
import model.copycatModel.synTreeGrammar.PlatonicLetter;
import model.copycatModel.synTreeGrammar.Position;
import model.copycatModel.synTreeGrammar.Size;
import model.synTreeModel.IFrame;
import settings.Settings;
import syntacticTreesGeneration.ISignalBuilder;

public class SignalBuilderImpl implements ISignalBuilder {

	private ICopycatSignal signal;
	
	public SignalBuilderImpl(String charString, String directionValue) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		boolean charStringIsLegal = testIfCharStringIsLegal(charString);
		boolean directionValueIsLegal = testIfDirectionValueIsLegal(directionValue);
		if (charStringIsLegal == true && directionValueIsLegal == true) {
			char[] chars = charString.toCharArray();
			List<IFrame> listOfFrames = new ArrayList<IFrame>();
			for (int i=0 ; i<chars.length ; i++) {
				char iChar = chars[i];
				String iString = getLetterAlphabeticPositionString(iChar);
				Letter iLetter = getLetter(iString, Integer.toString(i+1));
				Size iSize = new Size("1");
				Position iPosition = new Position(Settings.AWAITING_POSITION_VALUE);
				Frame frame = new Frame(iSize, iPosition, iLetter);
				listOfFrames.add(frame);
			}
			signal = new CopycatSignal(listOfFrames, directionValue);			
		}
		else throw new SynTreeGenerationException("SignalBuilder : illegal parameter.");
	}
	
	@Override
	public ICopycatSignal getSignal(){
		return signal;
	}
	
	private Letter getLetter(String platonicLetterValue, String positionValue) throws CloneNotSupportedException {
		Position position = new Position(positionValue);
		PlatonicLetter platonicLetter = new PlatonicLetter(platonicLetterValue);
		Letter letter = new Letter(position, platonicLetter);
		return letter;
	}
	
    private String getLetterAlphabeticPositionString(Character c){
        int aValueMinusOne = 96;
        int charAsciiValue = (int) c;
        Integer characterAlphabeticPosition = charAsciiValue - aValueMinusOne;
        return characterAlphabeticPosition.toString();
    }	
    
    private boolean testIfCharStringIsLegal(String charString) {
    	boolean charStringIsLegal = true;    	
    	if (charString.length() > Settings.MAX_NB_OF_CHARS_IN_STRING)
    		charStringIsLegal = false;
    	else {
        	int minAsciiValue; 
        	int maxAsciiValue;
        	if (Settings.USE_LOWERCASE_LETTER) {
        		minAsciiValue = 97;
        		maxAsciiValue = 122;
        	}
        	else {
        		minAsciiValue = 65;
        		maxAsciiValue = 90;
        	}    		
    		for (int i=0 ; i<charString.length() ; i++) {
    			if (charString.codePointAt(i) < minAsciiValue || charString.codePointAt(i) > maxAsciiValue)
    				charStringIsLegal = false;
    		}
    	}
    	return charStringIsLegal;
    }
    
    private boolean testIfDirectionValueIsLegal(String directionValue) {
		boolean directionValueIsLegal = (directionValue.equals(Settings.LEFT_TO_RIGHT) || directionValue.equals(Settings.RIGHT_TO_LEFT));
		return directionValueIsLegal; 	
    }

}
