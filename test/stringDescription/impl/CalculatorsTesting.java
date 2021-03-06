package stringDescription.impl;

import static org.junit.Assert.fail;

import java.awt.Frame;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import description.IScoreCalculator;
import description.impl.DescriptionValuator;
import description.impl.InformationQuantityCalculator;
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
import launcher.utils.KeyboardInputManager;
import model.copycatModel.signal.ICopycatSignal;
import model.copycatModel.synTreeGrammar.CharString;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.ListOfDescriptorsBuilder;
import syntacticTreesGeneration.impl.SignalBuilder;
import verbalization.dataEncoding.encoders.IVerbalizer;
import verbalization.dataEncoding.encoders.impl.Verbalizer;

public class CalculatorsTesting {

	@Test
	public void whenSignalEnteredThenMappingOfOrderedSetIDToConceptLatticeCanBeProvided() {
		try {
			ISignalBuilder signalBuilder = new SignalBuilder("ooopoq", "fromLeftToRight");
			ICopycatSignal signal = (ICopycatSignal) signalBuilder.getSignal();
			IScoreCalculator scoreCalculator = new InformationQuantityCalculator();
			DescriptionValuator valuator = new DescriptionValuator(signal, scoreCalculator);
			Map<String, ConceptLattice> orderedSetIDToConceptLattice = valuator.getOrderedSetIDToConceptLatticeMapping();
			List<String> listOfIDs = valuator.getOrderedListOfOrderedSetIDs();			
			for (String iD : listOfIDs) {
				
				System.out.println("press A key");
				String entry = KeyboardInputManager.readString();
				
				System.out.println("Description : ");
				System.out.println(valuator.getOrderedSetIDToVerbalDescriptionMapping().get(iD));
				System.out.println("");
				
				List<String> firstIDProperties = valuator.getOrderedSetIDToOrderedSet().get(iD).getListOfPropertiesWithPath();
				List<String> firstIDMaxChains = valuator.getOrderedSetIDToListOfMaximalChainsMapping().get(iD);
				 
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
				System.out.println(valuator.getOrderedSetIDToScoreMapping().get(iD));
				
				System.out.println("If you want to see the lattice, press 'y'");
				entry = KeyboardInputManager.readString();
				if (entry.equals("y")) {
					ConceptLattice latticeFirst = orderedSetIDToConceptLattice.get(iD);
					BinaryContext binaryContextFirst = valuator.getOrderedSetIDToBinaryContextMapping().get(iD);
					
					LMLogger.getLMLogger();
					LMImages.getLMImages();
					LMIcons.getLMIcons();
					LatticeStructure latticeStructureFirst = new LatticeStructure(latticeFirst, binaryContextFirst, LatticeStructure.BEST);
					GraphicalLattice graphicalLatticeFirst = new GraphicalLattice(latticeFirst, latticeStructureFirst);
					LatticeViewer latticeViewerFirst = new LatticeViewer(graphicalLatticeFirst);
					latticeViewerFirst.setExtendedState(Frame.MAXIMIZED_BOTH);
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
			ISignalBuilder signalBuilder = new SignalBuilder("mmnnoo", "fromLeftToRight");
			ICopycatSignal signal = (ICopycatSignal) signalBuilder.getSignal();
			IScoreCalculator scoreCalculator = new InformationQuantityCalculator();
			DescriptionValuator valuator = new DescriptionValuator(signal, scoreCalculator);
			Map<String, ConceptLattice> orderedSetIDToConceptLattice = valuator.getOrderedSetIDToConceptLatticeMapping();
			List<String> listOfIDs = valuator.getOrderedListOfOrderedSetIDs();	
			int descIndex = 1;
			for (String iD : listOfIDs) {
				
				System.out.println("Description n. " + descIndex + ": ");
				descIndex++;
				System.out.println(valuator.getOrderedSetIDToVerbalDescriptionMapping().get(iD));
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
				System.out.println(valuator.getOrderedSetIDToScoreMapping().get(iD));
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
	
}
