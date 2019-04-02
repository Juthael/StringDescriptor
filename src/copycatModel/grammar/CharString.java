package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;

import copycatModel.implementations.AbstractDescriptorV1;

public class CharString extends AbstractDescriptorV1 implements Cloneable {
	
	private static String descriptorName = "charString";
	protected Direction direction;
	protected Structure structure;
	protected Groups groups;

	public CharString(boolean codingDescriptor, Direction direction, Structure structure, Groups groups) {
		super(codingDescriptor);
		this.direction = direction;
		this.structure = structure;
		this.groups = groups;
	}
	
	@Override 
	protected CharString clone() throws CloneNotSupportedException {
		Direction cloneDirection = direction.clone();
		Structure cloneStructuration = structure.clone();
		Groups cloneGroups = groups.clone();
		CharString cloneCharString = new CharString(isCodingDescriptor, cloneDirection, cloneStructuration, cloneGroups);
		return cloneCharString;
	}
	
	@Override
	protected ArrayList<AbstractDescriptorV1> buildListOfComponents(){
		ArrayList<AbstractDescriptorV1> componentDescriptors = new ArrayList<AbstractDescriptorV1>(
				Arrays.asList(direction, structure, groups));
		return componentDescriptors;
	}
	
	@Override
	public String getDescriptorName() {
		return descriptorName;
	}
	
}
