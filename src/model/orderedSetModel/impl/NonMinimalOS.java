package model.orderedSetModel.impl;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import settings.Settings;

public abstract class NonMinimalOS extends OrderedSet implements IOrderedSet {

	protected MinimalOS minimalIdiosyncraticOS;
	
	public NonMinimalOS(String elementID) {
		super(elementID);
		setIdiosyncraticProperty();
	}

	public NonMinimalOS(String elementID, boolean isCodingElement) {
		super(elementID, isCodingElement);
		setIdiosyncraticProperty();
	}

	public NonMinimalOS(String elementID, boolean isCodingElement, boolean mayBeTheCodedElement) {
		super(elementID, isCodingElement, mayBeTheCodedElement);
		setIdiosyncraticProperty();
	}
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(minimalIdiosyncraticOS);
		return listOfComponents;
	}	
	
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		if (minimalIdiosyncraticOS.getElementID().equals(orderedSet.getElementID()) && minimalIdiosyncraticOS != orderedSet)
			minimalIdiosyncraticOS = (MinimalOS) orderedSet;
	}	

	protected void setIdiosyncraticProperty() {
		minimalIdiosyncraticOS = new MinimalOS(getDescriptorName(), Settings.THIS_ELEMENT_IS_IDIOSYNCRATIC);
	}
	
	@Override
	abstract public String getDescriptorName();

}
