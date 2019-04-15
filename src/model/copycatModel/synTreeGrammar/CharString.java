package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;

public class CharString extends SynTreeElementImpl implements ISynTreeElement, Cloneable {
	
	private final static String DESCRIPTOR_NAME = "charString";
	protected Direction direction;
	protected Structure structure;
	protected Groups groups;

	public CharString(Direction direction, Structure structure, Groups groups) {
		super(false);
		this.direction = direction;
		this.structure = structure;
		this.groups = groups;
	}
	
	@Override 
	protected CharString clone() throws CloneNotSupportedException {
		Direction cloneDirection = direction.clone();
		Structure cloneStructuration = structure.clone();
		Groups cloneGroups = groups.clone();
		CharString cloneCharString = new CharString(cloneDirection, cloneStructuration, cloneGroups);
		return cloneCharString;
	}
	
	@Override
	protected List<IElement> buildListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(direction, structure, groups));
		return componentDescriptors;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}
	
}
