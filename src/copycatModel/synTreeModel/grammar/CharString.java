package copycatModel.synTreeModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.synTreeModel.impl.SynTreeIntegrableElementImpl;

public class CharString extends SynTreeIntegrableElementImpl implements Cloneable {
	
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
		CharString cloneCharString = new CharString(isCodingByDecomposition, cloneDirection, cloneStructuration, cloneGroups);
		return cloneCharString;
	}
	
	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(direction, structure, groups));
		return componentDescriptors;
	}
	
	@Override
	public String getDescriptorName() {
		return descriptorName;
	}
	
}
