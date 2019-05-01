package model.orderedSetModel.impl;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;

public abstract class AbstractNonMinimalOS extends AbstractOrderedSet implements IOrderedSet {

	protected MinimalIdiosyncraticOS minimalIdiosyncraticOS;
	
	public AbstractNonMinimalOS(String elementID) {
		super(elementID);
		setIdiosyncraticProperty();
	}

	public AbstractNonMinimalOS(String elementID, boolean isCodingByDecomposition) {
		super(elementID, isCodingByDecomposition);
		setIdiosyncraticProperty();
	}

	public AbstractNonMinimalOS(String elementID, boolean isCodingByDecomposition, boolean mayBeTheCodedElement) {
		super(elementID, isCodingByDecomposition, mayBeTheCodedElement);
		setIdiosyncraticProperty();
	}
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(minimalIdiosyncraticOS);
		return listOfComponents;
	}	

	protected void setIdiosyncraticProperty() {
		minimalIdiosyncraticOS = new MinimalIdiosyncraticOS(getDescriptorName());
	}
	
	@Override
	abstract public String getDescriptorName();

}
