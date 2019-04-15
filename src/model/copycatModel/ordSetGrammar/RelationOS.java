package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.SetElementImpl;

public class RelationOS extends SetElementImpl implements HowManyRelationsOS {

	private DimensionOS dimension;
	private EnumerationOS enumeration;
	
	public RelationOS(String elementID, DimensionOS dimension, EnumerationOS enumeration) {
		super(elementID);
		this.dimension = dimension;
		this.enumeration = enumeration;
	}

	public RelationOS(String elementID, boolean isCodingByDecomposition) {
		super(elementID, isCodingByDecomposition);
		// TODO Auto-generated constructor stub
	}

	public RelationOS(String elementID, boolean isCodingByDecomposition, boolean mayBeTheCodedElement) {
		super(elementID, isCodingByDecomposition, mayBeTheCodedElement);
		// TODO Auto-generated constructor stub
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
