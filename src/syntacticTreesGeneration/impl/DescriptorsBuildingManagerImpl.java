package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import copycatModel.ISynTreeIntegrableElement;
import copycatModel.ISignal;
import copycatModel.grammar.Group;
import exceptions.DescriptorsBuilderException;
import settings.Settings;
import syntacticTreesGeneration.IDescriptorsBuildingManager;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IGen2Size1RelationDataContainerBuilder;
import syntacticTreesGeneration.INewDescriptorBuilder;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.IRelationDataContainerBuilder;
import syntacticTreesGeneration.ISequenceRelationalData;
import syntacticTreesGeneration.ISymmetryRelationalData;

public class DescriptorsBuildingManagerImpl implements IDescriptorsBuildingManager {

	private final ISignal signal;
	private final int componentsMaxGenerationNumber;
	private List<Group> listOfFactorizableDescriptors;
	private List<IRelationDataContainer> listOfRelationDataContainers = 
			new ArrayList<IRelationDataContainer>();	
	
	public DescriptorsBuildingManagerImpl(ISignal signal, int componentsGenerationNumber, 
			List<Group> listOfFactorizableDescriptors) 
					throws DescriptorsBuilderException {
		this.signal = signal;
		this.componentsMaxGenerationNumber = componentsGenerationNumber;
		this.listOfFactorizableDescriptors = listOfFactorizableDescriptors;
		if ((this.componentsMaxGenerationNumber == 1) && (listOfFactorizableDescriptors.size() == 1)) {
			if (listOfFactorizableDescriptors.get(0).getDescriptorName().equals("group")) {
				Group gen1Descriptor = (Group) listOfFactorizableDescriptors.get(0);
				IGen2Size1RelationDataContainerBuilder gen2Size1RelationDataContainerBuilder = 
						new Gen2Size1RelationDataContainerBuilderImpl(signal, gen1Descriptor);
				listOfRelationDataContainers = gen2Size1RelationDataContainerBuilder.getListOfRelationDataContainers();
				if (signal.getSignalSize() == 1) {
					IRelationDataContainer relationDC = new RelationDataContainerImpl();
					relationDC.setNewDescriptorWillCoverTheWholeString(true);
					listOfRelationDataContainers.add(relationDC);
				}
			}
			else throw new DescriptorsBuilderException(
					"DescriptorsBuildingManager : unexpected type of gen1 Descriptor.");
		}
		else {
			IRelationDataContainerBuilder relationDataContainerBuilder = 
					new RelationDataContainerBuilderImpl(signal, listOfFactorizableDescriptors);
			listOfRelationDataContainers.add(relationDataContainerBuilder.getRelationDataContainer());
		}
	}
	
	@Override
	public List<ISynTreeIntegrableElement> getListOfNewDescriptors() 
			throws DescriptorsBuilderException, CloneNotSupportedException{
		List<ISynTreeIntegrableElement> listOfNewDescriptors = new ArrayList<ISynTreeIntegrableElement>();
		for (IRelationDataContainer relationDataContainer : listOfRelationDataContainers) {
			boolean relationDataContainerIsValid = testIfRelationDataContainerIsValid(relationDataContainer);
			if (relationDataContainerIsValid == true) {
				INewDescriptorBuilder iNewDescriptorBuilder = 
						new NewDescriptorBuilderImpl(signal, relationDataContainer, listOfFactorizableDescriptors);
				ISynTreeIntegrableElement newDescriptor = iNewDescriptorBuilder.getNewDescriptor();
				listOfNewDescriptors.add(newDescriptor);
			}
		}
		return listOfNewDescriptors;
	}
	
	private boolean testIfRelationDataContainerIsValid(IRelationDataContainer relationDataContainer) 
			throws DescriptorsBuilderException {
		boolean relationDataContainerIsValid = false;
		boolean newDescriptorIsEligibleForRelation = testIfNewDescriptorIsEligibleForRelation();
		if (relationDataContainer.getNewDescriptorWillCoverTheFullString() == true) {
			if (newDescriptorIsEligibleForRelation == false) {
				boolean newDescriptorIsEligibleForMultiUnrelatedGroupsDescription = 
						testIfNewDescriptorIsEligibleForMultiUnrelatedGroupsDescription();
				if (newDescriptorIsEligibleForMultiUnrelatedGroupsDescription == true) {
					relationDataContainer.clear();
					relationDataContainerIsValid = true;
				}				
			}
			else relationDataContainerIsValid = true;
		}
		else 
			relationDataContainerIsValid = newDescriptorIsEligibleForRelation;
		return relationDataContainerIsValid;
	}
	
