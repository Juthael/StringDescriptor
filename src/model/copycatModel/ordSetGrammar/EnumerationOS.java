package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.orderedSetModel.impl.AbstractNonMinimalOS;

public class EnumerationOS extends AbstractNonMinimalOS implements IOrderedSet {

	private static final String NAME = "enumeration";
	private MinimalOS enumerationProperty;
	
	public EnumerationOS(String elementID, MinimalOS enumerationProperty) {
		super(elementID);
		this.enumerationProperty = enumerationProperty;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(enumerationProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!enumerationProperty.equals(idToIOrderedSet.get(enumerationProperty.getElementID())))
			enumerationProperty = (MinimalOS) idToIOrderedSet.get(enumerationProperty.getElementID());
	}		

}
