package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.ISynTreeIntegrableElement;
import copycatModel.impl.SynTreeIntegrableElementImpl;
import exceptions.DescriptorsBuilderException;

public class Group extends HowManyGroups implements Cloneable, ISynTreeIntegrableElement {

	private static final String descriptorName = "group";
	private Size size;
	private Position position;
	private RelationsOrLetter relationsOrLetter;
	
	public Group(boolean codingDescriptor, Size size, Position position, RelationsOrLetter relationsOrLetter) 
			throws DescriptorsBuilderException {
		super(codingDescriptor);
		this.size = size;
		this.position = position;
		this.relationsOrLetter = relationsOrLetter;
	}
	
	@Override
	public Group clone()  throws CloneNotSupportedException {
		Size cloneSize = size.clone();
		Position clonePosition = position.clone();
		RelationsOrLetter cloneRelationsOrLetter;
		switch (relationsOrLetter.getDescriptorName()) {
			case "relations" : 
				Relations relationsCasted = (Relations) relationsOrLetter;
				cloneRelationsOrLetter = relationsCasted.clone();
				break;
			case "letter":
				Letter letterCasted = (Letter) relationsOrLetter;
				cloneRelationsOrLetter = letterCasted.clone();
				break;
			default : 
				throw new CloneNotSupportedException("Group : error in clone() method.");
		}
		Group cloneGroup;
		try {
			cloneGroup = new Group(isCodingDescriptor, cloneSize, clonePosition, cloneRelationsOrLetter);
		} catch (DescriptorsBuilderException e) {
			throw new CloneNotSupportedException("Group : error in clone() method.");
		}
		return cloneGroup;
	}

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(size, position, relationsOrLetter));
		return componentDescriptors;
	}
	
	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}