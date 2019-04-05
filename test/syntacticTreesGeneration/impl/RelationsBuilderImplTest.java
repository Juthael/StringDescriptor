package syntacticTreesGeneration.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import copycatModel.ISignal;
import copycatModel.grammar.Group;
import copycatModel.grammar.Relations;
import exceptions.SynTreeGenerationException;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.IRelationsBuilder;
import syntacticTreesGeneration.ISequenceRelationalData;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.ISymmetryRelationalData;
import syntacticTreesGeneration.impl.EnumerationRelationalDataImpl;
import syntacticTreesGeneration.impl.RelationDataContainerImpl;
import syntacticTreesGeneration.impl.RelationsBuilderImpl;
import syntacticTreesGeneration.impl.SequenceRelationalDataImpl;
import syntacticTreesGeneration.impl.SignalBuilderImpl;
import syntacticTreesGeneration.impl.SymmetryRelationalDataImpl;

public class RelationsBuilderImplTest {


	private static String dimension1 = "dimension1";
	private static String dimension2 = "dimension2";
	private static String dimension3 = "dimension3";
	private static String dimension4 = "dimension4";
	private static String dimension5 = "dimension5";
	private static String dimension6 = "dimension6";
	private static String enumerationValue1 = "1";
	private static String enumerationValue2 = "2";
	private static String enumerationValue3 = "3";
	private static String enumerationValue4 = "4";
	private static String enumerationValue5 = "5";
	private static IEnumerationRelationalData enumerationRelationalData1;
	private static	IEnumerationRelationalData enumerationRelationalData2;
	private static IEnumerationRelationalData enumerationRelationalData3;
	private static	IEnumerationRelationalData enumerationRelationalData4;
	private static	IEnumerationRelationalData enumerationRelationalData5;	
	private static	ISequenceRelationalData sequenceRelationalData1;
	private static String commonDiff1 = "1";
	private static	ISymmetryRelationalData symmetryRelationalData1;
	private static IRelationDataContainer emptyContainer = new RelationDataContainerImpl();
	private static IRelationDataContainer otherContainer;
	private static List<Group> listOfGroups;
	
	@Before
	public void initialize() throws SynTreeGenerationException {
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		listOfGroups = signal.getGroups();
		enumerationRelationalData1 = new EnumerationRelationalDataImpl(dimension1, enumerationValue1);
		enumerationRelationalData2 = new EnumerationRelationalDataImpl(dimension2, enumerationValue2);
		enumerationRelationalData3 = new EnumerationRelationalDataImpl(dimension3, enumerationValue3);
		enumerationRelationalData4 = new EnumerationRelationalDataImpl(dimension4, enumerationValue4);
		enumerationRelationalData5 = new EnumerationRelationalDataImpl(dimension5, enumerationValue5);
		sequenceRelationalData1 = new SequenceRelationalDataImpl(dimension1, enumerationValue1, commonDiff1);
		symmetryRelationalData1 = new SymmetryRelationalDataImpl(dimension1, enumerationValue1, "withoutCentralElement");
	}
	
	@Test
	public void whenRDContainerIsEmptyThenThrowsException() throws CloneNotSupportedException {
		try {
			IRelationsBuilder relationsBuilder = new RelationsBuilderImpl(emptyContainer, listOfGroups);
			Relations relations = relationsBuilder.getRelations();
			fail();
		}
		catch (SynTreeGenerationException expected) {
		}
	}
	
	@Test
	public void whenRDContainerIsOverfilledThenThrowsException() throws CloneNotSupportedException {
		otherContainer = new RelationDataContainerImpl();
		otherContainer.addEnumeration(enumerationRelationalData1);
		otherContainer.addEnumeration(enumerationRelationalData2);
		otherContainer.addEnumeration(enumerationRelationalData3);
		otherContainer.addEnumeration(enumerationRelationalData4);
		otherContainer.addEnumeration(enumerationRelationalData5);
		otherContainer.addEnumeration(enumerationRelationalData5);
		otherContainer.addEnumeration(enumerationRelationalData5);
		otherContainer.addEnumeration(enumerationRelationalData5);
		otherContainer.addEnumeration(enumerationRelationalData3);
		otherContainer.addEnumeration(enumerationRelationalData4);
		otherContainer.addEnumeration(enumerationRelationalData5);
		otherContainer.addEnumeration(enumerationRelationalData5);
		otherContainer.addEnumeration(enumerationRelationalData5);
		otherContainer.addEnumeration(enumerationRelationalData5);		
		try {
			IRelationsBuilder relationsBuilder = new RelationsBuilderImpl(otherContainer, listOfGroups);
			Relations relations = relationsBuilder.getRelations();
			fail();
		}
		catch (SynTreeGenerationException expected) {
		}
	}
	
