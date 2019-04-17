package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;

import model.synTreeModel.ISynTreeElementWithPosition;
import model.synTreeModel.impl.SynTreeElementImpl;
import model.synTreeModel.impl.SynTreeElementWithPositionImpl;

public abstract class HowManyGroups extends SynTreeElementWithPositionImpl implements ISynTreeElementWithPosition, Cloneable {

	public HowManyGroups() {
	}
	
	public HowManyGroups(boolean isCodingDescriptor) {
		super(isCodingDescriptor);
	}

	@Override
	abstract public String getDescriptorName();	
	
	@Override
	abstract protected HowManyGroups clone() throws CloneNotSupportedException;
	
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
