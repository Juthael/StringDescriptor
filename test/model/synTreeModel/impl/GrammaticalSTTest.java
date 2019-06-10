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

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;
import model.copycatModel.synTreeGrammar.CharString;
import model.copycatModel.synTreeGrammar.Dimension;
import model.copycatModel.synTreeGrammar.Enumeration;
import model.copycatModel.synTreeGrammar.Frame;
import model.copycatModel.synTreeGrammar.FrameX;
import model.copycatModel.synTreeGrammar.IOneOrManyFrames;
import model.copycatModel.synTreeGrammar.Relation;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.IFrame;
import model.synTreeModel.ISignal;
import model.synTreeModel.impl.utils.IProperty;
import model.synTreeModel.impl.utils.IPropertyContainer;
import settings.Settings;
import syntacticTreesGeneration.IListOfDescriptorsBuilder;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.ListOfDescriptorsBuilderImpl;
import syntacticTreesGeneration.impl.SignalBuilderImpl;

public class GrammaticalSTTest {

	@Test
	public void whenAllComponentsAreRelevantForRelationBuildingThenContainerContainsAllProperties() 
			throws SynTreeGenerationException, CloneNotSupportedException {
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
		boolean frameX3ContainerDoesntContainPlatonicLetter2 = true;
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<IFrame> listOfFrames = signal.getFrames();
		IOneOrManyFrames frameX3 = new FrameX(listOfFrames);
		List<String> listOfFrameX3PropertiesWithPath = frameX3.getListOfPropertiesWithPath();
		boolean frameX3containsPlatonicLetter2 = false;
		for (String propertyWithPath : listOfFrameX3PropertiesWithPath) {
			if (propertyWithPath.contains("platonicLetter/2"))
				frameX3containsPlatonicLetter2 = true;
		}
		if (frameX3containsPlatonicLetter2 == true) {
			IPropertyContainer frameX3PropertyContainer = frameX3.getpropertyContainer();
			Map<String, IProperty> dimensionToProperty = 
					frameX3PropertyContainer.getIndexedPathToProperty();
			for (String dimension : dimensionToProperty.keySet()) {
				if (dimension.contains("platonicLetter")) {
					IProperty property = dimensionToProperty.get(dimension);
					if (property.getValue().equals("2"))
						frameX3ContainerDoesntContainPlatonicLetter2 = false;
				}
			}			
		}
		else throw new Exception();
		assertTrue(frameX3ContainerDoesntContainPlatonicLetter2);
	}
	
	@Test
	public void onlyClonedFramesAreRelatedInNewDescriptor() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		boolean onlyClonedFramesAreRelatedInNewDescriptor;
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<IFrame> listOfFrames = signal.getFrames();
		Frame frame2 = (Frame) listOfFrames.get(1);
		List<String> listOfFrame2PropertiesWithpath = frame2.getListOfPropertiesWithPath();
		IOneOrManyFrames frameX3 = new FrameX(listOfFrames);
		List<String> listOfFrameX3PropertiesWithPath = frameX3.getListOfPropertiesWithPath();
		boolean frameX3ContainsFramePosition2 = false;
		boolean frame2ContainsFramePosition2 = false;
		for (String frame2PropertyWithPath : listOfFrame2PropertiesWithpath) {
			if (frame2PropertyWithPath.contains("frame/prominentPosition/position/2"))
				frame2ContainsFramePosition2 = true;
		}		
		for (String frameX3PropertyWithPath : listOfFrameX3PropertiesWithPath) {
			if (frameX3PropertyWithPath.contains("frame/prominentPosition/position/2"))
				frameX3ContainsFramePosition2 = true;
		}		
		onlyClonedFramesAreRelatedInNewDescriptor = 
				(frame2ContainsFramePosition2 == false && 
				frameX3ContainsFramePosition2 == true);
		assertEquals(onlyClonedFramesAreRelatedInNewDescriptor, true);
	}
	
	@Test
	public void whenSynTreeElementsAreUpgradedThenOrderedSetElementsAreReturned() 
			throws SynTreeGenerationException, CloneNotSupportedException, VerbalizationException, OrderedSetsGenerationException {
		IListOfDescriptorsBuilder listOfDescriptorsBuilder = 
				new ListOfDescriptorsBuilderImpl("abcde", "fromLeftToRight");
		List<CharString> listOfDescriptors = listOfDescriptorsBuilder.getListOfComprehensiveDescriptors();
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
