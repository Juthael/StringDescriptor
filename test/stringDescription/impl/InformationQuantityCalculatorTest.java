package stringDescription.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Set;

import org.junit.Test;

import description.IScoreCalculator;
import description.impl.InformationQuantityCalculator;
import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;
import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import fca.core.lattice.FormalConcept;
import fca.core.util.BasicSet;
import fca.exception.AlreadyExistsException;
import fca.exception.InvalidTypeException;
import fca.exception.LMLogger;
import fca.gui.lattice.LatticeViewer;
import fca.gui.lattice.element.GraphicalLattice;
import fca.gui.lattice.element.LatticeStructure;
import fca.gui.util.constant.LMIcons;
import fca.gui.util.constant.LMImages;
import model.copycatModel.signal.ICopycatSignal;
import model.copycatModel.synTreeGrammar.CharString;
import model.orderedSetModel.IOrderedSet;
import orderedSetGeneration.IBinaryContextBuilder;
import orderedSetGeneration.IOrderedSetBuilder;
import orderedSetGeneration.impl.BinaryContextBuilder;
import orderedSetGeneration.impl.OrderedSetBuilder;
import syntacticTreesGeneration.IListOfDescriptorsBuilder;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.ListOfDescriptorsBuilder;
import syntacticTreesGeneration.impl.SignalBuilder;

public class InformationQuantityCalculatorTest {

	@Test
	public void whenSetOfCoatomsAndLatticeEnteredThenInfGeneratorSetsOfCoatomsReturned() throws SynTreeGenerationException, CloneNotSupportedException, VerbalizationException, OrderedSetsGenerationException, AlreadyExistsException, InvalidTypeException {
		boolean setsOfCoatomsReturnedAreInfGenerator = true;
		InformationQuantityCalculator calculator = new InformationQuantityCalculator();
		ISignalBuilder signalBuilder = new SignalBuilder("abcd", "fromLeftToRight");
		ICopycatSignal signal = (ICopycatSignal) signalBuilder.getSignal();
		IListOfDescriptorsBuilder descriptorsBuilder = new ListOfDescriptorsBuilder(signal);
		List<CharString> listOfDescriptors = descriptorsBuilder.getListOfComprehensiveDescriptors();
		int descIndex = 0;
		while (setsOfCoatomsReturnedAreInfGenerator == true && descIndex < listOfDescriptors.size()) {
			CharString descriptor = listOfDescriptors.get(descIndex);
			IOrderedSetBuilder oSBuilder = new OrderedSetBuilder(descriptor);
			IBinaryContextBuilder contextBuilder = new BinaryContextBuilder(oSBuilder.getOrderedSet());
			BinaryContext context = contextBuilder.getContext();
			ConceptLattice lattice = new ConceptLattice(context);
			/*
			//HERE
			LMLogger.getLMLogger();
			LMImages.getLMImages();
			LMIcons.getLMIcons();
			LatticeStructure latticeStructureFirst = new LatticeStructure(lattice, context, LatticeStructure.BEST);
			GraphicalLattice graphicalLatticeFirst = new GraphicalLattice(lattice, latticeStructureFirst);
			LatticeViewer latticeViewerFirst = new LatticeViewer(graphicalLatticeFirst);
			latticeViewerFirst.setVisible(true); 
			//
			*/
			Set<FormalConcept> setOfCoatoms = calculator.setSetOfCoatoms(lattice);
			Set<Set<FormalConcept>> setOfInfGeneratorSets = calculator.setSetOfInfGeneratorSets(setOfCoatoms, lattice);
			List<Set<FormalConcept>> listOfInfGenerators = new ArrayList<Set<FormalConcept>>(setOfInfGeneratorSets);
			boolean everySetElemIsInfGenerator = true;
			int infGenSetIndex = 0;
			while (everySetElemIsInfGenerator == true && infGenSetIndex < listOfInfGenerators.size()) {
				Set<FormalConcept> infGeneratorSet = listOfInfGenerators.get(infGenSetIndex);
				Set<String> intent = new HashSet<String>();
				for (FormalConcept coatom : infGeneratorSet) {
					intent.addAll(coatom.getIntent());
				}
				List<Set<String>> lowerBoundConceptIntents = new ArrayList<Set<String>>();
				for (FormalConcept concept : lattice.getConcepts()) {
					if (concept.getIntent().containsAll(intent))
						lowerBoundConceptIntents.add(concept.getIntent());
				}
				int smallestIntentIndex = getSmallestIntentIndex(lowerBoundConceptIntents);
				boolean thereIsAnInfimum = checkIfThisIsAnInfimumIntent(lowerBoundConceptIntents, smallestIntentIndex);
				if (thereIsAnInfimum == false)
					everySetElemIsInfGenerator = false;
				infGenSetIndex++;
			}
			descIndex++;
		}
		
	}
	
	private int getSmallestIntentIndex(List<Set<String>> lowerBoundConceptIntents) {
		int index = 0;
		int minSize = lowerBoundConceptIntents.get(0).size();
		for (int i=1 ; i<lowerBoundConceptIntents.size() ; i++) {
			if (lowerBoundConceptIntents.get(i).size() < minSize) {
				minSize = lowerBoundConceptIntents.get(i).size();
				index = i;
			}
		}
		return index;
	}
	
	private boolean checkIfThisIsAnInfimumIntent(List<Set<String>> lowerBoundConceptIntents, int smallestIntentIndex) {
		boolean thisIsAnInfimumIntent = true;
		Set<String> smallestIntent = lowerBoundConceptIntents.get(smallestIntentIndex);
		int intentIndex = 0;
		while (thisIsAnInfimumIntent==true && intentIndex < lowerBoundConceptIntents.size()) {
			if (intentIndex != smallestIntentIndex) {
				if (!lowerBoundConceptIntents.get(intentIndex).containsAll(smallestIntent))
					thisIsAnInfimumIntent = false;
			}
			intentIndex++;
		}
		return thisIsAnInfimumIntent;
	}

}
