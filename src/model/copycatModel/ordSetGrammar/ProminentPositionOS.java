package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalExplicitOS;
import settings.Settings;


public abstract class ProminentPositionOS extends NonMinimalExplicitOS implements WhichPositionTypeOS {

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
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (position.getElementID().equals(orderedSet.getElementID()) && position != orderedSet) {
			position = (PositionOS) orderedSet;
		}
		else position.eliminateRedundancies(orderedSet);
	}	
	
	@Override
	abstract public String getExplicitID();

}
