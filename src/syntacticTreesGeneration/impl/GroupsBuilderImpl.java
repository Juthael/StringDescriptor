package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Group;
import model.copycatModel.synTreeGrammar.GroupX;
import model.copycatModel.synTreeGrammar.Groups;
import model.copycatModel.synTreeGrammar.HowManyGroups;
import model.copycatModel.synTreeGrammar.Size;
import settings.Settings;
import syntacticTreesGeneration.IGroupsBuilder;

public class GroupsBuilderImpl implements IGroupsBuilder {

	protected List<Group> listOfGroups;
	protected boolean listOfGroupsCoverTheFullString = false;
	
	public GroupsBuilderImpl(List<Group> listOfGroups) {
		this.listOfGroups = listOfGroups;
	}
	
	public GroupsBuilderImpl(List<Group> listOfGroups, boolean listOfGroupsCoverTheFullString) 
			throws SynTreeGenerationException {
		if (listOfGroupsCoverTheFullString == Settings.LIST_OF_GROUPS_COVER_THE_FULL_STRING) {
			this.listOfGroups = listOfGroups;
			this.listOfGroupsCoverTheFullString = Settings.LIST_OF_GROUPS_COVER_THE_FULL_STRING;
		} else throw new SynTreeGenerationException("GroupsBuilder : constructor illegal parameter value");
	}
	
	@Override
	public Groups getGroups() throws SynTreeGenerationException, CloneNotSupportedException {
		Groups groups;
		HowManyGroups howManyGroups;
		Size size;
		int listOfGroupsSize = listOfGroups.size();
		if (listOfGroupsSize <= Settings.MAX_NB_OF_GROUPS_IN_RELATIONS && listOfGroupsSize > 0) {
			if (listOfGroupsSize == 1) {
				size = new Size("1");
				howManyGroups = listOfGroups.get(0).clone();
				if (listOfGroupsCoverTheFullString == false) {
					groups = new Groups(size, howManyGroups);
				}
				else groups = new Groups(size, howManyGroups, Settings.FULL_STRING_GROUP);
			}
			else {
				size = new Size (Integer.toString(listOfGroupsSize));
				List<Group> cloneListOfGroups = new ArrayList<Group>();
				for (Group group : listOfGroups)
					cloneListOfGroups.add(group.clone());
				howManyGroups = new GroupX(cloneListOfGroups);
				groups = new Groups(size, howManyGroups);
				if (listOfGroupsCoverTheFullString == true) {
					groups.setIsCodingByDecomposition(Settings.THIS_IS_A_CODING_ELEMENT);
				}
			}
		} else throw new SynTreeGenerationException("GroupsBuilder : listOfGroups size is illegal.");
		return groups;	
	}	

}
