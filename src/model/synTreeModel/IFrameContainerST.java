package model.synTreeModel;

import java.util.List;

public interface IFrameContainerST extends IGrammaticalST {
	
	List<IFrame> getListOfChildrenFrames();
	
	void setNewListOfChildrenFrames(List<IFrame> newListOfFrames);

}
