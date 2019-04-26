package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.MinimalOS;
import model.orderedSetModel.impl.NonMinimalRelevantOS;

public class SymmetryOS extends NonMinimalRelevantOS implements ISymmetryOS {

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

}
