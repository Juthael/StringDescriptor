package syntacticTreesGeneration.impl;

import java.util.List;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Frame;
import model.synTreeModel.ISignal;
import model.synTreeModel.ISynTreeElement;
import syntacticTreesGeneration.ICharStringBuilder;
import syntacticTreesGeneration.IFrameBuilder;
import syntacticTreesGeneration.INewDescriptorBuilder;
import syntacticTreesGeneration.IRelationDataContainer;

public class NewDescriptorBuilderImpl implements INewDescriptorBuilder {

	private final ISignal signal;
	private final IRelationDataContainer relationDataContainer;
	private final List<Frame> listOfFactorizableDescriptors;
	
	public NewDescriptorBuilderImpl(ISignal signal, IRelationDataContainer relationDataContainer, 
			List<Frame> listOfFactorizableDescriptors){
		this.signal = signal;
		this.relationDataContainer = relationDataContainer;
		this.listOfFactorizableDescriptors = listOfFactorizableDescriptors;
	}
	
	@Override
	public ISynTreeElement getNewDescriptor() throws SynTreeGenerationException, CloneNotSupportedException {
		ISynTreeElement synTreeElement;
		boolean newDescriptorWillCoverTheFullString = relationDataContainer.getNewDescriptorWillCoverTheFullString();
		if (newDescriptorWillCoverTheFullString == true) {
			ICharStringBuilder charStringBuilder = 
					new	CharStringBuilderImpl(signal.getDirectionValue(), listOfFactorizableDescriptors, 
							relationDataContainer);
			synTreeElement = charStringBuilder.getCharString();
		}
		else {
			boolean factorizableDescriptorsAreRelated = (relationDataContainer.getListOfEnumerations().size() != 0);
			if (factorizableDescriptorsAreRelated == true) {
				IFrameBuilder frameBuilder = new FrameBuilderImpl(listOfFactorizableDescriptors, relationDataContainer);
				synTreeElement = frameBuilder.getFrame();
			}
			else throw new SynTreeGenerationException("NewDescriptorBuilder : "
					+ "factorizable descriptors don't cover the full string and aren't related." );
		}
		return synTreeElement;
	}
	
}
