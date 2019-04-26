package model.synTreeModel.impl;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.CharString;
import model.copycatModel.synTreeGrammar.Dimension;
import model.copycatModel.synTreeGrammar.Enumeration;
import model.copycatModel.synTreeGrammar.Group;
import model.copycatModel.synTreeGrammar.GroupX;
import model.copycatModel.synTreeGrammar.HowManyGroups;
import model.copycatModel.synTreeGrammar.Relation;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.IProperty;
import model.synTreeModel.IPropertyContainer;
import model.synTreeModel.ISignal;
import settings.Settings;
import syntacticTreesGeneration.IListOfDescriptorsBuilder;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.ListOfDescriptorsBuilderImpl;
import syntacticTreesGeneration.impl.SignalBuilderImpl;

public class SynTreeElementImplTest {

	@Test
	public void whenAllComponentsAreRelevantForRelationBuildingThenContainerContainsAllProperties() 
			throws SynTreeGenerationException {
		boolean containerContainsAllProperties = true;
		Dimension dimension = new Dimension("dimension1");
		Enumeration enumeration = new Enumeration("1");
		Relation relation  = new Relation(dimension, enumeration);
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
		HowManyGroups groupX3 = new GroupX(listOfGroups);
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
		Group group2 = listOfGroups.get(1);
		List<String> listOfGroup2PropertiesWithpath = group2.getListOfPropertiesWithPath();
		HowManyGroups groupX3 = new GroupX(listOfGroups);
		List<String> listOfGroupX3PropertiesWithPath = groupX3.getListOfPropertiesWithPath();
		boolean groupX3ContainsGroupPosition2 = false;
		boolean group2ContainsGroupPosition2 = false;
		for (String group2PropertyWithPath : listOfGroup2PropertiesWithpath) {
			if (group2PropertyWithPath.contains("group/prominentPosition/position/2"))
				group2ContainsGroupPosition2 = true;
		}		
		for (String groupX3PropertyWithPath : listOfGroupX3PropertiesWithPath) {
			if (groupX3PropertyWithPath.contains("group/prominentPosition/position/2"))
				groupX3ContainsGroupPosition2 = true;
		}		
		onlyClonedGroupsAreRelatedInNewDescriptor = 
				(group2ContainsGroupPosition2 == false && 
				groupX3ContainsGroupPosition2 == true);
		assertEquals(onlyClonedGroupsAreRelatedInNewDescriptor, true);
	}
	
	@Test
	public void whenSynTreeElementsAreUpgradedThenOrderedSetElementsAreReturned() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		IListOfDescriptorsBuilder listOfDescriptorsBuilder = 
				new ListOfDescriptorsBuilderImpl("abcde", "fromLeftToRight");
		List<CharString> listOfDescriptors = listOfDescriptorsBuilder.getListOfStringDescriptors();
		Set<List<String>> setOfAccessibleListOfProperties = new HashSet<List<String>>();
		for (CharString charString : listOfDescriptors) {
			setOfAccessibleListOfProperties.addAll(charString.getSetOfAllPropertyListsAccessibleFromThisDescriptor());
		}
		Map<List<String>, Integer> listOfPropertiesToIndex = new HashMap<List<String>, Integer>();
		int listOfPropertiesIndex = 0;
		for (List<String> listOfProperties : setOfAccessibleListOfProperties) {
			listOfPropertiesToIndex.put(listOfProperties, listOfPropertiesIndex);
			listOfPropertiesIndex++;
		}
		List<IOrderedSet> listOfOrderedSetElements = new ArrayList<IOrderedSet>();
		try {
			for (CharString descriptor : listOfDescriptors)
				listOfOrderedSetElements.add(descriptor.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail();
		}
		/* for (ILowerSetElement lowerSetElement : listOfOrderedSetElements) {
			List<String> listOfMaximalChains = lowerSetElement.getListOfLowerSetMaximalChains();
			for (String chain : listOfMaximalChains) {
				System.out.println(chain);
			}
			System.out.println("");
		} */
		assertTrue(listOfDescriptors.size() == listOfOrderedSetElements.size());
	}

}
