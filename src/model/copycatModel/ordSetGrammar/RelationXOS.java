package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.IrrelevantSetElementImpl;

public class RelationXOS extends IrrelevantSetElementImpl implements HowManyRelationsOS {

	private List<RelationOS> listOfRelations;
	
	public RelationXOS(String elementID, List<RelationOS> listOfRelations) {
		super(elementID);
		this.listOfRelations = listOfRelations;
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
