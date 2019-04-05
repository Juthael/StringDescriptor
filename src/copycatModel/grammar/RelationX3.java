package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;

public class RelationX3 extends HowManyRelations implements Cloneable {

	private static final String descriptorName = "relationX3";
	private Relation relation1;
	private Relation relation2;	
	private Relation relation3;
	
	public RelationX3(boolean codingDescriptor, Relation relation1, Relation relation2, Relation relation3) {
		super(codingDescriptor);
		this.relation1 = relation1;
		this.relation2 = relation2;
		this.relation3 = relation3;
	}
	
	@Override
	protected RelationX3 clone() throws CloneNotSupportedException {
		RelationX3 cloneRelationX3;
		Relation cloneRelation1 = relation1.clone();
		Relation cloneRelation2 = relation2.clone();
		Relation cloneRelation3 = relation3.clone();
		cloneRelationX3 = new RelationX3(isCodingDescriptor, cloneRelation1, cloneRelation2, cloneRelation3);
		return cloneRelationX3;
	}

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(relation1, relation2, relation3));
		return componentDescriptors;
	}

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}
