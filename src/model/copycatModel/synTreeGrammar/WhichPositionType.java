package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;

import exceptions.SynTreeGenerationException;
import model.generalModel.IElement;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.IPositionableST;
import model.synTreeModel.impl.GrammaticalST;
import settings.Settings;

public abstract class WhichPositionType extends GrammaticalST implements IGrammaticalST, 
	IPositionableST, Cloneable {
	
	abstract protected WhichPositionType clone() throws CloneNotSupportedException;
	
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
