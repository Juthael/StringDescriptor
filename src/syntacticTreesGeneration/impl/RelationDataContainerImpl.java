package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.SynTreeGenerationException;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.ISequenceRelationalData;
import syntacticTreesGeneration.ISymmetryRelationalData;

public class RelationDataContainerImpl implements IRelationDataContainer {

	private boolean newDescriptorWillCoverTheFullString = false;
	private List<IEnumerationRelationalData> listOfEnumerations = new ArrayList<IEnumerationRelationalData>();
	private List<ISequenceRelationalData> listOfSequences = new ArrayList<ISequenceRelationalData>();
	private List<ISymmetryRelationalData> listOfSymmetries = new ArrayList<ISymmetryRelationalData>();
	
	//Getters
	@Override
	public boolean getNewDescriptorWillCoverTheFullString() {
		return newDescriptorWillCoverTheFullString;
	}	
	
	@Override
	public List<IEnumerationRelationalData> getListOfEnumerations() {
		return listOfEnumerations;
	}

	@Override
	public List<ISequenceRelationalData> getListOfSequences() {
		return listOfSequences;
	}

	@Override
	public List<ISymmetryRelationalData> getListOfSymmetries() {
		return listOfSymmetries;
	}	
	
	//Setters
	@Override
	public void setNewDescriptorWillCoverTheWholeString(boolean newDescriptorWillCoverTheFullString) {
		this.newDescriptorWillCoverTheFullString = newDescriptorWillCoverTheFullString;
	}
	
	@Override
	public void addEnumeration(IEnumerationRelationalData enumerationRelationalData) {
		listOfEnumerations.add(enumerationRelationalData);
	}
	
	@Override
	public void addSequence(ISequenceRelationalData sequenceRelationalData) {
		listOfSequences.add(sequenceRelationalData);
	}
	
	@Override
	public void addSymmetry(ISymmetryRelationalData symmetryRelationalData) {
		listOfSymmetries.add(symmetryRelationalData);
	}
	
	@Override
	public void cleanRelationsFromSubDimensions() throws SynTreeGenerationException {
		List<IEnumerationRelationalData> cleanListOfEnumerations = new ArrayList<IEnumerationRelationalData>();
		List<ISequenceRelationalData> cleanListOfSequences = new ArrayList<ISequenceRelationalData>();
		List<ISymmetryRelationalData> cleanListOfSymmetries = new ArrayList<ISymmetryRelationalData>();
		for (IEnumerationRelationalData enumerationData : listOfEnumerations) {
			String dimension = enumerationData.getIndexedPath();
			if (!dimension.contains("groups"))
				cleanListOfEnumerations.add(enumerationData);
		}
		for (ISequenceRelationalData sequenceData : listOfSequences) {
			String dimension = sequenceData.getIndexedPath();
			if (!dimension.contains("groups"))
				cleanListOfSequences.add(sequenceData);
		}	
		for (ISymmetryRelationalData symmetryData : listOfSymmetries) {
			String dimension = symmetryData.getIndexedPath();
			if (!dimension.contains("groups"))
				cleanListOfSymmetries.add(symmetryData);
		}
		listOfEnumerations = cleanListOfEnumerations;
		listOfSequences = cleanListOfSequences;
		listOfSymmetries = cleanListOfSymmetries;
	}
	
	@Override
	public void clear() {
		listOfEnumerations.clear();
		listOfSequences.clear();
		listOfSymmetries.clear();
	}

}
