package copycatModel.grammar;

import java.util.ArrayList;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;

public abstract class HowManyGroups extends SynTreeIntegrableElementImpl implements Cloneable {

	public HowManyGroups(boolean codingDescriptor) {
		super(codingDescriptor);
	}

	@Override
	abstract protected List<SynTreeIntegrableElementImpl> buildListOfComponents();
	
	@Override
	abstract public String getDescriptorName();	
	
	@Override
	abstract protected HowManyGroups clone() throws CloneNotSupportedException;
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath(){
		List<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		List<SynTreeIntegrableElementImpl> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (SynTreeIntegrableElementImpl componentDescriptor : listOfRelevantComponents) {
			listOfRelevantPropertiesWithPath.addAll(componentDescriptor.getListOfRelevantPropertiesWithPath());
		}
		return listOfRelevantPropertiesWithPath;
	}
}
