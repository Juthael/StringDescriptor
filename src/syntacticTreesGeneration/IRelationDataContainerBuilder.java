package syntacticTreesGeneration;

import exceptions.DescriptorsBuilderException;

public interface IRelationDataContainerBuilder {

	IRelationDataContainer getRelationDataContainer() throws DescriptorsBuilderException;

}