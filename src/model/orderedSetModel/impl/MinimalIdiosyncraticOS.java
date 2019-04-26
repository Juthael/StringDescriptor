package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.List;

import model.orderedSetModel.IOrderedSet;

public class MinimalIdiosyncraticOS extends MinimalOS implements IOrderedSet {

	public MinimalIdiosyncraticOS(String value) {
		super(value);
	}
	
	@Override
	public List<String> getListOfPropertiesWithPath(){
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		return listOfPropertiesWithPath;
	}	

}
