package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.IFrame;
import model.synTreeModel.IFrameContainerST;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.IPositionableST;
import model.synTreeModel.impl.FrameContainerST;
import settings.Settings;

public class FrameX extends FrameContainerST implements IFrameContainerST, IOneOrManyFrames, IPositionableST, Cloneable {

	private static final String DESCRIPTOR_PARTIAL_NAME = "frameX";
	private List<IFrame> listOfFrames;
	
	public FrameX(List<IFrame> listOfFrames) throws SynTreeGenerationException, CloneNotSupportedException {
		if (listOfFrames.size() < 2 || listOfFrames.size() > Settings.MAX_NB_OF_FRAMES_IN_RELATIONS)
			throw new SynTreeGenerationException("FrameX() : illegal number of frames (" + listOfFrames.size() + ")");
		else {
			this.listOfFrames = listOfFrames;
			List<IPositionableST> synTreeComponents = new ArrayList<IPositionableST>();
			for (IElement component : getListOfComponents())
				synTreeComponents.add((IPositionableST) component);
			updateComponentsPosition(Settings.COMPONENT_AUTO_POSITIONING, synTreeComponents);	
		}
		setHashCode();
	}
	
	@Override
	public FrameX clone() throws CloneNotSupportedException {
		FrameX cloneFrameX;
		List<IFrame> cloneListOfFrames = new ArrayList<IFrame>();
		for (IFrame frame : listOfFrames)
			cloneListOfFrames.add(frame.clone());
		try {
			cloneFrameX = new FrameX(cloneListOfFrames);
		} catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("Frame.clone() : " + e.getMessage());
		}
		return cloneFrameX;
	}	
	
	@Override
	public String getDescriptorName() {
		String name = DESCRIPTOR_PARTIAL_NAME.concat(Integer.toString(listOfFrames.size()));
		return name;
	}	

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = new ArrayList<IElement>();
		for (IFrame frame : listOfFrames)
			listOfComponents.add(frame);
		return listOfComponents;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath(){
		List<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		List<IGrammaticalST> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (IGrammaticalST componentDescriptor : listOfRelevantComponents) {
			listOfRelevantPropertiesWithPath.addAll(componentDescriptor.getListOfRelevantPropertiesWithPath());
		}
		return listOfRelevantPropertiesWithPath;
	}	
	
	@Override
	protected List<IGrammaticalST> buildListOfRelevantComponentsForRelationBuilding() {
		List<IGrammaticalST> listOfRelevantComponents = new ArrayList<IGrammaticalST>();
		listOfRelevantComponents.add((IGrammaticalST) listOfFrames.get(0));
		return listOfRelevantComponents;
	}
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		throw new OrderedSetsGenerationException("FrameX can't be upgraded.");	
	}
	
	public void updatePosition(String newPosition, List<IElement>componentDescriptors) 
			throws SynTreeGenerationException {
		doUpdatePosition(newPosition);
		updateComponentsPosition(newPosition, componentDescriptors);
	}		
	
	protected void doUpdatePosition(String newPosition) throws SynTreeGenerationException {
	}
	
	protected void updateComponentsPosition(String newPosition,	List<IElement> componentDescriptors) 
			throws SynTreeGenerationException {
		for (IElement componentDescriptor : componentDescriptors) {
			if (checkIfThisElementIfPositionable(componentDescriptor) == true) {
				List<IElement> listOfPositionnableSubComponents = new ArrayList<IElement>();
				for (IElement subComponent : componentDescriptor.getListOfComponents()) {
					boolean thisSubComponentIsPositionable = checkIfThisElementIfPositionable(subComponent);
					if (thisSubComponentIsPositionable == true)
						listOfPositionnableSubComponents.add((IPositionableST) subComponent);
				}
				IPositionableST synTreeComponent = (IPositionableST) componentDescriptor;
				synTreeComponent.updatePosition(newPosition, listOfPositionnableSubComponents);
			}
		}
	}	
	
	protected void updateComponentsPosition(int autoPosition, List<IPositionableST> componentDescriptors) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		if (autoPosition == Settings.COMPONENT_AUTO_POSITIONING) {
			int positionIndex = 1;
			StringBuilder sB;
			for (IPositionableST componentDescriptor : componentDescriptors) {
				if (checkIfThisElementIfPositionable(componentDescriptor) == true) {
					sB = new StringBuilder();
					String positionValue = Integer.toString(positionIndex);
					String specialPositionValue = getSpecialPositionValue(positionIndex, componentDescriptors.size());
					sB.append(positionValue);
					if (!specialPositionValue.isEmpty()) {
						sB.append(Settings.POSITION_VALUES_SEPARATOR);
						sB.append(specialPositionValue);	
					}
					List<IElement> listOfPositionnableSubComponents = new ArrayList<IElement>();
					for (IElement subComponent : componentDescriptor.getListOfComponents()) {
						boolean thisSubComponentIsPositionnable = checkIfThisElementIfPositionable(subComponent);
						if (thisSubComponentIsPositionnable == true)
							listOfPositionnableSubComponents.add((IPositionableST) subComponent);
					}
					componentDescriptor.updatePosition(sB.toString(), listOfPositionnableSubComponents);
					positionIndex++;
				}
			}
		} else throw new SynTreeGenerationException(
				"AbstractDescriptor.updateComponents() : illegal constant value.");
	}		
	
	private String getSpecialPositionValue(int positionIndex, int nbOfComponentDescriptors) {
		String specialPositionValue = "";
		if (nbOfComponentDescriptors > 1) {
			if (positionIndex == 1)
				specialPositionValue = Settings.FIRST_POSITION;
			else if (positionIndex == nbOfComponentDescriptors) {
				specialPositionValue = Settings.LAST_POSITION;
			}
			else if (nbOfComponentDescriptors % 2 == 1) {
				int halfIntegerPart = (int) nbOfComponentDescriptors/2;
				if (positionIndex == (halfIntegerPart + 1))
					specialPositionValue = Settings.CENTRAL_POSITION;
			}
		}
		return specialPositionValue;
	}	
	
	private boolean checkIfThisElementIfPositionable(IElement element) {
		boolean thisElementIsPositionnable = false;
		Class<?> subComponentClass = element.getClass();
		Class<?>[] subComponentInterfaceClasses = subComponentClass.getInterfaces();
		for (Class<?> interfaceClass : subComponentInterfaceClasses) {
			if (interfaceClass == IPositionableST.class)
				thisElementIsPositionnable = true;
		}
		return thisElementIsPositionnable;
	}

	@Override
	public List<IFrame> getListOfChildrenFrames() {
		return listOfFrames;
	}

	@Override
	public void setNewListOfChildrenFrames(List<IFrame> newListOfFrames) {
		listOfFrames = newListOfFrames;
	}		

}
