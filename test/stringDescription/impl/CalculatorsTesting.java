package stringDescription.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;
import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import fca.exception.LMLogger;
import fca.gui.lattice.LatticeViewer;
import fca.gui.lattice.element.GraphicalLattice;
import fca.gui.lattice.element.LatticeStructure;
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
		/* LMLogger.getLMLogger();
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
		}		*/
	}
	
	@Test
	public void whenSignalEnteredThenMappingOfOrderedSetIDToConceptLatticeCanBeProvided() {
		try {
			ISignalBuilder signalBuilder = new SignalBuilderImpl("aabbcc", "fromLeftToRight");
			ISignal signal = signalBuilder.getSignal();
			IScoreCalculator scoreCalculator = new KnowledgeEfficiencyCalculator();
			Description description = new Description(signal, scoreCalculator);
			Set<String> orderedSetsIDS = description.getOrderedSetIDToBinaryContextMapping().keySet();
			Map<String, ConceptLattice> orderedSetIDToConceptLattice = description.getOrderedSetIDToConceptLatticeMapping();
			List<String> listOfIDs = description.getOrderedListOfOrderedSetIDs();
			String firstID = listOfIDs.get(0);
			List<String> firstIDProperties = description.getOrderedSetIDToOrderedSet().get(firstID).getListOfPropertiesWithPath();
			List<String> firstIDMaxChains = description.getOrderedSetIDToListOfMaximalChainsMapping().get(firstID);
			System.out.println("FIRST RESULT : ");
			System.out.println("Syntactic tree : ");
			for (String property : firstIDProperties) {
				System.out.println(property);
			}
			System.out.println("");
			for (String maxChain : firstIDMaxChains) {
				System.out.println(maxChain);
			}
			System.out.println("");
			System.out.print("SCORE : ");
			System.out.println(description.getOrderedSetIDToScoreMapping().get(firstID));
			ConceptLattice latticeFirst = orderedSetIDToConceptLattice.get(firstID);
			BinaryContext binaryContextFirst = description.getOrderedSetIDToBinaryContextMapping().get(firstID);
			/*LMLogger.getLMLogger();
			LMImages.getLMImages();
			LMIcons.getLMIcons();
			LatticeStructure latticeStructureFirst = new LatticeStructure(latticeFirst, binaryContextFirst, LatticeStructure.BEST);
			GraphicalLattice graphicalLatticeFirst = new GraphicalLattice(latticeFirst, latticeStructureFirst);
			LatticeViewer latticeViewerFirst = new LatticeViewer(graphicalLatticeFirst);
			latticeViewerFirst.setVisible(true);*/
			
			String otherID = listOfIDs.get(listOfIDs.size()-1);
			List<String> otherIDMaxChains = description.getOrderedSetIDToListOfMaximalChainsMapping().get(otherID);
			List<String> otherIDProperties = description.getOrderedSetIDToOrderedSet().get(otherID).getListOfPropertiesWithPath();
			System.out.println("OTHER RESULT : ");
			System.out.println("Syntactic tree : ");
			for (String property : otherIDProperties) {
				System.out.println(property);
			}
			System.out.println("");
			for (String maxChain : otherIDMaxChains) {
				System.out.println(maxChain);
			}
			System.out.println("");
			System.out.print("SCORE : ");
			System.out.println(description.getOrderedSetIDToScoreMapping().get(otherID));
			ConceptLattice latticeOther = orderedSetIDToConceptLattice.get(otherID);
			BinaryContext binaryContextOther = description.getOrderedSetIDToBinaryContextMapping().get(otherID);
			/*LMLogger.getLMLogger();
			LMImages.getLMImages();
			LMIcons.getLMIcons(); */
			/* LatticeStructure latticeStructureOther = new LatticeStructure(latticeOther, binaryContextOther, LatticeStructure.BEST);
			GraphicalLattice graphicalLatticeOther = new GraphicalLattice(latticeOther, latticeStructureOther);
			LatticeViewer latticeViewerOther = new LatticeViewer(graphicalLatticeOther);
			latticeViewerOther.setVisible(true);
			System.out.println("STOP"); */
			
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			unexpected.printStackTrace();
			fail();
		}
	}	

}
