package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;

import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;

public abstract class HowManyDimensions extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	public HowManyDimensions(boolean codingDescriptor) {
		super(codingDescriptor);
	}

	@Override
	abstract protected List<IElement> getListOfComponents();

	@Override
	abstract public String getDescriptorName();
	
	@Override
	abstract protected HowManyDimensions clone()  throws CloneNotSupportedException;
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath(){
		List<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		List<SynTreeElementImpl> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (SynTreeElementImpl componentDescriptor : listOfRelevantComponents) {
			listOfRelevantPropertiesWithPath.addAll(componentDescriptor.getListOfRelevantPropertiesWithPath());
		}
		return listOfRelevantPropertiesWithPath;
	}	

}
