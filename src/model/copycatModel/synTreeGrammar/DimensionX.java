package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.SynTreeGenerationException;
import model.copycatModel.ordSetGrammar.IDimensionOS;
import model.copycatModel.ordSetGrammar.factory.OSFactory;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

public class DimensionX extends HowManyDimensions implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_PARTIAL_NAME = "descriptorX";
	private List<Dimension> listOfDimensions;
	
	public DimensionX(List<Dimension> listOfDimensions) throws SynTreeGenerationException {
		if (listOfDimensions.size() > 1 && listOfDimensions.size() <= Settings.MAX_NB_OF_DIMENSIONS_IN_RELATIONS) {
			this.listOfDimensions = listOfDimensions;
		}
		else throw new SynTreeGenerationException("DimensionX() : illegal number of dimensions (" + listOfDimensions.size() + ")");
	}

	@Override
	protected HowManyDimensions clone() throws CloneNotSupportedException {
		DimensionX cloneDimensionX;
		List<Dimension> cloneListOfDimension = new ArrayList<Dimension>();
		for (Dimension dimension : listOfDimensions)
			cloneListOfDimension.add(dimension.clone());
		try {
			cloneDimensionX = new DimensionX(cloneListOfDimension);
		}
		catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("DimensionX.clone() : " + e.getMessage());
		}
		return cloneDimensionX;
	}
	
	@Override
	public String getDescriptorName() {
		String name = DESCRIPTOR_PARTIAL_NAME.concat(Integer.toString(listOfDimensions.size()));
		return name;
	}	
	
	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = new ArrayList<IElement>();
		for (Dimension dimension : listOfDimensions) {
			listOfComponents.add(dimension);
		}
		return listOfComponents;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath(){
		List<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		List<SynTreeElementImpl> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (SynTreeElementImpl componentDescriptor : listOfRelevantComponents) {
			listOfRelevantPropertiesWithPath.addAll(componentDescriptor.getListOfRelevantPropertiesWithPath());
		}
		return listOfRelevantPropertiesWithPath;
	}		
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		IOrderedSet dimensionXOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer dimensionXIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String dimensionXID = getDescriptorName().concat(dimensionXIndex.toString());
		List<IDimensionOS> listOfDimensionsOS = new ArrayList<IDimensionOS>();
		for (Dimension dimension : listOfDimensions) {
			IDimensionOS dimensionOS= (IDimensionOS) dimension.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
			listOfDimensionsOS.add(dimensionOS);
		}
		dimensionXOS = OSFactory.getDimensionXOS(dimensionXID, listOfDimensionsOS);
		return dimensionXOS;		
	}	



}
