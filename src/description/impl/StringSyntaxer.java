package description.impl;

import java.util.ArrayList;
import java.util.List;

import description.IStringSyntaxer;
import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;
import model.synTreeModel.ISignal;
import model.synTreeModel.IStartElementST;
import model.synTreeModel.IStartGrammElementST;
import model.synTreeModel.impl.GenericStartElementST;
import settings.Settings;
import syntacticTreesGeneration.IListOfDescriptorsBuilder;
import syntacticTreesGeneration.impl.ListOfDescriptorsBuilder;

public class StringSyntaxer implements IStringSyntaxer {

	protected List<IStartElementST> listOfSyntacticTrees;
	
	public StringSyntaxer(ISignal signal) 
			throws SynTreeGenerationException, CloneNotSupportedException, VerbalizationException, OrderedSetsGenerationException {
		IListOfDescriptorsBuilder listOfDescriptorsBuilder = new ListOfDescriptorsBuilder(signal);
		if (!Settings.FRAME_COMPONENTS_ARE_ABSTRACTED_FOR_PAIRS) {
			listOfSyntacticTrees = 
					new ArrayList<IStartElementST>(listOfDescriptorsBuilder.getListOfComprehensiveDescriptors());
		}
		else {
			listOfSyntacticTrees = 
					new ArrayList<IStartElementST>(listOfDescriptorsBuilder.getListOfDescriptorsWithAbstractComponents());	
		}
		 
	}
	
	public StringSyntaxer(IStringSyntaxer syntaxer1, IStringSyntaxer syntaxer2) throws VerbalizationException {
		listOfSyntacticTrees = new ArrayList<IStartElementST>();
		List<IStartElementST> listOfSyntacticTrees1 = syntaxer1.getListOfSyntacticTrees();
		List<IStartElementST> listOfSyntacticTrees2 = syntaxer2.getListOfSyntacticTrees();
		for (IStartElementST synTree1 : listOfSyntacticTrees1) {
			for (IStartElementST synTree2 : listOfSyntacticTrees2) {
				IStartGrammElementST synTreeGramm1 = (IStartGrammElementST) synTree1;
				IStartGrammElementST synTreeGramm2 = (IStartGrammElementST) synTree2;
				IStartElementST newSynTree = new GenericStartElementST(synTreeGramm1, synTreeGramm2);
				listOfSyntacticTrees.add(newSynTree);
			}
		}
	}	

	@Override
	public List<IStartElementST> getListOfSyntacticTrees() {
		return listOfSyntacticTrees;
	}

	@Override
	public IStartElementST getSyntacticTree(int index) {
		return listOfSyntacticTrees.get(index);
	}

}
