package syntacticTreesGeneration.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import exceptions.SynTreeGenerationException;
import model.copycatModel.signal.ICopycatSignal;
import model.copycatModel.synTreeGrammar.CharString;
import model.synTreeModel.ISignal;
import settings.Settings;
import syntacticTreesGeneration.IRelationalDescriptorsBuilder;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.RelationalDescriptorsBuilder;
import syntacticTreesGeneration.impl.SignalBuilder;

public class RelationalDescriptorsBuilderTest {

	@Test
	public void whenRandomMaxSizeStringInParameterThenDescriptionProceededInLessThan5Sec() throws SynTreeGenerationException, CloneNotSupportedException {
		boolean descriptionIsProceededInLessThan5Sec = true;
		long start;
		long done;
		List<CharString> wholeStringDescriptors;
		int AsciiValueForA;
		if (Settings.USE_LOWERCASE_LETTER)
			AsciiValueForA = 97;
		else AsciiValueForA = 65;
		int[] randomValues = new int[20];
		Random r = new Random();
		for (int i=0 ; i<randomValues.length ; i++) {
			randomValues[i] = r.nextInt(26) + AsciiValueForA;
		}
		StringBuilder sB = new StringBuilder();
		for (int value : randomValues) {
			sB.append((char) value);
		}
		String possiblyOversizedString = sB.toString();
		String stringToBeDescribed = possiblyOversizedString.substring(0, Settings.MAX_NB_OF_CHARS_IN_STRING);
		/* System.out.println(stringToBeDescribed);
		System.out.println(""); */
		ISignalBuilder signalBuilder = new SignalBuilder(stringToBeDescribed, "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		start = System.currentTimeMillis();
		IRelationalDescriptorsBuilder relationalDescriptorsBuilder = 
				new RelationalDescriptorsBuilder((ICopycatSignal) signal);
		wholeStringDescriptors = relationalDescriptorsBuilder.getListOfDescriptorsCoveringTheWholeString();
		done = System.currentTimeMillis();
		if (wholeStringDescriptors.isEmpty() ||	(done - start) > 5000)
			descriptionIsProceededInLessThan5Sec = false;
		/* for (CharString descriptor : wholeStringDescriptors) {
			for (String property : descriptor.getListOfPropertiesWithPath()) {
				System.out.println(property);
			}
			System.out.println("");
		} */
		assertTrue(descriptionIsProceededInLessThan5Sec);
	}
	
	@Test
	public void whenStructuredMaxSizeStringInParameterThenDescriptionProceededInLessThan5Sec() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		boolean descriptionIsProceededInLessThan5Sec = true;
		long start;
		long done;
		String lowerCaseString =  "abcdefghijklmnopqrstuvwxyz";
		String upperCaseString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String overSizedParameter;
		String parameter;
		List<CharString> wholeStringDescriptors;
		if (Settings.USE_LOWERCASE_LETTER)
			overSizedParameter = lowerCaseString;
		else overSizedParameter = upperCaseString;
		parameter = overSizedParameter.substring(0, Settings.MAX_NB_OF_CHARS_IN_STRING - 1);
		ISignalBuilder signalBuilder = new SignalBuilder(parameter, "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		start = System.currentTimeMillis();
		IRelationalDescriptorsBuilder relationalDescriptorsBuilder = 
				new RelationalDescriptorsBuilder((ICopycatSignal) signal);
		wholeStringDescriptors = relationalDescriptorsBuilder.getListOfDescriptorsCoveringTheWholeString();
		done = System.currentTimeMillis();
		if (wholeStringDescriptors.isEmpty() ||	(done - start) > 5000)
			descriptionIsProceededInLessThan5Sec = false;
		/* int index = 0;
		for (CharString descriptor : wholeStringDescriptors) {
			System.out.println("n�" + index);
			for (String property : descriptor.getListOfRelevantPropertiesWithPath()) {
				System.out.println(property);
			}
			System.out.println("");
			index++;
		} */
		assertTrue(descriptionIsProceededInLessThan5Sec);		
	}

}
