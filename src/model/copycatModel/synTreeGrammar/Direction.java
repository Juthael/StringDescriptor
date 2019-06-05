package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import model.copycatModel.ordSetGrammar.DirectionOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.impl.MinimalST;
import model.synTreeModel.impl.GrammaticalST;

public class Direction extends GrammaticalST implements IGrammaticalST, Cloneable {

	private static final String DESCRIPTOR_NAME = "direction";
	private MinimalST directionValue;
	
	public Direction(String directionValue) throws CloneNotSupportedException {
		super(false);
		this.directionValue = new MinimalST(directionValue);
		setHashCode();
	}
	
	@Override
	protected Direction clone() throws CloneNotSupportedException {
		Direction cloneDirection = new Direction(directionValue.getValue());
		return cloneDirection;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = new ArrayList<IElement>();
		listOfComponents.add(directionValue);
		return listOfComponents;
	}	
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet directionOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer directionIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String directionID = getDescriptorName().concat(directionIndex.toString());
		MinimalOS directionProperty = (MinimalOS) directionValue.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		directionOS = new DirectionOS(directionID, directionProperty);
		return directionOS;		
	}	
	
}
