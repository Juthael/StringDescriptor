package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.NonMinimalRelevantSetElement;

public class CharStringOS extends NonMinimalRelevantSetElement implements ISetElement {

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

	public CharStringOS(String elementID, boolean isCodingByDecomposition, boolean mayBeTheCodedElement, DirectionOS direction, 
			StructureOS structure, GroupsOS groups) {
		super(elementID, isCodingByDecomposition, mayBeTheCodedElement);
		this.direction = direction;
		this.structure = structure;
		this.groups = groups;
	}

	@Override
	protected List<IElement> getListOfComponents() {
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

}
