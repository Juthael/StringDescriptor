package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.CentralProminentPositionOS;
import model.copycatModel.ordSetGrammar.IPositionOS;
import model.orderedSetModel.ILowerSetElement;
import model.orderedSetModel.impl.MinimalLowerSetElement;
import model.synTreeModel.ISynTreeElement;
import settings.Settings;

public class CentralProminentPosition extends ProminentPosition implements ISynTreeElement, Cloneable {

	public CentralProminentPosition(Position position) {
		super(position);
	}

	@Override
	protected CentralProminentPosition clone() throws CloneNotSupportedException {
		CentralProminentPosition cloneCentralProminentPosition;
		Position clonePosition = position.clone();
		cloneCentralProminentPosition = new CentralProminentPosition(clonePosition);
		return cloneCentralProminentPosition;
	}
	
	@Override
	public List<String> getListOfPropertiesWithPath() {
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		String path = DESCRIPTOR_NAME.concat(Settings.PATH_SEPARATOR);
		List<String> positionListOfProperties = position.getListOfPropertiesWithPath();
		for (String property : positionListOfProperties) {
			listOfPropertiesWithPath.add(path.concat(property));
		}
		listOfPropertiesWithPath.add(path.concat(Settings.CENTRAL_POSITION));
		
		return listOfPropertiesWithPath;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}	
	
	@Override
	public ILowerSetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ILowerSetElement centralProminentPositionOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer prominentPositionIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String prominentPositionID = getDescriptorName().concat(prominentPositionIndex.toString());
		IPositionOS positionOS = (IPositionOS) position.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		MinimalLowerSetElement centralPositionProperty = new MinimalLowerSetElement(Settings.CENTRAL_POSITION);
		centralProminentPositionOS = new CentralProminentPositionOS(prominentPositionID, positionOS, centralPositionProperty);
		return centralProminentPositionOS;	
	}	


}
