package syntacticTreesGeneration.impl;

import java.util.List;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Group;
import model.copycatModel.synTreeGrammar.Position;
import model.copycatModel.synTreeGrammar.Relations;
import model.copycatModel.synTreeGrammar.Size;
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
	
	public GroupBuilderImpl(List<Group> listOfGroups, IRelationDataContainer relationDataContainer, 
			boolean listOfGroupsCoverTheWholeString) {
		this.listOfGroups = listOfGroups;
		this.relationDataContainer = relationDataContainer;
	}	
	
	@Override
	public Group getGroup() throws SynTreeGenerationException, CloneNotSupportedException {
		Group group;
		Size size = new Size(Integer.toString(listOfGroups.size()));
		Position position = new Position(Settings.AWAITING_POSITION_VALUE);
		Relations relations;
		IRelationsBuilder relationsBuilder = new RelationsBuilderImpl(relationDataContainer, listOfGroups);
		relations = relationsBuilder.getRelations();
		group = new Group(false, size, position, relations);
		return group;		
	}

}
