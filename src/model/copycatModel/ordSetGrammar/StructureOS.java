package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.SetElementImpl;

public class StructureOS extends SetElementImpl implements ISetElement {

	private SizeOS size;
	private RelationOS relation;
	
	public StructureOS(String elementID, SizeOS size, RelationOS relation) {
		super(elementID);
		this.size = size;
		this.relation = relation;
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
