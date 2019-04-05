package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;

public class RelationX8 extends HowManyRelations implements Cloneable {

	private static final String descriptorName = "relationX8";
	private Relation relation1;
	private Relation relation2;	
	private Relation relation3;
	private Relation relation4;
	private Relation relation5;
	private Relation relation6;
	private Relation relation7;
	private Relation relation8;
	
	public RelationX8(boolean codingDescriptor, Relation relation1, Relation relation2, Relation relation3, 
			Relation relation4, Relation relation5, Relation relation6, Relation relation7, Relation relation8) {
		super(codingDescriptor);
		this.relation1 = relation1;
		this.relation2 = relation2;
		this.relation3 = relation3;
		this.relation4 = relation4;
		this.relation5 = relation5;
		this.relation6 = relation6;
		this.relation7 = relation7;
		this.relation8 = relation8;
	}
	
	@Override
	protected RelationX8 clone() throws CloneNotSupportedException {
		RelationX8 cloneRelationX8;
		Relation cloneRelation1 = relation1.clone();
		Relation cloneRelation2 = relation2.clone();
		Relation cloneRelation3 = relation3.clone();
		Relation cloneRelation4 = relation4.clone();
		Relation cloneRelation5 = relation5.clone();
		Relation cloneRelation6 = relation6.clone();
		Relation cloneRelation7 = relation7.clone();
		Relation cloneRelation8 = relation8.clone();
		cloneRelationX8 = new RelationX8(isCodingDescriptor, cloneRelation1, cloneRelation2, cloneRelation3, 
				cloneRelation4, cloneRelation5, cloneRelation6, cloneRelation7, cloneRelation8);
		return cloneRelationX8;
	}

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(relation1, relation2, relation3, relation4, relation5, relation6, relation7, relation8));
		return componentDescriptors;
	}

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}
}
