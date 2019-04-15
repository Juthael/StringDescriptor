package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;

import exceptions.SynTreeGenerationException;
import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;
import settings.Settings;

public class RelationX extends HowManyRelations implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_PARTIAL_NAME = "relationX";
	private List<Relation> listOfRelations;
	
	public RelationX(List<Relation> listOfRelations) throws SynTreeGenerationException {
		super(false);
		if (listOfRelations.size() > 1 && listOfRelations.size() <= Settings.MAX_NB_OF_RELATIONS)
			this.listOfRelations = listOfRelations;
		else throw new SynTreeGenerationException("RelationX() : illegal number of relations (" + listOfRelations.size() + ").");
	}

	@Override
	protected List<IElement> buildListOfComponents() {
		List<IElement> listOfComponents = new ArrayList<IElement>();
		for (Relation relation : listOfRelations)
			listOfComponents.add(relation);
		return listOfComponents;
	}
	
	@Override
	protected HowManyRelations clone() throws CloneNotSupportedException {
		HowManyRelations cloneRelationX;
		List<Relation> cloneListOfRelations = new ArrayList<Relation>();
		for (Relation relation : listOfRelations)
			cloneListOfRelations.add(relation.clone());
		try {
			cloneRelationX = new RelationX(cloneListOfRelations);
		}
		catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("RelationX.clone() : " + e.getMessage());
		}
		return cloneRelationX;
	}	

	@Override
	public String getDescriptorName() {
		String name = DESCRIPTOR_PARTIAL_NAME.concat(Integer.toString(listOfRelations.size()));
		return name;
	}

}
