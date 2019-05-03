package stringDescription.impl;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.junit.Test;

import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import fca.exception.LMLogger;
import fca.gui.lattice.LatticePanel;
import fca.gui.lattice.LatticeStructureFrame;
import fca.gui.lattice.LatticeViewer;
import fca.gui.lattice.element.GraphicalLattice;
import fca.gui.lattice.element.LatticeStructure;
import fca.gui.util.constant.LMIcons;
import fca.gui.util.constant.LMImages;
import model.synTreeModel.ISignal;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.SignalBuilderImpl;

public class AbstractStringDescriptorMultipleLatticesTest {

	@Test
	public void whenSignalEnteredThenSetOfCodedAttributesIDsReturned() {
		try {
			ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
			ISignal signal = signalBuilder.getSignal();
			AbstractStringDescriptorMultipleLattices stringDescriptor = new StringDescriptorMultipleLatticesImpl(signal);
			Set<String> codedAttributesID = stringDescriptor.getSetOfCodedAttributesIDs();
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			unexpected.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void whenSignalEnteredThenCodedAttributeToSetOfCodingAttributesMapReturned() {
		try {
			ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
			ISignal signal = signalBuilder.getSignal();
			AbstractStringDescriptorMultipleLattices stringDescriptor = new StringDescriptorMultipleLatticesImpl(signal);
			Map<String, Set<String>> codingAttributesID = stringDescriptor.getcodedAttributeToSetOfCodingAttributes();
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			unexpected.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void whenSignalEnteredThenIDToVerbalDescriptionReturned() {
		try {
			ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
			ISignal signal = signalBuilder.getSignal();
			AbstractStringDescriptorMultipleLattices stringDescriptor = new StringDescriptorMultipleLatticesImpl(signal);
			Map<String, String> iDToVerbalDescription = stringDescriptor.getCodedAttributeToVerbalDescription();
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			unexpected.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void whenSignalEnteredThenIDToLatticeReturned() {
		try {
			ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
			ISignal signal = signalBuilder.getSignal();
			AbstractStringDescriptorMultipleLattices stringDescriptor = new StringDescriptorMultipleLatticesImpl(signal);
			Map<String, ConceptLattice> iDToLattice = stringDescriptor.getCodedAttributeToLattice();
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			unexpected.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void whenSignalEnteredThenVerbalDescriptionToScoreReturned() {
		try {
			ISignalBuilder signalBuilder = new SignalBuilderImpl("ab", "fromLeftToRight");
			ISignal signal = signalBuilder.getSignal();
			AbstractStringDescriptorMultipleLattices stringDescriptor = new StringDescriptorMultipleLatticesImpl(signal);
			Map<String, Double> verbalDescriptionToScore = stringDescriptor.getVerbalDescriptionToScore();
			Set<String> verbalDescriptions = verbalDescriptionToScore.keySet();
			/*for (String verbalDescription : verbalDescriptions) {
				System.out.println(verbalDescription);
				System.out.println("SCORE : " + Double.toString(verbalDescriptionToScore.get(verbalDescription)));
				System.out.println();
			}*/
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			unexpected.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void whenSignalEnteredThenHasseDiagramCanBeBuilt() {
		try {
			ISignalBuilder signalBuilder = new SignalBuilderImpl("ab", "fromLeftToRight");
			ISignal signal = signalBuilder.getSignal();
			AbstractStringDescriptorMultipleLattices stringDescriptor = new StringDescriptorMultipleLatticesImpl(signal);
			Set<String> setOfCodedAttributesID = stringDescriptor.getSetOfCodedAttributesIDs();
			String bestCodedAttributeID = "";
			String secondBestCodedAttributeID = ""; 
			List<Double> setOfScores = new ArrayList<Double>(stringDescriptor.getCodedAttributeIDToScore().values());
			Collections.sort(setOfScores);
			for (String codedAttributeID : setOfCodedAttributesID) {
				if (stringDescriptor.getCodedAttributeIDToScore().get(codedAttributeID) == setOfScores.get(0))
					bestCodedAttributeID = codedAttributeID;
				else if (stringDescriptor.getCodedAttributeIDToScore().get(codedAttributeID) == setOfScores.get(1))
					secondBestCodedAttributeID = codedAttributeID;
			}
			BinaryContext binaryContext1 = stringDescriptor.getCodedAttributeIDToBinaryContext().get(bestCodedAttributeID);
			//BinaryContext binaryContext2 = stringDescriptor.getCodedAttributeIDToBinaryContext().get(secondBestCodedAttributeID);
			ConceptLattice lattice1 = stringDescriptor.getCodedAttributeIDToConceptLattice().get(bestCodedAttributeID);
			//ConceptLattice lattice2 = stringDescriptor.getCodedAttributeIDToConceptLattice().get(secondBestCodedAttributeID);
			LMLogger.getLMLogger();
			LMImages.getLMImages();
			LMIcons.getLMIcons();
			LatticeStructure latticeStructure1 = new LatticeStructure(lattice1, binaryContext1, LatticeStructure.BEST);
			//LatticeStructure latticeStructure2 = new LatticeStructure(lattice2, binaryContext2, LatticeStructure.BEST);
			GraphicalLattice graphicalLattice1 = new GraphicalLattice(lattice1, latticeStructure1);
			//GraphicalLattice graphicalLattice2 = new GraphicalLattice(lattice2, latticeStructure2);
			LatticeViewer latticeViewer1 = new LatticeViewer(graphicalLattice1);
			//LatticeViewer latticeViewer2 = new LatticeViewer(graphicalLattice2);
			//latticeViewer1.setVisible(true);	
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			unexpected.printStackTrace();
			fail();
		}
	}

}
