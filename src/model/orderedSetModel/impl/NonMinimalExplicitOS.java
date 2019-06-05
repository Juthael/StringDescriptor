package model.orderedSetModel.impl;

import model.orderedSetModel.IOrderedSet;

public abstract class NonMinimalExplicitOS extends NonMinimalOS implements IOrderedSet {

	public NonMinimalExplicitOS(String elementID) {
		super(elementID);
	}

	public NonMinimalExplicitOS(String elementID, boolean isCodingElement) {
		super(elementID, isCodingElement);
	}

	public NonMinimalExplicitOS(String elementID, boolean isCodingElement, boolean mayBeTheCodedElement) {
		super(elementID, isCodingElement, mayBeTheCodedElement);
	}

	@Override
	abstract public String getDescriptorName();
	
	abstract public String getExplicitID();

}
