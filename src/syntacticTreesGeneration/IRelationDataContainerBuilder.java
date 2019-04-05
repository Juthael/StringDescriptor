package syntacticTreesGeneration;

import exceptions.SynTreeGenerationException;

public interface IRelationDataContainerBuilder {

	IRelationDataContainer getRelationDataContainer() throws SynTreeGenerationException;

}