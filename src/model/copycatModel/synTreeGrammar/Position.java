package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.SynTreeGenerationException;
import model.copycatModel.ordSetGrammar.PositionOS;
import model.orderedSetModel.ILowerSetElement;
import model.orderedSetModel.impl.MinimalLowerSetElement;
import model.synTreeModel.ISynTreeElementWithPosition;
import settings.Settings;

public class Position extends WhichPositionType implements ISynTreeElementWithPosition, Cloneable {
	
	private static final String DESCRIPTOR_NAME = "position";
	private String positionValue;

	public Position(String positionValue) {
		this.positionValue = positionValue;	
	}
	
	@Override
	protected Position clone() throws CloneNotSupportedException {
		Position clonePosition = new Position(positionValue);
		return clonePosition;
	}

	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}
	
	public String getPositionValue() {
		return positionValue;
	}	
	
	@Override
	public List<String> getListOfPropertiesWithPath() {
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		StringBuilder sB = new StringBuilder();
		sB.append(DESCRIPTOR_NAME);
		sB.append(Settings.PATH_SEPARATOR);
		sB.append(positionValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}	
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}	
	
	@Override
	public ILowerSetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ILowerSetElement positionOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer positionIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String positionID = getDescriptorName().concat(positionIndex.toString());
		MinimalLowerSetElement positionProperty = new MinimalLowerSetElement(positionValue);
		positionOS = new PositionOS(positionID, positionProperty);
		return positionOS;		
	}	
	
	@Override
	protected void doUpdatePosition(String newPositionValue) throws SynTreeGenerationException {
		if (Settings.AWAITING_POSITION_VALUE.equals(positionValue) 
				&& !Settings.NO_POSITION_INFORMATION.equals(newPositionValue)) {
			if (newPositionValue.contains(Settings.POSITION_VALUES_SEPARATOR)) {
				throw new SynTreeGenerationException(
						"Position.doUpdatePosition() : unexpected parameter : " + newPositionValue);
			}
			else positionValue = newPositionValue;
		}
	}	
	
}
