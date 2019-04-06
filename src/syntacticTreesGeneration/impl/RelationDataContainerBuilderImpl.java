package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import copycatModel.ISynTreeIntegrableElement;
import copycatModel.IProperty;
import copycatModel.IPropertyContainer;
import copycatModel.ISignal;
import copycatModel.grammar.Group;
import exceptions.SynTreeGenerationException;
import syntacticTreesGeneration.IEnumerationChecker;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.IRelationDataContainerBuilder;
import syntacticTreesGeneration.ISequenceChecker;
import syntacticTreesGeneration.ISymmetryChecker;

public class RelationDataContainerBuilderImpl implements IRelationDataContainerBuilder {

	private final ISignal signal;
	private final List<Group> listOfDescriptors;
	private final List<IPropertyContainer> listOfPropertyContainers = new ArrayList<IPropertyContainer>();
	
	public RelationDataContainerBuilderImpl(ISignal signal, List<Group> listOfDescriptors) {
		this.signal = signal;
		this.listOfDescriptors = listOfDescriptors;
		for (Group group : this.listOfDescriptors) {
			IPropertyContainer propertyContainer = group.getpropertyContainer();
			listOfPropertyContainers.add(propertyContainer);
		}
	}

	@Override
	public IRelationDataContainer getRelationDataContainer() throws SynTreeGenerationException {
		IRelationDataContainer relationDataContainer = new RelationDataContainerImpl();
		if (listOfDescriptors.size() > 1) {
			List<ISynTreeIntegrableElement> listOfAbstractDescriptors = new ArrayList<ISynTreeIntegrableElement>();
			listOfAbstractDescriptors.addAll(listOfDescriptors);
			boolean wholeStringIsDescribed = 
					DescriptorSpanGetterImpl.testIfWholeStringIsDescribed(signal, listOfAbstractDescriptors);
			relationDataContainer.setNewDescriptorWillCoverTheWholeString(wholeStringIsDescribed);
			boolean descriptorsShareTheSameSetOfDimensions = testIfDescriptorsShareTheSameSetOfDimensions();
			if (descriptorsShareTheSameSetOfDimensions == true) {
				List<String> listOfDimensions = 
						new ArrayList<String>(listOfDescriptors.get(0).getpropertyContainer().getDimensionToProperty().keySet());
				boolean subComponentsAnalyzeCreatesNoRedundance = true;
				boolean componentsAreRelated = true;
				boolean containerHasToBeCleanedOfSubDimensionRelations = false;
				int dimensionIndex = 0;
				while (componentsAreRelated == true && dimensionIndex < listOfDimensions.size()) {
					String dimension = listOfDimensions.get(dimensionIndex);
					if (subComponentsAnalyzeCreatesNoRedundance == true || !dimension.contains("groups")) {
						List<String> listsOfValues;
						int dimensionLastWordIndex = dimension.lastIndexOf("/") + 1;
						String dimensionLastWord = dimension.substring(dimensionLastWordIndex);
						switch (dimensionLastWord) {
							case "direction" :
								listsOfValues = getlistsOfValuesForThisDimension(dimension);
								boolean valuesAreIdentical = testIfValuesAreIdentical(listsOfValues);
								if (valuesAreIdentical == false)
									componentsAreRelated = false;
								break;
							case "size" :
								if (testIfThisIsAGroupsSizeCreatingRedundance(dimension) == true){
									break;
								}
								else {};
							case "platonicLetter" :
							case "commonDiff" :
							case "enumeration" :
								listsOfValues = getlistsOfValuesForThisDimension(dimension);
								IEnumerationChecker enumerationChecker = 
										new EnumerationCheckerImpl(wholeStringIsDescribed, dimension, listsOfValues);
								boolean simpleEnumerationWasFound = enumerationChecker.getSimpleEnumerationWasFound();
								boolean secondDegreeEnumerationWasFound = 
										enumerationChecker.getSecondDegreeEnumerationWasFound();
								if (simpleEnumerationWasFound == true) {
									IEnumerationRelationalData enumerationRelationalData = 
											enumerationChecker.getEnumerationRelationalData();
									relationDataContainer.addEnumeration(enumerationRelationalData);
									ISequenceChecker sequenceChecker = 
											new SequenceCheckerImpl(wholeStringIsDescribed, dimension, listsOfValues, 
													enumerationRelationalData);
									boolean sequenceWasFound = sequenceChecker.getSequenceWasFound();
									if (sequenceWasFound == true) {
										relationDataContainer.addSequence(sequenceChecker.getSequenceRelationalData());
									}
									ISymmetryChecker symmetryChecker = 
											new SymmetryCheckerImpl(wholeStringIsDescribed, dimension, listsOfValues, 
													enumerationRelationalData);
									boolean symmetryWasFound = symmetryChecker.getSymmetryWasFound();
									if (symmetryWasFound == true) {
										relationDataContainer.addSymmetry(symmetryChecker.getSymmetryRelationalData());
									}
								}
								else if (secondDegreeEnumerationWasFound == true) {
									IEnumerationRelationalData enumerationRelationalData = 
											enumerationChecker.getEnumerationRelationalData();
									ISequenceChecker sequenceChecker = 
											new SequenceCheckerImpl(wholeStringIsDescribed, dimension, listsOfValues, 
													enumerationRelationalData);
									ISymmetryChecker symmetryChecker = 
											new SymmetryCheckerImpl(wholeStringIsDescribed, dimension, listsOfValues, 
													enumerationRelationalData);
									boolean sequenceWasFound = sequenceChecker.getSequenceWasFound();
									boolean symmetryWasFound = symmetryChecker.getSymmetryWasFound();
									if (sequenceWasFound == false && symmetryWasFound == false)
										componentsAreRelated = false;
									else {
										if (symmetryWasFound == true) {
											relationDataContainer.addEnumeration(enumerationRelationalData);
											relationDataContainer.addSymmetry(symmetryChecker.getSymmetryRelationalData());
										}
									}
								}
								else componentsAreRelated = false;
								break;
							case "symmetry" :
							case "position" :
							case "absCommonDiff" :
							case "dimension" :
							default : 
								break;
						}
					}
					dimensionIndex++;
				}
				if (componentsAreRelated == false)
					relationDataContainer.clear();
				else { 
					if (containerHasToBeCleanedOfSubDimensionRelations == true)
						relationDataContainer.cleanRelationsFromSubDimensions();
				}
			}
		} 
		else throw new SynTreeGenerationException("RelationDataContainerBuilder : "
				+ "can't find a relation with only one component.");
		return relationDataContainer;
	}
	
