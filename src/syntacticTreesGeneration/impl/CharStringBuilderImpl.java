package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.CharString;
import model.copycatModel.synTreeGrammar.Direction;
import model.copycatModel.synTreeGrammar.Group;
import model.copycatModel.synTreeGrammar.Groups;
import model.copycatModel.synTreeGrammar.Relation;
import model.copycatModel.synTreeGrammar.Size;
import model.copycatModel.synTreeGrammar.Structure;
import settings.Settings;
import syntacticTreesGeneration.IGroupsBuilder;
import syntacticTreesGeneration.ICharStringBuilder;
import syntacticTreesGeneration.IEnumerationChecker;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.IRelationalData;
import syntacticTreesGeneration.ISequenceChecker;
import syntacticTreesGeneration.ISequenceRelationalData;
import syntacticTreesGeneration.ISymmetryChecker;
import syntacticTreesGeneration.ISymmetryRelationalData;

public class CharStringBuilderImpl implements ICharStringBuilder {

	private final Direction direction;
	private final Structure structure;
	private final Groups groups;
	
	public CharStringBuilderImpl(String directionValue, List<Group> listOfGroups, 
			IRelationDataContainer relationDataContainer) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		direction = new Direction(directionValue);
		String charStringSizeValue = getCharStringSizeValue(listOfGroups);
		Size size = new Size(charStringSizeValue);
		Relation structureRelation;
		IGroupsBuilder groupsBuilder;
		if (relationDataContainer.getListOfEnumerations().size() == 0) {
			groupsBuilder = new GroupsBuilderImpl(listOfGroups, Settings.LIST_OF_GROUPS_COVER_THE_FULL_STRING);
			structureRelation = getStructureRelation(listOfGroups);
		} 
		else {
			groupsBuilder = new GroupsBuilderRelationalImpl(listOfGroups, relationDataContainer, 
					Settings.LIST_OF_GROUPS_COVER_THE_FULL_STRING);
			if (Settings.TAKE_SUBCOMP_INTO_ACCOUNT_IF_CHARSTRING_HAS_MONOSTRUCTURE)
				structureRelation = getStructureRelation(listOfGroups);
			else structureRelation = getMonoStructureRelation(listOfGroups);
		}
		structure = new Structure(size, structureRelation);		
		groups = groupsBuilder.getGroups();
	}
	
	@Override
	public CharString getCharString() throws SynTreeGenerationException {
		CharString charString = new CharString(direction, structure, groups);
		return charString;
	}
	
	private Relation getStructureRelation(List<Group> listOfGroups) throws SynTreeGenerationException {
		Relation structureRelation;
		String dimension = Settings.STRUCTURE_RELATION_CONVENTIONAL_DIMENSION;
		List<String> listOfSizeValues = new ArrayList<String>();
		for(Group group : listOfGroups) {
			List<Integer> currentGroupLetterPositions = DescriptorSpanGetterImpl.getDescriptorSpan(group);
			listOfSizeValues.add(Integer.toString(currentGroupLetterPositions.size()));
		}
		List<IRelationalData> listOfRelationalData = new ArrayList<IRelationalData>();
		IEnumerationChecker enumerationChecker = new EnumerationCheckerImpl(true, dimension, listOfSizeValues);
		if (enumerationChecker.getEnumerationWasFound() == true) {
			IEnumerationRelationalData enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
			listOfRelationalData.add(enumerationRelationalData);
			ISequenceChecker sequenceChecker = 
					new SequenceCheckerImpl(true, dimension, listOfSizeValues, enumerationRelationalData);
			if (sequenceChecker.getSequenceWasFound() == true) {
				ISequenceRelationalData sequenceRelationalData = sequenceChecker.getSequenceRelationalData();
				listOfRelationalData.add(sequenceRelationalData);
			}
			ISymmetryChecker symmetryChecker = 
					new SymmetryCheckerImpl(true, dimension, listOfSizeValues, enumerationRelationalData);
			if (symmetryChecker.getSymmetryWasFound() == true) {
				ISymmetryRelationalData symmetryRelationalData = symmetryChecker.getSymmetryRelationalData();
				listOfRelationalData.add(symmetryRelationalData);
			}
			structureRelation = RelationBuilderImpl.buildRelation(listOfRelationalData);
		}
		else throw new SynTreeGenerationException("CharStringBuilder.getStructureRelation() : "
				+ "no enumeration was found.");
		return structureRelation;	
	}
	
	private Relation getMonoStructureRelation(List<Group> listOfGroups) throws SynTreeGenerationException {
		Relation structureRelation;
		String dimension = Settings.STRUCTURE_RELATION_CONVENTIONAL_DIMENSION;
		List<String> listOfSizeValues = new ArrayList<String>();
		List<Integer> listOfLetterPositions = new ArrayList<Integer>();
		for(Group group : listOfGroups) {
			listOfLetterPositions.addAll(DescriptorSpanGetterImpl.getDescriptorSpan(group));
		}
		listOfSizeValues.add(Integer.toString(listOfLetterPositions.size()));
		List<IRelationalData> listOfRelationalData = new ArrayList<IRelationalData>();
		IEnumerationChecker enumerationChecker = new EnumerationCheckerImpl(true, dimension, listOfSizeValues);
		if (enumerationChecker.getEnumerationWasFound() == true) {
			IEnumerationRelationalData enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
			listOfRelationalData.add(enumerationRelationalData);
			ISequenceChecker sequenceChecker = 
					new SequenceCheckerImpl(true, dimension, listOfSizeValues, enumerationRelationalData);
			if (sequenceChecker.getSequenceWasFound() == true) {
				ISequenceRelationalData sequenceRelationalData = sequenceChecker.getSequenceRelationalData();
				listOfRelationalData.add(sequenceRelationalData);
			}
			ISymmetryChecker symmetryChecker = 
					new SymmetryCheckerImpl(true, dimension, listOfSizeValues, enumerationRelationalData);
			if (symmetryChecker.getSymmetryWasFound() == true) {
				ISymmetryRelationalData symmetryRelationalData = symmetryChecker.getSymmetryRelationalData();
				listOfRelationalData.add(symmetryRelationalData);
			}
			structureRelation = RelationBuilderImpl.buildRelation(listOfRelationalData);
		}
		else throw new SynTreeGenerationException("CharStringBuilder.getMonoStructureRelation() : "
				+ "no enumeration was found.");
		return structureRelation;	
	}	
	
	private String getCharStringSizeValue(List<Group> listOfGroups) throws SynTreeGenerationException {
		String charStringSizeValue;
		List<Integer> listOfLetterPositions = new ArrayList<Integer>();
		for (Group group : listOfGroups) {
			listOfLetterPositions.addAll(DescriptorSpanGetterImpl.getDescriptorSpan(group));
		}
		charStringSizeValue = Integer.toString(listOfLetterPositions.size());
		return charStringSizeValue;
	}

}
