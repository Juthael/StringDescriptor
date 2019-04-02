package syntacticTreesGeneration.interfaces;

import exceptions.DescriptorsBuilderCriticalException;

public interface SymmetryWithCentralElementCheckerInterface {

	boolean getSymmetryWithCentralElementWasFound();

	SymmetryRelationalDataInterface getSymmetryRelationalData() throws DescriptorsBuilderCriticalException;

}