package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import copycatModel.grammar.Dimension;
import copycatModel.grammar.DimensionX10;
import copycatModel.grammar.DimensionX2;
import copycatModel.grammar.DimensionX3;
import copycatModel.grammar.DimensionX4;
import copycatModel.grammar.DimensionX5;
import copycatModel.grammar.DimensionX6;
import copycatModel.grammar.DimensionX7;
import copycatModel.grammar.DimensionX8;
import copycatModel.grammar.DimensionX9;
import copycatModel.grammar.Group;
import copycatModel.grammar.Groups;
import copycatModel.grammar.HowManyDimensions;
import copycatModel.grammar.HowManyRelations;
import copycatModel.grammar.Relation;
import copycatModel.grammar.RelationX10;
import copycatModel.grammar.RelationX2;
import copycatModel.grammar.RelationX3;
import copycatModel.grammar.RelationX4;
import copycatModel.grammar.RelationX5;
import copycatModel.grammar.RelationX6;
import copycatModel.grammar.RelationX7;
import copycatModel.grammar.RelationX8;
import copycatModel.grammar.RelationX9;
import copycatModel.grammar.Relations;
import exceptions.SynTreeGenerationException;
import settings.Settings;
import syntacticTreesGeneration.IGroupsBuilder;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.IRelationalData;
import syntacticTreesGeneration.IRelationsBuilder;

public class RelationsBuilderImpl implements IRelationsBuilder {

	private final IRelationDataContainer relationDataContainer;
	private final List<Group> listOfGroups;
	
	public RelationsBuilderImpl(IRelationDataContainer relationDataContainer, List<Group> listOfGroups) {
		this.relationDataContainer = relationDataContainer;
		this.listOfGroups = listOfGroups;
	}
	
	@Override
	public Relations getRelations() throws SynTreeGenerationException, CloneNotSupportedException {
		List<Dimension> listOfDimensions = buildListOfDimensions(relationDataContainer);
		List<Relation> listOfRelations = buildListOfRelations(relationDataContainer);
		IGroupsBuilder groupsBuilder = new GroupsBuilderImpl(listOfGroups);
		Groups groups = groupsBuilder.getGroups();
		HowManyDimensions howManyDimensions = buildHowManyDimensions(listOfDimensions);
		HowManyRelations howManyRelations = buildHowManyRelations(listOfRelations);
		Relations relations = new Relations(false, groups, howManyDimensions, howManyRelations);
		return relations;
	}
	
	private List<Dimension> buildListOfDimensions(IRelationDataContainer relationDataContainer){
		List<Dimension> listOfDimensions = new ArrayList<Dimension>();
		for (IEnumerationRelationalData enumerationRelationalData : relationDataContainer.getListOfEnumerations()) {
			for (String dimensionString : enumerationRelationalData.getDimensions()) {
				Dimension dimension = new Dimension(false, dimensionString);
				listOfDimensions.add(dimension);
			}
		}
		return listOfDimensions;
	}
	
	private HowManyDimensions buildHowManyDimensions(List<Dimension> listOfDimensions) 
			throws SynTreeGenerationException {
		HowManyDimensions howManyDimensions;
		if (listOfDimensions.size() <= Settings.MAX_NB_OF_DIMENSIONS_IN_RELATIONS) {
			switch (listOfDimensions.size()) {
				case 1 : 
					howManyDimensions = listOfDimensions.get(0);
					break;
				case 2 :
					howManyDimensions = new DimensionX2(false, listOfDimensions.get(0), listOfDimensions.get(1));
					break;
				case 3 : 
					howManyDimensions = new DimensionX3(false, listOfDimensions.get(0), listOfDimensions.get(1), 
							listOfDimensions.get(2));
					break;
				case 4 : 
					howManyDimensions = new DimensionX4(false, listOfDimensions.get(0), listOfDimensions.get(1), 
							listOfDimensions.get(2), listOfDimensions.get(3));
					break;
				case 5 : 
					howManyDimensions = new DimensionX5(false, listOfDimensions.get(0), listOfDimensions.get(1), 
							listOfDimensions.get(2), listOfDimensions.get(3), listOfDimensions.get(4));
					break;
				case 6 : 
					howManyDimensions = new DimensionX6(false, listOfDimensions.get(0), listOfDimensions.get(1), 
							listOfDimensions.get(2), listOfDimensions.get(3), listOfDimensions.get(4), listOfDimensions.get(5));
					break;
				case 7 : 
					howManyDimensions = new DimensionX7(false, listOfDimensions.get(0), listOfDimensions.get(1), 
							listOfDimensions.get(2), listOfDimensions.get(3), listOfDimensions.get(4), listOfDimensions.get(5), 
							listOfDimensions.get(6));
					break;
				case 8 : 
					howManyDimensions = new DimensionX8(false, listOfDimensions.get(0), listOfDimensions.get(1), 
							listOfDimensions.get(2), listOfDimensions.get(3), listOfDimensions.get(4), listOfDimensions.get(5), 
							listOfDimensions.get(6), listOfDimensions.get(7));
					break;
				case 9 : 
					howManyDimensions = new DimensionX9(false, listOfDimensions.get(0), listOfDimensions.get(1), 
							listOfDimensions.get(2), listOfDimensions.get(3), listOfDimensions.get(4), listOfDimensions.get(5), 
							listOfDimensions.get(6), listOfDimensions.get(7), listOfDimensions.get(8));
					break;	
				case 10 : 
					howManyDimensions = new DimensionX10(false, listOfDimensions.get(0), listOfDimensions.get(1), 
							listOfDimensions.get(2), listOfDimensions.get(3), listOfDimensions.get(4), listOfDimensions.get(5), 
							listOfDimensions.get(6), listOfDimensions.get(7), listOfDimensions.get(8), listOfDimensions.get(9));
					break;					
				default : throw new SynTreeGenerationException(
						"RelationsBuilder : listOfDimensions size is illegal.");
			}
		} else throw new SynTreeGenerationException("RelationsBuilder : listOfDimensions size is empty.");
		return howManyDimensions;		
	}	
	
