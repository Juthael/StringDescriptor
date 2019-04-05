package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import copycatModel.grammar.Group;
import exceptions.DescriptorsBuilderException;
import syntacticTreesGeneration.IGroupBuilder;
import syntacticTreesGeneration.IGroupsBuilderRelational;
import syntacticTreesGeneration.IRelationDataContainer;

public class GroupsBuilderRelationalImpl extends GroupsBuilderImpl implements IGroupsBuilderRelational {

	private IRelationDataContainer relationDataContainer;
	private IGroupBuilder groupBuilder;
	
	public GroupsBuilderRelationalImpl(List<Group> listOfGroups, IRelationDataContainer relationDataContainer) 
			throws DescriptorsBuilderException, CloneNotSupportedException {
		super(listOfGroups);
		this.relationDataContainer = relationDataContainer;
		groupBuilder = new GroupBuilderImpl(super.listOfGroups, this.relationDataContainer);
		Group group = groupBuilder.getGroup();
		List<Group> newListOfGroups = new ArrayList<Group>();
		newListOfGroups.add(group);
		super.listOfGroups = newListOfGroups;
	}
	
	public GroupsBuilderRelationalImpl(List<Group> listOfGroups, IRelationDataContainer relationDataContainer, 
			boolean listOfGroupsCoverTheFullString) throws DescriptorsBuilderException, CloneNotSupportedException {
		super(listOfGroups, listOfGroupsCoverTheFullString);
		this.relationDataContainer = relationDataContainer;
		groupBuilder = new GroupBuilderImpl(super.listOfGroups, this.relationDataContainer);
		Group group = groupBuilder.getGroup();
		List<Group> newListOfGroups = new ArrayList<Group>();
		newListOfGroups.add(group);
		super.listOfGroups = newListOfGroups;
	}	

}
