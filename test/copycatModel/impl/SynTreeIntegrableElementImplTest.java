package copycatModel.impl;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import copycatModel.IProperty;
import copycatModel.IPropertyContainer;
import copycatModel.ISignal;
import copycatModel.grammar.Dimension;
import copycatModel.grammar.Enumeration;
import copycatModel.grammar.Group;
import copycatModel.grammar.GroupX3;
import copycatModel.grammar.HowManyGroups;
import copycatModel.grammar.Relation;
import exceptions.SynTreeGenerationException;
import settings.Settings;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.SignalBuilderImpl;

public class SynTreeIntegrableElementImplTest {

	@Test
	public void whenAllComponentsAreRelevantForRelationBuildingThenContainerContainsAllProperties() 
			throws SynTreeGenerationException {
		boolean containerContainsAllProperties = true;
		Dimension dimension = new Dimension(false, "dimension1");
		Enumeration enumeration = new Enumeration(false, "1");
		Relation relation  = new Relation(false, dimension, enumeration);
		List<String> listOfPropertiesWithPath = relation.getListOfPropertiesWithPath();
		IPropertyContainer propertyContainer = relation.getpropertyContainer();
		for (String propertyWithPath : listOfPropertiesWithPath) {
			int lastSlashIndex = propertyWithPath.lastIndexOf(Settings.PATH_SEPARATOR);
			String currentListDimension = propertyWithPath.substring(0, lastSlashIndex);
			String currentListValue = propertyWithPath.substring(lastSlashIndex + 1);
			boolean dimensionFound = false;
			int containerDimensionIndex = 0;
			while (dimensionFound == false && containerDimensionIndex < propertyContainer.getListOfIndexedPaths().size()) {
				String currentContainerDimension = propertyContainer.getListOfIndexedPaths().get(containerDimensionIndex);
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
	public void whenAllComponentsArentRelevantThenContainerContainsOnlyRelevantProperties() 
			throws Exception {
		boolean groupX3ContainerDoesntContainPlatonicLetter2 = true;
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<Group> listOfGroups = signal.getGroups();
		Group group1 = listOfGroups.get(0);
		Group group2 = listOfGroups.get(1);
		Group group3 = listOfGroups.get(2);
		HowManyGroups groupX3 = new GroupX3(false, group1, group2, group3);
		List<String> listOfGroupX3PropertiesWithPath = groupX3.getListOfPropertiesWithPath();
		boolean groupX3containsPlatonicLetter2 = false;
		for (String propertyWithPath : listOfGroupX3PropertiesWithPath) {
			if (propertyWithPath.contains("platonicLetter/2"))
				groupX3containsPlatonicLetter2 = true;
		}
		if (groupX3containsPlatonicLetter2 == true) {
			IPropertyContainer groupX3PropertyContainer = groupX3.getpropertyContainer();
			Map<String, IProperty> dimensionToProperty = 
					groupX3PropertyContainer.getIndexedPathToProperty();
			for (String dimension : dimensionToProperty.keySet()) {
				if (dimension.contains("platonicLetter")) {
					IProperty property = dimensionToProperty.get(dimension);
					if (property.getValue().equals("2"))
						groupX3ContainerDoesntContainPlatonicLetter2 = false;
				}
			}			
		}
		else throw new Exception();
		assertTrue(groupX3ContainerDoesntContainPlatonicLetter2);
	}
	
	@Test
	public void onlyClonedGroupsAreRelatedInNewDescriptor() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		boolean onlyClonedGroupsAreRelatedInNewDescriptor;
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<Group> listOfGroups = signal.getGroups();
		Group group1 = listOfGroups.get(0);
		Group group2 = listOfGroups.get(1);
		Group group3 = listOfGroups.get(2);
		List<String> listOfGroup2PropertiesWithpath = group2.getListOfPropertiesWithPath();
		HowManyGroups groupX3 = new GroupX3(false, group1, group2, group3);
		List<String> listOfGroupX3PropertiesWithPath = groupX3.getListOfPropertiesWithPath();
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
