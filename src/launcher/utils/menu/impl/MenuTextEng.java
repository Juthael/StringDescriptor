package launcher.utils.menu.impl;

import launcher.utils.menu.IMenuText;
import settings.Settings;

public class MenuTextEng implements IMenuText {

	private static final String newLine = System.lineSeparator();
	private static final String replace = "REPLACE";
	private static final String replace1 = "REPLACE1";
	private static final String replace2 = "REPLACE2";
	private static final String replace3 = "REPLACE3";
	
	private static final String A_STRING_DESCRIPTOR = 
			
newLine + "********** STRING DESCRIPTOR **********" + newLine + newLine + 

"This program is intended to apply a model of the categorization/analogy process " + newLine + 
"to the micro-world Copycat (Hofstadter/Mitchell), that deals with simple strings " + newLine + 
"of characters. " + newLine + 
"StringDescriptor generates various possible descriptions of a single string provided " + newLine + 
"by the user, and predicts which one will be perceived as the right one, or the most obvious. " + newLine + 
"It can also (in most cases) anticipate the 'human' interpretation of a pair of strings, " + newLine + 
"assuming - as humans do - that they resemble each other ‘in a certain way’." + newLine;
	
	private static final String B_MAIN_MENU = 
			
newLine + "***** MAIN MENU *****" + newLine + newLine + 
"Would you like to : " + newLine;
	
	private static final String C_SINGLE_STRING_DESCRIPTION = 
			
newLine + "***** DESCRIPTION OF A SINGLE STRING *****" + newLine + newLine +

"The description of a string of characters can take the form of a syntactic tree, " + newLine + 
"that can be 'translated' into an ordered set of properties. The ordering relation " + newLine + 
"can itself lead to the construction of a Galois lattice, from which the score of " + newLine +
"each description will be calculated." + newLine +
"All of these structures (syntactic tree, ordered set, lattice) are almost exhaustive " + newLine +
"but quite abstruse ; therefore, they can also be roughly translated into a simple " + newLine +
"verbal description, formulated as a recipe for the production of the provided string. " + newLine + newLine + 

"Would you like to : " + newLine;	
	
	private static final String D_SINGLE_STRING_RESULTS = 
			
newLine + "***** DESCRIPTION OF A SINGLE STRING : RESULTS *****" + newLine + newLine +

"The analysis of the string 'REPLACE' is complete." + newLine + newLine + 

"Would you like to : " + newLine;		
	
	private static final String E_SINGLE_STRING_UNIQUE_RESULT = 
			
newLine + "The requested description of the string REPLACE has been retreived. " + newLine +
"How would you like it to be displayed ?" + newLine;
	
	private static final String F_SINGLE_STRING_ALL_RESULTS = 
			
newLine + "All descriptions of the string REPLACE have been retreived. " + newLine +
"Would you like to : " + newLine;	
	
	private static final String G_SINGLE_STRING_ALL_RESULTS_1_BY_1 = 
			
newLine + "*** Description n°REPLACE1 . Score : REPLACE2 ***" + newLine + newLine +

"REPLACE3" + newLine + newLine + 

"Would you like to : " + newLine;
	
	private static final String H_SINGLE_STRING_SCORE_CALCULATION = 	

newLine + "***** SCORE CALCULATION *****" + newLine + newLine +

"All the descriptions of a single string of characters can be viewed as different encodings " + newLine + 
"of the same signal. A good encoding is one that minimizes the amount of information required. " + newLine +
"This quantity is measured here using the Galois lattice of the relation over the set of " + newLine + 
"properties of a given context. Each contextually salient property is associated with a " + newLine + 
"probability - depending on the lattice structure - of being maintained in a future " + newLine + 
"expansion of the context. Shannon's formula (- log2 p) then makes it possible to deduce" + newLine + 
"the amount of information contained in each description." + newLine + newLine + 

"Would you like to : " + newLine;
	
	private static final String I_PAIR_OF_STRINGS_SCORE_CALCULATION = "TO DO" + newLine + "Would you like to : ";
	
	private static final String J_PAIR_OF_STRINGS_DESCRIPTION = 
			
newLine + "***** DESCRIPTION OF A PAIR OF STRINGS *****" + newLine + newLine +

"The 'single string description' task was intended to test a formal expression of the " + newLine + 
"operation performed when one categorizes. " + newLine + 
"Here we apply the same treatment for the description of pairs of chains that are assumed "+ newLine + 
"to be similar from a certain point of vue, which remains to be determined. In doing so, " + newLine + 
"we test the idea that the usual distinction between analogy and categorization is unjustified : " + newLine + 
"those two words designate a unique phenomenon. " + newLine +
"As in the 'single string description' task, each description can be displayed in a variety " + newLine +
"of ways : some more exhaustive and abstruse (syntactic tree, ordered set, lattice), some " + newLine + 
"more approximate but easier to read (verbal translation). " + newLine + newLine +

"Would you like to : " + newLine;
	
