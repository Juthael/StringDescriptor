package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

import copycatModel.grammar.Group;
import copycatModel.grammar.Position;
import copycatModel.grammar.Relations;
import copycatModel.grammar.Size;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.interfaces.GroupBuilderInterface;
import syntacticTreesGeneration.interfaces.RelationDataContainerInterface;
import syntacticTreesGeneration.interfaces.RelationsBuilderInterface;

public class GroupBuilderV1 implements GroupBuilderInterface {

	private final ArrayList<Group> listOfGroups;
	private final RelationDataContainerInterface relationDataContainer;
	
	public GroupBuilderV1(ArrayList<Group> listOfGroups, RelationDataContainerInterface relationDataContainer) {
		this.listOfGroups = listOfGroups;
		this.relationDataContainer = relationDataContainer;
	}
	
	@Override
	public Group getGroup() throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		Group group;
		Size size = new Size(false, Integer.toString(listOfGroups.size()));
		Position position = new Position(false, DescGenSettings.AWAITING_POSITION_VALUE);
		Relations relations;
		RelationsBuilderInterface relationsBuilder = new RelationsBuilderV1(relationDataContainer, listOfGroups);
		relations = relationsBuilder.getRelations();
		group = new Group(false, size, position, relations);
		return group;		
	}

}
