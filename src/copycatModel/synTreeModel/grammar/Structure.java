package copycatModel.synTreeModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.synTreeModel.impl.SynTreeIntegrableElementImpl;

public class Structure extends SynTreeIntegrableElementImpl implements Cloneable {

	private static String descriptorName = "structure";
	private Size size;
	private Relation relation;
	
	public Structure(boolean codingDescriptor, Size size, Relation relation) {
		super(codingDescriptor);
		this.size = size;
		this.relation = relation;
	}
	
	@Override
	protected Structure clone() throws CloneNotSupportedException {
		Structure cloneStructuration;
		Size cloneSize = size.clone();
		Relation cloneRelation = relation.clone();
		cloneStructuration = new Structure(isCodingByDecomposition, cloneSize, cloneRelation);
		return cloneStructuration;
	}

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents() {
		List<SynTreeIntegrableElementImpl> listOfComponents = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(size, relation));
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}
