package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.SetElementImpl;

public class GroupsOS extends SetElementImpl implements ISetElement {

	private SizeOS size;
	private HowManyGroupsOS groupHM;
	
	public GroupsOS(String elementID, SizeOS size, HowManyGroupsOS groupHM) {
		super(elementID);
		this.size = size;
		this.groupHM = groupHM;
	}

	@Override
	protected List<IElement> buildListOfComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescriptorName() {
		// TODO Auto-generated method stub
		return null;
	}

}
