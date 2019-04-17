package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.NonMinimalRelevantSetElement;

public class StructureOS extends NonMinimalRelevantSetElement implements ISetElement {

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
