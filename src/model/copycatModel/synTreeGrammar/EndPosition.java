package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import model.copycatModel.ordSetGrammar.EndPositionOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.GenericOS;
import model.orderedSetModel.impl.MinimalOS;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.IPositionableST;
import model.synTreeModel.impl.MinimalST;
import model.synTreeModel.impl.GrammaticalST;
import settings.Settings;

public class EndPosition extends GrammaticalST implements IGrammaticalST, IPositionableST, Cloneable {

	private static final String DESCRIPTOR_NAME = "endPosition";
	private MinimalST endPositionValue;
	
	public EndPosition(String endPositionValue) throws SynTreeGenerationException {
		if (endPositionValue.equals(Settings.FIRST_POSITION) || endPositionValue.equals(Settings.LAST_POSITION))
			this.endPositionValue = new MinimalST(endPositionValue);
		else throw new SynTreeGenerationException("EndPosition() : unexpected value");
		setHashCode();
	}

	@Override
	protected EndPosition clone() throws CloneNotSupportedException {
		try {
			EndPosition cloneEndPosition = new EndPosition(endPositionValue.getDescriptorName());
			return cloneEndPosition;
		}
		catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("EndPosition.clone() : " + e.getMessage());
		}
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = new ArrayList<IElement>();
		listOfComponents.add(endPositionValue);
		return listOfComponents;
	}	
	
	@Override
	public IOrderedSet upgradeAsTheGenericElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet orderedSet;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer index = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String iD = getDescriptorName().concat(index.toString());
		MinimalOS endPositionProperty = (MinimalOS) endPositionValue.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		List<IOrderedSet> listOfComponents = new ArrayList<IOrderedSet>();
		listOfComponents.add(endPositionProperty);
		orderedSet = new GenericOS(iD, getDescriptorName(), listOfComponents);
		return orderedSet;
	}	
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet endPositionOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer positionIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String endPositionID = getDescriptorName().concat(positionIndex.toString());
		MinimalOS endPositionProperty = (MinimalOS) endPositionValue.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		endPositionOS = new EndPositionOS(endPositionID, endPositionProperty);
		return endPositionOS;		
	}	
	
	@Override
	public void updatePosition(String newPosition, List<IElement>componentDescriptors) throws SynTreeGenerationException {
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
