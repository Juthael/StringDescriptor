package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.SynTreeGenerationException;
import model.copycatModel.ordSetGrammar.factories.OSFactory;
import model.orderedSetModel.ILowerSetElement;
import model.orderedSetModel.impl.MinimalLowerSetElement;
import model.synTreeModel.ISynTreeElementWithPosition;
import model.synTreeModel.impl.SynTreeElementWithPositionImpl;
import settings.Settings;

public class EndPosition extends SynTreeElementWithPositionImpl implements ISynTreeElementWithPosition, Cloneable {

	private static final String DESCRIPTOR_NAME = "endPosition";
	private String endPositionValue;
	
	public EndPosition(String endPositionValue) throws SynTreeGenerationException {
		if (endPositionValue.equals(Settings.FIRST_POSITION) || endPositionValue.equals(Settings.LAST_POSITION))
			this.endPositionValue = endPositionValue;
		else throw new SynTreeGenerationException("EndPosition() : unexpected value");
	}

	@Override
	protected EndPosition clone() throws CloneNotSupportedException {
		try {
			EndPosition cloneEndPosition = new EndPosition(endPositionValue);
			return cloneEndPosition;
		}
		catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("EndPosition.clone() : " + e.getMessage());
		}
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	
	
	@Override
	public List<String> getListOfPropertiesWithPath() {
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		StringBuilder sB = new StringBuilder();
		sB.append(DESCRIPTOR_NAME);
		sB.append(Settings.PATH_SEPARATOR);
		sB.append(endPositionValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}	
	
	@Override
	public ILowerSetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ILowerSetElement endPositionOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer positionIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String endPositionID = getDescriptorName().concat(positionIndex.toString());
		MinimalLowerSetElement endPositionProperty = new MinimalLowerSetElement(endPositionValue);
		endPositionOS = OSFactory.getEndPositionOS(endPositionID, endPositionProperty);
		return endPositionOS;		
	}	

}
