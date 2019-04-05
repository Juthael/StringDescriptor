package syntacticTreesGeneration;

import java.util.List;

import exceptions.DescriptorsBuilderException;

public interface IGen2Size1RelationDataContainerBuilder {

	List<IRelationDataContainer> getListOfRelationDataContainers() throws DescriptorsBuilderException;

}