package model.copycatModel.synTreeGrammar;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;
import model.copycatModel.signal.ICopycatSignal;
import settings.Settings;
import syntacticTreesGeneration.IListOfDescriptorsBuilder;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.ListOfDescriptorsBuilderImpl;
import syntacticTreesGeneration.impl.SignalBuilderImpl;

public class CharStringTest {

	@Test
	public void whenCharStringInstantiatedThenCanReturnAVersionOfItselfWithAbstractFrames() 
			throws SynTreeGenerationException, CloneNotSupportedException, VerbalizationException, OrderedSetsGenerationException {
		
		try{
			ISignalBuilder signalBuilder = new SignalBuilderImpl("abcd", Settings.LEFT_TO_RIGHT);
			IListOfDescriptorsBuilder listOfDescriptorsBuilder = new ListOfDescriptorsBuilderImpl((ICopycatSignal) signalBuilder.getSignal());
			List<CharString> descriptors = listOfDescriptorsBuilder.getListOfStringDescriptors();
			Map<CharString, CharStringWithAbstractFrames> charStringToAbstract = new HashMap<CharString, CharStringWithAbstractFrames>();
			for (CharString descriptor : descriptors) {
				CharStringWithAbstractFrames descWithAbstractFrames = 
						(CharStringWithAbstractFrames) descriptor.getTreeWithAbstractFrames();
				charStringToAbstract.put(descriptor, descWithAbstractFrames);
			}
			for (CharString charString : descriptors) {
				String verbalDescription = charString.getVerbalDescription();
				List<String> properties = charString.getListOfPropertiesWithPath();
				List<String> propertiesAbstract =  charStringToAbstract.get(charString).getListOfPropertiesWithPath();
				System.out.println("*****NEW DESCRIPTOR*****");
				System.out.println("");
				System.out.println(verbalDescription);
				System.out.println("");
				for (String property : properties) {
					System.out.println(property);
				}
				System.out.println("");
				for (String property : propertiesAbstract) {
					System.out.println(property);
				}
				System.out.println("");
			}
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			unexpected.printStackTrace();
			fail();
		}
		
	}

}
