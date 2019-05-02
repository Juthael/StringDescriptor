package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.impl.AbstractNonMinimalOS;
import model.orderedSetModel.IOrderedSet;


public abstract class ProminentPositionOS extends AbstractNonMinimalOS implements WhichPositionTypeOS {

	private static final String NAME = "prominentPosition";
	protected PositionOS position;
	
	public ProminentPositionOS(String elementID, PositionOS position) {
		super(elementID);
		this.position = position;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(position);
		return listOfComponents;
	}
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!position.equals(idToIOrderedSet.get(position.getElementID())))
			position = (PositionOS) idToIOrderedSet.get(position.getElementID());
		position.eliminateRedundancies(idToIOrderedSet);
	}	

}
