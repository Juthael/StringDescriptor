package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;

public class StructureWithRelationOS extends StructureOS implements IOrderedSet {

	private RelationOS relation;
	
	public StructureWithRelationOS(String elementID, SizeOS size, RelationOS relation) {
		super(elementID, size);
		this.relation = relation;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(relation);
		return listOfComponents;
	}

	@Override
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (relation.getElementID().equals(orderedSet.getElementID()) && relation != orderedSet) {
			relation = (RelationOS) orderedSet;
		}
		else relation.eliminateRedundancies(orderedSet);
	}	

}
