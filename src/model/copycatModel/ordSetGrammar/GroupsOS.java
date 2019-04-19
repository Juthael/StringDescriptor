package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantLowerSetElement;

public class GroupsOS extends NonMinimalRelevantLowerSetElement implements ILowerSetElement {

	private static final String NAME = "groups";
	private ISizeOS size;
	private HowManyGroupsOS groupHM;
	
	public GroupsOS(String elementID, ISizeOS size, HowManyGroupsOS groupHM) {
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
