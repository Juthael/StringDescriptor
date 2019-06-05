package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import model.copycatModel.ordSetGrammar.CentralProminentPositionOS;
import model.copycatModel.ordSetGrammar.PositionOS;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.GenericOS;
import model.orderedSetModel.impl.MinimalOS;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.IPositionableST;
import settings.Settings;

public class CentralProminentPosition extends ProminentPosition implements IGrammaticalST, 
	IPositionableST, Cloneable {

	public CentralProminentPosition(Position position) {
		super(position);
		setHashCode();
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
	public IOrderedSet upgradeAsTheGenericElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet centralProminentPositionOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer prominentPositionIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String prominentPositionID = getDescriptorName().concat(prominentPositionIndex.toString());
		IOrderedSet positionOS = position.upgradeAsTheGenericElementOfAnOrderedSet(listOfPropertiesToIndex);
		MinimalOS centralPositionProperty = new MinimalOS(Settings.CENTRAL_POSITION);
		List<IOrderedSet> listOfComponents = new ArrayList<IOrderedSet>();
		listOfComponents.add(positionOS);
		listOfComponents.add(centralPositionProperty);
		centralProminentPositionOS = new GenericOS(prominentPositionID, getDescriptorName(), listOfComponents);
		return centralProminentPositionOS;	
	}	
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet centralProminentPositionOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer prominentPositionIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String prominentPositionID = getDescriptorName().concat(prominentPositionIndex.toString());
		PositionOS positionOS = (PositionOS) position.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		MinimalOS centralPositionProperty = new MinimalOS(Settings.CENTRAL_POSITION);
		centralProminentPositionOS = new CentralProminentPositionOS(prominentPositionID, positionOS, centralPositionProperty);
		return centralProminentPositionOS;	
	}	


}
