package description;

import java.util.List;
import java.util.Map;

import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import model.orderedSetModel.IOrderedSet;

public interface IDescriptionValuator {
	
	List<String> getOrderedListOfOrderedSetIDs();
	
	Map<String, IOrderedSet> getOrderedSetIDToOrderedSet();
	
	Map<String, List<String>> getOrderedSetIDToListOfPropertiesMapping();
	
	Map<String, String> getOrderedSetIDToVerbalDescriptionMapping();
	
	Map<String, List<String>> getOrderedSetIDToListOfMaximalChainsMapping();
	
	Map<String, BinaryContext> getOrderedSetIDToBinaryContextMapping();
	
	Map<String, ConceptLattice> getOrderedSetIDToConceptLatticeMapping();	
		
	Map<String, Double> getOrderedSetIDToScoreMapping();
	
	Map<List<String>, Double> getListOfPropertiesToScoreMapping();
	
	Map<String, Double> getVerbalDescriptionToScoreMapping();
	
	int getTotalNbOfAlternativeDescriptions();
	
	String getBestDescriptionOrderedSetID();
	
	List<String> getBestDescriptionListOfMaximalChains();
	
	String getBestDescriptionVerbalDescription();
	
	List<String> getBestDescriptionListOfProperties();
	
	String getDescriptionOrderedSetID(int index);
	
	List<String> getDescriptionListOfProperties(int index);	
	
	List<String> getDescriptionListOfMaximalChains(int index);
	
	String getDescriptionVerbalDescription(int index);	

}
