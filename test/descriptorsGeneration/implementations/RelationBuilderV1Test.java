package descriptorsGeneration.implementations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


import copycatModel.grammar.Relation;
import exceptions.DescriptorsBuilderCriticalException;
import syntacticTreesGeneration.implementations.EnumerationRelationalDataV1;
import syntacticTreesGeneration.implementations.RelationBuilderV1;
import syntacticTreesGeneration.implementations.SequenceRelationalDataV1;
import syntacticTreesGeneration.implementations.SymmetryRelationalDataV1;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.RelationalDataInterface;
import syntacticTreesGeneration.interfaces.SequenceRelationalDataInterface;
import syntacticTreesGeneration.interfaces.SymmetryRelationalDataInterface;

class RelationBuilderV1Test {

	private EnumerationRelationalDataInterface enumerationRelationalData1;
	private	EnumerationRelationalDataInterface enumerationRelationalData2;
	private	SequenceRelationalDataInterface sequenceRelationalData1;
	private	SequenceRelationalDataInterface sequenceRelationalData2;
	private	SymmetryRelationalDataInterface symmetryRelationalData;

	@Before
	public void buildListsOfRelationalDataInterfaces() throws DescriptorsBuilderCriticalException {
		String dimension1 = "group/letter/platonicLetter";
		String dimension2 = "group/relations/relation/enumeration";
		String enumerationValue1 = "1,2,3";
		String enumerationValue2 = "4,5";
		String commonDiff = "1";
		String symmetryType = "withoutCentralElement";
		enumerationRelationalData1 = new EnumerationRelationalDataV1(dimension1, enumerationValue1);
		enumerationRelationalData2 = new EnumerationRelationalDataV1(dimension2, enumerationValue2);
		sequenceRelationalData1 = new SequenceRelationalDataV1(dimension1, enumerationValue1, commonDiff);
		sequenceRelationalData2 = new SequenceRelationalDataV1(dimension2, enumerationValue2, commonDiff);
		symmetryRelationalData = new SymmetryRelationalDataV1(dimension2, enumerationValue2, symmetryType);
	}
	
	@Test
	void WhenEmptyListAsParameterThenThrowsException() {
		ArrayList<RelationalDataInterface> emptyList = new ArrayList<RelationalDataInterface>();
		
		try {
			Relation relation = RelationBuilderV1.buildRelation(emptyList);
			fail();
		}
		catch (Exception expected) {
		}
	}
	
	@Test
	void WhenListWithMoreThan3ElementsThenThrowsException() {
		ArrayList<RelationalDataInterface> tooBigList = new ArrayList<RelationalDataInterface>();
		tooBigList.add(enumerationRelationalData1);
		tooBigList.add(sequenceRelationalData1);
		tooBigList.add(sequenceRelationalData2);
		tooBigList.add(symmetryRelationalData);
		
		try {
			Relation relation = RelationBuilderV1.buildRelation(tooBigList);
			fail();
		}
		catch (Exception expected) {
		}
	}
	
	@Test
	void WhenListWithNoEnumerationRelationalDataAsParameterThenThrowsException() {
		ArrayList<RelationalDataInterface> listWithNoEnumeration = new ArrayList<RelationalDataInterface>();
		listWithNoEnumeration.add(sequenceRelationalData1);
		listWithNoEnumeration.add(sequenceRelationalData2);
		
		try {
			Relation relation = RelationBuilderV1.buildRelation(listWithNoEnumeration);
			fail();
		}
		catch (Exception expected) {
		}		
	}
	
	@Test
	void WhenListWithManyEnumerationRelationalDataAsParameterThenThrowsException() {
		ArrayList<RelationalDataInterface> listWithManyEnumeration = new ArrayList<RelationalDataInterface>();
		listWithManyEnumeration.add(enumerationRelationalData1);
		listWithManyEnumeration.add(enumerationRelationalData2);
		
		try {
			Relation relation = RelationBuilderV1.buildRelation(listWithManyEnumeration);
			fail();
		}
		catch (Exception expected) {
		}	
	}
	
	@Test
	void WhenParameterContainsEnumerationThenRelationIsBuilt() 
			throws DescriptorsBuilderCriticalException {
		ArrayList<RelationalDataInterface> conformList = new ArrayList<RelationalDataInterface>();
		conformList.add(enumerationRelationalData1);
		Relation relation = RelationBuilderV1.buildRelation(conformList);
		String className = relation.getClass().getName();
		String simpleClassName = className.substring(className.lastIndexOf(".") + 1);
		assertEquals(simpleClassName, "Relation");		
	}	
	
	@Test
	void WhenParameterContainsEnumerationSequenceThenSequenceRelIsBuilt() 
			throws DescriptorsBuilderCriticalException {
		ArrayList<RelationalDataInterface> conformList = new ArrayList<RelationalDataInterface>();
		conformList.add(enumerationRelationalData1);
		conformList.add(sequenceRelationalData1);
		Relation relation = RelationBuilderV1.buildRelation(conformList);
		String className = relation.getClass().getName();
		String simpleClassName = className.substring(className.lastIndexOf(".") + 1);
		assertEquals(simpleClassName, "SequenceRel");		
	}	
	
	@Test
	void WhenParameterContainsEnumerationSequenceSymmetryThenSequenceAndSymmetryRelIsBuilt() 
			throws DescriptorsBuilderCriticalException {
		ArrayList<RelationalDataInterface> conformList = new ArrayList<RelationalDataInterface>();
		conformList.add(enumerationRelationalData1);
		conformList.add(sequenceRelationalData1);
		conformList.add(symmetryRelationalData);
		Relation relation = RelationBuilderV1.buildRelation(conformList);
		String className = relation.getClass().getName();
		String simpleClassName = className.substring(className.lastIndexOf(".") + 1);
		assertEquals(simpleClassName, "SequenceAndSymmetryRel");		
	}

}
