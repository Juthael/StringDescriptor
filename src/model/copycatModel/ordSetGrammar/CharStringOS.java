package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.AbstractNonMinimalOS;

public class CharStringOS extends AbstractNonMinimalOS implements IOrderedSet {

	private static final String NAME = "charString";
	private DirectionOS direction;
	private StructureOS structure;
	private GroupsOS groups;
	
	public CharStringOS(String elementID, DirectionOS direction, StructureOS structure, GroupsOS groups) {
		super(elementID);
		this.direction = direction;
		this.structure = structure;
		this.groups = groups;
	}

	public CharStringOS(String elementID, boolean isCodingElement, boolean mayBeTheCodedElement, DirectionOS direction, 
			StructureOS structure, GroupsOS groups) {
		super(elementID, isCodingElement, mayBeTheCodedElement);
		this.direction = direction;
		this.structure = structure;
		this.groups = groups;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(direction);
		listOfComponents.add(structure);
		listOfComponents.add(groups);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!direction.equals(idToIOrderedSet.get(direction.getElementID()))) {
			direction= (DirectionOS) idToIOrderedSet.get(direction.getElementID());
			direction.eliminateRedundancies(idToIOrderedSet);
		}
		if (!structure.equals(idToIOrderedSet.get(structure.getElementID()))) {
			structure = (StructureOS) idToIOrderedSet.get(structure.getElementID());
			structure.eliminateRedundancies(idToIOrderedSet);
		}
		if (!groups.equals(idToIOrderedSet.get(groups.getElementID()))) {
			groups = (GroupsOS) idToIOrderedSet.get(groups.getElementID());
			groups.eliminateRedundancies(idToIOrderedSet);
		}
	}	

}
