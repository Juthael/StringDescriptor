package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.PropertyOSImpl;
import model.orderedSetModel.impl.SetElementImpl;

public class SymmetryOS extends SetElementImpl implements ISetElement {

	private PropertyOSImpl symmetryProperty;
	
	public SymmetryOS(String elementID, PropertyOSImpl symmetryProperty) {
		super(elementID);
		this.symmetryProperty = symmetryProperty;
	}

	public SymmetryOS(String elementID, boolean isCodingByDecomposition) {
		super(elementID, isCodingByDecomposition);
		// TODO Auto-generated constructor stub
	}

	public SymmetryOS(String elementID, boolean isCodingByDecomposition, boolean mayBeTheCodedElement) {
		super(elementID, isCodingByDecomposition, mayBeTheCodedElement);
		// TODO Auto-generated constructor stub
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
