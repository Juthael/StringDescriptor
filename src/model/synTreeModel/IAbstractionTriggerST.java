package model.synTreeModel;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;

public interface IAbstractionTriggerST extends IGrammaticalST {
	
	void triggerAbstractionProcess() throws OrderedSetsGenerationException, SynTreeGenerationException, CloneNotSupportedException;

}
