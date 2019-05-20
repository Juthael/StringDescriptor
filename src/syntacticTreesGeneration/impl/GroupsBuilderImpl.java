package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Group;
import model.copycatModel.synTreeGrammar.GroupX;
import model.copycatModel.synTreeGrammar.Groups;
import model.copycatModel.synTreeGrammar.HowManyGroups;
import model.copycatModel.synTreeGrammar.Size;
import model.generalModel.IElement;
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
	
	@SuppressWarnings("unused")
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
				else { 
					List<IElement> listOfCodingComponents = howManyGroups.getListOfCodingElements();
					if (listOfCodingComponents.isEmpty() && Settings.CODING_ELEM_ARE_SMALLEST_FULLSTRING_GROUPS_COMPONENTS) {
						howManyGroups.setIsCodingElement(true);
					}
					groups = new Groups(size, howManyGroups, Settings.FULL_STRING_GROUP);
				}
			}
			else {
				size = new Size (Integer.toString(listOfGroupsSize));
				List<Group> cloneListOfGroups = new ArrayList<Group>();
				for (Group group : listOfGroups) {
					Group groupClone = group.clone();
					if (listOfGroupsCoverTheFullString == true 
							&& Settings.CODING_ELEM_ARE_SMALLEST_FULLSTRING_GROUPS_COMPONENTS) {
						groupClone.setIsCodingElement(true);
					}
					cloneListOfGroups.add(groupClone);
				}					
				howManyGroups = new GroupX(cloneListOfGroups);
				groups = new Groups(size, howManyGroups);
				// HERE
				List<IElement> test = groups.getListOfCodingElements();
				//HERE
				if (listOfGroupsCoverTheFullString == true && Settings.CODING_ELEM_IS_GREATEST_GROUPS == true) {
					groups.setIsCodingElement(Settings.THIS_IS_A_CODING_ELEMENT);
				}
			}
		} else throw new SynTreeGenerationException("GroupsBuilder : listOfGroups size is illegal.");
		return groups;	
	}	

}