	@Test
	public void whenRDContainerContainsOnly1DimensionThenSimpleRelationIsBuilt() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		otherContainer = new RelationDataContainerImpl();
		otherContainer.addEnumeration(enumerationRelationalData1);
		IRelationsBuilder relationsBuilder = new RelationsBuilderImpl(otherContainer, listOfGroups);
		Relations relations = relationsBuilder.getRelations();
		List<String> relationsListOfPropertiesWithPath = relations.getListOfPropertiesWithPath();
		boolean simpleRelationIsBuilt = false;
		int propertyIndex = 0;
		while (simpleRelationIsBuilt == false && propertyIndex < relationsListOfPropertiesWithPath.size()) {
			if (relationsListOfPropertiesWithPath.get(propertyIndex).contains("relations/relation"))
				simpleRelationIsBuilt = true;
			propertyIndex++;
		}
		assertEquals(simpleRelationIsBuilt, true);
	}
	
	@Test
	public void whenRDCOntainerContainsEnumSequSymmForSameDimensionThenSequenceAndSymmetryRelIsBuilt() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		otherContainer = new RelationDataContainerImpl();
		otherContainer.addEnumeration(enumerationRelationalData1);
		otherContainer.addSequence(sequenceRelationalData1);
		otherContainer.addSymmetry(symmetryRelationalData1);
		IRelationsBuilder relationsBuilder = new RelationsBuilderImpl(otherContainer, listOfGroups);
		Relations relations = relationsBuilder.getRelations();
		List<String> relationsListOfPropertiesWithPath = relations.getListOfPropertiesWithPath();
		boolean enumerationIsBuilt = false;
		boolean sequenceIsBuilt = false;
		boolean symmetryIsBuilt = false;
		boolean sequenceAndSymmetryRelIsBuilt = false;
		for (String propertyWithPath : relationsListOfPropertiesWithPath) {
			if (propertyWithPath.contains("relation/enumeration"))
				enumerationIsBuilt = true;
			else if (propertyWithPath.contains("relation/sequence"))
					sequenceIsBuilt = true;
			else if (propertyWithPath.contains("relation/symmetry"))
				symmetryIsBuilt = true;
		}
		if (enumerationIsBuilt == true && sequenceIsBuilt == true && symmetryIsBuilt == true)
			sequenceAndSymmetryRelIsBuilt = true;
		assertEquals(sequenceAndSymmetryRelIsBuilt, true);		
	}
	
	@Test
	public void whenRDContainsRelationDataWith3DimensionsThen3RelationX3IsBuilt() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		otherContainer = new RelationDataContainerImpl();
		otherContainer.addEnumeration(enumerationRelationalData1);
		otherContainer.addEnumeration(enumerationRelationalData2);
		otherContainer.addEnumeration(enumerationRelationalData3);
		IRelationsBuilder relationsBuilder = new RelationsBuilderImpl(otherContainer, listOfGroups);
		Relations relations = relationsBuilder.getRelations();
		List<String> relationsListOfPropertiesWithPath = relations.getListOfPropertiesWithPath();
		boolean relationX3IsBuilt = false;
		int propertyIndex = 0;
		while (relationX3IsBuilt == false && propertyIndex < relationsListOfPropertiesWithPath.size()) {
			if (relationsListOfPropertiesWithPath.get(propertyIndex).contains("relations/relationX3"))
				relationX3IsBuilt = true;
			propertyIndex++;
		}
		assertEquals(relationX3IsBuilt, true);		
	}
	

}
