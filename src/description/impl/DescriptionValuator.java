package description.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import description.IDescriptionValuator;
import description.IScoreCalculator;
import description.IStringSyntaxer;
import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;
import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.OmegaOS;
import model.synTreeModel.ISignal;
import model.synTreeModel.IStartElementST;
import model.synTreeModel.IStartGrammElementST;
import orderedSetGeneration.IBinaryContextBuilder;
import orderedSetGeneration.IOrderedSetBuilder;
import orderedSetGeneration.impl.BinaryContextBuilder;
import orderedSetGeneration.impl.OrderedSetBuilder;

public class DescriptionValuator extends StringSyntaxer implements IDescriptionValuator {

	protected IScoreCalculator scoreCalculator;
	protected Map<String, IOrderedSet> orderedSetIDToOrderedSet = new HashMap<String, IOrderedSet>();
	protected Map<String, BinaryContext> orderedSetIDToBinaryContext = new HashMap<String, BinaryContext>();
	protected Map<String, ConceptLattice> orderedSetIDToConceptLattice = new HashMap<String, ConceptLattice>();
	protected Map<String, Double> orderedSetIDToScore = new HashMap<String, Double>();
	protected List<String> orderedListOfOrderedSetIDs;
	
	public DescriptionValuator(ISignal signal, IScoreCalculator scoreCalculator) 
			throws SynTreeGenerationException, CloneNotSupportedException, VerbalizationException, OrderedSetsGenerationException {
		super(signal);
		this.scoreCalculator = scoreCalculator;
		for (IStartElementST startElement : listOfSyntacticTrees) {
			IStartGrammElementST startGrammElement = (IStartGrammElementST) startElement;
			IOrderedSetBuilder orderedSetBuilder = 
					new OrderedSetBuilder(startGrammElement.getTreeWithAbstractFrames());
			IOrderedSet orderedSet = orderedSetBuilder.getOrderedSet();
			orderedSetIDToOrderedSet.put(orderedSet.getElementID(), orderedSet);
			IBinaryContextBuilder binaryContextBuilder = new BinaryContextBuilder(orderedSet);
			BinaryContext binaryContext = binaryContextBuilder.getContext();
			orderedSetIDToBinaryContext.put(orderedSet.getElementID(), binaryContext);
			ConceptLattice lattice = new ConceptLattice(binaryContext);
			orderedSetIDToConceptLattice.put(orderedSet.getElementID(), lattice);
			double score = scoreCalculator.calculateScore(orderedSet, lattice);
			orderedSetIDToScore.put(orderedSet.getElementID(), score);
		}
		orderedListOfOrderedSetIDs = setOrderedListOfOrderedSetIDs();
	}
	
	public DescriptionValuator(IStringSyntaxer syntaxer1, IStringSyntaxer syntaxer2, IScoreCalculator scoreCalculator) 
			throws VerbalizationException, OrderedSetsGenerationException, CloneNotSupportedException, SynTreeGenerationException {
		super(syntaxer1, syntaxer2);
		for (IStartElementST startElement : listOfSyntacticTrees) {
			IOrderedSetBuilder orderedSetBuilder = new OrderedSetBuilder(startElement);
			IOrderedSet orderedSet = orderedSetBuilder.getOrderedSet();
			orderedSetIDToOrderedSet.put(orderedSet.getElementID(), orderedSet);
			IBinaryContextBuilder binaryContextBuilder = new BinaryContextBuilder(orderedSet);
			BinaryContext binaryContext = binaryContextBuilder.getContext();
			orderedSetIDToBinaryContext.put(orderedSet.getElementID(), binaryContext);
			ConceptLattice lattice = new ConceptLattice(binaryContext);
			orderedSetIDToConceptLattice.put(orderedSet.getElementID(), lattice);
			double score = scoreCalculator.calculateScore(orderedSet, lattice);
			orderedSetIDToScore.put(orderedSet.getElementID(), score);
		}
		orderedListOfOrderedSetIDs = setOrderedListOfOrderedSetIDs();
	}	

	@Override
	public List<String> getOrderedListOfOrderedSetIDs() {
		return orderedListOfOrderedSetIDs;
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
	public Map<String, String> getOrderedSetIDToVerbalDescriptionMapping() {
		Map<String, String> orderedSetIDToVerbalDescription = new HashMap<String, String>();
		for (String orderedSetID : orderedSetIDToOrderedSet.keySet()) {
			OmegaOS orderedSet = (OmegaOS) orderedSetIDToOrderedSet.get(orderedSetID);
			orderedSetIDToVerbalDescription.put(orderedSetID, orderedSet.getVerbalDescription());
		}
		return orderedSetIDToVerbalDescription;
	}	

	@Override
	public Map<String, IOrderedSet> getOrderedSetIDToOrderedSet() {
		return orderedSetIDToOrderedSet;
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
			OmegaOS orderedSet = (OmegaOS) orderedSetIDToOrderedSet.get(orderedSetID);
			String verbalDescription = orderedSet.getVerbalDescription();
			Double score = orderedSetIDToScore.get(orderedSetID);
			verbalDescriptionToScore.put(verbalDescription, score);
		}
		return verbalDescriptionToScore;
	}	

	@Override
	public int getTotalNbOfAlternativeDescriptions() {
		return orderedListOfOrderedSetIDs.size();
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
		OmegaOS orderedSet = (OmegaOS) orderedSetIDToOrderedSet.get(orderedListOfOrderedSetIDs.get(0));
		verbalDescription = orderedSet.getVerbalDescription();
		return verbalDescription;
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
	public String getDescriptionVerbalDescription(int index) {
		String verbalDescription;
		OmegaOS orderedSet = (OmegaOS) orderedSetIDToOrderedSet.get(orderedListOfOrderedSetIDs.get(index));
		verbalDescription = orderedSet.getVerbalDescription();
		return verbalDescription;
	}		

	@Override
	public List<String> getDescriptionListOfMaximalChains(int index) {
		return orderedSetIDToOrderedSet.get(orderedListOfOrderedSetIDs.get(index)).getListOfLowerSetMaximalChains();
	}
	
	private List<String> setOrderedListOfOrderedSetIDs() {
		List<String> orderedListOfOrderedSetIDs = new ArrayList<String>();
		List<String> listOfIDs = new ArrayList<String>(orderedSetIDToScore.keySet());
		double maxScore;
		String maxScoreID;
		while (!listOfIDs.isEmpty()) {
			maxScore = -1;
			maxScoreID = "";
			for (String id : listOfIDs) {
				double currentScore = orderedSetIDToScore.get(id);
				if (currentScore > maxScore) {
					maxScore = currentScore;
					maxScoreID = id;
				}
			}
			orderedListOfOrderedSetIDs.add(maxScoreID);
			listOfIDs.remove(maxScoreID);			
		}
		Collections.reverse(orderedListOfOrderedSetIDs);
		return orderedListOfOrderedSetIDs;		
	}

}
