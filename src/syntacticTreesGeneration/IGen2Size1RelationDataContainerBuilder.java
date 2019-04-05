package syntacticTreesGeneration;

import java.util.List;

import exceptions.SynTreeGenerationException;

public interface IGen2Size1RelationDataContainerBuilder {

	List<IRelationDataContainer> getListOfRelationDataContainers() throws SynTreeGenerationException;

}