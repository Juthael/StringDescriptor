package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Group;
import model.copycatModel.synTreeGrammar.Letter;
import model.copycatModel.synTreeGrammar.PlatonicLetter;
import model.copycatModel.synTreeGrammar.Position;
import model.copycatModel.synTreeGrammar.Size;
import model.synTreeModel.ISignal;
import model.synTreeModel.impl.SignalImpl;
import settings.Settings;
import syntacticTreesGeneration.ISignalBuilder;

public class SignalBuilderImpl implements ISignalBuilder {

	private ISignal signal;
	
	public SignalBuilderImpl(String charString, String directionValue) throws SynTreeGenerationException {
		boolean charStringIsLegal = testIfCharStringIsLegal(charString);
		boolean directionValueIsLegal = testIfDirectionValueIsLegal(directionValue);
		if (charStringIsLegal == true && directionValueIsLegal == true) {
			char[] chars = charString.toCharArray();
			List<Group> listOfGroups = new ArrayList<Group>();
			for (int i=0 ; i<chars.length ; i++) {
				char iChar = chars[i];
				String iString = getLetterAlphabeticPositionString(iChar);
				Letter iLetter = getLetter(iString, Integer.toString(i+1));
				Size iSize = new Size("1");
				Position iPosition = new Position(Settings.AWAITING_POSITION_VALUE);
				Group group = new Group(iSize, iPosition, iLetter);
				listOfGroups.add(group);
			}
			signal = new SignalImpl(listOfGroups, directionValue);			
		}
		else throw new SynTreeGenerationException("SignalBuilder : illegal parameter.");
	}
	
	@Override
	public ISignal getSignal(){
		return signal;
	}
	
	private Letter getLetter(String platonicLetterValue, String positionValue) {
		Position position = new Position(positionValue);
		PlatonicLetter platonicLetter = new PlatonicLetter(platonicLetterValue);
		Letter letter = new Letter(Settings.THIS_IS_A_CODING_ELEMENT, position, platonicLetter);
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
		boolean directionValueIsLegal = (directionValue.equals("fromLeftToRight") || directionValue.equals("fromRightToLeft"));
		return directionValueIsLegal; 	
    }

}
