package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.List;

import model.orderedSetModel.ISetElement;

public class MinimalSetElement extends SetElementImpl implements ISetElement {

	public MinimalSetElement(String value) {
		super(value);
	}

	@Override
	public List<String> getListOfPropertiesWithPath(){
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		listOfPropertiesWithPath.add(getDescriptorName());
		return listOfPropertiesWithPath;
	}
	
	@Override
	public List<String> getLowerSetDescription() {
		List<String> listOfMaximalStringsOfElementLowerSet = new ArrayList<String>();
		listOfMaximalStringsOfElementLowerSet.add(getElementID());
		return listOfMaximalStringsOfElementLowerSet;
	}	

	@Override
	public String getDescriptorName() {
		return getElementID();
	}

}
