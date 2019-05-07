package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.AbstractNonMinimalOS;

public class RelationOS extends AbstractNonMinimalOS implements IOrderedSet {

	private static final String NAME = "relation";
	private DimensionOS dimension;
	private EnumerationOS enumeration;
	
	public RelationOS(String elementID, DimensionOS dimension, EnumerationOS enumeration) {
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
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!dimension.equals(idToIOrderedSet.get(dimension.getElementID())))
			dimension = (DimensionOS) idToIOrderedSet.get(dimension.getElementID());
		dimension.eliminateRedundancies(idToIOrderedSet);
		if (!enumeration.equals(idToIOrderedSet.get(enumeration.getElementID())))
			enumeration = (EnumerationOS) idToIOrderedSet.get(enumeration.getElementID());
		enumeration.eliminateRedundancies(idToIOrderedSet);
	}	
	
}
