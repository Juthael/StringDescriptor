package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.AbstractNonMinimalOS;

public class StructureOS extends AbstractNonMinimalOS implements IOrderedSet {

	private static final String NAME = "structure";
	private SizeOS size;
	private RelationOS relation;
	
	public StructureOS(String elementID, SizeOS size, RelationOS relation) {
		super(elementID);
		this.size = size;
		this.relation = relation;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(size);
		listOfComponents.add(relation);
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
		size.eliminateRedundancies(idToIOrderedSet);
		if (!relation.equals(idToIOrderedSet.get(relation.getElementID())))
			relation = (RelationOS) idToIOrderedSet.get(relation.getElementID());
		relation.eliminateRedundancies(idToIOrderedSet);
	}	

}
