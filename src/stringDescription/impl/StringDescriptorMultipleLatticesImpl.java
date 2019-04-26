package stringDescription.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;
import fca.core.lattice.ConceptLattice;
import fca.core.lattice.FormalConcept;
import model.synTreeModel.ISignal;
import stringDescription.IStringDescriptor;

public class StringDescriptorMultipleLatticesImpl extends AbstractStringDescriptorMultipleLattices
		implements IStringDescriptor {

	private Map<String, Double> codedAttributeIDToScore;
	
	public StringDescriptorMultipleLatticesImpl(ISignal signal) throws SynTreeGenerationException,
			CloneNotSupportedException, VerbalizationException, OrderedSetsGenerationException {
		super(signal);
		codedAttributeIDToScore = setCodedAttributeIDToScore();
	}
	
	@Override  
	public Map<String, Double> getCodedAttributeIDToScore(){
		return codedAttributeIDToScore;
	}
	
	@Override
	public Map<List<String>, Double> getListOfPropertiesToScore() {
		Map<List<String>, Double> listOfPropertiesToScore = new HashMap<List<String>, Double>();
		for (String codedAttributeID : setOfCodedAttributesIDs) {
			List<String> listOfPropertiesWithPath = 
					codedAttributeIDToOrderedSet.get(codedAttributeID).getListOfPropertiesWithPath();
			Double score = codedAttributeIDToScore.get(codedAttributeID);
			listOfPropertiesToScore.put(listOfPropertiesWithPath, score);
		}
		return listOfPropertiesToScore;
	}

	@Override
	public Map<String, Double> getVerbalDescriptionToScore() {
		Map<String, Double> verbalDescriptionToScore = new HashMap<String, Double>();
		for (String codedAttributeID : setOfCodedAttributesIDs) {
			String verbalDescription = codedAttributeIDToVerbalDescription.get(codedAttributeID);
			Double score = codedAttributeIDToScore.get(codedAttributeID);
			verbalDescriptionToScore.put(verbalDescription, score);
		}
		return verbalDescriptionToScore;
	}
	
	private Map<String, Double> setCodedAttributeIDToScore() throws OrderedSetsGenerationException {
		Map<String, Double> codedAttributeIDToScore = new HashMap<String, Double>();
		for (String codedAttributeID : setOfCodedAttributesIDs) {
			int nbOfInfReducibleConcepts = 0;
			int nbOfInfReducibleConceptsWithCodingElementInExtent = 0;
			ConceptLattice lattice = codedAttributeIDToLattice.get(codedAttributeID);
			Vector<FormalConcept> listOfConcepts = lattice.getConcepts();
			for (FormalConcept concept : listOfConcepts) {
				if (concept.getParents().size() != 1)
					nbOfInfReducibleConcepts++;
				Set<String> setOfCodingAttributesIDsClone = 
						new HashSet<String>(codedAttributeIDToSetOfCodingAttributesID.get(codedAttributeID));
				if (setOfCodingAttributesIDsClone.removeAll(concept.getExtent()) == true && concept.getParents().size() != 1)
					nbOfInfReducibleConceptsWithCodingElementInExtent++;
			}
			double score = log2(nbOfInfReducibleConcepts) / log2(nbOfInfReducibleConceptsWithCodingElementInExtent);
			codedAttributeIDToScore.put(codedAttributeID, score);
		}
		return codedAttributeIDToScore;
	}
	
	private double log2(int i) {
		double x = (double) i;
		return (Math.log(x) / Math.log(2));
	}

}
