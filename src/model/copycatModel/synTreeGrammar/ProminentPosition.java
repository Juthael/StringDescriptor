package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.IPositionableST;

public abstract class ProminentPosition extends WhichPositionType implements IGrammaticalST, 
	IPositionableST, Cloneable {

	protected static final String DESCRIPTOR_NAME = "prominentPosition";
	protected Position position;
	
	public ProminentPosition(Position position) {
		this.position = position;
		setHashCode();
	}
	
	@Override
	abstract protected WhichPositionType clone() throws CloneNotSupportedException;	
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = new ArrayList<IElement>();
		listOfComponents.add(position);
		return listOfComponents;
	}	

	@Override
	abstract public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException;

}
