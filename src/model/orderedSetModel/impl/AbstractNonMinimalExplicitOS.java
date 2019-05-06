package model.orderedSetModel.impl;

import model.orderedSetModel.IOrderedSet;

public abstract class AbstractNonMinimalExplicitOS extends AbstractNonMinimalOS implements IOrderedSet {

	public AbstractNonMinimalExplicitOS(String elementID) {
		super(elementID);
	}

	public AbstractNonMinimalExplicitOS(String elementID, boolean isCodingByDecomposition) {
		super(elementID, isCodingByDecomposition);
	}

	public AbstractNonMinimalExplicitOS(String elementID, boolean isCodingByDecomposition, boolean mayBeTheCodedElement) {
		super(elementID, isCodingByDecomposition, mayBeTheCodedElement);
	}

	@Override
	abstract public String getDescriptorName();
	
	abstract public String getExplicitID();

}
