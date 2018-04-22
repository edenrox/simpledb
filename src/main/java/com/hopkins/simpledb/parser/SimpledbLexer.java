// Generated from C:/Users/ian_000/IdeaProjects/simpledb/src/main/antlr4/com/hopkins/simpledb/parser\Simpledb.g4 by ANTLR 4.7
package com.hopkins.simpledb.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SimpledbLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		K_AS=18, K_AND=19, K_ASC=20, K_BY=21, K_CREATE=22, K_DELETE=23, K_DESC=24, 
		K_DROP=25, K_FROM=26, K_GROUP=27, K_JOIN=28, K_INDEX=29, K_INNER=30, K_INSERT=31, 
		K_INTO=32, K_LIMIT=33, K_NULL=34, K_OFFSET=35, K_ON=36, K_OR=37, K_ORDER=38, 
		K_SELECT=39, K_SET=40, K_TABLE=41, K_UPDATE=42, K_VALUES=43, K_VIEW=44, 
		K_WHERE=45, IDENTIFIER=46, NUMERIC_LITERAL=47, STRING_LITERAL=48, SPACES=49, 
		UNEXPECTED_CHAR=50;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"K_AS", "K_AND", "K_ASC", "K_BY", "K_CREATE", "K_DELETE", "K_DESC", "K_DROP", 
		"K_FROM", "K_GROUP", "K_JOIN", "K_INDEX", "K_INNER", "K_INSERT", "K_INTO", 
		"K_LIMIT", "K_NULL", "K_OFFSET", "K_ON", "K_OR", "K_ORDER", "K_SELECT", 
		"K_SET", "K_TABLE", "K_UPDATE", "K_VALUES", "K_VIEW", "K_WHERE", "IDENTIFIER", 
		"NUMERIC_LITERAL", "STRING_LITERAL", "SPACES", "UNEXPECTED_CHAR", "DIGIT", 
		"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", 
		"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "','", "')'", "'='", "'*'", "'.'", "'/'", "'%'", "'+'", "'-'", 
		"'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'<>'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, "K_AS", "K_AND", "K_ASC", "K_BY", 
		"K_CREATE", "K_DELETE", "K_DESC", "K_DROP", "K_FROM", "K_GROUP", "K_JOIN", 
		"K_INDEX", "K_INNER", "K_INSERT", "K_INTO", "K_LIMIT", "K_NULL", "K_OFFSET", 
		"K_ON", "K_OR", "K_ORDER", "K_SELECT", "K_SET", "K_TABLE", "K_UPDATE", 
		"K_VALUES", "K_VIEW", "K_WHERE", "IDENTIFIER", "NUMERIC_LITERAL", "STRING_LITERAL", 
		"SPACES", "UNEXPECTED_CHAR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public SimpledbLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Simpledb.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\64\u01d4\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4"+
		"I\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6"+
		"\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31"+
		"\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3!"+
		"\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$"+
		"\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3)"+
		"\3)\3)\3)\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,"+
		"\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3/\3/\7/\u015d\n/\f/\16/\u0160\13/\3"+
		"\60\6\60\u0163\n\60\r\60\16\60\u0164\3\60\3\60\7\60\u0169\n\60\f\60\16"+
		"\60\u016c\13\60\5\60\u016e\n\60\3\60\3\60\5\60\u0172\n\60\3\60\6\60\u0175"+
		"\n\60\r\60\16\60\u0176\5\60\u0179\n\60\3\60\3\60\6\60\u017d\n\60\r\60"+
		"\16\60\u017e\3\60\3\60\5\60\u0183\n\60\3\60\6\60\u0186\n\60\r\60\16\60"+
		"\u0187\5\60\u018a\n\60\5\60\u018c\n\60\3\61\3\61\3\61\3\61\7\61\u0192"+
		"\n\61\f\61\16\61\u0195\13\61\3\61\3\61\3\62\3\62\3\62\3\62\3\63\3\63\3"+
		"\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3"+
		"=\3=\3>\3>\3?\3?\3@\3@\3A\3A\3B\3B\3C\3C\3D\3D\3E\3E\3F\3F\3G\3G\3H\3"+
		"H\3I\3I\3J\3J\3K\3K\3L\3L\3M\3M\3N\3N\2\2O\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+"+
		"U,W-Y.[/]\60_\61a\62c\63e\64g\2i\2k\2m\2o\2q\2s\2u\2w\2y\2{\2}\2\177\2"+
		"\u0081\2\u0083\2\u0085\2\u0087\2\u0089\2\u008b\2\u008d\2\u008f\2\u0091"+
		"\2\u0093\2\u0095\2\u0097\2\u0099\2\u009b\2\3\2\"\5\2C\\aac|\6\2\62;C\\"+
		"aac|\4\2--//\3\2))\5\2\13\r\17\17\"\"\3\2\62;\4\2CCcc\4\2DDdd\4\2EEee"+
		"\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2N"+
		"Nnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4"+
		"\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\2\u01c6\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2"+
		"\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2"+
		"\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2"+
		"K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3"+
		"\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2"+
		"\2\2e\3\2\2\2\3\u009d\3\2\2\2\5\u009f\3\2\2\2\7\u00a1\3\2\2\2\t\u00a3"+
		"\3\2\2\2\13\u00a5\3\2\2\2\r\u00a7\3\2\2\2\17\u00a9\3\2\2\2\21\u00ab\3"+
		"\2\2\2\23\u00ad\3\2\2\2\25\u00af\3\2\2\2\27\u00b1\3\2\2\2\31\u00b3\3\2"+
		"\2\2\33\u00b6\3\2\2\2\35\u00b8\3\2\2\2\37\u00bb\3\2\2\2!\u00be\3\2\2\2"+
		"#\u00c1\3\2\2\2%\u00c4\3\2\2\2\'\u00c7\3\2\2\2)\u00cb\3\2\2\2+\u00cf\3"+
		"\2\2\2-\u00d2\3\2\2\2/\u00d9\3\2\2\2\61\u00e0\3\2\2\2\63\u00e5\3\2\2\2"+
		"\65\u00ea\3\2\2\2\67\u00ef\3\2\2\29\u00f5\3\2\2\2;\u00fa\3\2\2\2=\u0100"+
		"\3\2\2\2?\u0106\3\2\2\2A\u010d\3\2\2\2C\u0112\3\2\2\2E\u0118\3\2\2\2G"+
		"\u011d\3\2\2\2I\u0124\3\2\2\2K\u0127\3\2\2\2M\u012a\3\2\2\2O\u0130\3\2"+
		"\2\2Q\u0137\3\2\2\2S\u013b\3\2\2\2U\u0141\3\2\2\2W\u0148\3\2\2\2Y\u014f"+
		"\3\2\2\2[\u0154\3\2\2\2]\u015a\3\2\2\2_\u018b\3\2\2\2a\u018d\3\2\2\2c"+
		"\u0198\3\2\2\2e\u019c\3\2\2\2g\u019e\3\2\2\2i\u01a0\3\2\2\2k\u01a2\3\2"+
		"\2\2m\u01a4\3\2\2\2o\u01a6\3\2\2\2q\u01a8\3\2\2\2s\u01aa\3\2\2\2u\u01ac"+
		"\3\2\2\2w\u01ae\3\2\2\2y\u01b0\3\2\2\2{\u01b2\3\2\2\2}\u01b4\3\2\2\2\177"+
		"\u01b6\3\2\2\2\u0081\u01b8\3\2\2\2\u0083\u01ba\3\2\2\2\u0085\u01bc\3\2"+
		"\2\2\u0087\u01be\3\2\2\2\u0089\u01c0\3\2\2\2\u008b\u01c2\3\2\2\2\u008d"+
		"\u01c4\3\2\2\2\u008f\u01c6\3\2\2\2\u0091\u01c8\3\2\2\2\u0093\u01ca\3\2"+
		"\2\2\u0095\u01cc\3\2\2\2\u0097\u01ce\3\2\2\2\u0099\u01d0\3\2\2\2\u009b"+
		"\u01d2\3\2\2\2\u009d\u009e\7*\2\2\u009e\4\3\2\2\2\u009f\u00a0\7.\2\2\u00a0"+
		"\6\3\2\2\2\u00a1\u00a2\7+\2\2\u00a2\b\3\2\2\2\u00a3\u00a4\7?\2\2\u00a4"+
		"\n\3\2\2\2\u00a5\u00a6\7,\2\2\u00a6\f\3\2\2\2\u00a7\u00a8\7\60\2\2\u00a8"+
		"\16\3\2\2\2\u00a9\u00aa\7\61\2\2\u00aa\20\3\2\2\2\u00ab\u00ac\7\'\2\2"+
		"\u00ac\22\3\2\2\2\u00ad\u00ae\7-\2\2\u00ae\24\3\2\2\2\u00af\u00b0\7/\2"+
		"\2\u00b0\26\3\2\2\2\u00b1\u00b2\7>\2\2\u00b2\30\3\2\2\2\u00b3\u00b4\7"+
		">\2\2\u00b4\u00b5\7?\2\2\u00b5\32\3\2\2\2\u00b6\u00b7\7@\2\2\u00b7\34"+
		"\3\2\2\2\u00b8\u00b9\7@\2\2\u00b9\u00ba\7?\2\2\u00ba\36\3\2\2\2\u00bb"+
		"\u00bc\7?\2\2\u00bc\u00bd\7?\2\2\u00bd \3\2\2\2\u00be\u00bf\7#\2\2\u00bf"+
		"\u00c0\7?\2\2\u00c0\"\3\2\2\2\u00c1\u00c2\7>\2\2\u00c2\u00c3\7@\2\2\u00c3"+
		"$\3\2\2\2\u00c4\u00c5\5i\65\2\u00c5\u00c6\5\u008dG\2\u00c6&\3\2\2\2\u00c7"+
		"\u00c8\5i\65\2\u00c8\u00c9\5\u0083B\2\u00c9\u00ca\5o8\2\u00ca(\3\2\2\2"+
		"\u00cb\u00cc\5i\65\2\u00cc\u00cd\5\u008dG\2\u00cd\u00ce\5m\67\2\u00ce"+
		"*\3\2\2\2\u00cf\u00d0\5k\66\2\u00d0\u00d1\5\u0099M\2\u00d1,\3\2\2\2\u00d2"+
		"\u00d3\5m\67\2\u00d3\u00d4\5\u008bF\2\u00d4\u00d5\5q9\2\u00d5\u00d6\5"+
		"i\65\2\u00d6\u00d7\5\u008fH\2\u00d7\u00d8\5q9\2\u00d8.\3\2\2\2\u00d9\u00da"+
		"\5o8\2\u00da\u00db\5q9\2\u00db\u00dc\5\177@\2\u00dc\u00dd\5q9\2\u00dd"+
		"\u00de\5\u008fH\2\u00de\u00df\5q9\2\u00df\60\3\2\2\2\u00e0\u00e1\5o8\2"+
		"\u00e1\u00e2\5q9\2\u00e2\u00e3\5\u008dG\2\u00e3\u00e4\5m\67\2\u00e4\62"+
		"\3\2\2\2\u00e5\u00e6\5o8\2\u00e6\u00e7\5\u008bF\2\u00e7\u00e8\5\u0085"+
		"C\2\u00e8\u00e9\5\u0087D\2\u00e9\64\3\2\2\2\u00ea\u00eb\5s:\2\u00eb\u00ec"+
		"\5\u008bF\2\u00ec\u00ed\5\u0085C\2\u00ed\u00ee\5\u0081A\2\u00ee\66\3\2"+
		"\2\2\u00ef\u00f0\5u;\2\u00f0\u00f1\5\u008bF\2\u00f1\u00f2\5\u0085C\2\u00f2"+
		"\u00f3\5\u0091I\2\u00f3\u00f4\5\u0087D\2\u00f48\3\2\2\2\u00f5\u00f6\5"+
		"{>\2\u00f6\u00f7\5\u0085C\2\u00f7\u00f8\5y=\2\u00f8\u00f9\5\u0083B\2\u00f9"+
		":\3\2\2\2\u00fa\u00fb\5y=\2\u00fb\u00fc\5\u0083B\2\u00fc\u00fd\5o8\2\u00fd"+
		"\u00fe\5q9\2\u00fe\u00ff\5\u0097L\2\u00ff<\3\2\2\2\u0100\u0101\5y=\2\u0101"+
		"\u0102\5\u0083B\2\u0102\u0103\5\u0083B\2\u0103\u0104\5q9\2\u0104\u0105"+
		"\5\u008bF\2\u0105>\3\2\2\2\u0106\u0107\5y=\2\u0107\u0108\5\u0083B\2\u0108"+
		"\u0109\5\u008dG\2\u0109\u010a\5q9\2\u010a\u010b\5\u008bF\2\u010b\u010c"+
		"\5\u008fH\2\u010c@\3\2\2\2\u010d\u010e\5y=\2\u010e\u010f\5\u0083B\2\u010f"+
		"\u0110\5\u008fH\2\u0110\u0111\5\u0085C\2\u0111B\3\2\2\2\u0112\u0113\5"+
		"\177@\2\u0113\u0114\5y=\2\u0114\u0115\5\u0081A\2\u0115\u0116\5y=\2\u0116"+
		"\u0117\5\u008fH\2\u0117D\3\2\2\2\u0118\u0119\5\u0083B\2\u0119\u011a\5"+
		"\u0091I\2\u011a\u011b\5\177@\2\u011b\u011c\5\177@\2\u011cF\3\2\2\2\u011d"+
		"\u011e\5\u0085C\2\u011e\u011f\5s:\2\u011f\u0120\5s:\2\u0120\u0121\5\u008d"+
		"G\2\u0121\u0122\5q9\2\u0122\u0123\5\u008fH\2\u0123H\3\2\2\2\u0124\u0125"+
		"\5\u0085C\2\u0125\u0126\5\u0083B\2\u0126J\3\2\2\2\u0127\u0128\5\u0085"+
		"C\2\u0128\u0129\5\u008bF\2\u0129L\3\2\2\2\u012a\u012b\5\u0085C\2\u012b"+
		"\u012c\5\u008bF\2\u012c\u012d\5o8\2\u012d\u012e\5q9\2\u012e\u012f\5\u008b"+
		"F\2\u012fN\3\2\2\2\u0130\u0131\5\u008dG\2\u0131\u0132\5q9\2\u0132\u0133"+
		"\5\177@\2\u0133\u0134\5q9\2\u0134\u0135\5m\67\2\u0135\u0136\5\u008fH\2"+
		"\u0136P\3\2\2\2\u0137\u0138\5\u008dG\2\u0138\u0139\5q9\2\u0139\u013a\5"+
		"\u008fH\2\u013aR\3\2\2\2\u013b\u013c\5\u008fH\2\u013c\u013d\5i\65\2\u013d"+
		"\u013e\5k\66\2\u013e\u013f\5\177@\2\u013f\u0140\5q9\2\u0140T\3\2\2\2\u0141"+
		"\u0142\5\u0091I\2\u0142\u0143\5\u0087D\2\u0143\u0144\5o8\2\u0144\u0145"+
		"\5i\65\2\u0145\u0146\5\u008fH\2\u0146\u0147\5q9\2\u0147V\3\2\2\2\u0148"+
		"\u0149\5\u0093J\2\u0149\u014a\5i\65\2\u014a\u014b\5\177@\2\u014b\u014c"+
		"\5\u0091I\2\u014c\u014d\5q9\2\u014d\u014e\5\u008dG\2\u014eX\3\2\2\2\u014f"+
		"\u0150\5\u0093J\2\u0150\u0151\5y=\2\u0151\u0152\5q9\2\u0152\u0153\5\u0095"+
		"K\2\u0153Z\3\2\2\2\u0154\u0155\5\u0095K\2\u0155\u0156\5w<\2\u0156\u0157"+
		"\5q9\2\u0157\u0158\5\u008bF\2\u0158\u0159\5q9\2\u0159\\\3\2\2\2\u015a"+
		"\u015e\t\2\2\2\u015b\u015d\t\3\2\2\u015c\u015b\3\2\2\2\u015d\u0160\3\2"+
		"\2\2\u015e\u015c\3\2\2\2\u015e\u015f\3\2\2\2\u015f^\3\2\2\2\u0160\u015e"+
		"\3\2\2\2\u0161\u0163\5g\64\2\u0162\u0161\3\2\2\2\u0163\u0164\3\2\2\2\u0164"+
		"\u0162\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u016d\3\2\2\2\u0166\u016a\7\60"+
		"\2\2\u0167\u0169\5g\64\2\u0168\u0167\3\2\2\2\u0169\u016c\3\2\2\2\u016a"+
		"\u0168\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u016e\3\2\2\2\u016c\u016a\3\2"+
		"\2\2\u016d\u0166\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u0178\3\2\2\2\u016f"+
		"\u0171\5q9\2\u0170\u0172\t\4\2\2\u0171\u0170\3\2\2\2\u0171\u0172\3\2\2"+
		"\2\u0172\u0174\3\2\2\2\u0173\u0175\5g\64\2\u0174\u0173\3\2\2\2\u0175\u0176"+
		"\3\2\2\2\u0176\u0174\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0179\3\2\2\2\u0178"+
		"\u016f\3\2\2\2\u0178\u0179\3\2\2\2\u0179\u018c\3\2\2\2\u017a\u017c\7\60"+
		"\2\2\u017b\u017d\5g\64\2\u017c\u017b\3\2\2\2\u017d\u017e\3\2\2\2\u017e"+
		"\u017c\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u0189\3\2\2\2\u0180\u0182\5q"+
		"9\2\u0181\u0183\t\4\2\2\u0182\u0181\3\2\2\2\u0182\u0183\3\2\2\2\u0183"+
		"\u0185\3\2\2\2\u0184\u0186\5g\64\2\u0185\u0184\3\2\2\2\u0186\u0187\3\2"+
		"\2\2\u0187\u0185\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u018a\3\2\2\2\u0189"+
		"\u0180\3\2\2\2\u0189\u018a\3\2\2\2\u018a\u018c\3\2\2\2\u018b\u0162\3\2"+
		"\2\2\u018b\u017a\3\2\2\2\u018c`\3\2\2\2\u018d\u0193\7)\2\2\u018e\u0192"+
		"\n\5\2\2\u018f\u0190\7)\2\2\u0190\u0192\7)\2\2\u0191\u018e\3\2\2\2\u0191"+
		"\u018f\3\2\2\2\u0192\u0195\3\2\2\2\u0193\u0191\3\2\2\2\u0193\u0194\3\2"+
		"\2\2\u0194\u0196\3\2\2\2\u0195\u0193\3\2\2\2\u0196\u0197\7)\2\2\u0197"+
		"b\3\2\2\2\u0198\u0199\t\6\2\2\u0199\u019a\3\2\2\2\u019a\u019b\b\62\2\2"+
		"\u019bd\3\2\2\2\u019c\u019d\13\2\2\2\u019df\3\2\2\2\u019e\u019f\t\7\2"+
		"\2\u019fh\3\2\2\2\u01a0\u01a1\t\b\2\2\u01a1j\3\2\2\2\u01a2\u01a3\t\t\2"+
		"\2\u01a3l\3\2\2\2\u01a4\u01a5\t\n\2\2\u01a5n\3\2\2\2\u01a6\u01a7\t\13"+
		"\2\2\u01a7p\3\2\2\2\u01a8\u01a9\t\f\2\2\u01a9r\3\2\2\2\u01aa\u01ab\t\r"+
		"\2\2\u01abt\3\2\2\2\u01ac\u01ad\t\16\2\2\u01adv\3\2\2\2\u01ae\u01af\t"+
		"\17\2\2\u01afx\3\2\2\2\u01b0\u01b1\t\20\2\2\u01b1z\3\2\2\2\u01b2\u01b3"+
		"\t\21\2\2\u01b3|\3\2\2\2\u01b4\u01b5\t\22\2\2\u01b5~\3\2\2\2\u01b6\u01b7"+
		"\t\23\2\2\u01b7\u0080\3\2\2\2\u01b8\u01b9\t\24\2\2\u01b9\u0082\3\2\2\2"+
		"\u01ba\u01bb\t\25\2\2\u01bb\u0084\3\2\2\2\u01bc\u01bd\t\26\2\2\u01bd\u0086"+
		"\3\2\2\2\u01be\u01bf\t\27\2\2\u01bf\u0088\3\2\2\2\u01c0\u01c1\t\30\2\2"+
		"\u01c1\u008a\3\2\2\2\u01c2\u01c3\t\31\2\2\u01c3\u008c\3\2\2\2\u01c4\u01c5"+
		"\t\32\2\2\u01c5\u008e\3\2\2\2\u01c6\u01c7\t\33\2\2\u01c7\u0090\3\2\2\2"+
		"\u01c8\u01c9\t\34\2\2\u01c9\u0092\3\2\2\2\u01ca\u01cb\t\35\2\2\u01cb\u0094"+
		"\3\2\2\2\u01cc\u01cd\t\36\2\2\u01cd\u0096\3\2\2\2\u01ce\u01cf\t\37\2\2"+
		"\u01cf\u0098\3\2\2\2\u01d0\u01d1\t \2\2\u01d1\u009a\3\2\2\2\u01d2\u01d3"+
		"\t!\2\2\u01d3\u009c\3\2\2\2\21\2\u015e\u0164\u016a\u016d\u0171\u0176\u0178"+
		"\u017e\u0182\u0187\u0189\u018b\u0191\u0193\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}