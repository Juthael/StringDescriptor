package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import model.copycatModel.ordSetGrammar.PositionOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.GenericOS;
import model.orderedSetModel.impl.MinimalOS;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.IPositionableST;
import model.synTreeModel.impl.MinimalST;
import settings.Settings;

public class Position extends WhichPositionType implements IGrammaticalST, IPositionableST, Cloneable {
	
	private static final String DESCRIPTOR_NAME = "position";
	private MinimalST positionValue;

	public Position(String positionValue) throws CloneNotSupportedException {
		this.positionValue = new MinimalST(positionValue);
		setHashCode();
	}
	
	@Override
	protected Position clone() throws CloneNotSupportedException {
		Position clonePosition = new Position(positionValue.getValue());
		return clonePosition;
	}

	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}
	
	public String getValue() {
		return positionValue.getValue();
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}	
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = new ArrayList<IElement>();
		listOfComponents.add(positionValue);
		return listOfComponents;
	}	
	
	@Override
	public IOrderedSet upgradeAsTheGenericElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet orderedSet;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer index = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String iD = getDescriptorName().concat(index.toString());
		MinimalOS value = (MinimalOS) positionValue.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		List<IOrderedSet> listOfComponents = new ArrayList<IOrderedSet>();
		listOfComponents.add(value);
		orderedSet = new GenericOS(iD, getDescriptorName(), listOfComponents);
		return orderedSet;
	}		
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet positionOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer positionIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String positionID = getDescriptorName().concat(positionIndex.toString());
		MinimalOS positionProperty = (MinimalOS) positionValue.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		positionOS = new PositionOS(positionID, positionProperty);
		return positionOS;		
	}	
	
	@Override
	protected void doUpdatePosition(String newPositionValue) throws SynTreeGenerationException {
		if (Settings.AWAITING_POSITION_VALUE.equals(positionValue.getValue()) 
				&& !Settings.NO_POSITION_INFORMATION.equals(newPositionValue)) {
			if (newPositionValue.contains(Settings.POSITION_VALUES_SEPARATOR)) {
				throw new SynTreeGenerationException(
						"Position.doUpdatePosition() : unexpected parameter : " + newPositionValue);
			}
			else positionValue = new MinimalST(newPositionValue);
		}
	}	
	
}
