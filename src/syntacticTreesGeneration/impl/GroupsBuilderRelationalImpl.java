package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

import copycatModel.grammar.Group;
import exceptions.DescriptorsBuilderCriticalException;
import syntacticTreesGeneration.interfaces.GroupBuilderInterface;
import syntacticTreesGeneration.interfaces.GroupsBuilder_Relational_Interface;
import syntacticTreesGeneration.interfaces.RelationDataContainerInterface;

public class GroupsBuilder_RelationalV1 extends GroupsBuilderV1 implements GroupsBuilder_Relational_Interface {

	private RelationDataContainerInterface relationDataContainer;
	private GroupBuilderInterface groupBuilder;
	
	public GroupsBuilder_RelationalV1(ArrayList<Group> listOfGroups, RelationDataContainerInterface relationDataContainer) 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		super(listOfGroups);
		this.relationDataContainer = relationDataContainer;
		groupBuilder = new GroupBuilderV1(super.listOfGroups, this.relationDataContainer);
		Group group = groupBuilder.getGroup();
		ArrayList<Group> newListOfGroups = new ArrayList<Group>();
		newListOfGroups.add(group);
		super.listOfGroups = newListOfGroups;
	}
	
	public GroupsBuilder_RelationalV1(ArrayList<Group> listOfGroups, RelationDataContainerInterface relationDataContainer, 
			boolean listOfGroupsCoverTheFullString) throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		super(listOfGroups, listOfGroupsCoverTheFullString);
		this.relationDataContainer = relationDataContainer;
		groupBuilder = new GroupBuilderV1(super.listOfGroups, this.relationDataContainer);
		Group group = groupBuilder.getGroup();
		ArrayList<Group> newListOfGroups = new ArrayList<Group>();
		newListOfGroups.add(group);
		super.listOfGroups = newListOfGroups;
	}	

}
