package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.SetElementImpl;

public class CharStringOS extends SetElementImpl implements ISetElement {

	private DirectionOS direction;
	private StructureOS structure;
	private GroupsOS groups;
	
	public CharStringOS(String elementID, DirectionOS direction, StructureOS structure, GroupsOS groups) {
		super(elementID);
		this.direction = direction;
		this.structure = structure;
		this.groups = groups;
	}

	public CharStringOS(String elementID, boolean isCodingByDecomposition, boolean mayBeTheCodedElement, DirectionOS direction, 
			StructureOS structure, GroupsOS groups) {
		super(elementID, isCodingByDecomposition, mayBeTheCodedElement);
		this.direction = direction;
		this.structure = structure;
		this.groups = groups;
	}

	@Override
	protected List<IElement> buildListOfComponents() {
		return null;
	}

	@Override
	public String getDescriptorName() {
		// TODO Auto-generated method stub
		return null;
	}

}
