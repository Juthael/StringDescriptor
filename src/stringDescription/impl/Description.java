package stringDescription.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;
import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import model.copycatModel.synTreeGrammar.CharString;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.AbstractOmegaElement;
import model.synTreeModel.ISignal;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.ISynTreeStartElement;
import orderedSetGeneration.IBinaryContextBuilder;
import orderedSetGeneration.IOrderedSetBuilder;
import orderedSetGeneration.impl.BinaryContextBuilderImpl;
import orderedSetGeneration.impl.OrderedSetBuilderImpl;
import stringDescription.IDescription;
import stringDescription.IScoreCalculator;
import syntacticTreesGeneration.IListOfDescriptorsBuilder;
import syntacticTreesGeneration.impl.ListOfDescriptorsBuilderImpl;

public class Description implements IDescription {

	private ISignal signal;
	private IScoreCalculator scoreCalculator;
	private List<ISynTreeStartElement> listOfSyntacticTrees;
	private Map<String, IOrderedSet> orderedSetIDToOrderedSet = new HashMap<String, IOrderedSet>();
	private Map<String, BinaryContext> orderedSetIDToBinaryContext = new HashMap<String, BinaryContext>();
	private Map<String, ConceptLattice> orderedSetIDToConceptLattice = new HashMap<String, ConceptLattice>();
	private Map<String, Double> orderedSetIDToScore = new HashMap<String, Double>();
	private List<String> orderedListOfOrderedSetIDs;
	
	public Description(ISignal signal, IScoreCalculator scoreCalculator) 
			throws SynTreeGenerationException, CloneNotSupportedException, OrderedSetsGenerationException, VerbalizationException {
		this.signal = signal;
		this.scoreCalculator = scoreCalculator;
		IListOfDescriptorsBuilder listOfDescriptorsBuilder = new ListOfDescriptorsBuilderImpl(this.signal);
		this.listOfSyntacticTrees = new ArrayList<ISynTreeStartElement>(listOfDescriptorsBuilder.getListOfStringDescriptors()); 
		for (ISynTreeStartElement startElement : listOfSyntacticTrees) {
			IOrderedSetBuilder orderedSetBuilder = new OrderedSetBuilderImpl(startElement);
			IOrderedSet orderedSet = orderedSetBuilder.getOrderedSet();
			orderedSetIDToOrderedSet.put(orderedSet.getElementID(), orderedSet);
			IBinaryContextBuilder binaryContextBuilder = new BinaryContextBuilderImpl(orderedSet);
			BinaryContext binaryContext = binaryContextBuilder.getContext();
			orderedSetIDToBinaryContext.put(orderedSet.getElementID(), binaryContext);
			ConceptLattice lattice = new ConceptLattice(binaryContext);
			orderedSetIDToConceptLattice.put(orderedSet.getElementID(), lattice);
			double score = this.scoreCalculator.calculateScore(orderedSet, lattice);
			orderedSetIDToScore.put(orderedSet.getElementID(), score);
		}
		orderedListOfOrderedSetIDs = setOrderedListOfOrderedSetIDs();
	}

	@Override
	public List<ISynTreeElement> getListOfStringSyntacticTrees() {
		List<ISynTreeElement> listOfStringDescriptors = new ArrayList<ISynTreeElement>();
		for (ISynTreeElement synTree : listOfSyntacticTrees)
			listOfStringDescriptors.add((CharString) synTree);
		return listOfStringDescriptors;
	}
	
	@Override
	public List<String> getOrderedListOfOrderedSetIDs() {
		return orderedListOfOrderedSetIDs;
	}	
	
	@Override
	public Map<String, IOrderedSet> getOrderedSetIDToOrderedSet() {
		return orderedSetIDToOrderedSet;
	}	

	@Override
	public Map<String, List<String>> getOrderedSetIDToListOfPropertiesMapping() {
		Map<String, List<String>> orderedSetIDToListOfProperties = new HashMap<String, List<String>>();
		for (String orderedSetID : orderedSetIDToOrderedSet.keySet()) {
			orderedSetIDToListOfProperties.put(orderedSetID, orderedSetIDToOrderedSet.get(orderedSetID).getListOfPropertiesWithPath());
		}
		return orderedSetIDToListOfProperties;
	}
	
	@Override
	public Map<String, List<String>> getOrderedSetIDToListOfMaximalChainsMapping() {
		Map<String, List<String>> orderedSetIDToListOfMaxChains = new HashMap<String, List<String>>();
		for (String orderedSetID : orderedSetIDToOrderedSet.keySet()) {
			orderedSetIDToListOfMaxChains.put(orderedSetID, orderedSetIDToOrderedSet.get(orderedSetID).getListOfLowerSetMaximalChains());
		}
		return orderedSetIDToListOfMaxChains;
	}	

