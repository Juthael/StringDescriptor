package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.NonMinimalRelevantSetElement;

public class RelationOS extends NonMinimalRelevantSetElement implements HowManyRelationsOS {

	private static final String NAME = "relation";
	private DimensionOS dimension;
	private EnumerationOS enumeration;
	
	public RelationOS(String elementID, DimensionOS dimension, EnumerationOS enumeration) {
		super(elementID);
		this.dimension = dimension;
		this.enumeration = enumeration;
	}

	@Override
	protected List<IElement> getListOfComponents() {
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
