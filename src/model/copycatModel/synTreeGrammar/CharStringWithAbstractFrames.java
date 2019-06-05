package model.copycatModel.synTreeGrammar;

import exceptions.VerbalizationException;
import model.synTreeModel.ISyntacticTree;
import model.synTreeModel.IStartElementST;

public class CharStringWithAbstractFrames extends CharString implements IStartElementST, Cloneable {

	private String verbalDescription;
	
	public CharStringWithAbstractFrames(CharString charString, String verbalDescription) {
		super(charString);
		this.verbalDescription = verbalDescription;
	}

	@Override
	public ISyntacticTree getTreeWithAbstractFrames() {
		return this;
	}
	
	@Override
	public String getVerbalDescription() throws VerbalizationException {
		return verbalDescription;
	}	

}
