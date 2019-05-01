package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.AbstractNonMinimalOS;

public class RelationsOS extends AbstractNonMinimalOS implements RelationsOrLetterOS {

	private static final String NAME = "relations";
	private List<RelationOS> listOfRelations;
	private GroupsOS groups;
	
	public RelationsOS(String elementID, List<RelationOS> listOfRelations, GroupsOS groups) {
		super(elementID);
		this.listOfRelations = listOfRelations;
		this.groups = groups;
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

}
