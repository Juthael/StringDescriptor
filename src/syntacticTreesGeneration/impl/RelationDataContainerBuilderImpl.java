package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Frame;
import model.synTreeModel.ISignal;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.impl.utils.IProperty;
import model.synTreeModel.impl.utils.IPropertyContainer;
import settings.Settings;
import syntacticTreesGeneration.IEnumerationChecker;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.IRelationDataContainerBuilder;
import syntacticTreesGeneration.ISequenceChecker;
import syntacticTreesGeneration.ISymmetryChecker;

public class RelationDataContainerBuilderImpl implements IRelationDataContainerBuilder {

	private final ISignal signal;
	private final List<Frame> listOfDescriptors;
	private final List<IPropertyContainer> listOfPropertyContainers = new ArrayList<IPropertyContainer>();
	
	public RelationDataContainerBuilderImpl(ISignal signal, List<Frame> listOfDescriptors) {
		this.signal = signal;
		this.listOfDescriptors = listOfDescriptors;
		for (Frame frame : this.listOfDescriptors) {
			IPropertyContainer propertyContainer = frame.getpropertyContainer();
			listOfPropertyContainers.add(propertyContainer);
		}
	}

	@Override
	public IRelationDataContainer getRelationDataContainer() throws SynTreeGenerationException {
		IRelationDataContainer relationDataContainer = new RelationDataContainerImpl();
		if (listOfDescriptors.size() > 1) {
			List<IGrammaticalST> listOfAbstractDescriptors = new ArrayList<IGrammaticalST>();
			listOfAbstractDescriptors.addAll(listOfDescriptors);
			boolean wholeStringIsDescribed = 
					DescriptorSpanGetterImpl.testIfWholeStringIsDescribed(signal, listOfAbstractDescriptors);
			relationDataContainer.setNewDescriptorWillCoverTheWholeString(wholeStringIsDescribed);
			boolean descriptorsShareTheSameSetOfPaths = testIfDescriptorsShareTheSameSetOfPaths();
			if (descriptorsShareTheSameSetOfPaths == true) {
				List<String> orderedListOfIndexedPaths = listOfPropertyContainers.get(0).getOrderedListOfIndexedPaths();
				boolean componentsAreRelated = true;
				boolean containerHasToBeCleanedOfSubDimensionRelations = false;
				int pathIndex = 0;
				Set<Integer> setOfCheckedIndexes = new HashSet<Integer>();
				while (componentsAreRelated == true && pathIndex < orderedListOfIndexedPaths.size()) {
					String indexedPath = orderedListOfIndexedPaths.get(pathIndex);
					List<String> listsOfValues;
					int pathLastWordIndex = indexedPath.lastIndexOf(Settings.PATH_SEPARATOR) + 1;
					String pathLastWord = indexedPath.substring(pathLastWordIndex);
					switch (pathLastWord) {
						case "direction" :
							listsOfValues = getListsOfValuesForThisPath(indexedPath);
							boolean valuesAreIdentical = testIfValuesAreIdentical(listsOfValues);
							if (valuesAreIdentical == false)
								componentsAreRelated = false;
							break;
						case "size" :
							if (testIfThisIsAComponentssSizeCreatingRedundance(indexedPath) == true){
								break;
							}
							else {};
						case "platonicLetter" :
						case "commonDiff" :
						case "enumeration" :
							List<List<String>> listOfPropertyLists = new ArrayList<List<String>>();
							for (Frame frame : listOfDescriptors) {
								listOfPropertyLists.add(frame.getListOfPropertiesWithPathWithoutQuantifiers());
							}
							listsOfValues = getListsOfValuesForThisPath(indexedPath);
							DimensionEncodingManager dimensionEncodingManager = 
									new DimensionEncodingManager(setOfCheckedIndexes);
							String dimension = 
									dimensionEncodingManager.getDimension(listOfPropertyLists, 
											indexedPath, listsOfValues);
							setOfCheckedIndexes.addAll(dimensionEncodingManager.getSetOfCheckedIndexes());
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
								boolean thisEnumerationContainsNoAdditionalInformation = 
										enumerationChecker.getAllSecondDegreeValuesAreSequences();
								if (thisEnumerationContainsNoAdditionalInformation == false) {
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
					pathIndex++;
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
	
	private boolean testIfDescriptorsShareTheSameSetOfPaths() {
		boolean descriptorsShareTheSameSetOfPaths = true;
		Set<String> firstDescriptorSetOfPaths = 
				new HashSet<String>(listOfDescriptors.get(0).getpropertyContainer().getIndexedPathToProperty().keySet());
		int descriptorIndex = 1;
		while (descriptorsShareTheSameSetOfPaths == true && descriptorIndex < listOfDescriptors.size()) {
			Set<String> iDescriptorSetOfPaths = 
					new HashSet<String>(
							listOfDescriptors.get(
									descriptorIndex).getpropertyContainer().getIndexedPathToProperty().keySet());
			if (!iDescriptorSetOfPaths.equals(firstDescriptorSetOfPaths))
				descriptorsShareTheSameSetOfPaths = false;
			descriptorIndex++;
		}
		return descriptorsShareTheSameSetOfPaths;
		
	}
	
	private List<String> getListsOfValuesForThisPath(String dimension) 
			throws SynTreeGenerationException{
		List<String> listsOfValuesForThisPath = new ArrayList<String>();
		for (IPropertyContainer propertyContainer : listOfPropertyContainers) {
			IProperty property = 
					propertyContainer.getProperty(dimension);
			listsOfValuesForThisPath.add(property.getValue());
		}
		return listsOfValuesForThisPath;
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
	
	private boolean testIfThisIsAComponentssSizeCreatingRedundance(String dimension) {
		boolean thisIsAComponentsSizeCreatingRedundance;
		if (dimension.contains("components/size")) {
			thisIsAComponentsSizeCreatingRedundance = true;
		}
		else thisIsAComponentsSizeCreatingRedundance = false;
		return thisIsAComponentsSizeCreatingRedundance;
	}

}
