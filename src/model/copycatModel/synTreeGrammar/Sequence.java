package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;

public class Sequence extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_NAME = "sequence";
	private CommonDiff commonDiff;
	private AbsCommonDiff absCommonDiff;
	
	public Sequence(CommonDiff commonDiff, AbsCommonDiff absCommonDiff) {
		super(false);
		this.commonDiff = commonDiff;
		this.absCommonDiff = absCommonDiff;	
	}
	
	@Override
	protected Sequence clone() throws CloneNotSupportedException {
		Sequence cloneSequence;
		CommonDiff cloneCommonDiff = commonDiff.clone();
		AbsCommonDiff cloneAbsCommonDiff = absCommonDiff.clone();
		cloneSequence = new Sequence(cloneCommonDiff, cloneAbsCommonDiff);
		return cloneSequence;
	}
	
	@Override
	protected List<IElement> buildListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(commonDiff, absCommonDiff));
		return componentDescriptors;
	}
	
	protected List<SynTreeElementImpl> buildListOfRelevantComponentsForRelationBuilding() {
		List<SynTreeElementImpl> componentDescriptors = new ArrayList<SynTreeElementImpl>(
				Arrays.asList(commonDiff));
		return componentDescriptors;
	}	
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}

}
