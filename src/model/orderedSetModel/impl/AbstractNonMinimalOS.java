package model.orderedSetModel.impl;

import java.util.List;
import java.util.Map;

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
	
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		if (!minimalIdiosyncraticOS.equals(idToIOrderedSet.get(minimalIdiosyncraticOS.getElementID())))
			minimalIdiosyncraticOS = (MinimalIdiosyncraticOS) idToIOrderedSet.get(minimalIdiosyncraticOS.getElementID());
	}	

	protected void setIdiosyncraticProperty() {
		minimalIdiosyncraticOS = new MinimalIdiosyncraticOS(getDescriptorName());
	}
	
	@Override
	abstract public String getDescriptorName();

}
