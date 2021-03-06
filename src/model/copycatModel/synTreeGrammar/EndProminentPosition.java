package model.copycatModel.synTreeGrammar;

import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import model.copycatModel.ordSetGrammar.EndPositionOS;
import model.copycatModel.ordSetGrammar.EndProminentPositionOS;
import model.copycatModel.ordSetGrammar.PositionOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.IPositionableST;

public class EndProminentPosition extends ProminentPosition implements IGrammaticalST, IPositionableST, Cloneable {

	private EndPosition endPosition;
	
	public EndProminentPosition(Position position, EndPosition endPosition) {
		super(position);
		this.endPosition = endPosition;
		setHashCode();
	}

	@Override
	protected EndProminentPosition clone() throws CloneNotSupportedException {
		EndProminentPosition cloneEndProminentPosition;
		Position clonePosition = position.clone();
		EndPosition cloneEndPosition = endPosition.clone();
		cloneEndProminentPosition = new EndProminentPosition(clonePosition, cloneEndPosition);
		return cloneEndProminentPosition;
	}
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(endPosition);
		return listOfComponents;
	}
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet endProminentPositionOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer endProminentPositionIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String endOfProminentPositionID = getDescriptorName().concat(endProminentPositionIndex.toString());
		PositionOS positionOS = (PositionOS) position.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		EndPositionOS endPositionOS = (EndPositionOS) endPosition.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex); 
		endProminentPositionOS = new EndProminentPositionOS(endOfProminentPositionID, positionOS, endPositionOS);
		return endProminentPositionOS;		
	}		

}
