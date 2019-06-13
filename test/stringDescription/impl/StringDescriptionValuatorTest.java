package stringDescription.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import description.IScoreCalculator;
import description.impl.DescriptionValuator;
import description.impl.InformationQuantityCalculator;
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
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.SignalBuilder;

public class StringDescriptionValuatorTest {

	@Test
	public void whenSignalEnteredThenSetOfOrderedSetsIDsCanBeProvided() {
		try {
			ISignalBuilder signalBuilder = new SignalBuilder("abc", "fromLeftToRight");
			ICopycatSignal signal = (ICopycatSignal) signalBuilder.getSignal();
			IScoreCalculator scoreCalculator = new InformationQuantityCalculator();
			DescriptionValuator valuator = new DescriptionValuator(signal, scoreCalculator);
			Set<String> orderedSetsIDS = valuator.getOrderedSetIDToBinaryContextMapping().keySet();
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
			ISignalBuilder signalBuilder = new SignalBuilder("abc", "fromLeftToRight");
			ICopycatSignal signal = (ICopycatSignal) signalBuilder.getSignal();
			IScoreCalculator scoreCalculator = new InformationQuantityCalculator();
			DescriptionValuator valuator = new DescriptionValuator(signal, scoreCalculator);
			Set<String> orderedSetsIDS = valuator.getOrderedSetIDToBinaryContextMapping().keySet();
			Map<String, IOrderedSet> orderedSetIDToOrderedSet = valuator.getOrderedSetIDToOrderedSet();
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
			ISignalBuilder signalBuilder = new SignalBuilder("abc", "fromLeftToRight");
			ICopycatSignal signal = (ICopycatSignal) signalBuilder.getSignal();
			IScoreCalculator scoreCalculator = new InformationQuantityCalculator();
			DescriptionValuator valuator = new DescriptionValuator(signal, scoreCalculator);
			Set<String> orderedSetsIDS = valuator.getOrderedSetIDToBinaryContextMapping().keySet();
			Map<String, String> orderedSetIDToVerbalDescription = valuator.getOrderedSetIDToVerbalDescriptionMapping();
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
			ISignalBuilder signalBuilder = new SignalBuilder("abc", "fromLeftToRight");
			ICopycatSignal signal = (ICopycatSignal) signalBuilder.getSignal();
			IScoreCalculator scoreCalculator = new InformationQuantityCalculator();
			DescriptionValuator valuator = new DescriptionValuator(signal, scoreCalculator);
			Set<String> orderedSetsIDS = valuator.getOrderedSetIDToBinaryContextMapping().keySet();
			Map<String, ConceptLattice> orderedSetIDToConceptLattice = valuator.getOrderedSetIDToConceptLatticeMapping();
			List<String> listOfIDs = new ArrayList<String>(orderedSetIDToConceptLattice.keySet());
			String firstID = listOfIDs.get(0);
			String otherID = listOfIDs.get(listOfIDs.size()-1);
			ConceptLattice latticeFirst = orderedSetIDToConceptLattice.get(listOfIDs.get(0));
			BinaryContext binaryContextFirst = valuator.getOrderedSetIDToBinaryContextMapping().get(listOfIDs.get(0));
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
