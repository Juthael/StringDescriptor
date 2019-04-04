package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

import copycatModel.grammar.Group;
import copycatModel.grammar.GroupX10;
import copycatModel.grammar.GroupX11;
import copycatModel.grammar.GroupX12;
import copycatModel.grammar.GroupX2;
import copycatModel.grammar.GroupX3;
import copycatModel.grammar.GroupX4;
import copycatModel.grammar.GroupX5;
import copycatModel.grammar.GroupX6;
import copycatModel.grammar.GroupX7;
import copycatModel.grammar.GroupX8;
import copycatModel.grammar.GroupX9;
import copycatModel.grammar.Groups;
import copycatModel.grammar.HowManyGroups;
import copycatModel.grammar.Size;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.interfaces.GroupsBuilderInterface;

public class GroupsBuilderV1 implements GroupsBuilderInterface {

	protected ArrayList<Group> listOfGroups;
	protected boolean listOfGroupsCoverTheFullString = false;
	
	public GroupsBuilderV1(ArrayList<Group> listOfGroups) {
		this.listOfGroups = listOfGroups;
	}
	
	public GroupsBuilderV1(ArrayList<Group> listOfGroups, boolean listOfGroupsCoverTheFullString) 
			throws DescriptorsBuilderCriticalException {
		if (listOfGroupsCoverTheFullString == DescGenSettings.LIST_OF_GROUPS_COVER_THE_FULL_STRING) {
			this.listOfGroups = listOfGroups;
			this.listOfGroupsCoverTheFullString = DescGenSettings.LIST_OF_GROUPS_COVER_THE_FULL_STRING;
		} else throw new DescriptorsBuilderCriticalException("GroupsBuilder : constructor illegal parameter value");
	}
	
	@Override
	public Groups getGroups() throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		Groups groups;
		HowManyGroups howManyGroups;
		Size size;
		int listOfGroupsSize = listOfGroups.size();
		if (listOfGroupsSize <= DescGenSettings.MAX_NB_OF_GROUPS_IN_RELATIONS) {
			switch (listOfGroupsSize) {
				case 1 : 
					size = new Size(false, "1");
					howManyGroups = (Group) listOfGroups.get(0).clone();
					if (listOfGroupsCoverTheFullString == false) {
						groups = new Groups(false, size, howManyGroups);
					}
					else groups = new Groups(false, size, howManyGroups, DescGenSettings.FULL_STRING_GROUP);
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
				default : throw new DescriptorsBuilderCriticalException("GroupsBuilder : listOfGroups size is illegal.");
			}
		} else throw new DescriptorsBuilderCriticalException("GroupsBuilder : listOfGroups size is illegal.");
		return groups;	
	}	

}