	private static final String K_PAIR_OF_STRINGS_RESULTS = 
			
newLine + "***** DESCRIPTION OF A PAIR OF STRINGS – RESULTS *****" + newLine + newLine + 

"The pair of strings (‘REPLACE1’, ‘REPLACE2’) has been analyzed. Would you like to : " + newLine;	
	
	private static final String L_PAIR_OF_STRINGS_UNIQUE_RESULT = 
			
newLine + "The requested description of the pair of strings ('REPLACE1','REPLACE2') has been retreived. " + newLine +
"How would you like it to be displayed ? " + newLine;		
	
	private static final String M_PAIR_OF_STRINGS_ALL_RESULTS = 
			
newLine + "All descriptions of the pair of strings (REPLACE1,REPLACE2) have been retreived. " + newLine +
"Would you like to : " + newLine;			
	
	private static final String N_PAIR_OF_STRINGS_ALL_RESULTS_1_BY_1 = 
			
newLine + "*** Description n°REPLACE1 . Score : REPLACE2 ***" + newLine + newLine +

"REPLACE3" + newLine + newLine + 

"Would you like to : " + newLine;	
	
	private static final String O_EXIT = newLine + "Goodbye";
	
	private static final String Z2_INTERP_STRING = "enter a single string ";
	private static final String Z3_INTERP_PAIR_OF_STRINGS = "enter a pair of strings ";
	private static final String Z4_EXIT = "quit";
	private static final String Z6_SEE_RESULTS_A = "enter a string ";
	private static final String Z6_SEE_RESULTS_B = "Please enter a string. " + newLine +
			"Letters must be REPLACE1 and the string cannot contain more than REPLACE2 characters. ";
	private static final String Z7_BACK_TO_MAIN_MENU = "go back to the main menu ";
	private static final String Z8_SEE_BEST_DESCRIPTION = "see the optimal description ";
	private static final String Z9_SEE_ALL_DESCRIPTIONS = "see all descriptions, ranked in descending order of relevance ";
	private static final String Z10_ENTER_NEW_STRING = "enter a new string ";
	private static final String Z11_SEE_VERBAL_DESCRIPTION = "see the verbal description, with its score ";
	private static final String Z12_SEE_SYNTACTIC_TREE = "see the syntactic tree ";
	private static final String Z13_SEE_ORDERED_SET = "see the ordered set ";
	private static final String Z14_SEE_LATTICE = "see the galois lattice" ;
	private static final String Z15_BACK_TO_RESULTS = "go back to results " ;
	private static final String Z16_SEE_ALL_VERBAL_DESCRIPTIONS = "see them all at once, in a verbal form, with their respective scores ";
	private static final String Z17_SEE_RESULTS_1_BY_1 = "see them one by one, as a syntactic tree, an ordered set and/or a lattice ";
	private static final String Z18_SEE_NEXT_RESULT = "see next result ";
	private static final String Z20_ENTER_FIRST_STRING = "Please enter the first string. " + newLine +
			"Letters must be REPLACE1 and the string cannot contain more than REPLACE2 characters. ";
	private static final String Z21_ENTER_NEW_PAIR =  "enter a new pair of strings ";
	private static final String Z24_LEARN_MORE_ABOUT_SCORES = "learn more about score calculation ";
	private static final String Z26_ENTER_SECOND_STRING = "First string analysis is complete. Please enter the second string";
	

	public MenuTextEng() {
	}
	
	@Override
	public String getaStringDescriptor() {
		return A_STRING_DESCRIPTOR;
	}

	@Override
	public String getbMainMenu() {
		return B_MAIN_MENU;
	}

	@Override
	public String getcSingleStringDescription() {
		return C_SINGLE_STRING_DESCRIPTION;
	}

	@Override
	public String getdSingleStringResults(String string) {
		String dText = D_SINGLE_STRING_RESULTS;
		dText = dText.replace(replace, string);
		return dText;
	}

	@Override
	public String geteSingleStringUniqueResult(String string) {
		String eText = E_SINGLE_STRING_UNIQUE_RESULT;
		eText = eText.replace(replace, string);
		return eText;
	}

	@Override
	public String getfSingleStringAllResults(String string) {
		String fText = F_SINGLE_STRING_ALL_RESULTS;
		fText = fText.replace(replace, string);
		return fText;
	}

	@Override
	public String getgSingleStringAllResults1By1(int index, double score, String verbalDescription) {
		String gText = G_SINGLE_STRING_ALL_RESULTS_1_BY_1;
		gText = gText.replace(replace1, Integer.toString(index));
		gText = gText.replace(replace2, Double.toString(score));
		gText = gText.replace(replace3, verbalDescription);
		return gText;
	}

