package launcher.utils.menu;

public interface IMenuText {

	String getaStringDescriptor();

	String getbMainMenu();

	String getcSingleStringDescription();

	String getdSingleStringResults(String string);

	String geteSingleStringUniqueResult(String string);

	String getfSingleStringAllResults(String string);

	String getgSingleStringAllResults1By1(int index, double score, String verbalDescription);

	String gethSingleStringScoreCalculation();

	String getiPairOfStringsScoreCalculation();

	String getjPairOfStringsDescription();

	String getkPairOfStringsResults(String string1, String string2);

	String getlPairOfStringsUniqueResult(String string1, String string2);

	String getmPairOfStringsAllResults(String string1, String string2);

	String getnPairOfStringsAllResults1By1(int index, double score, String verbalDescription);

	String getoExit();

	String getZ2InterpString();

	String getZ3InterpPairOfStrings();

	String getZ4Exit();

	String getZ6SeeResultsA();

	String getZ6SeeResultsB();

	String getZ7BackToMainMenu();

	String getZ8SeeBestDescription();

	String getZ9SeeAllDescriptions();

	String getZ10EnterNewString();

	String getZ11SeeVerbalDescription();

	String getZ12SeeSyntacticTree();

	String getZ13SeeOrderedSet();

	String getZ14SeeLattice();

	String getZ15BackToResults();

	String getZ16SeeAllVerbalDescriptions();

	String getZ17SeeResults1By1();

	String getZ18SeeNextResult();

	String getZ20EnterFirstString();

	String getZ21EnterNewPair();

	String getZ24LearnMoreAboutScores();

	String getZ26EnterSecondString();

}