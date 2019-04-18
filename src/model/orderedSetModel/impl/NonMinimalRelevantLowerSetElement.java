package model.orderedSetModel.impl;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;

public abstract class NonMinimalRelevantLowerSetElement extends LowerSetElementImpl implements ILowerSetElement {

	protected MinimalIdiosyncraticLowerSetElement minimalIdiosyncraticLowerSetElement;
	
	public NonMinimalRelevantLowerSetElement(String elementID) {
		super(elementID);
		setIdiosyncraticProperty();
	}

	public NonMinimalRelevantLowerSetElement(String elementID, boolean isCodingByDecomposition) {
		super(elementID, isCodingByDecomposition);
	}

	public NonMinimalRelevantLowerSetElement(String elementID, boolean isCodingByDecomposition, boolean mayBeTheCodedElement) {
		super(elementID, isCodingByDecomposition, mayBeTheCodedElement);
	}
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(minimalIdiosyncraticLowerSetElement);
		return listOfComponents;
	}	

	protected void setIdiosyncraticProperty() {
		minimalIdiosyncraticLowerSetElement = new MinimalIdiosyncraticLowerSetElement(getDescriptorName());
	}
	
	@Override
	abstract public String getDescriptorName();

}