	@Override
	public String gethSingleStringScoreCalculation() {
		return H_SINGLE_STRING_SCORE_CALCULATION;
	}

	@Override
	public String getiPairOfStringsScoreCalculation() {
		return I_PAIR_OF_STRINGS_SCORE_CALCULATION;
	}

	@Override
	public String getjPairOfStringsDescription() {
		return J_PAIR_OF_STRINGS_DESCRIPTION;
	}

	@Override
	public String getkPairOfStringsResults(String string1, String string2) {
		String kText = K_PAIR_OF_STRINGS_RESULTS;
		kText = kText.replace(replace1, string1);
		kText = kText.replace(replace2, string2);
		return kText;
	}

	@Override
	public String getlPairOfStringsUniqueResult(String string1, String string2) {
		String lText = L_PAIR_OF_STRINGS_UNIQUE_RESULT;
		lText = lText.replace(replace1, string1);
		lText = lText.replace(replace2, string2);
		return lText;
	}

	@Override
	public String getmPairOfStringsAllResults(String string1, String string2) {
		String mText = M_PAIR_OF_STRINGS_ALL_RESULTS;
		mText = mText.replace(replace1, string1);
		mText = mText.replace(replace2, string2);
		return mText;
	}

	@Override
	public String getnPairOfStringsAllResults1By1(int index, double score, String description) {
		String nText = N_PAIR_OF_STRINGS_ALL_RESULTS_1_BY_1;
		nText = nText.replace(replace1, Integer.toString(index));
		nText = nText.replace(replace2, Double.toString(score));
		nText = nText.replace(replace3, description);
		return nText;
	}

	@Override
	public String getoExit() {
		return O_EXIT;
	}

	@Override
	public String getZ2InterpString() {
		return Z2_INTERP_STRING;
	}

	@Override
	public String getZ3InterpPairOfStrings() {
		return Z3_INTERP_PAIR_OF_STRINGS;
	}

	@Override
	public String getZ4Exit() {
		return Z4_EXIT;
	}

	@Override
	public String getZ6SeeResultsA() {
		return Z6_SEE_RESULTS_A;
	}

	@Override
	public String getZ6SeeResultsB() {
		String letterCase;
		if (Settings.USE_LOWERCASE_LETTER)
			letterCase = "lower case";
		else letterCase = "upper case";
		String stringMaxSize = Integer.toString(Settings.MAX_NB_OF_CHARS_IN_STRING);
		String text = Z6_SEE_RESULTS_B;
		text = text.replace(replace1, letterCase);
		text = text.replace(replace2, stringMaxSize);
		return text;
	}

	@Override
	public String getZ7BackToMainMenu() {
		return Z7_BACK_TO_MAIN_MENU;
	}

	@Override
	public String getZ8SeeBestDescription() {
		return Z8_SEE_BEST_DESCRIPTION;
	}

	@Override
	public String getZ9SeeAllDescriptions() {
		return Z9_SEE_ALL_DESCRIPTIONS;
	}

	@Override
	public String getZ10EnterNewString() {
		return Z10_ENTER_NEW_STRING;
	}

	@Override
	public String getZ11SeeVerbalDescription() {
		return Z11_SEE_VERBAL_DESCRIPTION;
	}

	@Override
	public String getZ12SeeSyntacticTree() {
		return Z12_SEE_SYNTACTIC_TREE;
	}

	@Override
	public String getZ13SeeOrderedSet() {
		return Z13_SEE_ORDERED_SET;
	}

	@Override
	public String getZ14SeeLattice() {
		return Z14_SEE_LATTICE;
	}

	@Override
	public String getZ15BackToResults() {
		return Z15_BACK_TO_RESULTS;
	}

	@Override
	public String getZ16SeeAllVerbalDescriptions() {
		return Z16_SEE_ALL_VERBAL_DESCRIPTIONS;
	}

	@Override
	public String getZ17SeeResults1By1() {
		return Z17_SEE_RESULTS_1_BY_1;
	}

	@Override
	public String getZ18SeeNextResult() {
		return Z18_SEE_NEXT_RESULT;
	}

	@Override
	public String getZ20EnterFirstString() {
		return Z20_ENTER_FIRST_STRING;
	}

	@Override
	public String getZ21EnterNewPair() {
		return Z21_ENTER_NEW_PAIR;
	}

	@Override
	public String getZ24LearnMoreAboutScores() {
		return Z24_LEARN_MORE_ABOUT_SCORES;
	}

	@Override
	public String getZ26EnterSecondString() {
		return Z26_ENTER_SECOND_STRING;
	}
	

}
