package verbalization.implementations.verbalStructureModel;

import java.util.ArrayList;
import java.util.List;

import exceptions.VerbalizationException;
import verbalization.interfaces.dataEncodingModel.InstructionCodeGetterInterface;
import verbalization.interfaces.dataEncodingModel.TransformationCodeGetterInterface;
import verbalization.interfaces.verbalStructureModel.EffectorInterface;
import verbalization.interfaces.verbalStructureModel.EnumerationInterface;
import verbalization.interfaces.verbalStructureModel.InstructionInterface;
import verbalization.interfaces.verbalStructureModel.RepeatOrderInterface;
import verbalization.interfaces.verbalStructureModel.SequenceInterface;

public class InstructionV2 implements InstructionInterface {

	private String verbalInstruction;
	
	public InstructionV2(InstructionCodeGetterInterface instructionCodeGetter) throws VerbalizationException {
		verbalInstruction = setVerbalInstruction(instructionCodeGetter);
	}

	@Override
	public String getVerbalInstruction() {
		return verbalInstruction;
	}
	
	private String setVerbalInstruction(InstructionCodeGetterInterface instructionCodeGetter) throws VerbalizationException {
		String verbalInstruction;
		String numberOfComponentsString = instructionCodeGetter.getNbOfRepetitionsCodeString();;
		int numberOfComponents = Integer.parseInt(numberOfComponentsString);
		List<TransformationCodeGetterInterface> listOfEnumerationCodeGetters = new ArrayList<TransformationCodeGetterInterface>();
		List<TransformationCodeGetterInterface> listOfVaryingSequencesCodeGetters = new ArrayList<TransformationCodeGetterInterface>();
		List<TransformationCodeGetterInterface> listOfConstantSequencesCodeGetters = new ArrayList<TransformationCodeGetterInterface>();
		List<TransformationCodeGetterInterface> listOfEffectorsCodeGetters = new ArrayList<TransformationCodeGetterInterface>();
		 
		List<TransformationCodeGetterInterface> listOfTransformationCodeGetters = 
				instructionCodeGetter.getListOfTransformationCodeGetters();
		for (TransformationCodeGetterInterface transformationCodeGetter : listOfTransformationCodeGetters) {
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
		RepeatOrderInterface repeatOrder = new RepeatOrderV2(numberOfComponentsString);
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
						TransformationCodeGetterInterface enumerationCodeGetter = listOfEnumerationCodeGetters.get(i);
						EnumerationInterface enumeration = new EnumerationV2(enumerationCodeGetter);
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
						TransformationCodeGetterInterface sequenceCodeGetter = listOfVaryingSequencesCodeGetters.get(i);
						SequenceInterface sequence = new SequenceV2(sequenceCodeGetter);
						sB.append(sequence.getVerbalTransformation());
						if (i < listOfVaryingSequencesCodeGetters.size() - 1)
							if (i < listOfVaryingSequencesCodeGetters.size() - 2)
								sB.append(", ");
							else sB.append(", and ");
					}
				}
			}
			else if (!listOfEffectorsCodeGetters.isEmpty()) {
				for (TransformationCodeGetterInterface effectorCodeGetter : listOfEffectorsCodeGetters) {
					EffectorInterface effector = new EffectorV2(effectorCodeGetter);
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
