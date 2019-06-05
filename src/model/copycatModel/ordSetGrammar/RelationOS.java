package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalOS;

public class RelationOS extends NonMinimalOS implements IOrderedSet {

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
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (dimension.getElementID().equals(orderedSet.getElementID()) && dimension != orderedSet) {
			dimension = (DimensionOS) orderedSet;
		}
		else dimension.eliminateRedundancies(orderedSet);
		if (enumeration.getElementID().equals(orderedSet.getElementID()) && enumeration != orderedSet) {
			enumeration = (EnumerationOS) orderedSet;
		}
		else enumeration.eliminateRedundancies(orderedSet);
	}	
	
}
