package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import model.copycatModel.ordSetGrammar.SizeOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.impl.MinimalST;
import model.synTreeModel.impl.GrammaticalST;

public class Size extends GrammaticalST implements IGrammaticalST, Cloneable {
	
	private static final String DESCRIPTOR_NAME = "size";
	private MinimalST sizeValue;

	public Size(String sizeValue) throws CloneNotSupportedException {
		this.sizeValue = new MinimalST(sizeValue);
		setHashCode();
	}
	
	@Override
	protected Size clone() throws CloneNotSupportedException {
		Size cloneSize = new Size(sizeValue.getValue());
		return cloneSize;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = new ArrayList<IElement>();
		listOfComponents.add(sizeValue);
		return listOfComponents;
	}	
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet sizeOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer sizeIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String sizeID = getDescriptorName().concat(sizeIndex.toString());
		MinimalOS sizeProperty = (MinimalOS) sizeValue.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		sizeOS = new SizeOS(sizeID, sizeProperty);
		return sizeOS;		
	}		
	
}
