package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import model.copycatModel.ordSetGrammar.DimensionOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.GenericOS;
import model.orderedSetModel.impl.MinimalOS;
import model.synTreeModel.impl.GrammaticalST;
import model.synTreeModel.impl.MinimalST;
import syntacticTreesGeneration.impl.DimensionEncodingManager;

public class Dimension extends GrammaticalST implements IOneOrManyDimensions, Cloneable {

	private static final String DESCRIPTOR_NAME = "dimension";
	private MinimalST indexedPath;
	
	public Dimension(String indexedPath) throws CloneNotSupportedException {
		this.indexedPath = new MinimalST(indexedPath);
		setHashCode();
	}
	
	@Override
	public Dimension clone() throws CloneNotSupportedException {
		Dimension cloneDimension = new Dimension(indexedPath.getValue());
		return cloneDimension;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = new ArrayList<IElement>();
		listOfComponents.add(indexedPath);
		return listOfComponents;
	}
	
	@Override
	public IOrderedSet upgradeAsTheGenericElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet orderedSet;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer index = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String iD = getDescriptorName().concat(index.toString());
		MinimalOS dimensionProperty = (MinimalOS) indexedPath.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		List<IOrderedSet> listOfComponents = new ArrayList<IOrderedSet>();
		listOfComponents.add(dimensionProperty);
		orderedSet = new GenericOS(iD, getDescriptorName(), listOfComponents);
		return orderedSet;
	}		
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		IOrderedSet dimensionOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer dimensionIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String dimensionID = getDescriptorName().concat(dimensionIndex.toString());
		MinimalOS dimensionProperty = new MinimalOS(getDimensionCode(indexedPath.getValue()));
		dimensionOS = new DimensionOS(dimensionID, dimensionProperty);
		return dimensionOS;		
	}	
	
	private String getDimensionCode(String fullDimensionValue) {
		String codedDimension = DimensionEncodingManager.getDimensionCodeFromIndexedPath(fullDimensionValue);
		return codedDimension;
	}	

}
