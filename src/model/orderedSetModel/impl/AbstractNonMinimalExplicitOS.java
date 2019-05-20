package model.orderedSetModel.impl;

import model.orderedSetModel.IOrderedSet;

public abstract class AbstractNonMinimalExplicitOS extends AbstractNonMinimalOS implements IOrderedSet {

	public AbstractNonMinimalExplicitOS(String elementID) {
		super(elementID);
	}

	public AbstractNonMinimalExplicitOS(String elementID, boolean isCodingElement) {
		super(elementID, isCodingElement);
	}

	public AbstractNonMinimalExplicitOS(String elementID, boolean isCodingElement, boolean mayBeTheCodedElement) {
		super(elementID, isCodingElement, mayBeTheCodedElement);
	}

	@Override
	abstract public String getDescriptorName();
	
	abstract public String getExplicitID();

}
