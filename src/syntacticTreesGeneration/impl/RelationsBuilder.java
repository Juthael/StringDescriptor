package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Components;
import model.copycatModel.synTreeGrammar.Dimension;
import model.copycatModel.synTreeGrammar.DimensionX;
import model.copycatModel.synTreeGrammar.Frame;
import model.copycatModel.synTreeGrammar.IOneOrManyDimensions;
import model.copycatModel.synTreeGrammar.IOneOrManyRelations;
import model.copycatModel.synTreeGrammar.Relation;
import model.copycatModel.synTreeGrammar.RelationX;
import model.copycatModel.synTreeGrammar.Relations;
import settings.Settings;
import syntacticTreesGeneration.IComponentsBuilder;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.IRelationalData;
import syntacticTreesGeneration.IRelationsBuilder;

public class RelationsBuilder implements IRelationsBuilder {

	private final IRelationDataContainer relationDataContainer;
	private final List<Frame> listOfFrames;
	
	public RelationsBuilder(IRelationDataContainer relationDataContainer, List<Frame> listOfFrames) {
		this.relationDataContainer = relationDataContainer;
		this.listOfFrames = listOfFrames;
	}
	
	@Override
	public Relations getRelations() throws SynTreeGenerationException, CloneNotSupportedException {
		List<Dimension> listOfDimensions = buildListOfDimensions(relationDataContainer);
		List<Relation> listOfRelations = buildListOfRelations(relationDataContainer);
		IComponentsBuilder componentsBuilder = new ComponentsBuilder(listOfFrames);
		Components components = componentsBuilder.getComponents();
		IOneOrManyDimensions howManyDimensions = buildHowManyDimensions(listOfDimensions);
		IOneOrManyRelations howManyRelations = getHowManyRelations(listOfRelations);
		Relations relations = new Relations(components, howManyDimensions, howManyRelations);
		return relations;
	}
	
	private List<Dimension> buildListOfDimensions(IRelationDataContainer relationDataContainer) 
			throws CloneNotSupportedException{
		List<Dimension> listOfDimensions = new ArrayList<Dimension>();
		for (IEnumerationRelationalData enumerationRelationalData : relationDataContainer.getListOfEnumerations()) {
			Dimension dimension = new Dimension(enumerationRelationalData.getIndexedPath());
			listOfDimensions.add(dimension);
		}
		return listOfDimensions;
	}
	
	private IOneOrManyDimensions buildHowManyDimensions(List<Dimension> listOfDimensions) 
			throws SynTreeGenerationException {
		IOneOrManyDimensions howManyDimensions;
		if (!listOfDimensions.isEmpty() && listOfDimensions.size() <= Settings.MAX_NB_OF_DIMENSIONS_IN_RELATIONS) {
			if (listOfDimensions.size() == 1) {
				howManyDimensions = listOfDimensions.get(0);
			}
			else {
				howManyDimensions = new DimensionX(listOfDimensions);
			}
		} else throw new SynTreeGenerationException("RelationsBuilder : listOfDimensions size is illegal (" 
			+ listOfDimensions.size() + ").");
		return howManyDimensions;		
	}	
	
	private List<Relation> buildListOfRelations(IRelationDataContainer relationDataContainer) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		List<Relation> listOfRelations = new ArrayList<Relation>();
		for (IEnumerationRelationalData enumerationRelationalData : relationDataContainer.getListOfEnumerations()) {
			Relation relation;
			List<IRelationalData> listOfRelationalData = new ArrayList<IRelationalData>();
			int sequenceIndex = -1;
			int symmetryIndex = -1;
			int i = 0;
			while (i < relationDataContainer.getListOfSequences().size()  && sequenceIndex == -1) {
				if (relationDataContainer.getListOfSequences().get(i).getIndexedPath().equals(
						enumerationRelationalData.getIndexedPath()))
					sequenceIndex = i;
				i++;
			}
			int j = 0;
			while (j < relationDataContainer.getListOfSymmetries().size() && symmetryIndex == -1) {
				if (relationDataContainer.getListOfSymmetries().get(j).getIndexedPath().equals(
						enumerationRelationalData.getIndexedPath()))
					symmetryIndex = j;
				j++;
			}
			listOfRelationalData.add(enumerationRelationalData);
			if (sequenceIndex != -1)
				listOfRelationalData.add(relationDataContainer.getListOfSequences().get(sequenceIndex));
			if (symmetryIndex != -1)
				listOfRelationalData.add(relationDataContainer.getListOfSymmetries().get(symmetryIndex));
			relation = RelationBuilder.buildRelation(listOfRelationalData);
			listOfRelations.add(relation);
		}
		return listOfRelations;		
	}
	
	private IOneOrManyRelations getHowManyRelations(List<Relation> listOfRelations) throws SynTreeGenerationException {
		IOneOrManyRelations howManyRelations;
		if (!listOfRelations.isEmpty() && listOfRelations.size() <= Settings.MAX_NB_OF_RELATIONS) {
			if (listOfRelations.size() == 1) {
				howManyRelations = listOfRelations.get(0);
			}
			else howManyRelations = new RelationX(listOfRelations);
		} else throw new SynTreeGenerationException("RelationsBuilder.getHowManyRelations() : list Of relations size is illegal.");
		return howManyRelations;
	}	
	
}
