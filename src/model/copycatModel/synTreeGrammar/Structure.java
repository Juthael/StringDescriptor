package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;

public class Structure extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	private final static String DESCRIPTOR_NAME = "structure";
	private Size size;
	private Relation relation;
	
	public Structure(Size size, Relation relation) {
		super(false);
		this.size = size;
		this.relation = relation;
	}
	
	@Override
	protected Structure clone() throws CloneNotSupportedException {
		Structure cloneStructuration;
		Size cloneSize = size.clone();
		Relation cloneRelation = relation.clone();
		cloneStructuration = new Structure(cloneSize, cloneRelation);
		return cloneStructuration;
	}

	@Override
	protected List<IElement> buildListOfComponents() {
		List<IElement> listOfComponents = new ArrayList<IElement>(
				Arrays.asList(size, relation));
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}

}
