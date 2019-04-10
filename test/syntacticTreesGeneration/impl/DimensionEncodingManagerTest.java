package syntacticTreesGeneration.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import exceptions.SynTreeGenerationException;
import settings.Settings;

public class DimensionEncodingManagerTest {

	
	
	@Test 
	public void whenListOfPropertiesAndPathEnteredThenCompleteDimensionReturned(){
		List<List<String>> listOfPropertyLists = new ArrayList<List<String>>();
		List<String> list1OfPropertiesWithPath = new ArrayList<String>();
		List<String> list2OfPropertiesWithPath = new ArrayList<String>();
		List<String> list3OfPropertiesWithPath = new ArrayList<String>();
		
		list1OfPropertiesWithPath.add("size/2");
		list1OfPropertiesWithPath.add("position/1");
		list1OfPropertiesWithPath.add("relations/dimensionX2/dimension/size");
		list1OfPropertiesWithPath.add("relations/dimensionX2/dimension/letter.platonicLetter");
		list1OfPropertiesWithPath.add("relations/relationX2/relation/dimension/size");
		list1OfPropertiesWithPath.add("relations/relationX2/relation/enumeration/1,1");
		list1OfPropertiesWithPath.add("relations/relationX2/relation/sequence/commonDiff/0");
		list1OfPropertiesWithPath.add("relations/relationX2/relation/sequence/absCommonDiff/0");
		list1OfPropertiesWithPath.add("relations/relationX2/relation/dimension/letter.platonicLetter");
		list1OfPropertiesWithPath.add("relations/relationX2/relation/enumeration/1,2");
		list1OfPropertiesWithPath.add("relations/relationX2/relation/sequence/commonDiff/1");
		list1OfPropertiesWithPath.add("relations/relationX2/relation/sequence/absCommonDiff/1");
		list1OfPropertiesWithPath.add("relations/groups/size/2");
		list1OfPropertiesWithPath.add("relations/groups/groupX2/group/size/1");
		list1OfPropertiesWithPath.add("relations/groups/groupX2/group/position/1");
		list1OfPropertiesWithPath.add("relations/groups/groupX2/group/letter/position/1");
		list1OfPropertiesWithPath.add("relations/groups/groupX2/group/letter/platonicLetter/1");
		list1OfPropertiesWithPath.add("relations/groups/groupX2/group/size/1");
		list1OfPropertiesWithPath.add("relations/groups/groupX2/group/position/2");
		list1OfPropertiesWithPath.add("relations/groups/groupX2/group/letter/position/2");
		list1OfPropertiesWithPath.add("relations/groups/groupX2/group/letter/platonicLetter/2");

		list2OfPropertiesWithPath.add("size/2");
		list2OfPropertiesWithPath.add("position/2");
		list2OfPropertiesWithPath.add("relations/dimensionX2/dimension/size");
		list2OfPropertiesWithPath.add("relations/dimensionX2/dimension/letter.platonicLetter");
		list2OfPropertiesWithPath.add("relations/relationX2/relation/dimension/size");
		list2OfPropertiesWithPath.add("relations/relationX2/relation/enumeration/1,1");
		list2OfPropertiesWithPath.add("relations/relationX2/relation/sequence/commonDiff/0");
		list2OfPropertiesWithPath.add("relations/relationX2/relation/sequence/absCommonDiff/0");
		list2OfPropertiesWithPath.add("relations/relationX2/relation/dimension/letter.platonicLetter");
		list2OfPropertiesWithPath.add("relations/relationX2/relation/enumeration/3,3");
		list2OfPropertiesWithPath.add("relations/relationX2/relation/sequence/commonDiff/0");
		list2OfPropertiesWithPath.add("relations/relationX2/relation/sequence/absCommonDiff/0");
		list2OfPropertiesWithPath.add("relations/groups/size/2");
		list2OfPropertiesWithPath.add("relations/groups/groupX2/group/size/1");
		list2OfPropertiesWithPath.add("relations/groups/groupX2/group/position/1");
		list2OfPropertiesWithPath.add("relations/groups/groupX2/group/letter/position/3");
		list2OfPropertiesWithPath.add("relations/groups/groupX2/group/letter/platonicLetter/3");
		list2OfPropertiesWithPath.add("relations/groups/groupX2/group/size/1");
		list2OfPropertiesWithPath.add("relations/groups/groupX2/group/position/2");
		list2OfPropertiesWithPath.add("relations/groups/groupX2/group/letter/position/4");
		list2OfPropertiesWithPath.add("relations/groups/groupX2/group/letter/platonicLetter/3");

		list3OfPropertiesWithPath.add("size/2");
		list3OfPropertiesWithPath.add("position/3");
		list3OfPropertiesWithPath.add("relations/dimensionX2/dimension/size");
		list3OfPropertiesWithPath.add("relations/dimensionX2/dimension/letter.platonicLetter");
		list3OfPropertiesWithPath.add("relations/relationX2/relation/dimension/size");
		list3OfPropertiesWithPath.add("relations/relationX2/relation/enumeration/1,1");
		list3OfPropertiesWithPath.add("relations/relationX2/relation/sequence/commonDiff/0");
		list3OfPropertiesWithPath.add("relations/relationX2/relation/sequence/absCommonDiff/0");
		list3OfPropertiesWithPath.add("relations/relationX2/relation/dimension/letter.platonicLetter");
		list3OfPropertiesWithPath.add("relations/relationX2/relation/enumeration/2,1");
		list3OfPropertiesWithPath.add("relations/relationX2/relation/sequence/commonDiff/-1");
		list3OfPropertiesWithPath.add("relations/relationX2/relation/sequence/absCommonDiff/1");
		list3OfPropertiesWithPath.add("relations/groups/size/2");
		list3OfPropertiesWithPath.add("relations/groups/groupX2/group/size/1");
		list3OfPropertiesWithPath.add("relations/groups/groupX2/group/position/1");
		list3OfPropertiesWithPath.add("relations/groups/groupX2/group/letter/position/5");
		list3OfPropertiesWithPath.add("relations/groups/groupX2/group/letter/platonicLetter/2");
		list3OfPropertiesWithPath.add("relations/groups/groupX2/group/size/1");
		list3OfPropertiesWithPath.add("relations/groups/groupX2/group/position/2");
		list3OfPropertiesWithPath.add("relations/groups/groupX2/group/letter/position/6");
		list3OfPropertiesWithPath.add("relations/groups/groupX2/group/letter/platonicLetter/1");
		
		listOfPropertyLists.add(list1OfPropertiesWithPath);
		listOfPropertyLists.add(list2OfPropertiesWithPath);
		listOfPropertyLists.add(list3OfPropertiesWithPath);
		
		Set<Integer> listOfCheckedIndexes = new HashSet<Integer>();
		String possiblyIndexedPath = "7" + Settings.PROPERTY_INDEX_SEPARATOR + 
				"relations/relationX2/relation/sequence/commonDiff";
		
		List<String> listOfValues = new ArrayList<String>();
		listOfValues.add("1");
		listOfValues.add("0");
		listOfValues.add("-1");
		try {
			DimensionEncodingManager dimensionEncodingManager = new DimensionEncodingManager(listOfCheckedIndexes);
			String completeDimension = 
					dimensionEncodingManager.getDimension(listOfPropertyLists, possiblyIndexedPath, listOfValues);
			assertTrue(completeDimension.equals("relations.relation.sequence.commonDiff>letter.platonicLetter"));
		}
		catch (SynTreeGenerationException e) {
			System.out.print(e.getMessage());
		}
	}
	