	@Override
	public Map<String, String> getOrderedSetIDToVerbalDescriptionMapping() {
		Map<String, String> orderedSetIDToVerbalDescription = new HashMap<String, String>();
		for (String orderedSetID : orderedSetIDToOrderedSet.keySet()) {
			AbstractOmegaElement orderedSet = (AbstractOmegaElement) orderedSetIDToOrderedSet.get(orderedSetID);
			orderedSetIDToVerbalDescription.put(orderedSetID, orderedSet.getVerbalDescription());
		}
		return orderedSetIDToVerbalDescription;
	}	
	
	@Override
	public Map<String, BinaryContext> getOrderedSetIDToBinaryContextMapping(){
		return orderedSetIDToBinaryContext;
	}
	
	@Override
	public Map<String, ConceptLattice> getOrderedSetIDToConceptLatticeMapping(){
		return orderedSetIDToConceptLattice;
	}

	@Override
	public Map<String, Double> getOrderedSetIDToScoreMapping() {
		return orderedSetIDToScore;
	}

	@Override
	public Map<List<String>, Double> getListOfPropertiesToScoreMapping() {
		Map<List<String>, Double> listOfPropertiesToScore = new HashMap<List<String>, Double>();
		for (String orderedSetID : orderedListOfOrderedSetIDs) {
			List<String> listOfProperties = orderedSetIDToOrderedSet.get(orderedSetID).getListOfPropertiesWithPath();
			Double score = orderedSetIDToScore.get(orderedSetID);
			listOfPropertiesToScore.put(listOfProperties, score);
		}
		return listOfPropertiesToScore;
	}

	@Override
	public Map<String, Double> getVerbalDescriptionToScoreMapping() {
		Map<String, Double> verbalDescriptionToScore = new HashMap<String, Double>();
		for (String orderedSetID : orderedListOfOrderedSetIDs) {
			AbstractOmegaElement orderedSet = (AbstractOmegaElement) orderedSetIDToOrderedSet.get(orderedSetID);
			String verbalDescription = orderedSet.getVerbalDescription();
			Double score = orderedSetIDToScore.get(orderedSetID);
			verbalDescriptionToScore.put(verbalDescription, score);
		}
		return verbalDescriptionToScore;
	}

	@Override
	public String getBestDescriptionOrderedSetID() {
		return orderedListOfOrderedSetIDs.get(0);
	}

	@Override
	public List<String> getBestDescriptionListOfProperties() {
		List<String> listOfProperties = orderedSetIDToOrderedSet.get(orderedListOfOrderedSetIDs.get(0)).getListOfPropertiesWithPath();
		return listOfProperties;
	}
	
	@Override
	public List<String> getBestDescriptionListOfMaximalChains() {
		return orderedSetIDToOrderedSet.get(orderedListOfOrderedSetIDs.get(0)).getListOfLowerSetMaximalChains();
	}	

	@Override
	public String getBestDescriptionVerbalDescription() {
		String verbalDescription;
		AbstractOmegaElement orderedSet = (AbstractOmegaElement) orderedSetIDToOrderedSet.get(orderedListOfOrderedSetIDs.get(0));
		verbalDescription = orderedSet.getVerbalDescription();
		return verbalDescription;
	}

	@Override
	public int getTotalNbOfAlternativeDescriptions() {
		return orderedListOfOrderedSetIDs.size();
	}

	@Override
	public String getDescriptionOrderedSetID(int index) {
		return orderedListOfOrderedSetIDs.get(index);
	}

	@Override
	public List<String> getDescriptionListOfProperties(int index) {
		return orderedSetIDToOrderedSet.get(orderedListOfOrderedSetIDs.get(index)).getListOfPropertiesWithPath();
	}
	
	@Override
	public List<String> getDescriptionListOfMaximalChains(int index) {
		return orderedSetIDToOrderedSet.get(orderedListOfOrderedSetIDs.get(index)).getListOfLowerSetMaximalChains();
	}	

	@Override
	public String getDescriptionVerbalDescription(int index) {
		String verbalDescription;
		AbstractOmegaElement orderedSet = (AbstractOmegaElement) orderedSetIDToOrderedSet.get(orderedListOfOrderedSetIDs.get(index));
		verbalDescription = orderedSet.getVerbalDescription();
		return verbalDescription;
	}
	
	private List<String> setOrderedListOfOrderedSetIDs() {
		List<String> orderedListOfOrderedSetIDs = new ArrayList<String>(orderedSetIDToOrderedSet.keySet());
		Collections.sort(orderedListOfOrderedSetIDs, Collections.reverseOrder());
		return orderedListOfOrderedSetIDs;		
	}

}
