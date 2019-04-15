package syntacticTreesGeneration.impl;

import java.util.List;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Group;
import model.synTreeModel.ISignal;
import model.synTreeModel.ISynTreeElement;
import syntacticTreesGeneration.ICharStringBuilder;
import syntacticTreesGeneration.IGroupBuilder;
import syntacticTreesGeneration.INewDescriptorBuilder;
import syntacticTreesGeneration.IRelationDataContainer;

public class NewDescriptorBuilderImpl implements INewDescriptorBuilder {

	private final ISignal signal;
	private final IRelationDataContainer relationDataContainer;
	private final List<Group> listOfFactorizableDescriptors;
	
	public NewDescriptorBuilderImpl(ISignal signal, IRelationDataContainer relationDataContainer, 
			List<Group> listOfFactorizableDescriptors){
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
				IGroupBuilder groupBuilder = new GroupBuilderImpl(listOfFactorizableDescriptors, relationDataContainer);
				synTreeElement = groupBuilder.getGroup();
			}
			else throw new SynTreeGenerationException("NewDescriptorBuilder : "
					+ "factorizable descriptors don't cover the full string and aren't related." );
		}
		return synTreeElement;
	}
	
}
