package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;

import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;

public abstract class HowManyRelations extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	public HowManyRelations(boolean codingDescriptor) {
		super(codingDescriptor);
	}

	@Override
	abstract protected List<IElement> getListOfComponents();

	@Override
	abstract public String getDescriptorName();
	
	@Override
	abstract protected HowManyRelations clone() throws CloneNotSupportedException;
	
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
