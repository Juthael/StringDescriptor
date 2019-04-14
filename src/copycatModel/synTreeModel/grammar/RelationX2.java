package copycatModel.synTreeModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.synTreeModel.impl.SynTreeIntegrableElementImpl;

public class RelationX2 extends HowManyRelations implements Cloneable {

	private static final String descriptorName = "relationX2";
	private Relation relation1;
	private Relation relation2;
	
	public RelationX2(boolean codingDescriptor, Relation relation1, Relation relation2) {
		super(codingDescriptor);
		this.relation1 = relation1;
		this.relation2 = relation2;
	}
	
	@Override
	protected RelationX2 clone() throws CloneNotSupportedException {
		RelationX2 cloneRelationX2;
		Relation cloneRelation1 = relation1.clone();
		Relation cloneRelation2 = relation2.clone();
		cloneRelationX2 = new RelationX2(isCodingByDecomposition, cloneRelation1, cloneRelation2);
		return cloneRelationX2;
	}

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(relation1, relation2));
		return componentDescriptors;
	}

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}
