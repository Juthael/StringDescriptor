package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.NonMinimalRelevantOS;

public class RelationOS extends NonMinimalRelevantOS implements HowManyRelationsOS {

	private static final String NAME = "relation";
	private IDimensionOS dimension;
	private IEnumerationOS enumeration;
	
	public RelationOS(String elementID, IDimensionOS dimension, IEnumerationOS enumeration) {
		super(elementID);
		this.dimension = dimension;
		this.enumeration = enumeration;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(dimension);
		listOfComponents.add(enumeration);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
