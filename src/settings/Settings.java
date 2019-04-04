package settings;

public class DescGenSettings {

	//1.Conventions
	public static final boolean USE_LOWERCASE_LETTER = true;
	public static final String AWAITING_POSITION_VALUE = "noValueYet";
	public static final int COMPONENT_AUTO_POSITIONING = 0;
	public static final String NO_POSITION_INFORMATION = "";
	public static final boolean FULL_STRING_GROUP = true;
	public static final String CONVENTIONAL_POSITION_FOR_FULL_STRING_GROUP = "1";
	public static final String IRRELEVANT_VALUE = "irrelevant";
	public static final String SECOND_DEGREE_ENUMERATION_SEPARATOR = ":";
	public static final boolean LIST_OF_GROUPS_COVER_THE_FULL_STRING = true;	
	public static final boolean REDUNDANCIES_IN_RELATIONS_CAN_BE_CLEANED = false;
	public static final boolean TAKE_SUBCOMP_INTO_ACCOUNT_IF_CHARSTRING_HAS_MONOSTRUCTURE = true;
	
	//2.Parameters preventing combinatorial explosion
	public static final int MAX_NB_OF_CHARS_IN_STRING = 10;
	public static final int MAX_NB_OF_UNRELATED_GROUPS = 4;
	public static final int MAX_NB_OF_GROUPS_IN_RELATIONS = 10; //error if >12
	public static final int MAX_NB_OF_DIMENSIONS_IN_RELATIONS = 10; //error if >10
	public static final int MAX_NB_OF_RELATION = 10; //error if >10
	public static final int MAX_NB_OF_TRANSFORMATION_RELATIONS = 2; //error if >5
	public static final int MAX_NB_OF_NON_CONSTANT_SEQUENCES = 2;
	public static final int MAX_NB_OF_SIMPLE_ENUMERATIONS_IN_RELATION = 1;
	public static final int MAX_NB_OF_SIZE1_COMPONENTS = 2;
	public static final int MAX_NB_OF_ADJACENT_SIZE1_COMPONENTS = 1;
	public static final boolean GEN2SIZE1_ENUMERATION_ALLOWED_FOR_THE_WORD_ENDS = true; 
	public static final boolean GEN2SIZE1_ENUMERATION_ALLOWED_FOR_ALL_LETTERS = true;
	public static final boolean GEN2SIZE1_SEQUENCE_ALLOWED_FOR_FIRST_LETTER = true; 
	public static final boolean GEN2SIZE1_RESTRICTED_SEQUENCE_ALLOWED_FOR_CENTRAL_LETTER = true; 
	public static final boolean GEN2SIZE1_SEQUENCE_ALLOWED_FOR_LAST_LETTER = true; 
	public static final boolean GEN2SIZE1_SEQUENCE_ALLOWED_FOR_ALL_LETTERS = false;
	public static final int GEN2SIZE1_SEQUENCE_MIN_INCREMENT = -1;
	public static final int GEN2SIZE1_SEQUENCE_MAX_INCREMENT = 1;
	public static final int GEN2SIZE1_RESTRICTED_SEQUENCE_MIN_INCREMENT = 0;
	public static final int GEN2SIZE1_RESTRICTED_SEQUENCE_MAX_INCREMENT = 1;
	public static final int MAX_NB_OF_DESCRIPTOR_GENERATIONS = 3; //error if >5
	public static final int MAX_NB_OF_COMPONENTS_FOR_2ND_DEGREE_ENUMERATION = 4;
	public static final int MAX_INCREMENT_ABS_VALUE = 3;

}
