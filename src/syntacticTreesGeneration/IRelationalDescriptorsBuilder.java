package syntacticTreesGeneration;

import java.util.List;

import copycatModel.synTreeModel.grammar.CharString;

public interface IRelationalDescriptorsBuilder {

	List<CharString> getListOfDescriptorsCoveringTheWholeString();

}