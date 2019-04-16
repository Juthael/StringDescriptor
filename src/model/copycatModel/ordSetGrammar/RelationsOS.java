package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.NonMinimalRelevantSetElement;

public class RelationsOS extends NonMinimalRelevantSetElement implements ISetElement {

	private static final String NAME = "relations";
	private HowManyDimensionsOS dimensionHM;
	private HowManyRelationsOS relationsHM;
	private GroupsOS groups;
	
	public RelationsOS(String elementID, HowManyDimensionsOS dimensionHM, HowManyRelationsOS relationsHM, GroupsOS groups) {
		super(elementID);
		this.dimensionHM = dimensionHM;
		this.relationsHM = relationsHM;
		this.groups = groups;
	}

	@Override
	protected List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(dimensionHM);
		listOfComponents.add(relationsHM);
		listOfComponents.add(groups);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
