package syntacticTreesGeneration.interfaces;

import exceptions.DescriptorsBuilderCriticalException;

public interface SymmetryWithNoCentralElementCheckerInterface {

	boolean getSymmetryWithNoCenterWasFound();

	SymmetryRelationalDataInterface getSymmetryRelationalData() throws DescriptorsBuilderCriticalException;

}