package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.DimensionOS;
import model.copycatModel.ordSetGrammar.EnumerationOS;
import model.copycatModel.ordSetGrammar.RelationOS;
import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.synTreeModel.ISynTreeElement;

public class Relation extends HowManyRelations implements ISynTreeElement, Cloneable {
	
	protected static final String DESCRIPTOR_NAME = "relation";
	protected Dimension dimension;
	protected Enumeration enumeration;

	public Relation(Dimension dimension, Enumeration enumeration) {
		this.dimension = dimension;
		this.enumeration = enumeration;
	}
	
	@Override
	protected Relation clone() throws CloneNotSupportedException {
		Relation cloneRelation;
		Dimension cloneDimension = dimension.clone();
		Enumeration cloneEnumeration = enumeration.clone();
		cloneRelation = new Relation(cloneDimension, cloneEnumeration);
		return cloneRelation;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(dimension, enumeration));
		return componentDescriptors;
	}		
	
	@Override
	public ISetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ISetElement relationOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer relationIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String relationID = getDescriptorName().concat(relationIndex.toString());
		DimensionOS dimensionOS = (DimensionOS) dimension.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		EnumerationOS enumerationOS = (EnumerationOS) enumeration.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		relationOS = new RelationOS(relationID, dimensionOS, enumerationOS);
		return relationOS;		
	}	
	
}
