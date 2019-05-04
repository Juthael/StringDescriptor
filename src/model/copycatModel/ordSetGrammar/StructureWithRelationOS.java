package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

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
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!relation.equals(idToIOrderedSet.get(relation.getElementID())))
			relation = (RelationOS) idToIOrderedSet.get(relation.getElementID());
		relation.eliminateRedundancies(idToIOrderedSet);
	}	

}
