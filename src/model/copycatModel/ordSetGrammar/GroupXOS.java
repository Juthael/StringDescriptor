package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.IrrelevantSetElementImpl;

public class GroupXOS extends IrrelevantSetElementImpl implements HowManyGroupsOS {

	private List<GroupOS> listOfGroups;
	
	public GroupXOS(String elementID, List<GroupOS> listOfGroups) {
		super(elementID);
		this.listOfGroups = listOfGroups;
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
