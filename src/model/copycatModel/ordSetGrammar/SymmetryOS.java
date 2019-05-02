package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.orderedSetModel.impl.AbstractNonMinimalOS;

public class SymmetryOS extends AbstractNonMinimalOS implements IOrderedSet {

	private static final String NAME = "symmetry";
	private MinimalOS symmetryProperty;
	
	public SymmetryOS(String elementID, MinimalOS symmetryProperty) {
		super(elementID);
		this.symmetryProperty = symmetryProperty;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(symmetryProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!symmetryProperty.equals(idToIOrderedSet.get(symmetryProperty.getElementID())))
			symmetryProperty = (MinimalOS) idToIOrderedSet.get(symmetryProperty.getElementID());
	}

}
