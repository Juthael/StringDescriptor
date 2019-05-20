package verbalization.dataEncoding.encoders.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import verbalization.dataEncoding.encoders.IRecipeCoder;
import verbalization.dataEncoding.encoders.impl.RecipeCoderImpl;
import verbalization.dataEncoding.encodingModel.IInstructionCodeGetter;
import verbalization.dataEncoding.encodingModel.IRecipeCodeGetter;
import verbalization.dataEncoding.encodingModel.ITransformationCodeGetter;

public class RecipeCoderImplTest {

	@Test
	public void whenParameterIsThisListOfPropertiesThenExpectedInstructionCodeGettersAreReturned() {
		List<String> listOfProperties = new ArrayList<String>();
		listOfProperties.add("frame/size/5");
		listOfProperties.add("frame/position/1");
		listOfProperties.add("frame/relations/dimensionX2/dimension/size");
		listOfProperties.add("frame/relations/dimensionX2/dimension/letter.platonicLetter");
		listOfProperties.add("frame/relations/relationX2/relation/dimension/size");
		listOfProperties.add("frame/relations/relationX2/relation/enumeration/1,1,1,1,1");
		listOfProperties.add("frame/relations/relationX2/relation/sequence/commonDiff/0");
		listOfProperties.add("frame/relations/relationX2/relation/sequence/absCommonDiff/0");
		listOfProperties.add("frame/relations/relationX2/relation/dimension/letter.platonicLetter");
		listOfProperties.add("frame/relations/relationX2/relation/enumeration/1,2,3,4,5");
		listOfProperties.add("frame/relations/relationX2/relation/sequence/commonDiff/1");
		listOfProperties.add("frame/relations/relationX2/relation/sequence/absCommonDiff/1");
		listOfProperties.add("frame/relations/components/size/5");
		listOfProperties.add("frame/relations/components/frameX5/frame/size/1");
		listOfProperties.add("frame/relations/components/frameX5/frame/position/1");
		listOfProperties.add("frame/relations/components/frameX5/frame/letter/position/1");
		listOfProperties.add("frame/relations/components/frameX5/frame/letter/platonicLetter/1");
		listOfProperties.add("frame/relations/components/frameX5/frame/size/1");
		listOfProperties.add("frame/relations/components/frameX5/frame/position/2");
		listOfProperties.add("frame/relations/components/frameX5/frame/letter/position/2");
		listOfProperties.add("frame/relations/components/frameX5/frame/letter/platonicLetter/2");
		listOfProperties.add("frame/relations/components/frameX5/frame/size/1");
		listOfProperties.add("frame/relations/components/frameX5/frame/position/3");
		listOfProperties.add("frame/relations/components/frameX5/frame/letter/position/3");
		listOfProperties.add("frame/relations/components/frameX5/frame/letter/platonicLetter/3");
		listOfProperties.add("frame/relations/components/frameX5/frame/size/1");
		listOfProperties.add("frame/relations/components/frameX5/frame/position/4");
		listOfProperties.add("frame/relations/components/frameX5/frame/letter/position/4");
		listOfProperties.add("frame/relations/components/frameX5/frame/letter/platonicLetter/4");
		listOfProperties.add("frame/relations/components/frameX5/frame/size/1");
		listOfProperties.add("frame/relations/components/frameX5/frame/position/5");
		listOfProperties.add("frame/relations/components/frameX5/frame/letter/position/5");
		listOfProperties.add("frame/relations/components/frameX5/frame/letter/platonicLetter/5");
		List<String> listOfPredicateCodes = new ArrayList<String>();
		try {
			IRecipeCoder recipeCoder = new RecipeCoderImpl(listOfProperties);
			IRecipeCodeGetter recipeCodeGetter = recipeCoder.getRecipeCodeGetter();
			for (IInstructionCodeGetter instructionCodeGetter : recipeCodeGetter.getListOfInstructionCodeGetters()) {
				List<ITransformationCodeGetter> listOfTransfoCodeGetters = 
						instructionCodeGetter.getListOfTransformationCodeGetters();
				for (ITransformationCodeGetter transfoCodeGetter : listOfTransfoCodeGetters) {
					List<String> currentListOfPredicateCodes = transfoCodeGetter.getListOfPredicateCodes();
					listOfPredicateCodes.addAll(currentListOfPredicateCodes);
				}
			}
			/* for (String predicateCode : listOfPredicateCodes) {
				System.out.println(predicateCode);
				System.out.println(",");
			}	*/		
		}
		catch (Exception unexpected) {
			System.out.print(unexpected.getMessage());
		}		
		assertTrue(listOfPredicateCodes.size() == 3 &&
				listOfPredicateCodes.get(0).equals("WriteLetterEffector") &&
				listOfPredicateCodes.get(1).equals("manySizeEquals") &&
				listOfPredicateCodes.get(2).equals("manyLetterIncrease"));
	}

}
