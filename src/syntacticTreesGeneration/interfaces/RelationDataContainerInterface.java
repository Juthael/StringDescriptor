package syntacticTreesGeneration.interfaces;

import java.util.ArrayList;

import exceptions.DescriptorsBuilderCriticalException;

public interface RelationDataContainerInterface {

	//Getters
	boolean getNewDescriptorWillCoverTheFullString();
	
	ArrayList<EnumerationRelationalDataInterface> getListOfEnumerations();

	ArrayList<SequenceRelationalDataInterface> getListOfSequences();

	ArrayList<SymmetryRelationalDataInterface> getListOfSymmetries();

	//Setters
	void setNewDescriptorWillCoverTheWholeString(boolean newDescriptorWillCoverTheFullString);	
	
	void addEnumeration(EnumerationRelationalDataInterface enumerationRelationalData);

	void addSequence(SequenceRelationalDataInterface sequenceRelationalData);

	void addSymmetry(SymmetryRelationalDataInterface symmetryRelationalData);

	void cleanValuesRedundancies() throws DescriptorsBuilderCriticalException;
	
	void clear();

	

	

}