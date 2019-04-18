package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.NonMinimalIrrelevantLowerSetElement;

public class RelationXOS extends NonMinimalIrrelevantLowerSetElement implements HowManyRelationsOS {

	private static final String PARTIAL_NAME = "relationX";
	private List<RelationOS> listOfRelations;
	
	public RelationXOS(String elementID, List<RelationOS> listOfRelations) {
		super(elementID);
		this.listOfRelations = listOfRelations;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.addAll(listOfRelations);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		String name = PARTIAL_NAME.concat(Integer.toString(listOfRelations.size()));
		return name;
	}

}
