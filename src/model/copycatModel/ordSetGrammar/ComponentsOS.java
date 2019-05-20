package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.AbstractNonMinimalOS;

public class ComponentsOS extends AbstractNonMinimalOS implements IOrderedSet {

	private static final String NAME = "components";
	private SizeOS size;
	private List<FrameOS> listOfFrames;
	
	public ComponentsOS(String elementID, SizeOS size, List<FrameOS> listOfFrames) {
		super(elementID);
		this.size = size;
		this.listOfFrames = listOfFrames;
	}
	
	public ComponentsOS(String elementID, boolean isCodingElement, SizeOS size, List<FrameOS> listOfFrames) {
		super(elementID, isCodingElement);
		this.size = size;
		this.listOfFrames = listOfFrames;
	}	

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(size);
		listOfComponents.addAll(listOfFrames);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!size.equals(idToIOrderedSet.get(size.getElementID())))
			size = (SizeOS) idToIOrderedSet.get(size.getElementID());
		for (FrameOS frame : listOfFrames) {
			if (!frame.equals(idToIOrderedSet.get(frame.getElementID())))
				frame = (FrameOS) idToIOrderedSet.get(frame.getElementID());
			frame.eliminateRedundancies(idToIOrderedSet);
		}
	}		

}
