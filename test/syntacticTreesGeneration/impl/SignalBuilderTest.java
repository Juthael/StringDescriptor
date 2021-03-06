package syntacticTreesGeneration.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.copycatModel.synTreeGrammar.Frame;
import model.synTreeModel.IFrame;
import model.synTreeModel.ISignal;
import settings.Settings;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.SignalBuilder;

public class SignalBuilderTest {

	private String legalParameterIfLowerCase = "abc";
	private String legalParameterIfUpperCase = "ABC";
	private String nonAuthorizedCharacters = "a1c";
	private String stringIsTooLong = "abcdefghijk";
	private String legalDirection = "fromLeftToRight";
	private String illegalDirection = "gyhszu";
	
	
	@Test
	public void throwsExceptionIfTheStringParameterLengthExceedValueSpecifiedInSettings() {		
		try {
			ISignalBuilder signalBuilder = new SignalBuilder(stringIsTooLong, legalDirection);
			ISignal signal = signalBuilder.getSignal();
			fail();
		}
		catch (Exception expected) {
		}
	}
	
	@Test
	public void throwsExceptionIfTheStringParameterContainsIllegalCharacter() {
		try {
			ISignalBuilder signalBuilder = new SignalBuilder(nonAuthorizedCharacters, legalDirection);
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
			ISignalBuilder signalBuilder = new SignalBuilder(legalString, illegalDirection);
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
			ISignalBuilder signalBuilder = new SignalBuilder(legalString, legalDirection);
			ISignal signal = signalBuilder.getSignal();
			List<IFrame> listOfFrames = signal.getFrames();
		}
		catch (Exception expected) {
			fail();
		}			
	}

}
