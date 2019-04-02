package descriptorsGeneration.implementations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import copycatModel.grammar.Group;
import copycatModel.interfaces.SignalInterface;
import settings.DescGenSettings;
import syntacticTreesGeneration.implementations.SignalBuilderV1;
import syntacticTreesGeneration.interfaces.SignalBuilderInterface;

class SignalBuilderV1Test {

	private String legalParameterIfLowerCase = "abc";
	private String legalParameterIfUpperCase = "ABC";
	private String nonAuthorizedCharacters = "a1c";
	private String stringIsTooLong = "abcdefghijk";
	private String legalDirection = "fromLeftToRight";
	private String illegalDirection = "gyhszu";
	
	
	@Test
	void throwsExceptionIfTheStringParameterLengthExceedValueSpecifiedInSettings() {		
		try {
			SignalBuilderInterface signalBuilder = new SignalBuilderV1(stringIsTooLong, legalDirection);
			SignalInterface signal = signalBuilder.getSignal();
			fail();
		}
		catch (Exception expected) {
		}
	}
	
	@Test
	void throwsExceptionIfTheStringParameterContainsIllegalCharacter() {
		try {
			SignalBuilderInterface signalBuilder = new SignalBuilderV1(nonAuthorizedCharacters, legalDirection);
			SignalInterface signal = signalBuilder.getSignal();
			fail();
		}
		catch (Exception expected) {
		}		
	}
	
	@Test
	void throwsExceptionIfDirectionStringValueIsIllegal() {
		String legalString;
		if (DescGenSettings.USE_LOWERCASE_LETTER)
		 legalString = legalParameterIfLowerCase;
		else legalString = legalParameterIfUpperCase;
		try {
			SignalBuilderInterface signalBuilder = new SignalBuilderV1(legalString, illegalDirection);
			SignalInterface signal = signalBuilder.getSignal();
			fail();
		}
		catch (Exception expected) {
		}			
	}
	
	@Test
	void buildsSignalIfParametersAreLegal() {
		String legalString;
		if (DescGenSettings.USE_LOWERCASE_LETTER)
		 legalString = legalParameterIfLowerCase;
		else legalString = legalParameterIfUpperCase;
		try {
			SignalBuilderInterface signalBuilder = new SignalBuilderV1(legalString, legalDirection);
			SignalInterface signal = signalBuilder.getSignal();
			ArrayList<Group> listOfGroups = signal.getGroups();
		}
		catch (Exception expected) {
			fail();
		}			
	}

}
