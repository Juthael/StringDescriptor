package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.List;

import model.orderedSetModel.IOrderedSet;

public class MinimalOS extends AbstractOrderedSet implements IOrderedSet {

	public MinimalOS(String value) {
		super(value);
	}

	@Override
	public List<String> getListOfPropertiesWithPath(){
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		listOfPropertiesWithPath.add(getDescriptorName());
		return listOfPropertiesWithPath;
	}
	
	@Override
	public List<String> getListOfLowerSetMaximalChains() {
		List<String> listOfMaximalStringsOfElementLowerSet = new ArrayList<String>();
		listOfMaximalStringsOfElementLowerSet.add(getElementID());
		return listOfMaximalStringsOfElementLowerSet;
	}	

	@Override
	public String getDescriptorName() {
		return getElementID();
	}

}
