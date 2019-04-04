package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.RelationDataContainerInterface;
import syntacticTreesGeneration.interfaces.SequenceRelationalDataInterface;
import syntacticTreesGeneration.interfaces.SymmetryRelationalDataInterface;

public class RelationDataContainerV1 implements RelationDataContainerInterface {

	private boolean newDescriptorWillCoverTheFullString = false;
	private ArrayList<EnumerationRelationalDataInterface> listOfEnumerations = new ArrayList<EnumerationRelationalDataInterface>();
	private ArrayList<SequenceRelationalDataInterface> listOfSequences = new ArrayList<SequenceRelationalDataInterface>();
	private ArrayList<SymmetryRelationalDataInterface> listOfSymmetries = new ArrayList<SymmetryRelationalDataInterface>();
	
	//Getters
	@Override
	public boolean getNewDescriptorWillCoverTheFullString() {
		return newDescriptorWillCoverTheFullString;
	}	
	
	@Override
	public ArrayList<EnumerationRelationalDataInterface> getListOfEnumerations() {
		return listOfEnumerations;
	}

	@Override
	public ArrayList<SequenceRelationalDataInterface> getListOfSequences() {
		return listOfSequences;
	}

	@Override
	public ArrayList<SymmetryRelationalDataInterface> getListOfSymmetries() {
		return listOfSymmetries;
	}	
	
	//Setters
	@Override
	public void setNewDescriptorWillCoverTheWholeString(boolean newDescriptorWillCoverTheFullString) {
		this.newDescriptorWillCoverTheFullString = newDescriptorWillCoverTheFullString;
	}
	
	@Override
	public void addEnumeration(EnumerationRelationalDataInterface enumerationRelationalData) {
		listOfEnumerations.add(enumerationRelationalData);
	}
	
	@Override
	public void addSequence(SequenceRelationalDataInterface sequenceRelationalData) {
		listOfSequences.add(sequenceRelationalData);
	}
	
	@Override
	public void addSymmetry(SymmetryRelationalDataInterface symmetryRelationalData) {
		listOfSymmetries.add(symmetryRelationalData);
	}
	
	@Override
	public void cleanValuesRedundancies() throws DescriptorsBuilderCriticalException {
		if (DescGenSettings.REDUNDANCIES_IN_RELATIONS_CAN_BE_CLEANED) {
			ArrayList<EnumerationRelationalDataInterface> cleanListOfEnumerationRelationalData 
			= new ArrayList<EnumerationRelationalDataInterface>();
			ArrayList<SequenceRelationalDataInterface> cleanListOfSequences = 
					new ArrayList<SequenceRelationalDataInterface>();
			ArrayList<SymmetryRelationalDataInterface> cleanListOfSymmetries = 
					new ArrayList<SymmetryRelationalDataInterface>();
			cloneEnumerationList();
			boolean enumerationListHasBeenCleaned = false;
			for (int i=0 ; i<listOfEnumerations.size() ; i++) {
				String iEnumerationValue = listOfEnumerations.get(i).getEnumerationValue();
				boolean iValueIsUnique = true;
				for (int j=i+1 ; j<listOfEnumerations.size() ; j++) {
					String jEnumerationValue = listOfEnumerations.get(j).getEnumerationValue();
					if (iEnumerationValue.equals(jEnumerationValue)){
						boolean ifSequencesFoundCommonDiffAreTheSame = 
								checkIfEnumerationsCanBeMerged(listOfEnumerations.get(i), listOfEnumerations.get(j));
						if (ifSequencesFoundCommonDiffAreTheSame == true) {
							ArrayList<String> dimensionsToBeAdded = listOfEnumerations.get(i).getDimensions();
							listOfEnumerations.get(j).addDimensions(dimensionsToBeAdded);
							iValueIsUnique = false;
							enumerationListHasBeenCleaned = true;
						}
					}
				}
				if (iValueIsUnique == true)
					cleanListOfEnumerationRelationalData.add(listOfEnumerations.get(i));
			}
			listOfEnumerations = cleanListOfEnumerationRelationalData;
			if (enumerationListHasBeenCleaned == true) {
				cloneOtherLists();
				for (EnumerationRelationalDataInterface enumerationRelationalData : listOfEnumerations) {
					boolean dimensionWasFoundInListOfSequences = false;
					boolean dimensionWasFoundInListOfSymmetries = false;
					ArrayList<String> enumerationDimensions = enumerationRelationalData.getDimensions();
					for (String enumerationDimension : enumerationDimensions) {
						for (SequenceRelationalDataInterface sequenceRelationalData : listOfSequences) {
							if (sequenceRelationalData.getDimensions().contains(enumerationDimension)){
								if (dimensionWasFoundInListOfSequences == false) {
									cleanListOfSequences.add(new SequenceRelationalDataV1(sequenceRelationalData));
									dimensionWasFoundInListOfSequences = true;
								}
								else {
									cleanListOfSequences.get(
										cleanListOfSequences.size()-1).addDimensions(sequenceRelationalData.getDimensions());
								}
							}
						}
						for (SymmetryRelationalDataInterface symmetryRelationalData : listOfSymmetries) {
							if (symmetryRelationalData.getDimensions().contains(enumerationDimension)){
								if (dimensionWasFoundInListOfSymmetries == false) {
									cleanListOfSymmetries.add(symmetryRelationalData);
									dimensionWasFoundInListOfSymmetries = true;
								}
								else {
									cleanListOfSymmetries.get(
											cleanListOfSymmetries.size()-1).addDimensions(symmetryRelationalData.getDimensions());
								}
							}
						}				
					}
				}
				listOfSequences = cleanListOfSequences;
				listOfSymmetries = cleanListOfSymmetries;							
			}
		}
	}
	
