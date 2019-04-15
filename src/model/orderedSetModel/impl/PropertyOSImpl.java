package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;

public class PropertyOSImpl extends SetElementImpl implements ISetElement {

	private final String name;
	
	public PropertyOSImpl(String elementID, String value) {
		super(elementID);
		name = value;
	}

	public PropertyOSImpl(String elementID, boolean isCodingByDecomposition, String value) {
		super(elementID, isCodingByDecomposition);
		name = value;
	}

	public PropertyOSImpl(String elementID, boolean isCodingByDecomposition, boolean mayBeTheCodedElement, String value) {
		super(elementID, isCodingByDecomposition, mayBeTheCodedElement);
		name = value;
	}

	@Override
	public List<String> getListOfPropertiesWithPath(){
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		listOfPropertiesWithPath.add(getDescriptorName());
		return listOfPropertiesWithPath;
	}
	
	@Override
	public List<String> getElementDescription() {
		List<String> listOfMaximalStringsOfElementLowerSet = new ArrayList<String>();
		listOfMaximalStringsOfElementLowerSet.add(getElementID());
		return listOfMaximalStringsOfElementLowerSet;
	}	
	
	@Override
	protected List<IElement> buildListOfComponents() {
		List<IElement> listOfComponents = new ArrayList<IElement>();
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return name;
	}

}
