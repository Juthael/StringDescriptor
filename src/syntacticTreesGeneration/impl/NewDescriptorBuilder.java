package syntacticTreesGeneration.impl;

import java.util.List;

import exceptions.SynTreeGenerationException;
import model.copycatModel.signal.ICopycatSignal;
import model.copycatModel.synTreeGrammar.Frame;
import model.synTreeModel.IGrammaticalST;
import syntacticTreesGeneration.ICharStringBuilder;
import syntacticTreesGeneration.IFrameBuilder;
import syntacticTreesGeneration.INewDescriptorBuilder;
import syntacticTreesGeneration.IRelationDataContainer;

public class NewDescriptorBuilder implements INewDescriptorBuilder {

	private final ICopycatSignal signal;
	private final IRelationDataContainer relationDataContainer;
	private final List<Frame> listOfFactorizableDescriptors;
	
	public NewDescriptorBuilder(ICopycatSignal signal, IRelationDataContainer relationDataContainer, 
			List<Frame> listOfFactorizableDescriptors){
		this.signal = signal;
		this.relationDataContainer = relationDataContainer;
		this.listOfFactorizableDescriptors = listOfFactorizableDescriptors;
	}
	
	@Override
	public IGrammaticalST getNewDescriptor() throws SynTreeGenerationException, CloneNotSupportedException {
		IGrammaticalST grammaticalST;
		boolean newDescriptorWillCoverTheFullString = relationDataContainer.getNewDescriptorWillCoverTheFullString();
		if (newDescriptorWillCoverTheFullString == true) {
			ICharStringBuilder charStringBuilder = 
					new	CharStringBuilder(signal.getDirectionValue(), listOfFactorizableDescriptors, 
							relationDataContainer);
			grammaticalST = charStringBuilder.getCharString();
		}
		else {
			boolean factorizableDescriptorsAreRelated = (relationDataContainer.getListOfEnumerations().size() != 0);
			if (factorizableDescriptorsAreRelated == true) {
				IFrameBuilder frameBuilder = new FrameBuilder(listOfFactorizableDescriptors, relationDataContainer);
				grammaticalST = frameBuilder.getFrame();
			}
			else throw new SynTreeGenerationException("NewDescriptorBuilder : "
					+ "factorizable descriptors don't cover the full string and aren't related." );
		}
		return grammaticalST;
	}
	
}
