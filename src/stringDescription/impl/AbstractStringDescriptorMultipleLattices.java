package stringDescription.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;
import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import model.copycatModel.synTreeGrammar.CharString;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISignal;
import model.synTreeModel.ISynTreeElement;
import orderedSetGeneration.IBinaryContextBuilder;
import orderedSetGeneration.IOrderedSetBuilder;
import orderedSetGeneration.impl.BinaryContextBuilderImpl;
import orderedSetGeneration.impl.OrderedSetBuilderImpl;
import stringDescription.IStringDescriptor;
import verbalization.dataEncoding.encoders.IVerbalizer;
import verbalization.dataEncoding.encoders.impl.VerbalizerImpl;

public abstract class AbstractStringDescriptorMultipleLattices extends AbstractStringDescriptor implements IStringDescriptor {

	protected Set<String> setOfCodedAttributesIDs = new HashSet<String>();
	protected Map<String, Set<String>> codedAttributeIDToSetOfCodingAttributesID = new HashMap<String, Set<String>>();
	protected Map<String, String> codedAttributeIDToVerbalDescription = new HashMap<String, String>();
	protected Map<String, IOrderedSet> codedAttributeIDToOrderedSet = new HashMap<String, IOrderedSet>();
	protected Map<String, BinaryContext> codedAttributeIDToBinaryContext = new HashMap<String, BinaryContext>();
	protected Map<String, ConceptLattice> codedAttributeIDToLattice = new HashMap<String, ConceptLattice>();
	
	public AbstractStringDescriptorMultipleLattices(ISignal signal)
			throws SynTreeGenerationException, CloneNotSupportedException, VerbalizationException, 
			OrderedSetsGenerationException {
		super(signal);
		for (int i=0 ; i<listOfSyntacticTrees.size() ; i++) {
			ISynTreeElement descriptor = listOfSyntacticTrees.get(i);
			IVerbalizer verbalizer = new VerbalizerImpl((CharString) descriptor);
			String verbalDescription = verbalizer.getTranslationInNaturalLanguage();
			IOrderedSetBuilder orderedSetBuilder = new OrderedSetBuilderImpl(descriptor);
			IOrderedSet orderedSet = orderedSetBuilder.getOrderedSet();
			IBinaryContextBuilder binaryContextBuilder = new BinaryContextBuilderImpl(orderedSet);
			BinaryContext binaryContext = binaryContextBuilder.getContext();
			ConceptLattice lattice = new ConceptLattice(binaryContext);
			setOfCodedAttributesIDs.add(orderedSet.getElementID());
			codedAttributeIDToOrderedSet.put(orderedSet.getElementID(), orderedSet);
			codedAttributeIDToVerbalDescription.put(orderedSet.getElementID(), verbalDescription);
			codedAttributeIDToBinaryContext.put(orderedSet.getElementID(), binaryContext);
			codedAttributeIDToLattice.put(orderedSet.getElementID(), lattice);
			Set<String> setOfCodingAttributesIDs = new HashSet<String>();
			Set<IOrderedSet> lowerSet = orderedSet.getLowerSet();
			for (IOrderedSet subSet : lowerSet) {
				if (subSet.getIsCodingByDecomposition() == true)
					setOfCodingAttributesIDs.add(subSet.getElementID());
			}
			codedAttributeIDToSetOfCodingAttributesID.put(orderedSet.getElementID(), setOfCodingAttributesIDs);
		}
	}
	
	@Override
	public Map<String, BinaryContext> getCodedAttributeIDToBinaryContext(){
		return codedAttributeIDToBinaryContext;
	}
	
	@Override
	public Map<String, ConceptLattice> getCodedAttributeIDToConceptLattice(){
		return codedAttributeIDToLattice;
	}

	@Override
	abstract public Map<String, Double> getCodedAttributeIDToScore();
	
	@Override
	abstract public Map<List<String>, Double> getListOfPropertiesToScore();

	@Override
	abstract public Map<String, Double> getVerbalDescriptionToScore();
	
	
	public Set<String> getSetOfCodedAttributesIDs(){
		return setOfCodedAttributesIDs;
	}
	
	public Map<String, Set<String>> getcodedAttributeToSetOfCodingAttributes(){
		return codedAttributeIDToSetOfCodingAttributesID;
	}
	
	public Map<String, String> getCodedAttributeToVerbalDescription(){
		return codedAttributeIDToVerbalDescription;
	}
	
	public Map<String, ConceptLattice> getCodedAttributeToLattice(){
		return codedAttributeIDToLattice;
	}
	
	public void getHasseDiagram() {
		
	}
}
