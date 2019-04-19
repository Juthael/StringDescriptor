package model.copycatModel.synTreeGrammar;

import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.EndProminentPositionOS;
import model.copycatModel.ordSetGrammar.IEndPositionOS;
import model.copycatModel.ordSetGrammar.IPositionOS;
import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;
import model.synTreeModel.ISynTreeElementWithPosition;

public class EndProminentPosition extends ProminentPosition implements ISynTreeElementWithPosition, Cloneable {

	private EndPosition endPosition;
	
	public EndProminentPosition(Position position, EndPosition endPosition) {
		super(position);
		this.endPosition = endPosition;
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
	public ILowerSetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ILowerSetElement endProminentPositionOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer endProminentPositionIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String endOfProminentPositionID = getDescriptorName().concat(endProminentPositionIndex.toString());
		IPositionOS positionOS = (IPositionOS) position.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		IEndPositionOS endPositionOS = (IEndPositionOS) endPosition.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex); 
		endProminentPositionOS = new EndProminentPositionOS(endOfProminentPositionID, positionOS, endPositionOS);
		return endProminentPositionOS;		
	}		

}
