package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.List;

import model.orderedSetModel.IOrderedSet;
import settings.Settings;

public class MinimalOS extends OrderedSet implements IOrderedSet {

	private boolean isIdiosyncraticIfCalledByNonGeneric;
	
	public MinimalOS(String value) {
		super(value);
		isIdiosyncraticIfCalledByNonGeneric = false;
	}
	
	public MinimalOS(String value, boolean isIdiosyncratic) {
		super(value);
		this.isIdiosyncraticIfCalledByNonGeneric = isIdiosyncratic;
	}	
	
	@Override
	public List<String> getListOfPropertiesWithPath(){
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		if (isIdiosyncraticIfCalledByNonGeneric == false) {
			listOfPropertiesWithPath.add(getDescriptorName());
		}
		return listOfPropertiesWithPath;
	}
	
	@Override
	public List<String> getListOfPropertiesWithPath(boolean calledByGeneric){
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		if (calledByGeneric == Settings.CALLED_BY_GENERIC) {
			listOfPropertiesWithPath.add(getDescriptorName());
		}
		return listOfPropertiesWithPath;
	}	
	
	@Override
	public List<String> getListOfLowerSetMaximalChains() {
		List<String> listOfMaximalStringsOfElementLowerSet = new ArrayList<String>();
		if (getThisSetIsInformative() == true) {
			listOfMaximalStringsOfElementLowerSet.add(getElementID());
		}
		return listOfMaximalStringsOfElementLowerSet;
	}	

	@Override
	public String getDescriptorName() {
		return getElementID();
	}
	
	@Override
	public boolean getThisSetIsGeneric() {
		return true;
	}
	
	@Override 
	public boolean getIsMinimal() {
		return true;
	}

	@Override
	public void eliminateRedundancies(IOrderedSet idToIOrderedSet) {
	}

}