	private boolean testIfDescriptorsShareTheSameSetOfDimensions() {
		boolean descriptorsShareTheSameSetOfDimensions = true;
		Set<String> firstDescriptorSetOfDimensions = 
				new HashSet<String>(listOfDescriptors.get(0).getpropertyContainer().getDimensionToProperty().keySet());
		int descriptorIndex = 1;
		while (descriptorsShareTheSameSetOfDimensions == true && descriptorIndex < listOfDescriptors.size()) {
			Set<String> iDescriptorSetOfDimensions = 
					new HashSet<String>(
							listOfDescriptors.get(
									descriptorIndex).getpropertyContainer().getDimensionToProperty().keySet());
			if (!iDescriptorSetOfDimensions.equals(firstDescriptorSetOfDimensions))
				descriptorsShareTheSameSetOfDimensions = false;
			descriptorIndex++;
		}
		return descriptorsShareTheSameSetOfDimensions;
		
	}
	
	private List<String> getlistsOfValuesForThisDimension(String dimension) 
			throws SynTreeGenerationException{
		List<String> listsOfValuesForThisDimension = new ArrayList<String>();
		for (IPropertyContainer propertyContainer : listOfPropertyContainers) {
			IProperty property = 
					propertyContainer.getProperty(dimension);
			listsOfValuesForThisDimension.add(property.getValue());
		}
		return listsOfValuesForThisDimension;
	}
	
	private boolean testIfValuesAreIdentical(List<String> listOfValues) 
			throws SynTreeGenerationException {
		boolean valuesAreIdentical = true;
		if (listOfValues.size() > 1) {
			String referenceValue = listOfValues.get(0);
			int listIndex = 1;
			while (valuesAreIdentical == true && listIndex < listOfValues.size()) {
				if (!listOfValues.get(listIndex).equals(referenceValue))
					valuesAreIdentical = false;
				listIndex++;
			}				
		}
		else throw new SynTreeGenerationException(
				"RelationContainerBuilder.testIfListsValuesAreIdentical() : illegal parameter.");
		return valuesAreIdentical;
	}
	
	private boolean testIfThisIsAGroupsSizeCreatingRedundance(String dimension) {
		boolean thisIsAGroupsSizeCreatingRedundance;
		if (dimension.contains("groups/size")) {
			thisIsAGroupsSizeCreatingRedundance = true;
		}
		else thisIsAGroupsSizeCreatingRedundance = false;
		return thisIsAGroupsSizeCreatingRedundance;
	}

}
