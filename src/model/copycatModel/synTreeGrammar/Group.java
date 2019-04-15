package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.SynTreeGenerationException;
import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

public class Group extends HowManyGroups implements Cloneable, ISynTreeElement {

	private static final String DESCRIPTOR_NAME = "group";
	private Size size;
	private Position position;
	private RelationsOrLetter relationsOrLetter;
	
	public Group(boolean codingDescriptor, Size size, Position position, RelationsOrLetter relationsOrLetter) 
			throws SynTreeGenerationException {
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
			cloneGroup = new Group(isCodingByDecomposition, cloneSize, clonePosition, cloneRelationsOrLetter);
		} catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("Group : error in clone() method.");
		}
		return cloneGroup;
	}

	@Override
	protected List<IElement> buildListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(size, position, relationsOrLetter));
		return componentDescriptors;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath(){
		List<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		List<SynTreeElementImpl> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (SynTreeElementImpl componentDescriptor : listOfRelevantComponents) {
			List<String> listOfComponentRelevantPropertiesWithPath = 
					componentDescriptor.getListOfRelevantPropertiesWithPath();
			for (String propertyWithPath : listOfComponentRelevantPropertiesWithPath){
				String propertyWithUpdatedPath = 
						this.getDescriptorName().concat(Settings.PATH_SEPARATOR + propertyWithPath);
				listOfRelevantPropertiesWithPath.add(propertyWithUpdatedPath);
			}
		}
		return listOfRelevantPropertiesWithPath;
	}	

}
