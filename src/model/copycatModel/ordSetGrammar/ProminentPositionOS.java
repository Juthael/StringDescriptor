package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.NonMinimalRelevantSetElement;

public abstract class ProminentPositionOS extends NonMinimalRelevantSetElement implements WhichPositionTypeOS {

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

}
