package model.copycatModel.ordSetGrammar;

import java.util.ArrayList;
import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalOS;

public class RelationsOS extends NonMinimalOS implements RelationsOrLetterOS {

	private static final String NAME = "relations";
	private List<RelationOS> listOfRelations;
	private IOrderedSet components;
	
	public RelationsOS(String elementID, List<RelationOS> listOfRelations, IOrderedSet components) {
		super(elementID);
		this.listOfRelations = listOfRelations;
		this.components = components;	
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.addAll(listOfRelations);
		listOfComponents.add(components);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		List<RelationOS> cleanListOfRelations = new ArrayList<RelationOS>();
		for (RelationOS relation : listOfRelations) {
			if (relation.getElementID().equals(orderedSet.getElementID()) && relation != orderedSet)
				relation = (RelationOS) orderedSet;
			else relation.eliminateRedundancies(orderedSet);
			cleanListOfRelations.add(relation);
		}
		listOfRelations = cleanListOfRelations;
		if (components.getElementID().equals(orderedSet.getElementID()) && components != orderedSet) {
			components = orderedSet;
		}
		else components.eliminateRedundancies(orderedSet);
	}	

}
