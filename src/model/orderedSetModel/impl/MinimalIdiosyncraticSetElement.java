package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.List;

import model.orderedSetModel.ISetElement;

public class MinimalIdiosyncraticSetElement extends MinimalSetElement implements ISetElement {

	public MinimalIdiosyncraticSetElement(String value) {
		super(value);
	}
	
	@Override
	public List<String> getListOfPropertiesWithPath(){
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		return listOfPropertiesWithPath;
	}	

}
