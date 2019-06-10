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
import launcher.utils.DescriptionKeyboardInputManager;
import model.copycatModel.signal.ICopycatSignal;
import model.copycatModel.synTreeGrammar.CharString;
import model.orderedSetModel.impl.OmegaOS;
import model.synTreeModel.ISignal;
import stringDescription.IDescription;
import stringDescription.IScoreCalculator;
import stringDescription.impl.Description;
import stringDescription.impl.KnowledgeEfficiencyCalculator;
import stringDescription.impl.RelationalDensityCalculator;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.ListOfDescriptorsBuilder;
import syntacticTreesGeneration.impl.SignalBuilder;
import verbalization.dataEncoding.encoders.IVerbalizer;
import verbalization.dataEncoding.encoders.impl.Verbalizer;

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
			ISignalBuilder signalBuilder = new SignalBuilder("ooopoq", "fromLeftToRight");
			ICopycatSignal signal = (ICopycatSignal) signalBuilder.getSignal();
			IScoreCalculator scoreCalculator = new InformationQuantityCalculator();
			Description description = new Description(signal, scoreCalculator);
			Map<String, ConceptLattice> orderedSetIDToConceptLattice = description.getOrderedSetIDToConceptLatticeMapping();
			List<String> listOfIDs = description.getOrderedListOfOrderedSetIDs();			
			for (String iD : listOfIDs) {
				
				System.out.println("press A key");
				String entry = DescriptionKeyboardInputManager.readString();
				
				System.out.println("Description : ");
				System.out.println(description.getOrderedSetIDToVerbalDescriptionMapping().get(iD));
				System.out.println("");
				
				List<String> firstIDProperties = description.getOrderedSetIDToOrderedSet().get(iD).getListOfPropertiesWithPath();
				List<String> firstIDMaxChains = description.getOrderedSetIDToListOfMaximalChainsMapping().get(iD);
				 
				System.out.println("Syntactic tree : ");
				for (String property : firstIDProperties) {
					System.out.println(property);
				}
				System.out.println("");
				System.out.println("Ordered set : ");
				for (String maxChain : firstIDMaxChains) {
					System.out.println(maxChain);
				}
				System.out.println("");
				System.out.print("SCORE : ");
				System.out.println(description.getOrderedSetIDToScoreMapping().get(iD));
				
				System.out.println("If you want to see the lattice, press 'y'");
				entry = DescriptionKeyboardInputManager.readString();
				if (entry.equals("y")) {
					ConceptLattice latticeFirst = orderedSetIDToConceptLattice.get(iD);
					BinaryContext binaryContextFirst = description.getOrderedSetIDToBinaryContextMapping().get(iD);
					
					LMLogger.getLMLogger();
					LMImages.getLMImages();
					LMIcons.getLMIcons();
					LatticeStructure latticeStructureFirst = new LatticeStructure(latticeFirst, binaryContextFirst, LatticeStructure.BEST);
					GraphicalLattice graphicalLatticeFirst = new GraphicalLattice(latticeFirst, latticeStructureFirst);
					LatticeViewer latticeViewerFirst = new LatticeViewer(graphicalLatticeFirst);
					latticeViewerFirst.setVisible(true); 
				}
				
			}		
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			unexpected.printStackTrace();
			fail();
		}
	}	

	
	@Test
	public void whenSignalEnteredThenMappingOfOrderedSetIDToConceptLatticeCanBeProvided2() {
		try {
			ISignalBuilder signalBuilder = new SignalBuilder("jkkl", "fromLeftToRight");
			ICopycatSignal signal = (ICopycatSignal) signalBuilder.getSignal();
			IScoreCalculator scoreCalculator = new InformationQuantityCalculator();
			Description description = new Description(signal, scoreCalculator);
			Map<String, ConceptLattice> orderedSetIDToConceptLattice = description.getOrderedSetIDToConceptLatticeMapping();
			List<String> listOfIDs = description.getOrderedListOfOrderedSetIDs();	
			int descIndex = 1;
			for (String iD : listOfIDs) {
				
				System.out.println("Description n. " + descIndex + ": ");
				descIndex++;
				System.out.println(description.getOrderedSetIDToVerbalDescriptionMapping().get(iD));
				System.out.println("");
				
				/*
				
				List<String> firstIDProperties = description.getOrderedSetIDToOrderedSet().get(iD).getListOfPropertiesWithPath();
				List<String> firstIDMaxChains = description.getOrderedSetIDToListOfMaximalChainsMapping().get(iD);
				
				System.out.println("Syntactic tree : ");
				for (String property : firstIDProperties) {
					System.out.println(property);
				}
				System.out.println("");
				System.out.println("Ordered set : ");
				for (String maxChain : firstIDMaxChains) {
					System.out.println(maxChain);
				}
				
				*/
				
				
				System.out.print("SCORE : ");
				System.out.println(description.getOrderedSetIDToScoreMapping().get(iD));
				System.out.println("");
				System.out.println("");
				
				
			}		
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			unexpected.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void temp() throws SynTreeGenerationException, CloneNotSupportedException, VerbalizationException, OrderedSetsGenerationException {
		ISignalBuilder signalBuilder = new SignalBuilder("xyyzzz", "fromLeftToRight");
		ICopycatSignal signal = (ICopycatSignal) signalBuilder.getSignal();
		ListOfDescriptorsBuilder builder = new ListOfDescriptorsBuilder(signal);
		List<CharString> listOfCharStrings = builder.getListOfComprehensiveDescriptors();
		for (CharString descriptor : listOfCharStrings) {
			IVerbalizer verbalizer = new Verbalizer(descriptor);
			String description = verbalizer.getTranslationInNaturalLanguage();
			System.out.println(description);
			// System.out.
		}
	}
}
