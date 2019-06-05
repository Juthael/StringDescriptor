package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import model.copycatModel.ordSetGrammar.RelationOS;
import model.copycatModel.ordSetGrammar.SizeOS;
import model.copycatModel.ordSetGrammar.StructureOS;
import model.copycatModel.ordSetGrammar.StructureWithRelationOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.impl.GrammaticalST;

public class Structure extends GrammaticalST implements IGrammaticalST, Cloneable {

	private final static String DESCRIPTOR_NAME = "structure";
	private Size size;
	private Relation relation;
	
	public Structure(Size size, Relation relation) {
		this.size = size;
		this.relation = relation;
		setHashCode();
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
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) throws OrderedSetsGenerationException {
		IOrderedSet structureOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer structureIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String structureID = getDescriptorName().concat(structureIndex.toString());
		SizeOS sizeOS = (SizeOS) size.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		if (relation.getThisRelationIsUpgradable()) {
			RelationOS relationOS = (RelationOS) relation.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
			structureOS = new StructureWithRelationOS(structureID, sizeOS, relationOS);
		}
		else structureOS = new StructureOS(structureID, sizeOS);
		return structureOS;		
	}		

}
