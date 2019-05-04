package stringDescription.impl;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;
import fca.exception.LMLogger;
import fca.gui.util.constant.LMIcons;
import fca.gui.util.constant.LMImages;
import model.orderedSetModel.impl.AbstractOmegaElement;
import model.synTreeModel.ISignal;
import stringDescription.IDescription;
import stringDescription.IScoreCalculator;
import stringDescription.impl.Description;
import stringDescription.impl.KnowledgeEfficiencyCalculator;
import stringDescription.impl.RelationalDensityCalculator;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.SignalBuilderImpl;

public class CalculatorsTesting {

	@Test
	public void testAABBCC() throws SynTreeGenerationException, CloneNotSupportedException, OrderedSetsGenerationException, VerbalizationException {
		LMLogger.getLMLogger();
		LMImages.getLMImages();
		LMIcons.getLMIcons();		
		ISignalBuilder signalBuilder = new SignalBuilderImpl("aabbcc", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		IScoreCalculator knowledgeEfficiencyCalculator = new KnowledgeEfficiencyCalculator();
		IScoreCalculator relationalDensity = new RelationalDensityCalculator();
		IDescription descriptionKE = new Description(signal, knowledgeEfficiencyCalculator);
		IDescription descriptionRD = new Description(signal, relationalDensity);
		Map<String, Double> verbalDescriptionToScoreKE = descriptionKE.getVerbalDescriptionToScoreMapping();
		Map<String, Double> verbalDescriptionToScoreRD = descriptionRD.getVerbalDescriptionToScoreMapping();
		List<String> orderedListOfIDsKE = descriptionKE.getOrderedListOfOrderedSetIDs();
		List<String> orderedListOfIDsRD = descriptionRD.getOrderedListOfOrderedSetIDs();
		System.out.println("SCORE CALCULATOR COMPARISON : ");
		System.out.println("");
		System.out.println("KNOWLEDGE EFFICIENCY CALULATOR : ");
		System.out.println("");
		for (String setID : orderedListOfIDsKE) {
			AbstractOmegaElement set = (AbstractOmegaElement) descriptionKE.getOrderedSetIDToOrderedSet().get(setID);
			System.out.println(set.getVerbalDescription());
			System.out.print("Score : ");
			System.out.println(descriptionKE.getOrderedSetIDToScoreMapping().get(setID).toString());
			System.out.println("");
		}
		System.out.println("RELATIONAL DENSITY CALULATOR : ");
		System.out.println("");
		for (String setID : orderedListOfIDsRD) {
			AbstractOmegaElement set = (AbstractOmegaElement) descriptionRD.getOrderedSetIDToOrderedSet().get(setID);
			System.out.println(set.getVerbalDescription());
			System.out.print("Score : ");
			System.out.println(descriptionRD.getOrderedSetIDToScoreMapping().get(setID).toString());
			System.out.println("");
		}		
	}

}
