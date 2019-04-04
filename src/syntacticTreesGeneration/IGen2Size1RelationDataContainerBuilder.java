package syntacticTreesGeneration.interfaces;

import java.util.ArrayList;

import exceptions.DescriptorsBuilderCriticalException;

public interface Gen2Size1RelationDataContainerBuilderInterface {

	ArrayList<RelationDataContainerInterface> getListOfRelationDataContainers() throws DescriptorsBuilderCriticalException;

}