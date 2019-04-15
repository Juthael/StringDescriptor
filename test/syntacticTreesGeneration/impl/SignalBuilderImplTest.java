package syntacticTreesGeneration.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.copycatModel.synTreeGrammar.Group;
import model.synTreeModel.ISignal;
import settings.Settings;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.SignalBuilderImpl;

public class SignalBuilderImplTest {

	private String legalParameterIfLowerCase = "abc";
	private String legalParameterIfUpperCase = "ABC";
	private String nonAuthorizedCharacters = "a1c";
	private String stringIsTooLong = "abcdefghijk";
	private String legalDirection = "fromLeftToRight";
	private String illegalDirection = "gyhszu";
	
	
	@Test
	public void throwsExceptionIfTheStringParameterLengthExceedValueSpecifiedInSettings() {		
		try {
			ISignalBuilder signalBuilder = new SignalBuilderImpl(stringIsTooLong, legalDirection);
			ISignal signal = signalBuilder.getSignal();
			fail();
		}
		catch (Exception expected) {
		}
	}
	
	@Test
	public void throwsExceptionIfTheStringParameterContainsIllegalCharacter() {
		try {
			ISignalBuilder signalBuilder = new SignalBuilderImpl(nonAuthorizedCharacters, legalDirection);
			ISignal signal = signalBuilder.getSignal();
			fail();
		}
		catch (Exception expected) {
		}		
	}
	
	@Test
	public void throwsExceptionIfDirectionStringValueIsIllegal() {
		String legalString;
		if (Settings.USE_LOWERCASE_LETTER)
		 legalString = legalParameterIfLowerCase;
		else legalString = legalParameterIfUpperCase;
		try {
			ISignalBuilder signalBuilder = new SignalBuilderImpl(legalString, illegalDirection);
			ISignal signal = signalBuilder.getSignal();
			fail();
		}
		catch (Exception expected) {
		}			
	}
	
	@Test
	public void buildsSignalIfParametersAreLegal() {
		String legalString;
		if (Settings.USE_LOWERCASE_LETTER)
		 legalString = legalParameterIfLowerCase;
		else legalString = legalParameterIfUpperCase;
		try {
			ISignalBuilder signalBuilder = new SignalBuilderImpl(legalString, legalDirection);
			ISignal signal = signalBuilder.getSignal();
			List<Group> listOfGroups = signal.getGroups();
		}
		catch (Exception expected) {
			fail();
		}			
	}

}