	private boolean testIfNewDescriptorIsEligibleForRelation() throws DescriptorsBuilderException {
		boolean newDescriptorIsEligibleForRelation = true;
		if (componentsMaxGenerationNumber != 1) {
			int numberOfDimensions = 0;
			int numberOfRelations;
			int numberOfSimpleEnumerations;
			int numberOfNonConstantSequences;
			int numberOfTransformationRelations;
			if (listOfRelationDataContainers.size() == 1) {
				IRelationDataContainer relationDataContainer = listOfRelationDataContainers.get(0);
				numberOfRelations = relationDataContainer.getListOfEnumerations().size();
				numberOfTransformationRelations = numberOfRelations;
				numberOfNonConstantSequences = 0;
				numberOfSimpleEnumerations = checkNumberOfSimpleEnumerations(relationDataContainer);
				if (numberOfRelations > 0 && 
						numberOfRelations <= Settings.MAX_NB_OF_RELATION && 
						numberOfSimpleEnumerations <= Settings.MAX_NB_OF_SIMPLE_ENUMERATIONS_IN_RELATION) {
					for (IEnumerationRelationalData enumerationRD : relationDataContainer.getListOfEnumerations()) {
						numberOfDimensions += enumerationRD.getDimensions().size();
					}
					for (ISequenceRelationalData sequenceRD : relationDataContainer.getListOfSequences()) {
						numberOfNonConstantSequences++;
						if (sequenceRD.getCommonDifference().equals("0")) {
							numberOfTransformationRelations--;
							numberOfNonConstantSequences--;
						}
					}
					if ((numberOfDimensions > Settings.MAX_NB_OF_DIMENSIONS_IN_RELATIONS) || 
							(numberOfTransformationRelations > Settings.MAX_NB_OF_TRANSFORMATION_RELATIONS) ||
							(numberOfNonConstantSequences > Settings.MAX_NB_OF_NON_CONSTANT_SEQUENCES))
						newDescriptorIsEligibleForRelation = false;
				}
				else newDescriptorIsEligibleForRelation = false;
			}
			else throw new DescriptorsBuilderException("DescriptorsBuildingManager : "
					+ "unexpected number of RelationDataContainers.");
		}
		return newDescriptorIsEligibleForRelation;
	}
	
	private boolean testIfNewDescriptorIsEligibleForMultiUnrelatedGroupsDescription() throws DescriptorsBuilderException {
		boolean newDescriptorIsEligibleForMultiUnrelatedGroupsDescription = false;
		if (listOfFactorizableDescriptors.size() <= Settings.MAX_NB_OF_UNRELATED_GROUPS) {
			if (componentsMaxGenerationNumber >= 2) {
				boolean thereIsAGen2Size1Descriptor = testIfThereIsAGen2Size1Descriptor();
				if (thereIsAGen2Size1Descriptor == false)
					newDescriptorIsEligibleForMultiUnrelatedGroupsDescription = true;
			}
			else newDescriptorIsEligibleForMultiUnrelatedGroupsDescription = true;
		}
		return newDescriptorIsEligibleForMultiUnrelatedGroupsDescription;
	}
	
	private int checkNumberOfSimpleEnumerations(IRelationDataContainer relationDataContainer) {
		int numberOfSimpleEnumerations = 0;
		for (IEnumerationRelationalData enumerationRD : relationDataContainer.getListOfEnumerations()) {			
			boolean sequenceWasFound = false;
			int sequenceIndex = 0;
			boolean symmetryWasFound = false;
			int symmetryIndex = 0;
			while (sequenceWasFound == false && sequenceIndex < relationDataContainer.getListOfSequences().size()) {
				ISequenceRelationalData sequenceRD = 
						relationDataContainer.getListOfSequences().get(sequenceIndex);
				if (sequenceRD.getDimensions().equals(enumerationRD.getDimensions()))
					sequenceWasFound = true;
				sequenceIndex++;
			}
			if (sequenceWasFound == false) {
				while (symmetryWasFound == false && symmetryIndex < relationDataContainer.getListOfSymmetries().size()) {
					ISymmetryRelationalData symmetryRD = 
							relationDataContainer.getListOfSymmetries().get(symmetryIndex);
					if (symmetryRD.getDimensions().equals(enumerationRD.getDimensions()))
							symmetryWasFound = true;
					symmetryIndex++;
				}
				if (sequenceWasFound == false)
					numberOfSimpleEnumerations++;
			}
		}
		return numberOfSimpleEnumerations;
	}
	
	private boolean testIfThereIsAGen2Size1Descriptor() throws DescriptorsBuilderException {
		boolean thereIsAGen2Size1Sequence = false;
		if (componentsMaxGenerationNumber >= 2) {
			for (Group group : listOfFactorizableDescriptors) {
				List<Integer> listOfLetterPositions = 
						DescriptorSpanGetterImpl.getDescriptorSpan((ISynTreeIntegrableElement) group);
				if (listOfLetterPositions.size() == 1) {
					List<String> listOfProperties = group.getListOfPropertiesWithPath();
					if (listOfProperties.get(2).contains("group/relation"))
						thereIsAGen2Size1Sequence = true;
				}
			}
		}
		return thereIsAGen2Size1Sequence;
	}
}