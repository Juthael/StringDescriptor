package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;

public class SequenceRel extends Relation implements ISynTreeElement, Cloneable {

	private Sequence sequence;
	
	public SequenceRel(Dimension dimension, Enumeration enumeration, 
			Sequence sequence) {
		super(dimension, enumeration);
		this.sequence = sequence;			
	}
	
	@Override
	protected SequenceRel clone() throws CloneNotSupportedException {
		SequenceRel cloneSequenceRel;
		Dimension cloneDimension = dimension.clone();
		Enumeration cloneEnumeration = this.enumeration.clone();
		Sequence cloneSequence = sequence.clone();
		cloneSequenceRel = new SequenceRel(cloneDimension, cloneEnumeration, cloneSequence);
		return cloneSequenceRel;
	}	
	
	@Override
	protected List<IElement> buildListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(dimension, enumeration, sequence));
		return componentDescriptors;
	}	
	
	@Override
	protected List<SynTreeElementImpl> buildListOfRelevantComponentsForRelationBuilding() {
		List<SynTreeElementImpl> listOfRelevantComponents = new ArrayList<SynTreeElementImpl>();
		for (IElement component : buildListOfComponents())
			listOfRelevantComponents.add((SynTreeElementImpl) component);
		return listOfRelevantComponents;
	}	

}
