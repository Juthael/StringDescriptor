package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import copycatModel.grammar.Group;
import copycatModel.grammar.Position;
import copycatModel.grammar.Relations;
import copycatModel.grammar.Size;
import exceptions.DescriptorsBuilderException;
import settings.Settings;
import syntacticTreesGeneration.IGroupBuilder;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.IRelationsBuilder;

public class GroupBuilderImpl implements IGroupBuilder {

	private final List<Group> listOfGroups;
	private final IRelationDataContainer relationDataContainer;
	
	public GroupBuilderImpl(List<Group> listOfGroups, IRelationDataContainer relationDataContainer) {
		this.listOfGroups = listOfGroups;
		this.relationDataContainer = relationDataContainer;
	}
	
	@Override
	public Group getGroup() throws DescriptorsBuilderException, CloneNotSupportedException {
		Group group;
		Size size = new Size(false, Integer.toString(listOfGroups.size()));
		Position position = new Position(false, Settings.AWAITING_POSITION_VALUE);
		Relations relations;
		IRelationsBuilder relationsBuilder = new RelationsBuilderImpl(relationDataContainer, listOfGroups);
		relations = relationsBuilder.getRelations();
		group = new Group(false, size, position, relations);
		return group;		
	}

}