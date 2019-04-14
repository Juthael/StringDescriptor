package syntacticTreesGeneration.impl;

import java.util.List;

import copycatModel.synTreeModel.grammar.Group;
import copycatModel.synTreeModel.grammar.GroupX10;
import copycatModel.synTreeModel.grammar.GroupX11;
import copycatModel.synTreeModel.grammar.GroupX12;
import copycatModel.synTreeModel.grammar.GroupX2;
import copycatModel.synTreeModel.grammar.GroupX3;
import copycatModel.synTreeModel.grammar.GroupX4;
import copycatModel.synTreeModel.grammar.GroupX5;
import copycatModel.synTreeModel.grammar.GroupX6;
import copycatModel.synTreeModel.grammar.GroupX7;
import copycatModel.synTreeModel.grammar.GroupX8;
import copycatModel.synTreeModel.grammar.GroupX9;
import copycatModel.synTreeModel.grammar.Groups;
import copycatModel.synTreeModel.grammar.HowManyGroups;
import copycatModel.synTreeModel.grammar.Size;
import exceptions.SynTreeGenerationException;
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
		if (listOfGroupsSize <= Settings.MAX_NB_OF_GROUPS_IN_RELATIONS) {
			switch (listOfGroupsSize) {
				case 1 : 
					size = new Size(false, "1");
					howManyGroups = (Group) listOfGroups.get(0).clone();
					if (listOfGroupsCoverTheFullString == false) {
						groups = new Groups(false, size, howManyGroups);
					}
					else groups = new Groups(false, size, howManyGroups, Settings.FULL_STRING_GROUP);
					break;
				case 2 :
					size = new Size(false, "2");
					howManyGroups = new GroupX2(false, listOfGroups.get(0).clone(), listOfGroups.get(1).clone());
					groups = new Groups(false, size, howManyGroups);
					break;
				case 3 : 
					size = new Size(false, "3");
					howManyGroups = new GroupX3(false, listOfGroups.get(0).clone(), listOfGroups.get(1).clone(), 
							listOfGroups.get(2).clone());
					groups = new Groups(false, size, howManyGroups);
					break;
				case 4 :
					size = new Size(false, "4");
					howManyGroups = new GroupX4(false, listOfGroups.get(0).clone(), listOfGroups.get(1).clone(), 
							listOfGroups.get(2).clone(), listOfGroups.get(3).clone());
					groups = new Groups(false, size, howManyGroups);
					break;
				case 5 :
					size = new Size(false, "5");
					howManyGroups = new GroupX5(false, listOfGroups.get(0).clone(), listOfGroups.get(1).clone(), 
							listOfGroups.get(2).clone(), listOfGroups.get(3).clone(), listOfGroups.get(4).clone());
					groups = new Groups(false, size, howManyGroups);
					break;
				case 6 :
					size = new Size(false, "6");
					howManyGroups = new GroupX6(false, listOfGroups.get(0).clone(), listOfGroups.get(1).clone(), 
							listOfGroups.get(2).clone(), listOfGroups.get(3).clone(), listOfGroups.get(4).clone(), 
							listOfGroups.get(5).clone());
					groups = new Groups(false, size, howManyGroups);					
					break;				
				case 7 :
					size = new Size(false, "7");
					howManyGroups = new GroupX7(false, listOfGroups.get(0).clone(), listOfGroups.get(1).clone(), 
							listOfGroups.get(2).clone(), listOfGroups.get(3).clone(), listOfGroups.get(4).clone(), 
							listOfGroups.get(5).clone(), listOfGroups.get(6).clone());
					groups = new Groups(false, size, howManyGroups);					
					break;					
				case 8 :
					size = new Size(false, "8");
					howManyGroups = new GroupX8(false, listOfGroups.get(0).clone(), listOfGroups.get(1).clone(), 
							listOfGroups.get(2).clone(), listOfGroups.get(3).clone(), listOfGroups.get(4).clone(), 
							listOfGroups.get(5).clone(), listOfGroups.get(6).clone(), listOfGroups.get(7).clone());
					groups = new Groups(false, size, howManyGroups);					
					break;			
				case 9 :
					size = new Size(false, "9");
					howManyGroups = new GroupX9(false, listOfGroups.get(0).clone(), listOfGroups.get(1).clone(), 
							listOfGroups.get(2).clone(), listOfGroups.get(3).clone(), listOfGroups.get(4).clone(), 
							listOfGroups.get(5).clone(), listOfGroups.get(6).clone(), listOfGroups.get(7).clone(), 
							listOfGroups.get(8).clone());
					groups = new Groups(false, size, howManyGroups);					
					break;			
				case 10 :
					size = new Size(false, "10");
					howManyGroups = new GroupX10(false, listOfGroups.get(0).clone(), listOfGroups.get(1).clone(), 
							listOfGroups.get(2).clone(), listOfGroups.get(3).clone(), listOfGroups.get(4).clone(), 
							listOfGroups.get(5).clone(), listOfGroups.get(6).clone(), listOfGroups.get(7).clone(), 
							listOfGroups.get(8).clone(), listOfGroups.get(9).clone());
					groups = new Groups(false, size, howManyGroups);					
					break;		
				case 11 :
					size = new Size(false, "11");
					howManyGroups = new GroupX11(false, listOfGroups.get(0).clone(), listOfGroups.get(1).clone(), 
							listOfGroups.get(2).clone(), listOfGroups.get(3).clone(), listOfGroups.get(4).clone(), 
							listOfGroups.get(5).clone(), listOfGroups.get(6).clone(), listOfGroups.get(7).clone(), 
							listOfGroups.get(8).clone(), listOfGroups.get(9).clone(), listOfGroups.get(10).clone());
					groups = new Groups(false, size, howManyGroups);					
					break;		
				case 12 :
					size = new Size(false, "12");
					howManyGroups = new GroupX12(false, listOfGroups.get(0).clone(), listOfGroups.get(1).clone(), 
							listOfGroups.get(2).clone(), listOfGroups.get(3).clone(), listOfGroups.get(4).clone(), 
							listOfGroups.get(5).clone(), listOfGroups.get(6).clone(), listOfGroups.get(7).clone(), 
							listOfGroups.get(8).clone(), listOfGroups.get(9).clone(), listOfGroups.get(10).clone(), 
							listOfGroups.get(11).clone());
					groups = new Groups(false, size, howManyGroups);					
					break;						
				default : throw new SynTreeGenerationException("GroupsBuilder : listOfGroups size is illegal.");
			}
		} else throw new SynTreeGenerationException("GroupsBuilder : listOfGroups size is illegal.");
		return groups;	
	}	

}
