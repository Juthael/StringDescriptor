package syntacticTreesGeneration.implementations;

import java.util.ArrayList;
import java.util.List;

import copycatModel.grammar.CharString;
import copycatModel.grammar.Direction;
import copycatModel.grammar.Group;
import copycatModel.grammar.Groups;
import copycatModel.grammar.Relation;
import copycatModel.grammar.Size;
import copycatModel.grammar.Structure;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.interfaces.CharStringBuilderInterface;
import syntacticTreesGeneration.interfaces.EnumerationCheckerInterface;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.GroupsBuilderInterface;
import syntacticTreesGeneration.interfaces.RelationDataContainerInterface;
import syntacticTreesGeneration.interfaces.RelationalDataInterface;
import syntacticTreesGeneration.interfaces.SequenceCheckerInterface;
import syntacticTreesGeneration.interfaces.SequenceRelationalDataInterface;
import syntacticTreesGeneration.interfaces.SymmetryCheckerInterface;
import syntacticTreesGeneration.interfaces.SymmetryRelationalDataInterface;

public class CharStringBuilderV1 implements CharStringBuilderInterface {

	private final Direction direction;
	private final Structure structure;
	private final Groups groups;
	
	public CharStringBuilderV1(String directionValue, ArrayList<Group> listOfGroups, 
			RelationDataContainerInterface relationDataContainer) 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		direction = new Direction(false, directionValue);
		String charStringSizeValue = getCharStringSizeValue(listOfGroups);
		Size size = new Size(false, charStringSizeValue);
		Relation structureRelation;
		GroupsBuilderInterface groupsBuilder;
		if (relationDataContainer.getListOfEnumerations().size() == 0) {
			groupsBuilder = new GroupsBuilderV1(listOfGroups, DescGenSettings.LIST_OF_GROUPS_COVER_THE_FULL_STRING);
			structureRelation = getStructureRelation(listOfGroups);
		} 
		else {
			groupsBuilder = new GroupsBuilder_RelationalV1(listOfGroups, relationDataContainer);
			if (DescGenSettings.TAKE_SUBCOMP_INTO_ACCOUNT_IF_CHARSTRING_HAS_MONOSTRUCTURE)
				structureRelation = getStructureRelation(listOfGroups);
			else structureRelation = getMonoStructureRelation(listOfGroups);
		}
		
		structure = new Structure(false, size, structureRelation);		
		groups = groupsBuilder.getGroups();
	}
	
	@Override
	public CharString getCharString() throws DescriptorsBuilderCriticalException {
		CharString charString = new CharString(false, direction, structure, groups);
		return charString;
	}
	
	private Relation getStructureRelation(List<Group> listOfGroups) throws DescriptorsBuilderCriticalException {
		Relation structureRelation;
		String dimension = "charString/groups/group/size";
		ArrayList<String> listOfSizeValues = new ArrayList<String>();
		for(Group group : listOfGroups) {
			List<Integer> currentGroupLetterPositions = DescriptorSpanGetterV1.getDescriptorSpan(group);
			listOfSizeValues.add(Integer.toString(currentGroupLetterPositions.size()));
		}
		ArrayList<RelationalDataInterface> listOfRelationalData = new ArrayList<RelationalDataInterface>();
		EnumerationCheckerInterface enumerationChecker = new EnumerationCheckerV1(true, dimension, listOfSizeValues);
		if (enumerationChecker.getEnumerationWasFound() == true) {
			EnumerationRelationalDataInterface enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
			listOfRelationalData.add(enumerationRelationalData);
			SequenceCheckerInterface sequenceChecker = 
					new SequenceCheckerV1(true, dimension, listOfSizeValues, enumerationRelationalData);
			if (sequenceChecker.getSequenceWasFound() == true) {
				SequenceRelationalDataInterface sequenceRelationalData = sequenceChecker.getSequenceRelationalData();
				listOfRelationalData.add(sequenceRelationalData);
			}
			SymmetryCheckerInterface symmetryChecker = 
					new SymmetryCheckerV1(true, dimension, listOfSizeValues, enumerationRelationalData);
			if (symmetryChecker.getSymmetryWasFound() == true) {
				SymmetryRelationalDataInterface symmetryRelationalData = symmetryChecker.getSymmetryRelationalData();
				listOfRelationalData.add(symmetryRelationalData);
			}
			structureRelation = RelationBuilderV1.buildRelation(listOfRelationalData);
		}
		else throw new DescriptorsBuilderCriticalException("CharStringBuilder.getStructureRelation() : "
				+ "no enumeration was found.");
		return structureRelation;	
	}
	
	private Relation getMonoStructureRelation(List<Group> listOfGroups) throws DescriptorsBuilderCriticalException {
		Relation structureRelation;
		String dimension = "charString/groups/group/size";
		ArrayList<String> listOfSizeValues = new ArrayList<String>();
		ArrayList<Integer> listOfLetterPositions = new ArrayList<Integer>();
		for(Group group : listOfGroups) {
			listOfLetterPositions.addAll(DescriptorSpanGetterV1.getDescriptorSpan(group));
		}
		listOfSizeValues.add(Integer.toString(listOfLetterPositions.size()));
		ArrayList<RelationalDataInterface> listOfRelationalData = new ArrayList<RelationalDataInterface>();
		EnumerationCheckerInterface enumerationChecker = new EnumerationCheckerV1(true, dimension, listOfSizeValues);
		if (enumerationChecker.getEnumerationWasFound() == true) {
			EnumerationRelationalDataInterface enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
			listOfRelationalData.add(enumerationRelationalData);
			SequenceCheckerInterface sequenceChecker = 
					new SequenceCheckerV1(true, dimension, listOfSizeValues, enumerationRelationalData);
			if (sequenceChecker.getSequenceWasFound() == true) {
				SequenceRelationalDataInterface sequenceRelationalData = sequenceChecker.getSequenceRelationalData();
				listOfRelationalData.add(sequenceRelationalData);
			}
			SymmetryCheckerInterface symmetryChecker = 
					new SymmetryCheckerV1(true, dimension, listOfSizeValues, enumerationRelationalData);
			if (symmetryChecker.getSymmetryWasFound() == true) {
				SymmetryRelationalDataInterface symmetryRelationalData = symmetryChecker.getSymmetryRelationalData();
				listOfRelationalData.add(symmetryRelationalData);
			}
			structureRelation = RelationBuilderV1.buildRelation(listOfRelationalData);
		}
		else throw new DescriptorsBuilderCriticalException("CharStringBuilder.getMonoStructureRelation() : "
				+ "no enumeration was found.");
		return structureRelation;	
	}	
	
	private String getCharStringSizeValue(List<Group> listOfGroups) throws DescriptorsBuilderCriticalException {
		String charStringSizeValue;
		List<Integer> listOfLetterPositions = new ArrayList<Integer>();
		for (Group group : listOfGroups) {
			listOfLetterPositions.addAll(DescriptorSpanGetterV1.getDescriptorSpan(group));
		}
		charStringSizeValue = Integer.toString(listOfLetterPositions.size());
		return charStringSizeValue;
	}

}
