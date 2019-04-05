package verbalization.verbalStructureModel.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import verbalization.dataEncoding.encodingModel.ITransformationCodeGetter;
import verbalization.dataEncoding.encodingModel.impl.TransformationCodeGetterImpl;
import verbalization.verbalStructureModel.ITransformation;
import verbalization.verbalStructureModel.impl.EffectorImpl;
import verbalization.verbalStructureModel.impl.EnumerationImpl;
import verbalization.verbalStructureModel.impl.SequenceImpl;

public class TransformationTest {

	@Test
	public void whenParameterIsAnExpectedCodeThenVerbalDescriptionIsGiven() {
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
				ITransformationCodeGetter transformationCodeGetter = new TransformationCodeGetterImpl(listOfPredicates, "1");
				ITransformation transformation;
				if (transfoCode.contains("Enumerate"))
					transformation = new EnumerationImpl(transformationCodeGetter);
				else if (transfoCode.contains("crease") || transfoCode.contains("Equals"))
					transformation = new SequenceImpl(transformationCodeGetter);
				else if (transfoCode.contains("Effector"))
					transformation = new EffectorImpl(transformationCodeGetter);
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
