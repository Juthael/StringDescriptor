package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

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
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.GroupsBuilderInterface;
import syntacticTreesGeneration.interfaces.RelationDataContainerInterface;
import syntacticTreesGeneration.interfaces.RelationalDataInterface;
import syntacticTreesGeneration.interfaces.RelationsBuilderInterface;

public class RelationsBuilderV1 implements RelationsBuilderInterface {

	private final RelationDataContainerInterface relationDataContainer;
	private final ArrayList<Group> listOfGroups;
	
	public RelationsBuilderV1(RelationDataContainerInterface relationDataContainer, ArrayList<Group> listOfGroups) {
		this.relationDataContainer = relationDataContainer;
		this.listOfGroups = listOfGroups;
	}
	
	@Override
	public Relations getRelations() throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		ArrayList<Dimension> listOfDimensions = buildListOfDimensions(relationDataContainer);
		ArrayList<Relation> listOfRelations = buildListOfRelations(relationDataContainer);
		GroupsBuilderInterface groupsBuilder = new GroupsBuilderV1(listOfGroups);
		Groups groups = groupsBuilder.getGroups();
		HowManyDimensions howManyDimensions = buildHowManyDimensions(listOfDimensions);
		HowManyRelations howManyRelations = buildHowManyRelations(listOfRelations);
		Relations relations = new Relations(false, groups, howManyDimensions, howManyRelations);
		return relations;
	}
	
	private ArrayList<Dimension> buildListOfDimensions(RelationDataContainerInterface relationDataContainer){
		ArrayList<Dimension> listOfDimensions = new ArrayList<Dimension>();
		for (EnumerationRelationalDataInterface enumerationRelationalData : relationDataContainer.getListOfEnumerations()) {
			for (String dimensionString : enumerationRelationalData.getDimensions()) {
				Dimension dimension = new Dimension(false, dimensionString);
				listOfDimensions.add(dimension);
			}
		}
		return listOfDimensions;
	}
	
	private HowManyDimensions buildHowManyDimensions(ArrayList<Dimension> listOfDimensions) 
			throws DescriptorsBuilderCriticalException {
		HowManyDimensions howManyDimensions;
		if (listOfDimensions.size() <= DescGenSettings.MAX_NB_OF_DIMENSIONS_IN_RELATIONS) {
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
				default : throw new DescriptorsBuilderCriticalException(
						"RelationsBuilder : listOfDimensions size is illegal.");
			}
		} else throw new DescriptorsBuilderCriticalException("RelationsBuilder : listOfDimensions size is empty.");
		return howManyDimensions;		
	}	
	
	private ArrayList<Relation> buildListOfRelations(RelationDataContainerInterface relationDataContainer) 
			throws DescriptorsBuilderCriticalException {
		ArrayList<Relation> listOfRelations = new ArrayList<Relation>();
		for (EnumerationRelationalDataInterface enumerationRelationalData : relationDataContainer.getListOfEnumerations()) {
			Relation relation;
			ArrayList<RelationalDataInterface> listOfRelationalData = new ArrayList<RelationalDataInterface>();
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
			relation = RelationBuilderV1.buildRelation(listOfRelationalData);
			listOfRelations.add(relation);
		}
		return listOfRelations;		
	}
	
	private HowManyRelations buildHowManyRelations(ArrayList<Relation> listOfRelations) throws DescriptorsBuilderCriticalException {
		HowManyRelations howManyRelations;
		if (listOfRelations.size() <= DescGenSettings.MAX_NB_OF_RELATION) {
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
				default : throw new DescriptorsBuilderCriticalException(
						"RelationsBuilder : listOfRelations size is illegal.");
			}
		} else throw new DescriptorsBuilderCriticalException("RelationsBuilder : list Of relations size is illegal.");
		return howManyRelations;
	}	
	
}
