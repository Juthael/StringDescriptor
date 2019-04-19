package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.factories.OSFactory;
import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;
import model.orderedSetModel.impl.MinimalLowerSetElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

public class Direction extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_NAME = "direction";
	private String directionValue;
	
	public Direction(String directionValue) {
		super(false);
		this.directionValue = directionValue;
	}
	
	@Override
	protected Direction clone() throws CloneNotSupportedException {
		Direction cloneDirection = new Direction(directionValue);
		return cloneDirection;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>();
		return componentDescriptors;
	}
	
	@Override
	public List<String> getListOfPropertiesWithPath() {
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		StringBuilder sB = new StringBuilder();
		sB.append(DESCRIPTOR_NAME);
		sB.append(Settings.PATH_SEPARATOR);
		sB.append(directionValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}
	
	@Override
	public ILowerSetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ILowerSetElement directionOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer directionIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String directionID = getDescriptorName().concat(directionIndex.toString());
		MinimalLowerSetElement directionProperty = new MinimalLowerSetElement(directionValue);
		directionOS = OSFactory.getDirectionOS(directionID, directionProperty);
		return directionOS;		
	}	
	
}
