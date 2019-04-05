package syntacticTreesGeneration.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


import copycatModel.grammar.Relation;
import exceptions.SynTreeGenerationException;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationalData;
import syntacticTreesGeneration.ISequenceRelationalData;
import syntacticTreesGeneration.ISymmetryRelationalData;
import syntacticTreesGeneration.impl.EnumerationRelationalDataImpl;
import syntacticTreesGeneration.impl.RelationBuilderImpl;
import syntacticTreesGeneration.impl.SequenceRelationalDataImpl;
import syntacticTreesGeneration.impl.SymmetryRelationalDataImpl;

public class RelationBuilderImplTest {

	private IEnumerationRelationalData enumerationRelationalData1;
	private	IEnumerationRelationalData enumerationRelationalData2;
	private	ISequenceRelationalData sequenceRelationalData1;
	private	ISequenceRelationalData sequenceRelationalData2;
	private	ISymmetryRelationalData symmetryRelationalData;

	@Before
	public void buildListsOfRelationalDataInterfaces() throws SynTreeGenerationException {
		String dimension1 = "group/letter/platonicLetter";
		String dimension2 = "group/relations/relation/enumeration";
		String enumerationValue1 = "1,2,3";
		String enumerationValue2 = "4,5";
		String commonDiff = "1";
		String symmetryType = "withoutCentralElement";
		enumerationRelationalData1 = new EnumerationRelationalDataImpl(dimension1, enumerationValue1);
		enumerationRelationalData2 = new EnumerationRelationalDataImpl(dimension2, enumerationValue2);
		sequenceRelationalData1 = new SequenceRelationalDataImpl(dimension1, enumerationValue1, commonDiff);
		sequenceRelationalData2 = new SequenceRelationalDataImpl(dimension2, enumerationValue2, commonDiff);
		symmetryRelationalData = new SymmetryRelationalDataImpl(dimension2, enumerationValue2, symmetryType);
	}
	
	@Test
	public void WhenEmptyListAsParameterThenThrowsException() {
		List<IRelationalData> emptyList = new ArrayList<IRelationalData>();
		
		try {
			Relation relation = RelationBuilderImpl.buildRelation(emptyList);
			fail();
		}
		catch (Exception expected) {
		}
	}
	
	@Test
	public void WhenListWithMoreThan3ElementsThenThrowsException() {
		List<IRelationalData> tooBigList = new ArrayList<IRelationalData>();
		tooBigList.add(enumerationRelationalData1);
		tooBigList.add(sequenceRelationalData1);
		tooBigList.add(sequenceRelationalData2);
		tooBigList.add(symmetryRelationalData);
		
		try {
			Relation relation = RelationBuilderImpl.buildRelation(tooBigList);
			fail();
		}
		catch (Exception expected) {
		}
	}
	
	@Test
	public void WhenListWithNoEnumerationRelationalDataAsParameterThenThrowsException() {
		List<IRelationalData> listWithNoEnumeration = new ArrayList<IRelationalData>();
		listWithNoEnumeration.add(sequenceRelationalData1);
		listWithNoEnumeration.add(sequenceRelationalData2);
		
		try {
			Relation relation = RelationBuilderImpl.buildRelation(listWithNoEnumeration);
			fail();
		}
		catch (Exception expected) {
		}		
	}
	
	@Test
	public void WhenListWithManyEnumerationRelationalDataAsParameterThenThrowsException() {
		List<IRelationalData> listWithManyEnumeration = new ArrayList<IRelationalData>();
		listWithManyEnumeration.add(enumerationRelationalData1);
		listWithManyEnumeration.add(enumerationRelationalData2);
		
		try {
			Relation relation = RelationBuilderImpl.buildRelation(listWithManyEnumeration);
			fail();
		}
		catch (Exception expected) {
		}	
	}
	
	@Test
	public void WhenParameterContainsEnumerationThenRelationIsBuilt() 
			throws SynTreeGenerationException {
		List<IRelationalData> conformList = new ArrayList<IRelationalData>();
		conformList.add(enumerationRelationalData1);
		Relation relation = RelationBuilderImpl.buildRelation(conformList);
		String className = relation.getClass().getName();
		String simpleClassName = className.substring(className.lastIndexOf(".") + 1);
		assertEquals(simpleClassName, "Relation");		
	}	
	
	@Test
	public void WhenParameterContainsEnumerationSequenceThenSequenceRelIsBuilt() 
			throws SynTreeGenerationException {
		List<IRelationalData> conformList = new ArrayList<IRelationalData>();
		conformList.add(enumerationRelationalData1);
		conformList.add(sequenceRelationalData1);
		Relation relation = RelationBuilderImpl.buildRelation(conformList);
		String className = relation.getClass().getName();
		String simpleClassName = className.substring(className.lastIndexOf(".") + 1);
		assertEquals(simpleClassName, "SequenceRel");		
	}	
	
	@Test
	public void WhenParameterContainsEnumerationSequenceSymmetryThenSequenceAndSymmetryRelIsBuilt() 
			throws SynTreeGenerationException {
		List<IRelationalData> conformList = new ArrayList<IRelationalData>();
		conformList.add(enumerationRelationalData1);
		conformList.add(sequenceRelationalData1);
		conformList.add(symmetryRelationalData);
		Relation relation = RelationBuilderImpl.buildRelation(conformList);
		String className = relation.getClass().getName();
		String simpleClassName = className.substring(className.lastIndexOf(".") + 1);
		assertEquals(simpleClassName, "SequenceAndSymmetryRel");		
	}

}
