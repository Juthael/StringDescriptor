package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;

import copycatModel.implementations.AbstractDescriptorV1;

public class RelationX6 extends HowManyRelations implements Cloneable {

	private static final String descriptorName = "relationX6";
	private Relation relation1;
	private Relation relation2;	
	private Relation relation3;
	private Relation relation4;
	private Relation relation5;
	private Relation relation6;
	
	public RelationX6(boolean codingDescriptor, Relation relation1, Relation relation2, Relation relation3, 
			Relation relation4, Relation relation5, Relation relation6) {
		super(codingDescriptor);
		this.relation1 = relation1;
		this.relation2 = relation2;
		this.relation3 = relation3;
		this.relation4 = relation4;
		this.relation5 = relation5;
		this.relation6 = relation6;
	}
	
	@Override
	protected RelationX6 clone() throws CloneNotSupportedException {
		RelationX6 cloneRelationX6;
		Relation cloneRelation1 = relation1.clone();
		Relation cloneRelation2 = relation2.clone();
		Relation cloneRelation3 = relation3.clone();
		Relation cloneRelation4 = relation4.clone();
		Relation cloneRelation5 = relation5.clone();
		Relation cloneRelation6 = relation6.clone();
		cloneRelationX6 = new RelationX6(isCodingDescriptor, cloneRelation1, cloneRelation2, cloneRelation3, 
				cloneRelation4, cloneRelation5, cloneRelation6);
		return cloneRelationX6;
	}

	@Override
	protected ArrayList<AbstractDescriptorV1> buildListOfComponents(){
		ArrayList<AbstractDescriptorV1> componentDescriptors = new ArrayList<AbstractDescriptorV1>(
				Arrays.asList(relation1, relation2, relation3, relation4, relation5, relation6));
		return componentDescriptors;
	}

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}
