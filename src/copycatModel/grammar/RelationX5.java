package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;

public class RelationX5 extends HowManyRelations implements Cloneable {

	private static final String descriptorName = "relationX5";
	private Relation relation1;
	private Relation relation2;	
	private Relation relation3;
	private Relation relation4;
	private Relation relation5;
	
	public RelationX5(boolean codingDescriptor, Relation relation1, Relation relation2, Relation relation3, 
			Relation relation4, Relation relation5) {
		super(codingDescriptor);
		this.relation1 = relation1;
		this.relation2 = relation2;
		this.relation3 = relation3;
		this.relation4 = relation4;
		this.relation5 = relation5;
	}
	
	@Override
	protected RelationX5 clone() throws CloneNotSupportedException {
		RelationX5 cloneRelationX5;
		Relation cloneRelation1 = relation1.clone();
		Relation cloneRelation2 = relation2.clone();
		Relation cloneRelation3 = relation3.clone();
		Relation cloneRelation4 = relation4.clone();
		Relation cloneRelation5 = relation5.clone();
		cloneRelationX5 = new RelationX5(isCodingDescriptor, cloneRelation1, cloneRelation2, cloneRelation3, 
				cloneRelation4, cloneRelation5);
		return cloneRelationX5;
	}

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(relation1, relation2, relation3, relation4, relation5));
		return componentDescriptors;
	}

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}
