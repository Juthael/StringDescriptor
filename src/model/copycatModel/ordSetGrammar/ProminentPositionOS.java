package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.NonMinimalRelevantLowerSetElement;

public abstract class ProminentPositionOS extends NonMinimalRelevantLowerSetElement implements WhichPositionTypeOS {

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
