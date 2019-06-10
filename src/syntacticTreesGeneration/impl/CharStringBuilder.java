package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.CharString;
import model.copycatModel.synTreeGrammar.Direction;
import model.copycatModel.synTreeGrammar.Frame;
import model.copycatModel.synTreeGrammar.Components;
import model.copycatModel.synTreeGrammar.Relation;
import model.copycatModel.synTreeGrammar.Size;
import model.copycatModel.synTreeGrammar.Structure;
import settings.Settings;
import syntacticTreesGeneration.IComponentsBuilder;
import syntacticTreesGeneration.ICharStringBuilder;
import syntacticTreesGeneration.IEnumerationChecker;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.IRelationalData;
import syntacticTreesGeneration.ISequenceChecker;
import syntacticTreesGeneration.ISequenceRelationalData;
import syntacticTreesGeneration.ISymmetryChecker;
import syntacticTreesGeneration.ISymmetryRelationalData;

public class CharStringBuilder implements ICharStringBuilder {

	private final Direction direction;
	private final Structure structure;
	private final Components components;
	
	public CharStringBuilder(String directionValue, List<Frame> listOfFrames, 
			IRelationDataContainer relationDataContainer) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		direction = new Direction(directionValue);
		String charStringSizeValue = getCharStringSizeValue(listOfFrames);
		Size size = new Size(charStringSizeValue);
		Relation structureRelation;
		IComponentsBuilder componentsBuilder;
		if (relationDataContainer.getListOfEnumerations().size() == 0) {
			componentsBuilder = new ComponentsBuilder(listOfFrames, Settings.LIST_OF_FRAMES_COVER_THE_FULL_STRING);
			structureRelation = getStructureRelation(listOfFrames);
		} 
		else {
			componentsBuilder = new ComponentsBuilderRelational(listOfFrames, relationDataContainer, 
					Settings.LIST_OF_FRAMES_COVER_THE_FULL_STRING);
			if (Settings.TAKE_SUBCOMP_INTO_ACCOUNT_IF_CHARSTRING_HAS_MONOSTRUCTURE)
				structureRelation = getStructureRelation(listOfFrames);
			else structureRelation = getMonoStructureRelation(listOfFrames);
		}
		structure = new Structure(size, structureRelation);		
		components = componentsBuilder.getComponents();
	}
	
	@Override
	public CharString getCharString() throws SynTreeGenerationException {
		CharString charString = new CharString(direction, structure, components);
		return charString;
	}
	
	private Relation getStructureRelation(List<Frame> listOfFrames) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		Relation structureRelation;
		String dimension = Settings.STRUCTURE_RELATION_CONVENTIONAL_DIMENSION;
		List<String> listOfSizeValues = new ArrayList<String>();
		for(Frame frame : listOfFrames) {
			List<Integer> currentFrameLetterPositions = DescriptorSpanGetter.getDescriptorSpan(frame);
			listOfSizeValues.add(Integer.toString(currentFrameLetterPositions.size()));
		}
		List<IRelationalData> listOfRelationalData = new ArrayList<IRelationalData>();
		IEnumerationChecker enumerationChecker = new EnumerationChecker(true, dimension, listOfSizeValues);
		if (enumerationChecker.getEnumerationWasFound() == true) {
			IEnumerationRelationalData enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
			listOfRelationalData.add(enumerationRelationalData);
			ISequenceChecker sequenceChecker = 
					new SequenceChecker(true, dimension, listOfSizeValues, enumerationRelationalData);
			if (sequenceChecker.getSequenceWasFound() == true) {
				ISequenceRelationalData sequenceRelationalData = sequenceChecker.getSequenceRelationalData();
				listOfRelationalData.add(sequenceRelationalData);
			}
			ISymmetryChecker symmetryChecker = 
					new SymmetryChecker(true, dimension, listOfSizeValues, enumerationRelationalData);
			if (symmetryChecker.getSymmetryWasFound() == true) {
				ISymmetryRelationalData symmetryRelationalData = symmetryChecker.getSymmetryRelationalData();
				listOfRelationalData.add(symmetryRelationalData);
			}
			structureRelation = RelationBuilder.buildRelation(listOfRelationalData);
		}
		else throw new SynTreeGenerationException("CharStringBuilder.getStructureRelation() : "
				+ "no enumeration was found.");
		return structureRelation;	
	}
	
	private Relation getMonoStructureRelation(List<Frame> listOfFrames) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		Relation structureRelation;
		String dimension = Settings.STRUCTURE_RELATION_CONVENTIONAL_DIMENSION;
		List<String> listOfSizeValues = new ArrayList<String>();
		List<Integer> listOfLetterPositions = new ArrayList<Integer>();
		for(Frame frame : listOfFrames) {
			listOfLetterPositions.addAll(DescriptorSpanGetter.getDescriptorSpan(frame));
		}
		listOfSizeValues.add(Integer.toString(listOfLetterPositions.size()));
		List<IRelationalData> listOfRelationalData = new ArrayList<IRelationalData>();
		IEnumerationChecker enumerationChecker = new EnumerationChecker(true, dimension, listOfSizeValues);
		if (enumerationChecker.getEnumerationWasFound() == true) {
			IEnumerationRelationalData enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
			listOfRelationalData.add(enumerationRelationalData);
			ISequenceChecker sequenceChecker = 
					new SequenceChecker(true, dimension, listOfSizeValues, enumerationRelationalData);
			if (sequenceChecker.getSequenceWasFound() == true) {
				ISequenceRelationalData sequenceRelationalData = sequenceChecker.getSequenceRelationalData();
				listOfRelationalData.add(sequenceRelationalData);
			}
			ISymmetryChecker symmetryChecker = 
					new SymmetryChecker(true, dimension, listOfSizeValues, enumerationRelationalData);
			if (symmetryChecker.getSymmetryWasFound() == true) {
				ISymmetryRelationalData symmetryRelationalData = symmetryChecker.getSymmetryRelationalData();
				listOfRelationalData.add(symmetryRelationalData);
			}
			structureRelation = RelationBuilder.buildRelation(listOfRelationalData);
		}
		else throw new SynTreeGenerationException("CharStringBuilder.getMonoStructureRelation() : "
				+ "no enumeration was found.");
		return structureRelation;	
	}	
	
	private String getCharStringSizeValue(List<Frame> listOfFrames) throws SynTreeGenerationException {
		String charStringSizeValue;
		List<Integer> listOfLetterPositions = new ArrayList<Integer>();
		for (Frame frame : listOfFrames) {
			listOfLetterPositions.addAll(DescriptorSpanGetter.getDescriptorSpan(frame));
		}
		charStringSizeValue = Integer.toString(listOfLetterPositions.size());
		return charStringSizeValue;
	}

}
