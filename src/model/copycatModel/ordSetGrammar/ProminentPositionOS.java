package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.NonMinimalRelevantOS;

public abstract class ProminentPositionOS extends NonMinimalRelevantOS implements WhichPositionTypeOS {

	private static final String NAME = "prominentPosition";
	protected IPositionOS position;
	
	public ProminentPositionOS(String elementID, IPositionOS position) {
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

}
