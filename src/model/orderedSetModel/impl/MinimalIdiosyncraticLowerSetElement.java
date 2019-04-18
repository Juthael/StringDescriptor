package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.List;

import model.orderedSetModel.ILowerSetElement;

public class MinimalIdiosyncraticLowerSetElement extends MinimalLowerSetElement implements ILowerSetElement {

	public MinimalIdiosyncraticLowerSetElement(String value) {
		super(value);
	}
	
	@Override
	public List<String> getListOfPropertiesWithPath(){
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		return listOfPropertiesWithPath;
	}	

}
