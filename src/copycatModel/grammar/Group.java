package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;

import copycatModel.implementations.AbstractDescriptorV1;
import copycatModel.interfaces.AbstractDescriptorInterface;
import exceptions.DescriptorsBuilderCriticalException;

public class Group extends HowManyGroups implements Cloneable, AbstractDescriptorInterface {

	private static final String descriptorName = "group";
	private Size size;
	private Position position;
	private RelationsOrLetter relationsOrLetter;
	
	public Group(boolean codingDescriptor, Size size, Position position, RelationsOrLetter relationsOrLetter) 
			throws DescriptorsBuilderCriticalException {
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
		} catch (DescriptorsBuilderCriticalException e) {
			throw new CloneNotSupportedException("Group : error in clone() method.");
		}
		return cloneGroup;
	}

	@Override
	protected ArrayList<AbstractDescriptorV1> buildListOfComponents(){
		ArrayList<AbstractDescriptorV1> componentDescriptors = new ArrayList<AbstractDescriptorV1>(
				Arrays.asList(size, position, relationsOrLetter));
		return componentDescriptors;
	}
	
	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}
