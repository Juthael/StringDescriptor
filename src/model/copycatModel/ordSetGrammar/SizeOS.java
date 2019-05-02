package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.orderedSetModel.impl.AbstractNonMinimalOS;

public class SizeOS extends AbstractNonMinimalOS implements IOrderedSet {

	private static final String NAME = "size";
	private MinimalOS sizeProperty;
	
	public SizeOS(String elementID, MinimalOS sizeProperty) {
		super(elementID);
		this.sizeProperty = sizeProperty;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(sizeProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!sizeProperty.equals(idToIOrderedSet.get(sizeProperty.getElementID())))
			sizeProperty = (MinimalOS) idToIOrderedSet.get(sizeProperty.getElementID());
	}	

}