	@Test 
	public void whenDimensionEnteredThenExpectedDimensionCodeReturned() {
		String code1 = "relations.relation.sequence.commonDiff";
		String dim1 = "relations/relation/sequence/commonDiff";
		String code2 = ":group.size";
		String dim2 = "relations/groups/group/size";
		String code3 = "relations.relation.sequence.commonDiff>letter.platonicLetter";
		String dim3 = "relations/relation/sequence/commonDiff>letter/platonicLetter";
		boolean anErrorWasFound = false;
		String dimensionCode1 = DimensionEncodingManager.getDimensionCodeFromIndexedPath(dim1);
		if (!dimensionCode1.equals(code1))
			anErrorWasFound = true;
		String dimensionCode2 = DimensionEncodingManager.getDimensionCodeFromIndexedPath(dim2);
		if (!dimensionCode2.equals(code2))
			anErrorWasFound = true;
		String dimensionCode3 = DimensionEncodingManager.getDimensionCodeFromIndexedPath(dim3);
		if (!dimensionCode3.equals(code3))
			anErrorWasFound = true;
		assertFalse(anErrorWasFound);	
	}
	
	/*@Test
	public void whenDimensionCodeEnteredThenExpectedDimensionReturned() {
		String code1 = "relations.relation.sequence.commonDiff";
		String dim1 = "relations/relation/sequence/commonDiff";
		String code2 = ":group.size";
		String dim2 = "relations/groups/group/size";
		String code3 = "relations.relation.sequence.commonDiff>letter.platonicLetter";
		String dim3 = "relations/relation/sequence/commonDiff>letter/platonicLetter";
		boolean anErrorWasFound = false;
		if (!DimensionEncodingManager.getDimensionFromDimensionCode(code1).equals(dim1))
			anErrorWasFound = true;
		else if (!DimensionEncodingManager.getDimensionFromDimensionCode(code2).equals(dim2))
			anErrorWasFound = true;
		else if (!DimensionEncodingManager.getDimensionFromDimensionCode(code3).equals(dim3))
			anErrorWasFound = true;
		assertFalse(anErrorWasFound);		
	}*/
	
	@Test
	public void whenDimensionEnteredThenExpectedDimensionTypeReturned() {
		String dimensionPart1 = "relations/relation/sequence/commonDiff";
		assertTrue(DimensionEncodingManager.getDimensionTypeFromPath(dimensionPart1).equals("commonDiff"));
	}
	
	@Test
	public void whenDimensionCodeEnteredThenExpectedDimensionTypeReturned() {
		String code1 = ":group.size";
		String code2 = ":group.size" + Settings.SECOND_DEG_DIMENSION_SEPARATOR + "platonicLetter" ;
		StringBuilder sB = new StringBuilder();
		sB.append(DimensionEncodingManager.getDimensionTypeFromCode(code1));
		sB.append(",");
		sB.append(DimensionEncodingManager.getDimensionTypeFromCode(code2));
		assertTrue(sB.toString().equals("size,size"));
	}
	
	@Test
	public void whenDimensionCodeEnteredThenExpectedDimensionDegreeReturned() {
		String code1 = "size";
		String code2 = ":group.size";
		String code3 = "::relation.sequence.commonDiff";
		String code4 = "::relation.sequence.commonDiff" + Settings.SECOND_DEG_DIMENSION_SEPARATOR + "size";
		StringBuilder sB = new StringBuilder();
		sB.append(DimensionEncodingManager.getDimensionDegree(code1));
		sB.append(",");
		sB.append(DimensionEncodingManager.getDimensionDegree(code2));
		sB.append(",");
		sB.append(DimensionEncodingManager.getDimensionDegree(code3));
		sB.append(",");
		sB.append(DimensionEncodingManager.getDimensionDegree(code4));
		assertTrue(sB.toString().equals("0,1,2,0"));
	}

}
