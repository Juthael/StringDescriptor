package model.orderedSetModel.impl;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;

public abstract class NonMinimalRelevantSetElement extends SetElementImpl implements ISetElement {

	protected static MinimalIdiosyncraticSetElement minimalIdiosyncraticSetElement;
	
	public NonMinimalRelevantSetElement(String elementID) {
		super(elementID);
		setIdiosyncraticProperty();
	}

	public NonMinimalRelevantSetElement(String elementID, boolean isCodingByDecomposition) {
		super(elementID, isCodingByDecomposition);
	}

	public NonMinimalRelevantSetElement(String elementID, boolean isCodingByDecomposition, boolean mayBeTheCodedElement) {
		super(elementID, isCodingByDecomposition, mayBeTheCodedElement);
	}
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(minimalIdiosyncraticSetElement);
		return listOfComponents;
	}	

	private void setIdiosyncraticProperty() {
		minimalIdiosyncraticSetElement = new MinimalIdiosyncraticSetElement(getDescriptorName());
	}
	
	@Override
	abstract public String getDescriptorName();

}
