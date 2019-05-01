package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.orderedSetModel.impl.AbstractNonMinimalOS;

public class AbsCommonDiffOS extends AbstractNonMinimalOS implements IOrderedSet {
	
	private static final String NAME = "absCommonDiff";
	private MinimalOS absCommonDiffProperty;

	public AbsCommonDiffOS(String elementID, MinimalOS absCommonDiffProperty) {
		super(elementID);
		this.absCommonDiffProperty = absCommonDiffProperty;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(absCommonDiffProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
