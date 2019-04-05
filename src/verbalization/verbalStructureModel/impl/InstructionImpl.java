package verbalization.verbalStructureModel.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.VerbalizationException;
import verbalization.dataEncoding.encodingModel.IInstructionCodeGetter;
import verbalization.dataEncoding.encodingModel.ITransformationCodeGetter;
import verbalization.verbalStructureModel.IEffector;
import verbalization.verbalStructureModel.IEnumeration;
import verbalization.verbalStructureModel.IInstruction;
import verbalization.verbalStructureModel.IRepeatOrder;
import verbalization.verbalStructureModel.ISequence;

public class InstructionImpl implements IInstruction {

	private String verbalInstruction;
	
	public InstructionImpl(IInstructionCodeGetter instructionCodeGetter) throws VerbalizationException {
		verbalInstruction = setVerbalInstruction(instructionCodeGetter);
	}

	@Override
	public String getVerbalInstruction() {
		return verbalInstruction;
	}
	
	private String setVerbalInstruction(IInstructionCodeGetter instructionCodeGetter) throws VerbalizationException {
		String verbalInstruction;
		String numberOfComponentsString = instructionCodeGetter.getNbOfRepetitionsCodeString();;
		int numberOfComponents = Integer.parseInt(numberOfComponentsString);
		List<ITransformationCodeGetter> listOfEnumerationCodeGetters = new ArrayList<ITransformationCodeGetter>();
		List<ITransformationCodeGetter> listOfVaryingSequencesCodeGetters = new ArrayList<ITransformationCodeGetter>();
		List<ITransformationCodeGetter> listOfConstantSequencesCodeGetters = new ArrayList<ITransformationCodeGetter>();
		List<ITransformationCodeGetter> listOfEffectorsCodeGetters = new ArrayList<ITransformationCodeGetter>();
		 
		List<ITransformationCodeGetter> listOfTransformationCodeGetters = 
				instructionCodeGetter.getListOfTransformationCodeGetters();
		for (ITransformationCodeGetter transformationCodeGetter : listOfTransformationCodeGetters) {
			String firstPredicateCode = transformationCodeGetter.getListOfPredicateCodes().get(0);
			if (firstPredicateCode.contains("crease"))
				listOfVaryingSequencesCodeGetters.add(transformationCodeGetter);
			else if (firstPredicateCode.contains("Equals"))
				listOfConstantSequencesCodeGetters.add(transformationCodeGetter);
			else if (firstPredicateCode.contains("Enumerate"))
				listOfEnumerationCodeGetters.add(transformationCodeGetter);
			else if (firstPredicateCode.contains("Effector")) {
				listOfEffectorsCodeGetters.add(transformationCodeGetter);
			}
			else throw new VerbalizationException("Instruction.setVerbalInstruction() : unknown predicate Code '" 
					+ firstPredicateCode + "'.");
		}
		StringBuilder sB = new StringBuilder();
		IRepeatOrder repeatOrder = new RepeatOrderImpl(numberOfComponentsString);
		sB.append(repeatOrder.getverbalRepeatOrder());
		if ((numberOfComponents == 2 && listOfEnumerationCodeGetters.isEmpty() && !listOfVaryingSequencesCodeGetters.isEmpty())
				|| (numberOfComponents > 2 &&	(!listOfEnumerationCodeGetters.isEmpty() || !listOfVaryingSequencesCodeGetters.isEmpty()))) {
			sB.append(", ");
		}
		if (!listOfEnumerationCodeGetters.isEmpty() || !listOfConstantSequencesCodeGetters.isEmpty() || 
				!listOfVaryingSequencesCodeGetters.isEmpty() || !listOfEffectorsCodeGetters.isEmpty()) {
			if (!listOfEnumerationCodeGetters.isEmpty() || !listOfVaryingSequencesCodeGetters.isEmpty()) {
				if (!listOfEnumerationCodeGetters.isEmpty()) {
					for (int i=0 ; i<listOfEnumerationCodeGetters.size() ; i++) {
						ITransformationCodeGetter enumerationCodeGetter = listOfEnumerationCodeGetters.get(i);
						IEnumeration enumeration = new EnumerationImpl(enumerationCodeGetter);
						sB.append(enumeration.getVerbalTransformation());
						if (i < listOfEnumerationCodeGetters.size() - 1 && 
								!instructionCodeGetter.getNbOfRepetitionsCodeString().equals("1")) {
							if (i < listOfEnumerationCodeGetters.size() - 2)
								sB.append(", ");
							else sB.append(", and ");
						}
					}
				}
				if (!listOfVaryingSequencesCodeGetters.isEmpty()) {
					if (!listOfEnumerationCodeGetters.isEmpty())
						sB.append(", and ");
					for (int i=0 ; i<listOfVaryingSequencesCodeGetters.size() ; i++) {
						ITransformationCodeGetter sequenceCodeGetter = listOfVaryingSequencesCodeGetters.get(i);
						ISequence sequence = new SequenceImpl(sequenceCodeGetter);
						sB.append(sequence.getVerbalTransformation());
						if (i < listOfVaryingSequencesCodeGetters.size() - 1)
							if (i < listOfVaryingSequencesCodeGetters.size() - 2)
								sB.append(", ");
							else sB.append(", and ");
					}
				}
			}
			else if (!listOfEffectorsCodeGetters.isEmpty()) {
				for (ITransformationCodeGetter effectorCodeGetter : listOfEffectorsCodeGetters) {
					IEffector effector = new EffectorImpl(effectorCodeGetter);
					sB.append(effector.getVerbalTransformation());
				}
			}
			sB.append(" ;");
			verbalInstruction = sB.toString();
			return verbalInstruction;
		}
		else throw new VerbalizationException("InstructionV2 : all transformation lists are empty.");
	}

}
