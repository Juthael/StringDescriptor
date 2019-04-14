package copycatModel.synTreeModel.grammar;

import java.util.ArrayList;
import java.util.List;

import copycatModel.synTreeModel.impl.SynTreeIntegrableElementImpl;
import settings.Settings;
import syntacticTreesGeneration.impl.DimensionEncodingManager;

public class Dimension extends HowManyDimensions implements Cloneable {

	private static final String descriptorName = "dimension";
	private String indexedPath;
	
	public Dimension(boolean codingDescriptor, String indexedPath) {
		super(codingDescriptor);
		this.indexedPath = getDimensionCode(indexedPath);
	}
	
	@Override
	protected Dimension clone() throws CloneNotSupportedException {
		Dimension cloneDimension = new Dimension(isCodingByDecomposition, indexedPath);
		return cloneDimension;
	}

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents() {
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>();
		return componentDescriptors;
	}
	
	@Override
	public String getDescriptorName() {
		return descriptorName;
	}
	
	@Override
	public List<String> getListOfPropertiesWithPath() {
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		StringBuilder sB = new StringBuilder();
		sB.append(descriptorName);
		sB.append(Settings.PATH_SEPARATOR);
		sB.append(indexedPath);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}		
	
	private String getDimensionCode(String fullDimensionValue) {
		String codedDimension = DimensionEncodingManager.getDimensionCodeFromIndexedPath(fullDimensionValue);
		return codedDimension;
	}

}
