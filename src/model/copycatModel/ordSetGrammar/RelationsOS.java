package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.SetElementImpl;

public class RelationsOS extends SetElementImpl implements ISetElement {

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
	protected List<IElement> buildListOfComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescriptorName() {
		// TODO Auto-generated method stub
		return null;
	}

}
