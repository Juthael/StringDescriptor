package stringDescription.impl;

import java.util.HashSet;
import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISignal;
import orderedSetGeneration.IBinaryContextBuilder;
import orderedSetGeneration.IOrderedSetBuilder;
import orderedSetGeneration.impl.BinaryContextBuilderImpl;
import orderedSetGeneration.impl.OrderedSetBuilderImpl;
import stringDescription.IStringDescriptor;

public abstract class AbstractStringDescriptorSingleLattice extends AbstractStringDescriptor implements IStringDescriptor {

	protected Set<String> setOfCodingAttributes;
	protected Set<String> setOfCodedAttributes;
	protected IOrderedSet orderedSet;
	protected ConceptLattice lattice;
	
	public AbstractStringDescriptorSingleLattice(ISignal signal) 
			throws SynTreeGenerationException, CloneNotSupportedException, OrderedSetsGenerationException {
		super(signal);
		IOrderedSetBuilder orderedSetBuilder = new OrderedSetBuilderImpl(listOfSyntacticTrees);
		orderedSet = orderedSetBuilder.getOrderedSet();
		IBinaryContextBuilder binaryContextBuilder = new BinaryContextBuilderImpl(orderedSet);
		BinaryContext binaryContext = binaryContextBuilder.getContext();
		lattice = new ConceptLattice(binaryContext);
		setOfCodingAttributes = setSetOfCodingAttributes(orderedSet);
		setOfCodedAttributes = setSetOfCodedAttributes(orderedSet);
	}

	public IOrderedSet getOrderedSet() {
		return orderedSet;
	}
	
	public ConceptLattice getLattice() {
		return lattice;
	}
	
	public Set<String> getSetOfCodingAttributes(){
		return setOfCodingAttributes;
	}
	
	public Set<String> getSetOfCodedAttributes(){
		return setOfCodedAttributes;
	}
	
	private Set<String> setSetOfCodingAttributes(IOrderedSet orderedSet) {
		Set<String> setOfCodingAttributes = new HashSet<String>();
		Set<IOrderedSet> lowerSet = orderedSet.getLowerSet();
		for (IOrderedSet element : lowerSet) {
			if (element.getIsCodingByDecomposition() == true)
				setOfCodingAttributes.add(element.getElementID());
		}
		return setOfCodingAttributes;
	}
	
	private Set<String> setSetOfCodedAttributes(IOrderedSet orderedSet){
		Set<String> setOfCodedAttributes = new HashSet<String>();
		Set<IOrderedSet> lowerSet = orderedSet.getLowerSet();
		for (IOrderedSet element : lowerSet) {
			if (element.getMayBeTheCodedElement() == true)
				setOfCodedAttributes.add(element.getElementID());
		}
		return setOfCodedAttributes;
	}	

}
