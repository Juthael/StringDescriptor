package copycatModel.synTreeModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.synTreeModel.impl.SynTreeIntegrableElementImpl;

public class RelationX4 extends HowManyRelations implements Cloneable {

	private static final String descriptorName = "relationX4";
	private Relation relation1;
	private Relation relation2;	
	private Relation relation3;
	private Relation relation4;
	
	public RelationX4(boolean codingDescriptor, Relation relation1, Relation relation2, Relation relation3, 
			Relation relation4) {
		super(codingDescriptor);
		this.relation1 = relation1;
		this.relation2 = relation2;
		this.relation3 = relation3;
		this.relation4 = relation4;
	}
	
	@Override
	protected RelationX4 clone() throws CloneNotSupportedException {
		RelationX4 cloneRelationX4;
		Relation cloneRelation1 = relation1.clone();
		Relation cloneRelation2 = relation2.clone();
		Relation cloneRelation3 = relation3.clone();
		Relation cloneRelation4 = relation4.clone();
		cloneRelationX4 = new RelationX4(isCodingByDecomposition, cloneRelation1, cloneRelation2, cloneRelation3, 
				cloneRelation4);
		return cloneRelationX4;
	}

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(relation1, relation2, relation3, relation4));
		return componentDescriptors;
	}

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}
