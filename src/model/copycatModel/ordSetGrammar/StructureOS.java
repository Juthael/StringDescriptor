package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantLowerSetElement;

public class StructureOS extends NonMinimalRelevantLowerSetElement implements ILowerSetElement {

	private static final String NAME = "structure";
	private ISizeOS size;
	private RelationOS relation;
	
	public StructureOS(String elementID, ISizeOS size, RelationOS relation) {
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
