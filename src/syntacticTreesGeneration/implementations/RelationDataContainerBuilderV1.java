package syntacticTreesGeneration.implementations;

import java.util.ArrayList;
import java.util.HashSet;

import copycatModel.grammar.Group;
import copycatModel.interfaces.AbstractDescriptorInterface;
import copycatModel.interfaces.PropertyContainerInterface;
import copycatModel.interfaces.PropertyInterface;
import copycatModel.interfaces.SignalInterface;
import exceptions.DescriptorsBuilderCriticalException;
import syntacticTreesGeneration.interfaces.EnumerationCheckerInterface;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.RelationDataContainerBuilderInterface;
import syntacticTreesGeneration.interfaces.RelationDataContainerInterface;
import syntacticTreesGeneration.interfaces.SequenceCheckerInterface;
import syntacticTreesGeneration.interfaces.SymmetryCheckerInterface;

public class RelationDataContainerBuilderV1 implements RelationDataContainerBuilderInterface {

	private final SignalInterface signal;
	private final ArrayList<Group> listOfDescriptors;
	private final ArrayList<PropertyContainerInterface> listOfPropertyContainers = new ArrayList<PropertyContainerInterface>();
	
	public RelationDataContainerBuilderV1(SignalInterface signal, ArrayList<Group> listOfDescriptors) {
		this.signal = signal;
		this.listOfDescriptors = listOfDescriptors;
		for (Group group : this.listOfDescriptors) {
			PropertyContainerInterface propertyContainer = group.getpropertyContainer();
			listOfPropertyContainers.add(propertyContainer);
		}
	}

	@Override
	public RelationDataContainerInterface getRelationDataContainer() throws DescriptorsBuilderCriticalException {
		RelationDataContainerInterface relationDataContainer = new RelationDataContainerV1();
		if (listOfDescriptors.size() > 1) {
			ArrayList<AbstractDescriptorInterface> listOfAbstractDescriptors = new ArrayList<AbstractDescriptorInterface>();
			listOfAbstractDescriptors.addAll(listOfDescriptors);
			boolean wholeStringIsDescribed = 
					DescriptorSpanGetterV1.testIfWholeStringIsDescribed(signal, listOfAbstractDescriptors);
			relationDataContainer.setNewDescriptorWillCoverTheWholeString(wholeStringIsDescribed);
			boolean descriptorsShareTheSameSetOfDimensions = testIfDescriptorsShareTheSameSetOfDimensions();
			if (descriptorsShareTheSameSetOfDimensions == true) {
				ArrayList<String> listOfDimensions = 
						new ArrayList<String>(listOfDescriptors.get(0).getpropertyContainer().getDimensionToProperty().keySet());
				boolean subComponentsAnalyzeCreatesNoRedundance = true;
				boolean componentsAreRelated = true;
				int dimensionIndex = 0;
				while (componentsAreRelated == true && dimensionIndex < listOfDimensions.size()) {
					String dimension = listOfDimensions.get(dimensionIndex);
					if (subComponentsAnalyzeCreatesNoRedundance == true || !dimension.contains("groups")) {
						ArrayList<String> listsOfValues;
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
								EnumerationCheckerInterface enumerationChecker = 
										new EnumerationCheckerV1(wholeStringIsDescribed, dimension, listsOfValues);
								boolean simpleEnumerationWasFound = enumerationChecker.getSimpleEnumerationWasFound();
								boolean secondDegreeEnumerationWasFound = 
										enumerationChecker.getSecondDegreeEnumerationWasFound();
								if (simpleEnumerationWasFound == true) {
									EnumerationRelationalDataInterface enumerationRelationalData = 
											enumerationChecker.getEnumerationRelationalData();
									relationDataContainer.addEnumeration(enumerationRelationalData);
									SequenceCheckerInterface sequenceChecker = 
											new SequenceCheckerV1(wholeStringIsDescribed, dimension, listsOfValues, 
													enumerationRelationalData);
									boolean sequenceWasFound = sequenceChecker.getSequenceWasFound();
									if (sequenceWasFound == true) {
										relationDataContainer.addSequence(sequenceChecker.getSequenceRelationalData());
									}
									SymmetryCheckerInterface symmetryChecker = 
											new SymmetryCheckerV1(wholeStringIsDescribed, dimension, listsOfValues, 
													enumerationRelationalData);
									boolean symmetryWasFound = symmetryChecker.getSymmetryWasFound();
									if (symmetryWasFound == true) {
										relationDataContainer.addSymmetry(symmetryChecker.getSymmetryRelationalData());
									}
								}
								else if (secondDegreeEnumerationWasFound == true) {
									EnumerationRelationalDataInterface enumerationRelationalData = 
											enumerationChecker.getEnumerationRelationalData();
									SequenceCheckerInterface sequenceChecker = 
											new SequenceCheckerV1(wholeStringIsDescribed, dimension, listsOfValues, 
													enumerationRelationalData);
									SymmetryCheckerInterface symmetryChecker = 
											new SymmetryCheckerV1(wholeStringIsDescribed, dimension, listsOfValues, 
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
				else relationDataContainer.cleanValuesRedundancies();
			}
		} 
		else throw new DescriptorsBuilderCriticalException("RelationDataContainerBuilder : "
				+ "can't find a relation with only one component.");
		return relationDataContainer;
	}
	
	private boolean testIfDescriptorsShareTheSameSetOfDimensions() {
		boolean descriptorsShareTheSameSetOfDimensions = true;
		HashSet<String> firstDescriptorSetOfDimensions = 
				new HashSet<String>(listOfDescriptors.get(0).getpropertyContainer().getDimensionToProperty().keySet());
		int descriptorIndex = 1;
		while (descriptorsShareTheSameSetOfDimensions == true && descriptorIndex < listOfDescriptors.size()) {
			HashSet<String> iDescriptorSetOfDimensions = 
					new HashSet<String>(
							listOfDescriptors.get(descriptorIndex).getpropertyContainer().getDimensionToProperty().keySet());
			if (!iDescriptorSetOfDimensions.equals(firstDescriptorSetOfDimensions))
				descriptorsShareTheSameSetOfDimensions = false;
			descriptorIndex++;
		}
		return descriptorsShareTheSameSetOfDimensions;
		
	}
	
	private ArrayList<String> getlistsOfValuesForThisDimension(String dimension) 
			throws DescriptorsBuilderCriticalException{
		ArrayList<String> listsOfValuesForThisDimension = new ArrayList<String>();
		for (PropertyContainerInterface propertyContainer : listOfPropertyContainers) {
			PropertyInterface property = 
					propertyContainer.getProperty(dimension);
			listsOfValuesForThisDimension.add(property.getValue());
		}
		return listsOfValuesForThisDimension;
	}
	
	private boolean testIfValuesAreIdentical(ArrayList<String> listOfValues) 
			throws DescriptorsBuilderCriticalException {
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
		else throw new DescriptorsBuilderCriticalException(
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
