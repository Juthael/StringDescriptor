package descriptorsGeneration.implementations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import copycatModel.grammar.Group;
import copycatModel.grammar.Relations;
import copycatModel.interfaces.SignalInterface;
import exceptions.DescriptorsBuilderCriticalException;
import syntacticTreesGeneration.implementations.EnumerationRelationalDataV1;
import syntacticTreesGeneration.implementations.RelationDataContainerV1;
import syntacticTreesGeneration.implementations.RelationsBuilderV1;
import syntacticTreesGeneration.implementations.SequenceRelationalDataV1;
import syntacticTreesGeneration.implementations.SignalBuilderV1;
import syntacticTreesGeneration.implementations.SymmetryRelationalDataV1;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.RelationDataContainerInterface;
import syntacticTreesGeneration.interfaces.RelationsBuilderInterface;
import syntacticTreesGeneration.interfaces.SequenceRelationalDataInterface;
import syntacticTreesGeneration.interfaces.SignalBuilderInterface;
import syntacticTreesGeneration.interfaces.SymmetryRelationalDataInterface;

class RelationsBuilderV1Test {


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
	private static EnumerationRelationalDataInterface enumerationRelationalData1;
	private static	EnumerationRelationalDataInterface enumerationRelationalData2;
	private static EnumerationRelationalDataInterface enumerationRelationalData3;
	private static	EnumerationRelationalDataInterface enumerationRelationalData4;
	private static	EnumerationRelationalDataInterface enumerationRelationalData5;	
	private static	SequenceRelationalDataInterface sequenceRelationalData1;
	private static String commonDiff1 = "1";
	private static	SymmetryRelationalDataInterface symmetryRelationalData1;
	private static RelationDataContainerInterface emptyContainer = new RelationDataContainerV1();
	private static RelationDataContainerInterface otherContainer;
	private static ArrayList<Group> listOfGroups;
	
	@BeforeAll
	static void initialize() throws DescriptorsBuilderCriticalException {
		SignalBuilderInterface signalBuilder = new SignalBuilderV1("abc", "fromLeftToRight");
		SignalInterface signal = signalBuilder.getSignal();
		listOfGroups = signal.getGroups();
		enumerationRelationalData1 = new EnumerationRelationalDataV1(dimension1, enumerationValue1);
		enumerationRelationalData2 = new EnumerationRelationalDataV1(dimension2, enumerationValue2);
		enumerationRelationalData3 = new EnumerationRelationalDataV1(dimension3, enumerationValue3);
		enumerationRelationalData4 = new EnumerationRelationalDataV1(dimension4, enumerationValue4);
		enumerationRelationalData5 = new EnumerationRelationalDataV1(dimension5, enumerationValue5);
		sequenceRelationalData1 = new SequenceRelationalDataV1(dimension1, enumerationValue1, commonDiff1);
		symmetryRelationalData1 = new SymmetryRelationalDataV1(dimension1, enumerationValue1, "withoutCentralElement");
	}
	
	@Test
	void whenRDContainerIsEmptyThenThrowsException() throws CloneNotSupportedException {
		try {
			RelationsBuilderInterface relationsBuilder = new RelationsBuilderV1(emptyContainer, listOfGroups);
			Relations relations = relationsBuilder.getRelations();
			fail();
		}
		catch (DescriptorsBuilderCriticalException expected) {
		}
	}
	
	@Test
	void whenRDContainerIsOverfilledThenThrowsException() throws CloneNotSupportedException {
		otherContainer = new RelationDataContainerV1();
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
			RelationsBuilderInterface relationsBuilder = new RelationsBuilderV1(otherContainer, listOfGroups);
			Relations relations = relationsBuilder.getRelations();
			fail();
		}
		catch (DescriptorsBuilderCriticalException expected) {
		}
	}
	
	@Test
	void whenRDContainerContainsOnly1DimensionThenSimpleRelationIsBuilt() 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		otherContainer = new RelationDataContainerV1();
		otherContainer.addEnumeration(enumerationRelationalData1);
		RelationsBuilderInterface relationsBuilder = new RelationsBuilderV1(otherContainer, listOfGroups);
		Relations relations = relationsBuilder.getRelations();
		ArrayList<String> relationsListOfPropertiesWithPath = relations.getListOfPropertiesWithPath();
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
	void whenRDCOntainerContainsEnumSequSymmForSameDimensionThenSequenceAndSymmetryRelIsBuilt() 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		otherContainer = new RelationDataContainerV1();
		otherContainer.addEnumeration(enumerationRelationalData1);
		otherContainer.addSequence(sequenceRelationalData1);
		otherContainer.addSymmetry(symmetryRelationalData1);
		RelationsBuilderInterface relationsBuilder = new RelationsBuilderV1(otherContainer, listOfGroups);
		Relations relations = relationsBuilder.getRelations();
		ArrayList<String> relationsListOfPropertiesWithPath = relations.getListOfPropertiesWithPath();
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
	void whenRDContainsRelationDataWith3DimensionsThen3RelationX3IsBuilt() 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		otherContainer = new RelationDataContainerV1();
		otherContainer.addEnumeration(enumerationRelationalData1);
		otherContainer.addEnumeration(enumerationRelationalData2);
		otherContainer.addEnumeration(enumerationRelationalData3);
		RelationsBuilderInterface relationsBuilder = new RelationsBuilderV1(otherContainer, listOfGroups);
		Relations relations = relationsBuilder.getRelations();
		ArrayList<String> relationsListOfPropertiesWithPath = relations.getListOfPropertiesWithPath();
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
