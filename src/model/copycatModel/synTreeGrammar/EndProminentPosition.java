package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.EndPositionOS;
import model.copycatModel.ordSetGrammar.EndProminentPositionOS;
import model.copycatModel.ordSetGrammar.PositionOS;
import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
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
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(position, endPosition));
		return componentDescriptors;
	}
	
	@Override
	public ISetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ISetElement endProminentPositionOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer endProminentPositionIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String endOfProminentPositionID = getDescriptorName().concat(endProminentPositionIndex.toString());
		PositionOS positionOS = (PositionOS) position.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		EndPositionOS endPositionOS = (EndPositionOS) endPosition.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex); 
		endProminentPositionOS = new EndProminentPositionOS(endOfProminentPositionID, positionOS, endPositionOS);
		return endProminentPositionOS;		
	}		

}