	@Override
	public void clear() {
		listOfEnumerations.clear();
		listOfSequences.clear();
		listOfSymmetries.clear();
	}	
	
	private boolean checkIfEnumerationsCanBeMerged(EnumerationRelationalDataInterface enum1, EnumerationRelationalDataInterface enum2) {
		boolean enumerationsCanBeMerged = true;
		String dimension1 = enum1.getDimensions().get(0);
		String dimension2 = enum2.getDimensions().get(0);
		int sequenceListIndexA = 0;
		while (enumerationsCanBeMerged == true && sequenceListIndexA < listOfSequences.size()) {
			SequenceRelationalDataInterface sequenceA = listOfSequences.get(sequenceListIndexA);
			if (sequenceA.getDimensions().contains(dimension1)) {
				String commonDiffA = sequenceA.getCommonDifference();
				boolean sequenceBHasBeenFound = false;
				for (SequenceRelationalDataInterface sequenceB : listOfSequences) {
					if (sequenceB.getDimensions().contains(dimension2)) {
						sequenceBHasBeenFound = true;
						String commonDiffB = sequenceB.getCommonDifference();
						enumerationsCanBeMerged = (commonDiffA.equals(commonDiffB));
					}
				}
				if (sequenceBHasBeenFound == false)
					enumerationsCanBeMerged = false;
			}
			sequenceListIndexA++;
		}
		return enumerationsCanBeMerged;
	}
	
	private void cloneEnumerationList() {
		ArrayList<EnumerationRelationalDataInterface> listOfEnumerationsClone = 
				new ArrayList<EnumerationRelationalDataInterface>();
		for (EnumerationRelationalDataInterface enumerationRelationalData : listOfEnumerations) {
			EnumerationRelationalDataInterface enumerationRelationalDataClone = 
					new EnumerationRelationalDataV1(enumerationRelationalData);
			listOfEnumerationsClone.add(enumerationRelationalDataClone);
		}
		listOfEnumerations = listOfEnumerationsClone;
	}
	
	private void cloneOtherLists() {
		ArrayList<SequenceRelationalDataInterface> listOfSequencesClone = 
				new ArrayList<SequenceRelationalDataInterface>();
		for (SequenceRelationalDataInterface sequenceRelationalData : listOfSequences) {
			SequenceRelationalDataInterface sequenceRelationalDataClone = 
					new SequenceRelationalDataV1(sequenceRelationalData);
			listOfSequencesClone.add(sequenceRelationalDataClone);
		}
		ArrayList<SymmetryRelationalDataInterface> listOfSymmetriesClone = 
				new ArrayList<SymmetryRelationalDataInterface>();	
		for (SymmetryRelationalDataInterface symmetryRelationalData : listOfSymmetries) {
			SymmetryRelationalDataInterface symmetryRelationalDataClone = 
					new SymmetryRelationalDataV1(symmetryRelationalData);
			listOfSymmetriesClone.add(symmetryRelationalDataClone);
		}
		
		listOfSequences = listOfSequencesClone;
		listOfSymmetries = listOfSymmetriesClone;
	}

}
