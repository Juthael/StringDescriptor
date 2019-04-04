package syntacticTreesGeneration.interfaces;

import exceptions.DescriptorsBuilderCriticalException;

public interface SymmetryCheckerInterface {

	/* (non-Javadoc)
	 * @see descriptorsGeneration.implementations.SymmetryWithNoCentralElementChecker#getSymmetryWithNoCenterWasFound()
	 */
	boolean getSymmetryWasFound();

	/* (non-Javadoc)
	 * @see descriptorsGeneration.implementations.SymmetryWithNoCentralElementChecker#getSymmetryRelationalData()
	 */
	SymmetryRelationalDataInterface getSymmetryRelationalData() throws DescriptorsBuilderCriticalException;

}