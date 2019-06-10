package syntacticTreesGeneration;

import java.util.List;

import model.copycatModel.synTreeGrammar.CharString;

public interface IListOfDescriptorsBuilder {

	List<CharString> getListOfComprehensiveDescriptors();
	
	List<CharString> getListOfDescriptorsWithAbstractComponents();

}