package stringDescription.impl;

import static org.junit.Assert.assertFalse;
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
import model.copycatModel.signal.ICopycatSignal;
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
			ICopycatSignal signal = (ICopycatSignal) signalBuilder.getSignal();
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
	public void whenSignalEnteredThenCodingElementsIDsCanBeProvided() {
		try {
			ISignalBuilder signalBuilder1 = new SignalBuilderImpl("abc", "fromLeftToRight");
			ICopycatSignal signal1 = (ICopycatSignal) signalBuilder1.getSignal();
			IScoreCalculator scoreCalculator = new KnowledgeEfficiencyCalculator();
			Description description1 = new Description(signal1, scoreCalculator);
			List<String> listOfOrderedSets1 = description1.getOrderedListOfOrderedSetIDs();
			boolean anEmptyListOfCodingDescriptorsWasFound = false;
			for (int i=0 ; i<listOfOrderedSets1.size() ; i++) {
				IOrderedSet orderedSet1 = description1.getOrderedSetIDToOrderedSet().get(listOfOrderedSets1.get(i));
				Set<IOrderedSet> lowerSet1 = orderedSet1.getLowerSet();
				List<String> listOfCodingDescriptorIDs1 = new ArrayList<String>();
				for (IOrderedSet set : lowerSet1) {
					if (set.getIsCodingElement() == true)
						listOfCodingDescriptorIDs1.add(set.getElementID());
				}
				if (listOfCodingDescriptorIDs1.isEmpty())
					anEmptyListOfCodingDescriptorsWasFound = true;
				List<String> maxChains1 = orderedSet1.getListOfLowerSetMaximalChains();
				/* for (String maxChain : maxChains1) {
					System.out.println(maxChain);
				}
				System.out.println("");
				for (String codingID : listOfCodingDescriptorIDs1)
					System.out.println(codingID);
				System.out.println(""); */
			}
			assertFalse(anEmptyListOfCodingDescriptorsWasFound);
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
			ICopycatSignal signal = (ICopycatSignal) signalBuilder.getSignal();
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
			ICopycatSignal signal = (ICopycatSignal) signalBuilder.getSignal();
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
			ICopycatSignal signal = (ICopycatSignal) signalBuilder.getSignal();
			IScoreCalculator scoreCalculator = new KnowledgeEfficiencyCalculator();
			Description description = new Description(signal, scoreCalculator);
			Set<String> orderedSetsIDS = description.getOrderedSetIDToBinaryContextMapping().keySet();
			Map<String, ConceptLattice> orderedSetIDToConceptLattice = description.getOrderedSetIDToConceptLatticeMapping();
			List<String> listOfIDs = new ArrayList<String>(orderedSetIDToConceptLattice.keySet());
			String firstID = listOfIDs.get(0);
			String otherID = listOfIDs.get(listOfIDs.size()-1);
			ConceptLattice latticeFirst = orderedSetIDToConceptLattice.get(listOfIDs.get(0));
			BinaryContext binaryContextFirst = description.getOrderedSetIDToBinaryContextMapping().get(listOfIDs.get(0));
			LMLogger.getLMLogger();
			LMImages.getLMImages();
			LMIcons.getLMIcons();
			LatticeStructure latticeStructureFirst = new LatticeStructure(latticeFirst, binaryContextFirst, LatticeStructure.BEST);
			GraphicalLattice graphicalLatticeFirst = new GraphicalLattice(latticeFirst, latticeStructureFirst);
			LatticeViewer latticeViewerFirst = new LatticeViewer(graphicalLatticeFirst);
			latticeViewerFirst.setVisible(true);
			assertTrue(!orderedSetIDToConceptLattice.isEmpty());
			
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			unexpected.printStackTrace();
			fail();
		}
	}	

}
