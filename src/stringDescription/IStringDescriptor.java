package stringDescription;

import java.util.List;
import java.util.Map;

import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import model.copycatModel.synTreeGrammar.CharString;

public interface IStringDescriptor {
	
	List<CharString> getListOfStringSyntacticTrees();
	
	Map<String, BinaryContext> getCodedAttributeIDToBinaryContext();
	
	Map<String, ConceptLattice> getCodedAttributeIDToConceptLattice();	
		
	Map<String, Double> getCodedAttributeIDToScore();
	
	Map<List<String>, Double> getListOfPropertiesToScore();
	
	Map<String, Double> getVerbalDescriptionToScore();
	
}
