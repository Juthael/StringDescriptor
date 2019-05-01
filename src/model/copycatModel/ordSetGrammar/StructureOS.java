package model.copycatModel.ordSetGrammar;

import java.util.List;

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

}
