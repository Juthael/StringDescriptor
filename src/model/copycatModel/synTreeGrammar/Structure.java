package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.ISizeOS;
import model.copycatModel.ordSetGrammar.RelationOS;
import model.copycatModel.ordSetGrammar.StructureOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;

public class Structure extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	private final static String DESCRIPTOR_NAME = "structure";
	private Size size;
	private Relation relation;
	
	public Structure(Size size, Relation relation) {
		this.size = size;
		this.relation = relation;
	}
	
	@Override
	protected Structure clone() throws CloneNotSupportedException {
		Structure cloneStructuration;
		Size cloneSize = size.clone();
		Relation cloneRelation = relation.clone();
		cloneStructuration = new Structure(cloneSize, cloneRelation);
		return cloneStructuration;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = new ArrayList<IElement>(
				Arrays.asList(size, relation));
		return listOfComponents;
	}
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		IOrderedSet structureOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer structureIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String structureID = getDescriptorName().concat(structureIndex.toString());
		ISizeOS sizeOS = (ISizeOS) size.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		RelationOS relationOS = (RelationOS) relation.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		structureOS = new StructureOS(structureID, sizeOS, relationOS);
		return structureOS;		
	}		

}
