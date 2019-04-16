package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.MinimalSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantSetElement;

public class SymmetryOS extends NonMinimalRelevantSetElement implements ISetElement {

	private static final String NAME = "symmetry";
	private MinimalSetElement symmetryProperty;
	
	public SymmetryOS(String elementID, MinimalSetElement symmetryProperty) {
		super(elementID);
		this.symmetryProperty = symmetryProperty;
	}

	@Override
	protected List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(symmetryProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
