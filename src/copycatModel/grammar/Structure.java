package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;

import copycatModel.implementations.AbstractDescriptorV1;

public class Structure extends AbstractDescriptorV1 implements Cloneable {

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
		cloneStructuration = new Structure(isCodingDescriptor, cloneSize, cloneRelation);
		return cloneStructuration;
	}

	@Override
	protected ArrayList<AbstractDescriptorV1> buildListOfComponents() {
		ArrayList<AbstractDescriptorV1> listOfComponents = new ArrayList<AbstractDescriptorV1>(
				Arrays.asList(size, relation));
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}
