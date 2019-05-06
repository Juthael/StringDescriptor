package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.AbstractNonMinimalExplicitOS;
import settings.Settings;


public abstract class ProminentPositionOS extends AbstractNonMinimalExplicitOS implements WhichPositionTypeOS {

	private static final String NAME = "prominentPosition";
	protected PositionOS position;
	
	public ProminentPositionOS(String elementID, PositionOS position) {
		super(elementID);
		this.position = position;
		if (Settings.MAKE_ELEMENT_ID_MORE_EXPLICIT)
			setElementID(getExplicitID());
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
	
	@Override
	abstract public String getExplicitID();

}
