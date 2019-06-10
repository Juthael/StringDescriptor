package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import model.copycatModel.ordSetGrammar.ComponentsOS;
import model.copycatModel.ordSetGrammar.FrameOS;
import model.copycatModel.ordSetGrammar.SizeOS;
import model.generalModel.IElement;
import model.orderedSetModel.IFrameOS;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.GenericOS;
import model.synTreeModel.IFrame;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.IPositionableST;
import model.synTreeModel.ISyntacticTree;
import model.synTreeModel.impl.GenericST;
import model.synTreeModel.impl.GrammaticalST;
import settings.Settings;

public class Components extends GrammaticalST implements IGrammaticalST, IPositionableST, Cloneable {

	private static final String DESCRIPTOR_NAME = "components";
	private Size size;
	private ISyntacticTree oneOrManyFrames;
	
	public Components(Size size, IOneOrManyFrames frameHM) throws SynTreeGenerationException {
		this.size = size;
		if (frameHM.getDescriptorName().equals("frame")) {
			this.oneOrManyFrames = (Frame) frameHM;
			List<IElement> listWithSingleFrame = new ArrayList<IElement>(); 
			listWithSingleFrame.add(this.oneOrManyFrames);
			updateComponentsPosition("1", listWithSingleFrame);
		}
		else this.oneOrManyFrames = frameHM;
		setHashCode();
	}
	
	public Components(Size size, IOneOrManyFrames frameHM, boolean wholeStringFrame) throws SynTreeGenerationException {
		if (wholeStringFrame == Settings.FULL_STRING_FRAME && frameHM.getDescriptorName().equals("frame")){
			this.size = size;
			Frame castFrame = (Frame) frameHM;
			this.oneOrManyFrames = castFrame;
			List<IElement> listWithSingleFrame = new ArrayList<IElement>(); 
			listWithSingleFrame.add(this.oneOrManyFrames);
			updateComponentsPosition(Settings.CONVENTIONAL_POSITION_FOR_FULL_STRING_FRAME, listWithSingleFrame);				
		} else throw new SynTreeGenerationException("Components : illegal parameter values in constructor");
		setHashCode();
	}
	
	public Components(Size size, GenericST abstractFrame) throws SynTreeGenerationException, CloneNotSupportedException {
		this.size = size;
		this.oneOrManyFrames = abstractFrame;
		setHashCode();
	}		
	
	@Override
	protected Components clone() throws CloneNotSupportedException {
		Components cloneComponents;
		Size cloneSize = size.clone();
		try {
			if (!oneOrManyFrames.getDescriptorName().equals(Settings.ABSTRACT_TREE_NAME)) {
				IOneOrManyFrames frameHM = (IOneOrManyFrames) this.oneOrManyFrames;
				IOneOrManyFrames cloneFrameHM1 = frameHM.clone();
				cloneComponents = new Components(cloneSize, cloneFrameHM1);
			}
			else {
				GenericST abstractFrame = (GenericST) oneOrManyFrames;
				GenericST cloneFrameHM2 = abstractFrame.clone();
				cloneComponents = new Components(cloneSize, cloneFrameHM2);
			}	
		} catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("Components : error in clone() method");
		}
		return cloneComponents;
	}

	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	

	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(size, oneOrManyFrames));
		return componentDescriptors;
	}
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet componentsOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer componentsIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String componentsID = getDescriptorName().concat(componentsIndex.toString());
		SizeOS sizeOS = (SizeOS) size.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		List<IFrameOS> listOfFrameOS = new ArrayList<IFrameOS>();
		if (!oneOrManyFrames.getDescriptorName().equals(Settings.ABSTRACT_TREE_NAME)) {
			if (oneOrManyFrames.getDescriptorName().contains("frameX")) {
				for (IElement element : oneOrManyFrames.getListOfComponents()) {
					ISyntacticTree synTree = (ISyntacticTree) element;
					IFrameOS castframeOS = (IFrameOS) synTree.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
					listOfFrameOS.add(castframeOS);
				}
			}
			else if (oneOrManyFrames.getDescriptorName().equals("frame")){
				Frame castFrame = (Frame) oneOrManyFrames;
				listOfFrameOS.add((FrameOS) castFrame.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex));
			}
			else throw new OrderedSetsGenerationException("Components.upgradeAsTheElementOfAnOrderedSet() : "
					+ "frame descriptor name '" + oneOrManyFrames.getDescriptorName() + "' is inconsistant.");
			componentsOS = new ComponentsOS(componentsID, isCodingElement, sizeOS, listOfFrameOS);
		}
		else {
			List<IOrderedSet> listOfElements = new ArrayList<IOrderedSet>();
			listOfElements.add(oneOrManyFrames.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex));
			listOfElements.add(sizeOS);
			componentsOS = new GenericOS(componentsID, getDescriptorName(), listOfElements);			
		}
		return componentsOS;		
	}	
	
	@Override
	public void updatePosition(String newPosition, List<IElement>componentDescriptors) throws SynTreeGenerationException {
		doUpdatePosition(newPosition);
		updateComponentsPosition(newPosition, componentDescriptors);
	}
	
	public void preventFrameAbstraction() throws SynTreeGenerationException, CloneNotSupportedException {
		if (oneOrManyFrames.getDescriptorName().contains("frameX")){
			IFrameX iFrameX = (IFrameX) oneOrManyFrames;
			if (iFrameX.isAbstractable()) {
				FrameX manyFrames = (FrameX) oneOrManyFrames;
				List<IFrame> listOfFrames = manyFrames.getListOfChildrenFrames();
				FrameX2Unabstractable sanctuarizedFrames = new FrameX2Unabstractable(listOfFrames);
				oneOrManyFrames = sanctuarizedFrames;
			}
		}
	}
	
	@Override
	public Set<ISyntacticTree> getFramesToAbstract() throws SynTreeGenerationException, CloneNotSupportedException {
		if (oneOrManyFrames.getDescriptorName().equals("frame")) {
			List<IFrame> listOfFrames = new ArrayList<IFrame>();
			listOfFrames.add((IFrame) oneOrManyFrames);
			FrameX frameX = new FrameX(listOfFrames, getIsWaitingForAbstraction());
			oneOrManyFrames = frameX;
		}
		return super.getFramesToAbstract();
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
			throws SynTreeGenerationException {
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

}
