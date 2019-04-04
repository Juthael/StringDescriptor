package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

import copycatModel.grammar.Group;
import copycatModel.interfaces.AbstractDescriptorInterface;
import copycatModel.interfaces.SignalInterface;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.interfaces.DescriptorsBuildingManagerInterface;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.Gen2Size1RelationDataContainerBuilderInterface;
import syntacticTreesGeneration.interfaces.NewDescriptorBuilderInterface;
import syntacticTreesGeneration.interfaces.RelationDataContainerBuilderInterface;
import syntacticTreesGeneration.interfaces.RelationDataContainerInterface;
import syntacticTreesGeneration.interfaces.SequenceRelationalDataInterface;
import syntacticTreesGeneration.interfaces.SymmetryRelationalDataInterface;

public class DescriptorsBuildingManagerV1 implements DescriptorsBuildingManagerInterface {

	private final SignalInterface signal;
	private final int componentsMaxGenerationNumber;
	private ArrayList<Group> listOfFactorizableDescriptors;
	private ArrayList<RelationDataContainerInterface> listOfRelationDataContainers = 
			new ArrayList<RelationDataContainerInterface>();	
	
	public DescriptorsBuildingManagerV1(SignalInterface signal, int componentsGenerationNumber, 
			ArrayList<Group> listOfFactorizableDescriptors) 
					throws DescriptorsBuilderCriticalException {
		this.signal = signal;
		this.componentsMaxGenerationNumber = componentsGenerationNumber;
		this.listOfFactorizableDescriptors = listOfFactorizableDescriptors;
		if ((this.componentsMaxGenerationNumber == 1) && (listOfFactorizableDescriptors.size() == 1)) {
			if (listOfFactorizableDescriptors.get(0).getDescriptorName().equals("group")) {
				Group gen1Descriptor = (Group) listOfFactorizableDescriptors.get(0);
				Gen2Size1RelationDataContainerBuilderInterface gen2Size1RelationDataContainerBuilder = 
						new Gen2Size1RelationDataContainerBuilderV1(signal, gen1Descriptor);
				listOfRelationDataContainers = gen2Size1RelationDataContainerBuilder.getListOfRelationDataContainers();
				if (signal.getSignalSize() == 1) {
					RelationDataContainerInterface relationDC = new RelationDataContainerV1();
					relationDC.setNewDescriptorWillCoverTheWholeString(true);
					listOfRelationDataContainers.add(relationDC);
				}
			}
			else throw new DescriptorsBuilderCriticalException(
					"DescriptorsBuildingManager : unexpected type of gen1 Descriptor.");
		}
		else {
			RelationDataContainerBuilderInterface relationDataContainerBuilder = 
					new RelationDataContainerBuilderV1(signal, listOfFactorizableDescriptors);
			listOfRelationDataContainers.add(relationDataContainerBuilder.getRelationDataContainer());
		}
	}
	
	@Override
	public ArrayList<AbstractDescriptorInterface> getListOfNewDescriptors() 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException{
		ArrayList<AbstractDescriptorInterface> listOfNewDescriptors = new ArrayList<AbstractDescriptorInterface>();
		for (RelationDataContainerInterface relationDataContainer : listOfRelationDataContainers) {
			boolean relationDataContainerIsValid = testIfRelationDataContainerIsValid(relationDataContainer);
			if (relationDataContainerIsValid == true) {
				NewDescriptorBuilderInterface newDescriptorBuilderInterface = 
						new NewDescriptorBuilderV1(signal, relationDataContainer, listOfFactorizableDescriptors);
				AbstractDescriptorInterface newDescriptor = newDescriptorBuilderInterface.getNewDescriptor();
				listOfNewDescriptors.add(newDescriptor);
			}
		}
		return listOfNewDescriptors;
	}
	
