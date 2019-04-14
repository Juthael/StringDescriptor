package syntacticTreesGeneration.impl;

import java.util.List;

import copycatModel.synTreeModel.grammar.Group;
import copycatModel.synTreeModel.grammar.Position;
import copycatModel.synTreeModel.grammar.Relations;
import copycatModel.synTreeModel.grammar.Size;
import exceptions.SynTreeGenerationException;
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
	public Group getGroup() throws SynTreeGenerationException, CloneNotSupportedException {
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
