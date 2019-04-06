package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;

public class Relation extends HowManyRelations implements Cloneable {
	
	protected static final String descriptorName = "relation";
	protected Dimension dimension;
	protected Enumeration enumeration;

	public Relation(boolean codingDescriptor, Dimension dimension, Enumeration enumeration) {
		super(codingDescriptor);
		this.dimension = dimension;
		this.enumeration = enumeration;
	}
	
	@Override
	protected Relation clone() throws CloneNotSupportedException {
		Relation cloneRelation;
		Dimension cloneDimension = dimension.clone();
		Enumeration cloneEnumeration = enumeration.clone();
		cloneRelation = new Relation(isCodingDescriptor, cloneDimension, cloneEnumeration);
		return cloneRelation;
	}
	
	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(dimension, enumeration));
		return componentDescriptors;
	}
	
	@Override
	public String getDescriptorName() {
		return descriptorName;
	}
}
