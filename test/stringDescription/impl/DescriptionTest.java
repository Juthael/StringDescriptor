package stringDescription.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import fca.exception.LMLogger;
import fca.gui.lattice.LatticeViewer;
import fca.gui.lattice.element.GraphicalLattice;
import fca.gui.lattice.element.LatticeStructure;
import fca.gui.util.constant.LMIcons;
import fca.gui.util.constant.LMImages;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISignal;
import stringDescription.IScoreCalculator;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.SignalBuilderImpl;

public class DescriptionTest {

	@Test
	public void whenSignalEnteredThenSetOfOrderedSetsIDsCanBeProvided() {
		try {
			ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
			ISignal signal = signalBuilder.getSignal();
			IScoreCalculator scoreCalculator = new KnowledgeEfficiencyCalculator();
			Description description = new Description(signal, scoreCalculator);
			Set<String> orderedSetsIDS = description.getOrderedSetIDToBinaryContextMapping().keySet();
			assertTrue(!orderedSetsIDS.isEmpty());
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			unexpected.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void whenSignalEnteredThenMappingOfOrderedSetIDToOrderedSetCanBeProvided() {
		try {
			ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
			ISignal signal = signalBuilder.getSignal();
			IScoreCalculator scoreCalculator = new KnowledgeEfficiencyCalculator();
			Description description = new Description(signal, scoreCalculator);
			Set<String> orderedSetsIDS = description.getOrderedSetIDToBinaryContextMapping().keySet();
			Map<String, IOrderedSet> orderedSetIDToOrderedSet = description.getOrderedSetIDToOrderedSet();
			assertTrue(!orderedSetIDToOrderedSet.isEmpty());
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			unexpected.printStackTrace();
			fail();
		}
	}	
	
	@Test
	public void whenSignalEnteredThenMappingOfOrderedSetIDToVerbalDescriptionCanBeProvided() {
		try {
			ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
			ISignal signal = signalBuilder.getSignal();
			IScoreCalculator scoreCalculator = new KnowledgeEfficiencyCalculator();
			Description description = new Description(signal, scoreCalculator);
			Set<String> orderedSetsIDS = description.getOrderedSetIDToBinaryContextMapping().keySet();
			Map<String, String> orderedSetIDToVerbalDescription = description.getOrderedSetIDToVerbalDescriptionMapping();
			assertTrue(!orderedSetIDToVerbalDescription.isEmpty());
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			unexpected.printStackTrace();
			fail();
		}
	}	
	
	@Test
	public void whenSignalEnteredThenMappingOfOrderedSetIDToConceptLatticeCanBeProvided() {
		try {
			ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
			ISignal signal = signalBuilder.getSignal();
			IScoreCalculator scoreCalculator = new KnowledgeEfficiencyCalculator();
			Description description = new Description(signal, scoreCalculator);
			Set<String> orderedSetsIDS = description.getOrderedSetIDToBinaryContextMapping().keySet();
			Map<String, ConceptLattice> orderedSetIDToConceptLattice = description.getOrderedSetIDToConceptLatticeMapping();
			List<String> listOfIDs = new ArrayList<String>(orderedSetIDToConceptLattice.keySet());
			/*
			ConceptLattice testLattice = orderedSetIDToConceptLattice.get(listOfIDs.get(0));
			BinaryContext testBinaryContext = description.getOrderedSetIDToBinaryContextMapping().get(listOfIDs.get(0));
			LMLogger.getLMLogger();
			LMImages.getLMImages();
			LMIcons.getLMIcons();
			LatticeStructure testLatticeStructure = new LatticeStructure(testLattice, testBinaryContext, LatticeStructure.BEST);
			GraphicalLattice testGraphicalLattice = new GraphicalLattice(testLattice, testLatticeStructure);
			LatticeViewer testLatticeViewer = new LatticeViewer(testGraphicalLattice);
			testLatticeViewer.setVisible(true);
			*/
			assertTrue(!orderedSetIDToConceptLattice.isEmpty());
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			unexpected.printStackTrace();
			fail();
		}
	}	

}
