package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.impl.GrammaticalST;
import settings.Settings;

public class DimensionX extends GrammaticalST implements IOneOrManyDimensions, Cloneable {

	private static final String DESCRIPTOR_PARTIAL_NAME = "dimensionX";
	private List<Dimension> listOfDimensions;
	
	public DimensionX(List<Dimension> listOfDimensions) throws SynTreeGenerationException {
		if (listOfDimensions.size() > 1 && listOfDimensions.size() <= Settings.MAX_NB_OF_DIMENSIONS_IN_RELATIONS) {
			this.listOfDimensions = listOfDimensions;
		}
		else throw new SynTreeGenerationException(
				"DimensionX() : illegal number of dimensions (" + listOfDimensions.size() + ")");
		setHashCode();
	}

	@Override
	public DimensionX clone() throws CloneNotSupportedException {
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
		List<IGrammaticalST> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (IGrammaticalST componentDescriptor : listOfRelevantComponents) {
			listOfRelevantPropertiesWithPath.addAll(componentDescriptor.getListOfRelevantPropertiesWithPath());
		}
		return listOfRelevantPropertiesWithPath;
	}		
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) throws OrderedSetsGenerationException {
		throw new OrderedSetsGenerationException("DimensionX can't be upgraded.");
	}	



}
