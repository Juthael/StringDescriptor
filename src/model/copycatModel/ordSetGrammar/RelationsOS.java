package model.copycatModel.ordSetGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.impl.AbstractNonMinimalOS;
import model.orderedSetModel.IOrderedSet;

public class RelationsOS extends AbstractNonMinimalOS implements RelationsOrLetterOS {

	private static final String NAME = "relations";
	private List<RelationOS> listOfRelations;
	private GroupsOS groups;
	
	public RelationsOS(String elementID, List<RelationOS> listOfRelations, GroupsOS groups) {
		super(elementID);
		this.listOfRelations = listOfRelations;
		this.groups = groups;
		//HERE
		System.out.println("SET" + getElementID());
		List<String> listOfProperties = getListOfPropertiesWithPath();
		List<String> maxChains = getListOfLowerSetMaximalChains();
		System.out.println("Properties : ");
		for (String property : listOfProperties) {
			System.out.println(property);
		}
		System.out.println("");
		System.out.print("Max chains");
		for (String maxChain : maxChains) {
			System.out.println(maxChain);
		}
		System.out.println("");
		System.out.println("*******************");
		System.out.println("");		
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.addAll(listOfRelations);
		listOfComponents.add(groups);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		List<RelationOS> listOfRelationsWithNoRedundancy = new ArrayList<RelationOS>();
		for (RelationOS relation : listOfRelations) {
			RelationOS rightRelation = (RelationOS) idToIOrderedSet.get(relation.getElementID());
			rightRelation.eliminateRedundancies(idToIOrderedSet);
			listOfRelationsWithNoRedundancy.add(rightRelation);
		}
		listOfRelations = listOfRelationsWithNoRedundancy;
		if (!groups.equals(idToIOrderedSet.get(groups.getElementID())))
			groups = (GroupsOS) idToIOrderedSet.get(groups.getElementID());
		groups.eliminateRedundancies(idToIOrderedSet);
	}	

}
