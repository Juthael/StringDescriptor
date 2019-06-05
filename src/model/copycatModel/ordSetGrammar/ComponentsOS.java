package model.copycatModel.ordSetGrammar;

import java.util.ArrayList;
import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IFrameOS;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalOS;

public class ComponentsOS extends NonMinimalOS implements IOrderedSet {

	private static final String NAME = "components";
	private SizeOS size;
	private List<IFrameOS> listOfFrames;
	
	public ComponentsOS(String elementID, SizeOS size, List<IFrameOS> listOfFrames) {
		super(elementID);
		this.size = size;
		this.listOfFrames = listOfFrames;
	}
	
	public ComponentsOS(String elementID, boolean isCodingElement, SizeOS size, List<IFrameOS> listOfFrames) {
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
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (size.getElementID().equals(orderedSet.getElementID()) && size != orderedSet) {
			size = (SizeOS) orderedSet;
		}
		else size.eliminateRedundancies(orderedSet);
		List<IFrameOS> cleanListOfFrames = new ArrayList<IFrameOS>();
		for (IFrameOS frame : listOfFrames) {
			if (frame.getElementID().equals(orderedSet.getElementID()) && frame != orderedSet) {
				frame = (IFrameOS) orderedSet;
			}
			else frame.eliminateRedundancies(orderedSet);
			cleanListOfFrames.add(frame);
		}
		listOfFrames = cleanListOfFrames;
	}		

}