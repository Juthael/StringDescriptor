package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

import copycatModel.grammar.Group;
import copycatModel.interfaces.AbstractDescriptorInterface;
import copycatModel.interfaces.SignalInterface;
import exceptions.DescriptorsBuilderCriticalException;
import syntacticTreesGeneration.interfaces.CharStringBuilderInterface;
import syntacticTreesGeneration.interfaces.GroupBuilderInterface;
import syntacticTreesGeneration.interfaces.NewDescriptorBuilderInterface;
import syntacticTreesGeneration.interfaces.RelationDataContainerInterface;

public class NewDescriptorBuilderV1 implements NewDescriptorBuilderInterface {

	private final SignalInterface signal;
	private final RelationDataContainerInterface relationDataContainer;
	private final ArrayList<Group> listOfFactorizableDescriptors;
	
	public NewDescriptorBuilderV1(SignalInterface signal, RelationDataContainerInterface relationDataContainer, 
			ArrayList<Group> listOfFactorizableDescriptors){
		this.signal = signal;
		this.relationDataContainer = relationDataContainer;
		this.listOfFactorizableDescriptors = listOfFactorizableDescriptors;
	}
	
	@Override
	public AbstractDescriptorInterface getNewDescriptor() throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		AbstractDescriptorInterface abstractDescriptor;
		boolean newDescriptorWillCoverTheFullString = relationDataContainer.getNewDescriptorWillCoverTheFullString();
		if (newDescriptorWillCoverTheFullString == true) {
			CharStringBuilderInterface charStringBuilder = 
					new	CharStringBuilderV1(signal.getDirectionValue(), listOfFactorizableDescriptors, relationDataContainer);
			abstractDescriptor = charStringBuilder.getCharString();
		}
		else {
			boolean factorizableDescriptorsAreRelated = (relationDataContainer.getListOfEnumerations().size() != 0);
			if (factorizableDescriptorsAreRelated == true) {
				GroupBuilderInterface groupBuilder = new GroupBuilderV1(listOfFactorizableDescriptors, relationDataContainer);
				abstractDescriptor = groupBuilder.getGroup();
			}
			else throw new DescriptorsBuilderCriticalException("NewDescriptorBuilder : "
					+ "factorizable descriptors don't cover the full string and aren't related." );
		}
		return abstractDescriptor;
	}
	
}
