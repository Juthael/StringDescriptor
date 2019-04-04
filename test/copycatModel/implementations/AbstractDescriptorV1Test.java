package copycatModel.implementations;



import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import copycatModel.grammar.Dimension;
import copycatModel.grammar.Enumeration;
import copycatModel.grammar.Group;
import copycatModel.grammar.GroupX3;
import copycatModel.grammar.HowManyGroups;
import copycatModel.grammar.Relation;
import copycatModel.interfaces.PropertyContainerInterface;
import copycatModel.interfaces.PropertyInterface;
import copycatModel.interfaces.SignalInterface;
import exceptions.DescriptorsBuilderCriticalException;
import syntacticTreesGeneration.implementations.SignalBuilderV1;
import syntacticTreesGeneration.interfaces.SignalBuilderInterface;

class AbstractDescriptorV1Test {

	@Test
	void whenAllComponentsAreRelevantForRelationBuildingThenContainerContainsAllProperties() 
			throws DescriptorsBuilderCriticalException {
		boolean containerContainsAllProperties = true;
		Dimension dimension = new Dimension(false, "dimension1");
		Enumeration enumeration = new Enumeration(false, "1");
		Relation relation  = new Relation(false, dimension, enumeration);
		ArrayList<String> listOfPropertiesWithPath = relation.getListOfPropertiesWithPath();
		PropertyContainerInterface propertyContainer = relation.getpropertyContainer();
		for (String propertyWithPath : listOfPropertiesWithPath) {
			int lastSlashIndex = propertyWithPath.lastIndexOf("/");
			String currentListDimension = propertyWithPath.substring(0, lastSlashIndex);
			String currentListValue = propertyWithPath.substring(lastSlashIndex + 1);
			boolean dimensionFound = false;
			int containerDimensionIndex = 0;
			while (dimensionFound == false && containerDimensionIndex < propertyContainer.getListOfDimensions().size()) {
				String currentContainerDimension = propertyContainer.getListOfDimensions().get(containerDimensionIndex);
				if (currentContainerDimension.contains(currentListDimension)) {
					dimensionFound = true;
					if (!currentListValue.equals(propertyContainer.getProperty(currentContainerDimension).getValue()))
						containerContainsAllProperties = false;
				}
				containerDimensionIndex++;
			}
			if (dimensionFound == false)
				containerContainsAllProperties = false;		
		}
		assertTrue(containerContainsAllProperties);
	}
	
	@Test
	void whenAllComponentsArentRelevantThenContainerContainsOnlyRelevantProperties() 
			throws Exception {
		boolean groupX3ContainerDoesntContainPlatonicLetter2 = true;
		SignalBuilderInterface signalBuilder = new SignalBuilderV1("abc", "fromLeftToRight");
		SignalInterface signal = signalBuilder.getSignal();
		ArrayList<Group> listOfGroups = signal.getGroups();
		Group group1 = listOfGroups.get(0);
		Group group2 = listOfGroups.get(1);
		Group group3 = listOfGroups.get(2);
		HowManyGroups groupX3 = new GroupX3(false, group1, group2, group3);
		ArrayList<String> listOfGroupX3PropertiesWithPath = groupX3.getListOfPropertiesWithPath();
		boolean groupX3containsPlatonicLetter2 = false;
		for (String propertyWithPath : listOfGroupX3PropertiesWithPath) {
			if (propertyWithPath.contains("platonicLetter/2"))
				groupX3containsPlatonicLetter2 = true;
		}
		if (groupX3containsPlatonicLetter2 == true) {
			PropertyContainerInterface groupX3PropertyContainer = groupX3.getpropertyContainer();
			HashMap<String, PropertyInterface> dimensionToProperty = 
					groupX3PropertyContainer.getDimensionToProperty();
			for (String dimension : dimensionToProperty.keySet()) {
				if (dimension.contains("platonicLetter")) {
					PropertyInterface property = dimensionToProperty.get(dimension);
					if (property.getValue().equals("2"))
						groupX3ContainerDoesntContainPlatonicLetter2 = false;
				}
			}			
		}
		else throw new Exception();
		assertTrue(groupX3ContainerDoesntContainPlatonicLetter2);
	}
	
	@Test
	void onlyClonedGroupsAreRelatedInNewDescriptor() 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		boolean onlyClonedGroupsAreRelatedInNewDescriptor;
		SignalBuilderInterface signalBuilder = new SignalBuilderV1("abc", "fromLeftToRight");
		SignalInterface signal = signalBuilder.getSignal();
		ArrayList<Group> listOfGroups = signal.getGroups();
		Group group1 = listOfGroups.get(0);
		Group group2 = listOfGroups.get(1);
		Group group3 = listOfGroups.get(2);
		ArrayList<String> listOfGroup2PropertiesWithpath = group2.getListOfPropertiesWithPath();
		HowManyGroups groupX3 = new GroupX3(false, group1, group2, group3);
		ArrayList<String> listOfGroupX3PropertiesWithPath = groupX3.getListOfPropertiesWithPath();
		boolean groupX3ContainsGroupPosition2 = false;
		boolean group2ContainsGroupPosition2 = false;
		for (String group2PropertyWithPath : listOfGroup2PropertiesWithpath) {
			if (group2PropertyWithPath.contains("group/position/2"))
				group2ContainsGroupPosition2 = true;
		}		
		for (String groupX3PropertyWithPath : listOfGroupX3PropertiesWithPath) {
			if (groupX3PropertyWithPath.contains("group/position/2"))
				groupX3ContainsGroupPosition2 = true;
		}		
		onlyClonedGroupsAreRelatedInNewDescriptor = 
				(group2ContainsGroupPosition2 == false && 
				groupX3ContainsGroupPosition2 == true);
		assertEquals(onlyClonedGroupsAreRelatedInNewDescriptor, true);
	}

}
