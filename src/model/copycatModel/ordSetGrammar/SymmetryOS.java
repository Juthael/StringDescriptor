package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.MinimalLowerSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantLowerSetElement;

public class SymmetryOS extends NonMinimalRelevantLowerSetElement implements ISymmetryOS {

	private static final String NAME = "symmetry";
	private MinimalLowerSetElement symmetryProperty;
	
	public SymmetryOS(String elementID, MinimalLowerSetElement symmetryProperty) {
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