	private boolean testIfRelationDataContainerIsValid(RelationDataContainerInterface relationDataContainer) 
			throws DescriptorsBuilderCriticalException {
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
	
	private boolean testIfNewDescriptorIsEligibleForRelation() throws DescriptorsBuilderCriticalException {
		boolean newDescriptorIsEligibleForRelation = true;
		if (componentsMaxGenerationNumber != 1) {
			int numberOfDimensions = 0;
			int numberOfRelations;
			int numberOfSimpleEnumerations;
			int numberOfNonConstantSequences;
			int numberOfTransformationRelations;
			if (listOfRelationDataContainers.size() == 1) {
				RelationDataContainerInterface relationDataContainer = listOfRelationDataContainers.get(0);
				numberOfRelations = relationDataContainer.getListOfEnumerations().size();
				numberOfTransformationRelations = numberOfRelations;
				numberOfNonConstantSequences = 0;
				numberOfSimpleEnumerations = checkNumberOfSimpleEnumerations(relationDataContainer);
				if (numberOfRelations > 0 && 
						numberOfRelations <= DescGenSettings.MAX_NB_OF_RELATION && 
						numberOfSimpleEnumerations <= DescGenSettings.MAX_NB_OF_SIMPLE_ENUMERATIONS_IN_RELATION) {
					for (EnumerationRelationalDataInterface enumerationRD : relationDataContainer.getListOfEnumerations()) {
						numberOfDimensions += enumerationRD.getDimensions().size();
					}
					for (SequenceRelationalDataInterface sequenceRD : relationDataContainer.getListOfSequences()) {
						numberOfNonConstantSequences++;
						if (sequenceRD.getCommonDifference().equals("0")) {
							numberOfTransformationRelations--;
							numberOfNonConstantSequences--;
						}
					}
					if ((numberOfDimensions > DescGenSettings.MAX_NB_OF_DIMENSIONS_IN_RELATIONS) || 
							(numberOfTransformationRelations > DescGenSettings.MAX_NB_OF_TRANSFORMATION_RELATIONS) ||
							(numberOfNonConstantSequences > DescGenSettings.MAX_NB_OF_NON_CONSTANT_SEQUENCES))
						newDescriptorIsEligibleForRelation = false;
				}
				else newDescriptorIsEligibleForRelation = false;
			}
			else throw new DescriptorsBuilderCriticalException("DescriptorsBuildingManager : "
					+ "unexpected number of RelationDataContainers.");
		}
		return newDescriptorIsEligibleForRelation;
	}
	
	private boolean testIfNewDescriptorIsEligibleForMultiUnrelatedGroupsDescription() throws DescriptorsBuilderCriticalException {
		boolean newDescriptorIsEligibleForMultiUnrelatedGroupsDescription = false;
		if (listOfFactorizableDescriptors.size() <= DescGenSettings.MAX_NB_OF_UNRELATED_GROUPS) {
			if (componentsMaxGenerationNumber >= 2) {
				boolean thereIsAGen2Size1Descriptor = testIfThereIsAGen2Size1Descriptor();
				if (thereIsAGen2Size1Descriptor == false)
					newDescriptorIsEligibleForMultiUnrelatedGroupsDescription = true;
			}
			else newDescriptorIsEligibleForMultiUnrelatedGroupsDescription = true;
		}
		return newDescriptorIsEligibleForMultiUnrelatedGroupsDescription;
	}
	
	private int checkNumberOfSimpleEnumerations(RelationDataContainerInterface relationDataContainer) {
		int numberOfSimpleEnumerations = 0;
		for (EnumerationRelationalDataInterface enumerationRD : relationDataContainer.getListOfEnumerations()) {			
			boolean sequenceWasFound = false;
			int sequenceIndex = 0;
			boolean symmetryWasFound = false;
			int symmetryIndex = 0;
			while (sequenceWasFound == false && sequenceIndex < relationDataContainer.getListOfSequences().size()) {
				SequenceRelationalDataInterface sequenceRD = 
						relationDataContainer.getListOfSequences().get(sequenceIndex);
				if (sequenceRD.getDimensions().equals(enumerationRD.getDimensions()))
					sequenceWasFound = true;
				sequenceIndex++;
			}
			if (sequenceWasFound == false) {
				while (symmetryWasFound == false && symmetryIndex < relationDataContainer.getListOfSymmetries().size()) {
					SymmetryRelationalDataInterface symmetryRD = 
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
	
	private boolean testIfThereIsAGen2Size1Descriptor() throws DescriptorsBuilderCriticalException {
		boolean thereIsAGen2Size1Sequence = false;
		if (componentsMaxGenerationNumber >= 2) {
			for (Group group : listOfFactorizableDescriptors) {
				ArrayList<Integer> listOfLetterPositions = 
						DescriptorSpanGetterV1.getDescriptorSpan((AbstractDescriptorInterface) group);
				if (listOfLetterPositions.size() == 1) {
					ArrayList<String> listOfProperties = group.getListOfPropertiesWithPath();
					if (listOfProperties.get(2).contains("group/relation"))
						thereIsAGen2Size1Sequence = true;
				}
			}
		}
		return thereIsAGen2Size1Sequence;
	}
}
