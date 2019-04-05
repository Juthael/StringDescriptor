package syntacticTreesGeneration;

import java.util.List;

import exceptions.DescriptorsBuilderException;

public interface IRelationDataContainer {

	//Getters
	boolean getNewDescriptorWillCoverTheFullString();
	
	List<IEnumerationRelationalData> getListOfEnumerations();

	List<ISequenceRelationalData> getListOfSequences();

	List<ISymmetryRelationalData> getListOfSymmetries();

	//Setters
	void setNewDescriptorWillCoverTheWholeString(boolean newDescriptorWillCoverTheFullString);	
	
	void addEnumeration(IEnumerationRelationalData enumerationRelationalData);

	void addSequence(ISequenceRelationalData sequenceRelationalData);

	void addSymmetry(ISymmetryRelationalData symmetryRelationalData);

	void cleanValuesRedundancies() throws DescriptorsBuilderException;
	
	void clear();

	

	

}