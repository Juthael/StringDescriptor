package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.NonMinimalIrrelevantOS;

public class GroupXOS extends NonMinimalIrrelevantOS implements HowManyGroupsOS {

	private static final String PARTIAL_NAME = "groupX";
	private List<GroupOS> listOfGroups;
	
	public GroupXOS(String elementID, List<GroupOS> listOfGroups) {
		super(elementID);
		this.listOfGroups = listOfGroups;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.addAll(listOfGroups);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		String name = PARTIAL_NAME.concat(Integer.toString(listOfGroups.size()));
		return name;
	}

}
