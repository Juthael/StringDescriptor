package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalOS;

public class StructureOS extends NonMinimalOS implements IOrderedSet {

	private static final String NAME = "structure";
	private SizeOS size;
	
	public StructureOS(String elementID, SizeOS size) {
		super(elementID);
		this.size = size;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(size);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (size.getElementID().equals(orderedSet.getElementID()) && size != orderedSet) {
			size = (SizeOS) orderedSet;
		}
		else size.eliminateRedundancies(orderedSet);
	}	

}
