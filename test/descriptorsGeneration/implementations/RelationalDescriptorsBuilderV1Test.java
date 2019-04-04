package descriptorsGeneration.implementations;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import copycatModel.grammar.CharString;
import copycatModel.interfaces.SignalInterface;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.implementations.RelationalDescriptorsBuilderV1;
import syntacticTreesGeneration.implementations.SignalBuilderV1;
import syntacticTreesGeneration.interfaces.RelationalDescriptorsBuilderInterface;
import syntacticTreesGeneration.interfaces.SignalBuilderInterface;

class RelationalDescriptorsBuilderV1Test {

	@Test
	void whenRandomMaxSizeStringInParameterThenDescriptionProceededInLessThan5Sec() throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		boolean descriptionIsProceededInLessThan5Sec = true;
		long start;
		long done;
		ArrayList<CharString> wholeStringDescriptors;
		int AsciiValueForA;
		if (DescGenSettings.USE_LOWERCASE_LETTER)
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
		String stringToBeDescribed = possiblyOversizedString.substring(0, DescGenSettings.MAX_NB_OF_CHARS_IN_STRING);
		/* System.out.println(stringToBeDescribed);
		System.out.println(""); */
		SignalBuilderInterface signalBuilder = new SignalBuilderV1(stringToBeDescribed, "fromLeftToRight");
		SignalInterface signal = signalBuilder.getSignal();
		start = System.currentTimeMillis();
		RelationalDescriptorsBuilderInterface relationalDescriptorsBuilder = 
				new RelationalDescriptorsBuilderV1(signal);
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
	void whenStructuredMaxSizeStringInParameterThenDescriptionProceededInLessThan5Sec() 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		boolean descriptionIsProceededInLessThan5Sec = true;
		long start;
		long done;
		String lowerCaseString =  "abcdefghijklmnopqrstuvwxyz";
		String upperCaseString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String overSizedParameter;
		String parameter;
		ArrayList<CharString> wholeStringDescriptors;
		if (DescGenSettings.USE_LOWERCASE_LETTER)
			overSizedParameter = lowerCaseString;
		else overSizedParameter = upperCaseString;
		parameter = overSizedParameter.substring(0, DescGenSettings.MAX_NB_OF_CHARS_IN_STRING);
		parameter = "abcd"; //
		SignalBuilderInterface signalBuilder = new SignalBuilderV1(parameter, "fromLeftToRight");
		SignalInterface signal = signalBuilder.getSignal();
		start = System.currentTimeMillis();
		RelationalDescriptorsBuilderInterface relationalDescriptorsBuilder = 
				new RelationalDescriptorsBuilderV1(signal);
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
