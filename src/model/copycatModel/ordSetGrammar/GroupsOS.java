package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalRelevantOS;

public class GroupsOS extends NonMinimalRelevantOS implements IOrderedSet {

	private static final String NAME = "groups";
	private SizeOS size;
	private HowManyGroupsOS groupHM;
	
	public GroupsOS(String elementID, SizeOS size, HowManyGroupsOS groupHM) {
		super(elementID);
		this.size = size;
		this.groupHM = groupHM;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(size);
		listOfComponents.add(groupHM);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
