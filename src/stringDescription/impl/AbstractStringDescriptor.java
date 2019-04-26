package stringDescription.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.SynTreeGenerationException;
import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import model.copycatModel.synTreeGrammar.CharString;
import model.synTreeModel.ISignal;
import model.synTreeModel.ISynTreeElement;
import stringDescription.IStringDescriptor;
import syntacticTreesGeneration.IListOfDescriptorsBuilder;
import syntacticTreesGeneration.impl.ListOfDescriptorsBuilderImpl;

public abstract class AbstractStringDescriptor implements IStringDescriptor {

	protected ISignal signal;
	protected List<ISynTreeElement> listOfSyntacticTrees;
	
	public AbstractStringDescriptor(ISignal signal) throws SynTreeGenerationException, CloneNotSupportedException {
		this.signal = signal;
		IListOfDescriptorsBuilder listOfDescriptorsBuilder = new ListOfDescriptorsBuilderImpl(this.signal);
		this.listOfSyntacticTrees = new ArrayList<ISynTreeElement>(listOfDescriptorsBuilder.getListOfStringDescriptors()); 
	}

	@Override
	public List<CharString> getListOfStringSyntacticTrees() {
		List<CharString> listOfStringDescriptors = new ArrayList<CharString>();
		for (ISynTreeElement synTree : listOfSyntacticTrees)
			listOfStringDescriptors.add((CharString) synTree);
		return listOfStringDescriptors;
	}
	
	@Override
	abstract public Map<String, ConceptLattice> getCodedAttributeIDToConceptLattice();

	@Override
	abstract public Map<String, Double> getCodedAttributeIDToScore();

	@Override
	abstract public Map<String, BinaryContext> getCodedAttributeIDToBinaryContext();

}
