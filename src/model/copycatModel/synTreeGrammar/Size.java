package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.SizeOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

public class Size extends SynTreeElementImpl implements ISynTreeElement, Cloneable {
	
	private static final String DESCRIPTOR_NAME = "size";
	private String sizeValue;

	public Size(String sizeValue) {
		this.sizeValue = sizeValue;
	}
	
	@Override
	protected Size clone() throws CloneNotSupportedException {
		Size cloneSize = new Size(sizeValue);
		return cloneSize;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	
	
	@Override
	public List<IElement> getListOfComponents(){
		ArrayList<IElement> componentDescriptors = new ArrayList<IElement>();
		return componentDescriptors;
	}
	
	@Override
	public List<String> getListOfPropertiesWithPath() {
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		StringBuilder sB = new StringBuilder();
		sB.append(DESCRIPTOR_NAME);
		sB.append(Settings.PATH_SEPARATOR);
		sB.append(sizeValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}	
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		IOrderedSet sizeOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer sizeIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String sizeID = getDescriptorName().concat(sizeIndex.toString());
		MinimalOS sizeProperty = new MinimalOS(sizeValue);
		sizeOS = new SizeOS(sizeID, sizeProperty);
		return sizeOS;		
	}		
	
}