	private List<Relation> buildListOfRelations(IRelationDataContainer relationDataContainer) 
			throws SynTreeGenerationException {
		List<Relation> listOfRelations = new ArrayList<Relation>();
		for (IEnumerationRelationalData enumerationRelationalData : relationDataContainer.getListOfEnumerations()) {
			Relation relation;
			List<IRelationalData> listOfRelationalData = new ArrayList<IRelationalData>();
			int sequenceIndex = -1;
			int symmetryIndex = -1;
			int i = 0;
			while (i < relationDataContainer.getListOfSequences().size()  && sequenceIndex == -1) {
				if (relationDataContainer.getListOfSequences().get(i).getDimensions().equals(
						enumerationRelationalData.getDimensions()))
					sequenceIndex = i;
				i++;
			}
			int j = 0;
			while (j < relationDataContainer.getListOfSymmetries().size() && symmetryIndex == -1) {
				if (relationDataContainer.getListOfSymmetries().get(j).getDimensions().equals(
						enumerationRelationalData.getDimensions()))
					symmetryIndex = j;
				j++;
			}
			listOfRelationalData.add(enumerationRelationalData);
			if (sequenceIndex != -1)
				listOfRelationalData.add(relationDataContainer.getListOfSequences().get(sequenceIndex));
			if (symmetryIndex != -1)
				listOfRelationalData.add(relationDataContainer.getListOfSymmetries().get(symmetryIndex));
			relation = RelationBuilderImpl.buildRelation(listOfRelationalData);
			listOfRelations.add(relation);
		}
		return listOfRelations;		
	}
	
	private HowManyRelations buildHowManyRelations(List<Relation> listOfRelations) throws SynTreeGenerationException {
		HowManyRelations howManyRelations;
		if (listOfRelations.size() <= Settings.MAX_NB_OF_RELATION) {
			switch (listOfRelations.size()) {
				case 1 : 
					howManyRelations = listOfRelations.get(0);
					break;
				case 2 : 
					howManyRelations = new RelationX2(false, listOfRelations.get(0), listOfRelations.get(1));
					break;
				case 3 : 
					howManyRelations = new RelationX3(false, listOfRelations.get(0), listOfRelations.get(1), 
							listOfRelations.get(2));
					break;
				case 4 :
					howManyRelations = new RelationX4(false, listOfRelations.get(0), listOfRelations.get(1), 
							listOfRelations.get(2), listOfRelations.get(3));
					break;
				case 5 : 
					howManyRelations = new RelationX5(false, listOfRelations.get(0), listOfRelations.get(1), 
							listOfRelations.get(2), listOfRelations.get(3), listOfRelations.get(4));
					break;
				case 6 : 
					howManyRelations = new RelationX6(false, listOfRelations.get(0), listOfRelations.get(1), 
							listOfRelations.get(2), listOfRelations.get(3), listOfRelations.get(4), 
							listOfRelations.get(5));
					break;
				case 7 : 
					howManyRelations = new RelationX7(false, listOfRelations.get(0), listOfRelations.get(1), 
							listOfRelations.get(2), listOfRelations.get(3), listOfRelations.get(4), 
							listOfRelations.get(5), listOfRelations.get(6));
					break;	
				case 8 : 
					howManyRelations = new RelationX8(false, listOfRelations.get(0), listOfRelations.get(1), 
							listOfRelations.get(2), listOfRelations.get(3), listOfRelations.get(4), 
							listOfRelations.get(5), listOfRelations.get(6), listOfRelations.get(7));
					break;	
				case 9 : 
					howManyRelations = new RelationX9(false, listOfRelations.get(0), listOfRelations.get(1), 
							listOfRelations.get(2), listOfRelations.get(3), listOfRelations.get(4), 
							listOfRelations.get(5), listOfRelations.get(6), listOfRelations.get(7), 
							listOfRelations.get(8));
					break;			
				case 10 : 
					howManyRelations = new RelationX10(false, listOfRelations.get(0), listOfRelations.get(1), 
							listOfRelations.get(2), listOfRelations.get(3), listOfRelations.get(4), 
							listOfRelations.get(5), listOfRelations.get(6), listOfRelations.get(7), 
							listOfRelations.get(8), listOfRelations.get(9));
					break;						
				default : throw new SynTreeGenerationException(
						"RelationsBuilder : listOfRelations size is illegal.");
			}
		} else throw new SynTreeGenerationException("RelationsBuilder : list Of relations size is illegal.");
		return howManyRelations;
	}	
	
}
