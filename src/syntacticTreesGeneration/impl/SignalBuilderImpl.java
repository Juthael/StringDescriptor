package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

import copycatModel.grammar.Group;
import copycatModel.grammar.Letter;
import copycatModel.grammar.PlatonicLetter;
import copycatModel.grammar.Position;
import copycatModel.grammar.Size;
import copycatModel.implementations.SignalV1;
import copycatModel.interfaces.SignalInterface;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.interfaces.SignalBuilderInterface;

public class SignalBuilderV1 implements SignalBuilderInterface {

	private SignalInterface signal;
	
	public SignalBuilderV1(String charString, String directionValue) throws DescriptorsBuilderCriticalException {
		boolean charStringIsLegal = testIfCharStringIsLegal(charString);
		boolean directionValueIsLegal = testIfDirectionValueIsLegal(directionValue);
		if (charStringIsLegal == true && directionValueIsLegal == true) {
			char[] chars = charString.toCharArray();
			ArrayList<Group> listOfGroups = new ArrayList<Group>();
			for (int i=0 ; i<chars.length ; i++) {
				char iChar = chars[i];
				String iString = getLetterAlphabeticPositionString(iChar);
				Letter iLetter = getLetter(iString, Integer.toString(i+1));
				Size iSize = new Size(false, "1");
				Position iPosition = new Position(false, DescGenSettings.AWAITING_POSITION_VALUE);
				Group group = new Group(true, iSize, iPosition, iLetter);
				listOfGroups.add(group);
			}
			signal = new SignalV1(listOfGroups, directionValue);			
		}
		else throw new DescriptorsBuilderCriticalException("SignalBuilder : illegal parameter.");
	}
	
	@Override
	public SignalInterface getSignal(){
		return signal;
	}
	
	private Letter getLetter(String platonicLetterValue, String positionValue) {
		Position position = new Position(false, positionValue);
		PlatonicLetter platonicLetter = new PlatonicLetter(false, platonicLetterValue);
		Letter letter = new Letter(true, position, platonicLetter);
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
    	if (charString.length() > DescGenSettings.MAX_NB_OF_CHARS_IN_STRING)
    		charStringIsLegal = false;
    	else {
        	int minAsciiValue; 
        	int maxAsciiValue;
        	if (DescGenSettings.USE_LOWERCASE_LETTER) {
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
