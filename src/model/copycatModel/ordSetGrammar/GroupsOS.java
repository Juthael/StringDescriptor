package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.NonMinimalRelevantSetElement;

public class GroupsOS extends NonMinimalRelevantSetElement implements ISetElement {

	private static final String NAME = "groups";
	private SizeOS size;
	private HowManyGroupsOS groupHM;
	
	public GroupsOS(String elementID, SizeOS size, HowManyGroupsOS groupHM) {
		super(elementID);
		this.size = size;
		this.groupHM = groupHM;
	}

	@Override
	protected List<IElement> getListOfComponents() {
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
