package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.AbstractNonMinimalOS;

public class GroupsOS extends AbstractNonMinimalOS implements IOrderedSet {

	private static final String NAME = "groups";
	private SizeOS size;
	private List<GroupOS> listOfGroups;
	
	public GroupsOS(String elementID, SizeOS size, List<GroupOS> listOfGroups) {
		super(elementID);
		this.size = size;
		this.listOfGroups = listOfGroups;
	}
	
	public GroupsOS(String elementID, boolean isCodingElement, SizeOS size, List<GroupOS> listOfGroups) {
		super(elementID, isCodingElement);
		this.size = size;
		this.listOfGroups = listOfGroups;
	}	

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(size);
		listOfComponents.addAll(listOfGroups);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!size.equals(idToIOrderedSet.get(size.getElementID())))
			size = (SizeOS) idToIOrderedSet.get(size.getElementID());
		for (GroupOS group : listOfGroups) {
			if (!group.equals(idToIOrderedSet.get(group.getElementID())))
				group = (GroupOS) idToIOrderedSet.get(group.getElementID());
			group.eliminateRedundancies(idToIOrderedSet);
		}
	}		

}
