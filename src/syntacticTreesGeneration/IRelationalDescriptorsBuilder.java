package syntacticTreesGeneration;

import java.util.List;

import model.copycatModel.synTreeGrammar.CharString;

public interface IRelationalDescriptorsBuilder {

	List<CharString> getListOfDescriptorsCoveringTheWholeString();

}