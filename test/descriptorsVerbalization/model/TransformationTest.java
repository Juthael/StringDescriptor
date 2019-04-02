package descriptorsVerbalization.model;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import verbalization.implementations.dataEncodingModel.TransformationCodeGetterV1;
import verbalization.implementations.verbalStructureModel.EffectorV2;
import verbalization.implementations.verbalStructureModel.EnumerationV2;
import verbalization.implementations.verbalStructureModel.SequenceV2;
import verbalization.interfaces.dataEncodingModel.TransformationCodeGetterInterface;
import verbalization.interfaces.verbalStructureModel.TransformationInterface;

class TransformationTest {

	@Test
	void whenParameterIsAnExpectedCodeThenVerbalDescriptionIsGiven() {
		List<String> listOfCodes = new ArrayList<String>();
		listOfCodes.add("oneLetterDecrease");
		listOfCodes.add("oneLetterEquals");
		listOfCodes.add("oneLetterIncrease");
		listOfCodes.add("oneLetterEnumerate");
		listOfCodes.add("twoLetterDecrease");
		listOfCodes.add("twoSizeDecrease");
		listOfCodes.add("twoIncrementDecrease");
		listOfCodes.add("twoSubLetterDecrease");
		listOfCodes.add("twoSubSizeDecrease");
		listOfCodes.add("twoSubIncrementDecrease");
		listOfCodes.add("twoSubSubLetterDecrease");
		listOfCodes.add("twoSubSubSizeDecrease");
		listOfCodes.add("twoSubSubIncrementDecrease");
		listOfCodes.add("twoLetterEquals");
		listOfCodes.add("twoSizeEquals");
		listOfCodes.add("twoIncrementEquals");
		listOfCodes.add("twoSubLetterEquals");
		listOfCodes.add("twoSubSizeEquals");
		listOfCodes.add("twoSubIncrementEquals");
		listOfCodes.add("twoSubSubLetterEquals");
		listOfCodes.add("twoSubSubSizeEquals");
		listOfCodes.add("twoSubSubIncrementEquals");
		listOfCodes.add("twoLetterIncrease");
		listOfCodes.add("twoSizeIncrease");
		listOfCodes.add("twoIncrementIncrease");
		listOfCodes.add("twoSubLetterIncrease");
		listOfCodes.add("twoSubSizeIncrease");
		listOfCodes.add("twoSubIncrementIncrease");
		listOfCodes.add("twoSubSubLetterIncrease");
		listOfCodes.add("twoSubSubSizeIncrease");
		listOfCodes.add("twoSubSubIncrementIncrease");
		listOfCodes.add("twoLetterEnumerate");
		listOfCodes.add("twoSizeEnumerate");
		listOfCodes.add("twoIncrementEnumerate");
		listOfCodes.add("twoSubLetterEnumerate");
		listOfCodes.add("twoSubSizeEnumerate");
		listOfCodes.add("twoSubIncrementEnumerate");
		listOfCodes.add("twoSubSubLetterEnumerate");
		listOfCodes.add("twoSubSubSizeEnumerate");
		listOfCodes.add("twoSubSubIncrementEnumerate");
		listOfCodes.add("manyLetterDecrease");
		listOfCodes.add("manySizeDecrease");
		listOfCodes.add("manyIncrementDecrease");
		listOfCodes.add("manySubLetterDecrease");
		listOfCodes.add("manySubSizeDecrease");
		listOfCodes.add("manySubIncrementDecrease");
		listOfCodes.add("manySubSubLetterDecrease");
		listOfCodes.add("manySubSubSizeDecrease");
		listOfCodes.add("manySubSubIncrementDecrease");
		listOfCodes.add("manyLetterEquals");
		listOfCodes.add("manySizeEquals");
		listOfCodes.add("manyIncrementEquals");
		listOfCodes.add("manySubLetterEquals");
		listOfCodes.add("manySubSizeEquals");
		listOfCodes.add("manySubIncrementEquals");
		listOfCodes.add("manySubSubLetterEquals");
		listOfCodes.add("manySubSubSizeEquals");
		listOfCodes.add("manySubSubIncrementEquals");
		listOfCodes.add("manyLetterIncrease");
		listOfCodes.add("manySizeIncrease");
		listOfCodes.add("manyIncrementIncrease");
		listOfCodes.add("manySubLetterIncrease");
		listOfCodes.add("manySubSizeIncrease");
		listOfCodes.add("manySubIncrementIncrease");
		listOfCodes.add("manySubSubLetterIncrease");
		listOfCodes.add("manySubSubSizeIncrease");
		listOfCodes.add("manySubSubIncrementIncrease");
		listOfCodes.add("manyLetterEnumerate");
		listOfCodes.add("manySizeEnumerate");
		listOfCodes.add("manyIncrementEnumerate");
		listOfCodes.add("manySubLetterEnumerate");
		listOfCodes.add("manySubSizeEnumerate");
		listOfCodes.add("manySubIncrementEnumerate");
		listOfCodes.add("manySubSubLetterEnumerate");
		listOfCodes.add("manySubSubSizeEnumerate");
		listOfCodes.add("manySubSubIncrementEnumerate");
		listOfCodes.add("WriteLetterEffector");
		try {
			for (String transfoCode : listOfCodes) {
				List<String> listOfPredicates = new ArrayList<String>();
				listOfPredicates.add(transfoCode);
				TransformationCodeGetterInterface transformationCodeGetter = new TransformationCodeGetterV1(listOfPredicates, "1");
				TransformationInterface transformation;
				if (transfoCode.contains("Enumerate"))
					transformation = new EnumerationV2(transformationCodeGetter);
				else if (transfoCode.contains("crease") || transfoCode.contains("Equals"))
					transformation = new SequenceV2(transformationCodeGetter);
				else if (transfoCode.contains("Effector"))
					transformation = new EffectorV2(transformationCodeGetter);
				else throw new Exception("transfoCode type wasn't recognized");
				// System.out.println(transformation.getVerbalTransformation());
			}
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			fail();
		}
	}

}
