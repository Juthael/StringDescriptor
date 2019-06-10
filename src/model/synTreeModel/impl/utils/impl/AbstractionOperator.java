package model.synTreeModel.impl.utils.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import fca.core.lattice.FormalConcept;
import fca.core.util.BasicSet;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.IFrame;
import model.synTreeModel.ISyntacticTree;
import model.synTreeModel.impl.GenericST;
import model.synTreeModel.impl.utils.IAbstractionOperator;
import orderedSetGeneration.IBinaryContextBuilder;
import orderedSetGeneration.IOrderedSetBuilder;
import orderedSetGeneration.impl.BinaryContextBuilderImpl;
import orderedSetGeneration.impl.OrderedSetBuilderImpl;
import settings.Settings;

public class AbstractionOperator implements IAbstractionOperator {

	List<ISyntacticTree> listOfSynTreeElements;
	IOrderedSet localOrderedSet;
	Map<String, String> orderedSetIDsToDescriptorName = new HashMap<String, String>();
	ConceptLattice localConceptLattice;
	FormalConcept topConcept;
	FormalConcept bottomConcept;
	FormalConcept intersectionConcept;
	
	public AbstractionOperator(Set<ISyntacticTree> setOfSynTreeElements) 
			throws OrderedSetsGenerationException, SynTreeGenerationException {
		this.listOfSynTreeElements = new ArrayList<ISyntacticTree>(setOfSynTreeElements);
		IOrderedSetBuilder oSBuilder = new OrderedSetBuilderImpl(listOfSynTreeElements);
		localOrderedSet = oSBuilder.getOrderedSet();
		Set<IOrderedSet> lowerSet = localOrderedSet.getLowerSet();
		for (IOrderedSet set : lowerSet) {
			orderedSetIDsToDescriptorName.put(set.getElementID(), set.getDescriptorName());
		}
		IBinaryContextBuilder binaryContextBuilder = new BinaryContextBuilderImpl(localOrderedSet);
		BinaryContext binaryContext = binaryContextBuilder.getContext();
		localConceptLattice = new ConceptLattice(binaryContext);
		localConceptLattice.findGenerators();
		bottomConcept = localConceptLattice.getBottomConcept();
		topConcept = localConceptLattice.getTopConcept();
		intersectionConcept = findIntersectionConcept();
	}

	@Override
	public IFrame getAbstractedTree() throws SynTreeGenerationException {
		Set<String> abstractedTreeSetOfMaxChains = getAbstractedTreeMaxChains(intersectionConcept);
		List<String> abstractedTreeListOfMaxChains = new ArrayList<String>(abstractedTreeSetOfMaxChains);
		Collections.sort(abstractedTreeListOfMaxChains);
		IFrame syntacticTree = 
				new GenericST(abstractedTreeListOfMaxChains, orderedSetIDsToDescriptorName);
		return syntacticTree;
	}
	
	private FormalConcept findIntersectionConcept() throws SynTreeGenerationException {
		FormalConcept intersectionConcept;
		List<FormalConcept> atoms = bottomConcept.getParents();
		List<FormalConcept> significantAtoms = new ArrayList<FormalConcept>();
		for (FormalConcept formalConcept : atoms) {
			if (!formalConcept.getIntent().contains(Settings.TREE_TOP_ELEMENT_GENERIC_NAME))
				significantAtoms.add(formalConcept);
		}
		BasicSet significantAtomsIntersectionIntent = significantAtoms.get(0).getIntent();
		for (int i=1 ; i<significantAtoms.size() ; i++) {
			significantAtomsIntersectionIntent = 
					significantAtomsIntersectionIntent.intersection(significantAtoms.get(i).getIntent());
		}
		intersectionConcept = localConceptLattice.getConceptWithIntent(significantAtomsIntersectionIntent);
		if (intersectionConcept == null) {
			throw new SynTreeGenerationException("AbstractionOperatorImpl.findIntersectionConcept : no concept found");
		}
		else return intersectionConcept;
	}
	
	public Set<String> getAbstractedTreeMaxChains(FormalConcept formalConcept) throws SynTreeGenerationException {
		Set<String> setOfChains = new HashSet<String>();
		Vector<BasicSet> generators = formalConcept.getGenerators();
		String synTreeName = "";
		if (formalConcept == intersectionConcept) {
			synTreeName = Settings.ABSTRACT_TREE_NAME.concat(Settings.PATH_SEPARATOR);
		}
		else {
			for (BasicSet generatorSet : generators) {
				if (generatorSet.size() == 1) {
					if (synTreeName.isEmpty())
						synTreeName = generatorSet.first().concat(Settings.PATH_SEPARATOR);
					else throw new SynTreeGenerationException("AbstractionOperatorImpl.getAbstractedTreeMaxChains : "
							+ "many concept labels found");
				}
			}
		}
		Vector<FormalConcept> parents = formalConcept.getParents();
		for (FormalConcept parent : parents) {
			if (parent != topConcept) {
				if (synTreeName.isEmpty()) {
					setOfChains.addAll(getAbstractedTreeMaxChains(parent));
				}
				else {
					for (String parentChain : getAbstractedTreeMaxChains(parent)) {
						setOfChains.add(synTreeName.concat(parentChain));
					}
				}
			}
			else if (!synTreeName.isEmpty()) 
				setOfChains.add(synTreeName);
		}
		return setOfChains;
	}

}
