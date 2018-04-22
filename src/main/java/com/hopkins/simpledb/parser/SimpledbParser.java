// Generated from C:/Users/ian_000/IdeaProjects/simpledb/src/main/antlr4/com/hopkins/simpledb/parser\Simpledb.g4 by ANTLR 4.7
package com.hopkins.simpledb.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SimpledbParser extends Parser {
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
	public static final int
		RULE_parse = 0, RULE_error = 1, RULE_sql_stmt = 2, RULE_create_index_stmt = 3, 
		RULE_create_table_stmt = 4, RULE_create_view_stmt = 5, RULE_drop_index_stmt = 6, 
		RULE_drop_table_stmt = 7, RULE_drop_view_stmt = 8, RULE_delete_stmt = 9, 
		RULE_insert_stmt = 10, RULE_update_stmt = 11, RULE_join_clause = 12, RULE_join_operator = 13, 
		RULE_select_stmt = 14, RULE_ordering_term = 15, RULE_result_column = 16, 
		RULE_column_def = 17, RULE_type_name = 18, RULE_expr = 19, RULE_indexed_column = 20, 
		RULE_literal_value = 21, RULE_keyword = 22, RULE_function_name = 23, RULE_table_name = 24, 
		RULE_column_name = 25, RULE_index_name = 26, RULE_view_name = 27, RULE_any_name = 28;
	public static final String[] ruleNames = {
		"parse", "error", "sql_stmt", "create_index_stmt", "create_table_stmt", 
		"create_view_stmt", "drop_index_stmt", "drop_table_stmt", "drop_view_stmt", 
		"delete_stmt", "insert_stmt", "update_stmt", "join_clause", "join_operator", 
		"select_stmt", "ordering_term", "result_column", "column_def", "type_name", 
		"expr", "indexed_column", "literal_value", "keyword", "function_name", 
		"table_name", "column_name", "index_name", "view_name", "any_name"
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

	@Override
	public String getGrammarFileName() { return "Simpledb.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SimpledbParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ParseContext extends ParserRuleContext {
		public Sql_stmtContext sql_stmt() {
			return getRuleContext(Sql_stmtContext.class,0);
		}
		public TerminalNode EOF() { return getToken(SimpledbParser.EOF, 0); }
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitParse(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			sql_stmt();
			setState(59);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ErrorContext extends ParserRuleContext {
		public Token UNEXPECTED_CHAR;
		public TerminalNode UNEXPECTED_CHAR() { return getToken(SimpledbParser.UNEXPECTED_CHAR, 0); }
		public ErrorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_error; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitError(this);
		}
	}

	public final ErrorContext error() throws RecognitionException {
		ErrorContext _localctx = new ErrorContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_error);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			((ErrorContext)_localctx).UNEXPECTED_CHAR = match(UNEXPECTED_CHAR);

			     throw new RuntimeException("UNEXPECTED_CHAR=" + (((ErrorContext)_localctx).UNEXPECTED_CHAR!=null?((ErrorContext)_localctx).UNEXPECTED_CHAR.getText():null));
			   
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sql_stmtContext extends ParserRuleContext {
		public Create_index_stmtContext create_index_stmt() {
			return getRuleContext(Create_index_stmtContext.class,0);
		}
		public Create_table_stmtContext create_table_stmt() {
			return getRuleContext(Create_table_stmtContext.class,0);
		}
		public Create_view_stmtContext create_view_stmt() {
			return getRuleContext(Create_view_stmtContext.class,0);
		}
		public Delete_stmtContext delete_stmt() {
			return getRuleContext(Delete_stmtContext.class,0);
		}
		public Drop_index_stmtContext drop_index_stmt() {
			return getRuleContext(Drop_index_stmtContext.class,0);
		}
		public Drop_table_stmtContext drop_table_stmt() {
			return getRuleContext(Drop_table_stmtContext.class,0);
		}
		public Drop_view_stmtContext drop_view_stmt() {
			return getRuleContext(Drop_view_stmtContext.class,0);
		}
		public Insert_stmtContext insert_stmt() {
			return getRuleContext(Insert_stmtContext.class,0);
		}
		public Select_stmtContext select_stmt() {
			return getRuleContext(Select_stmtContext.class,0);
		}
		public Update_stmtContext update_stmt() {
			return getRuleContext(Update_stmtContext.class,0);
		}
		public Sql_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sql_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterSql_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitSql_stmt(this);
		}
	}

	public final Sql_stmtContext sql_stmt() throws RecognitionException {
		Sql_stmtContext _localctx = new Sql_stmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sql_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(64);
				create_index_stmt();
				}
				break;
			case 2:
				{
				setState(65);
				create_table_stmt();
				}
				break;
			case 3:
				{
				setState(66);
				create_view_stmt();
				}
				break;
			case 4:
				{
				setState(67);
				delete_stmt();
				}
				break;
			case 5:
				{
				setState(68);
				drop_index_stmt();
				}
				break;
			case 6:
				{
				setState(69);
				drop_table_stmt();
				}
				break;
			case 7:
				{
				setState(70);
				drop_view_stmt();
				}
				break;
			case 8:
				{
				setState(71);
				insert_stmt();
				}
				break;
			case 9:
				{
				setState(72);
				select_stmt();
				}
				break;
			case 10:
				{
				setState(73);
				update_stmt();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Create_index_stmtContext extends ParserRuleContext {
		public TerminalNode K_CREATE() { return getToken(SimpledbParser.K_CREATE, 0); }
		public TerminalNode K_INDEX() { return getToken(SimpledbParser.K_INDEX, 0); }
		public Index_nameContext index_name() {
			return getRuleContext(Index_nameContext.class,0);
		}
		public TerminalNode K_ON() { return getToken(SimpledbParser.K_ON, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public List<Indexed_columnContext> indexed_column() {
			return getRuleContexts(Indexed_columnContext.class);
		}
		public Indexed_columnContext indexed_column(int i) {
			return getRuleContext(Indexed_columnContext.class,i);
		}
		public Create_index_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_index_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterCreate_index_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitCreate_index_stmt(this);
		}
	}

	public final Create_index_stmtContext create_index_stmt() throws RecognitionException {
		Create_index_stmtContext _localctx = new Create_index_stmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_create_index_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(K_CREATE);
			setState(77);
			match(K_INDEX);
			setState(78);
			index_name();
			setState(79);
			match(K_ON);
			setState(80);
			table_name();
			setState(81);
			match(T__0);
			setState(82);
			indexed_column();
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(83);
				match(T__1);
				setState(84);
				indexed_column();
				}
				}
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(90);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Create_table_stmtContext extends ParserRuleContext {
		public TerminalNode K_CREATE() { return getToken(SimpledbParser.K_CREATE, 0); }
		public TerminalNode K_TABLE() { return getToken(SimpledbParser.K_TABLE, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public List<Column_defContext> column_def() {
			return getRuleContexts(Column_defContext.class);
		}
		public Column_defContext column_def(int i) {
			return getRuleContext(Column_defContext.class,i);
		}
		public Create_table_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_table_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterCreate_table_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitCreate_table_stmt(this);
		}
	}

	public final Create_table_stmtContext create_table_stmt() throws RecognitionException {
		Create_table_stmtContext _localctx = new Create_table_stmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_create_table_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(K_CREATE);
			setState(93);
			match(K_TABLE);
			setState(94);
			table_name();
			setState(95);
			match(T__0);
			setState(96);
			column_def();
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(97);
				match(T__1);
				setState(98);
				column_def();
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(104);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Create_view_stmtContext extends ParserRuleContext {
		public TerminalNode K_CREATE() { return getToken(SimpledbParser.K_CREATE, 0); }
		public TerminalNode K_VIEW() { return getToken(SimpledbParser.K_VIEW, 0); }
		public View_nameContext view_name() {
			return getRuleContext(View_nameContext.class,0);
		}
		public TerminalNode K_AS() { return getToken(SimpledbParser.K_AS, 0); }
		public Select_stmtContext select_stmt() {
			return getRuleContext(Select_stmtContext.class,0);
		}
		public Create_view_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_view_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterCreate_view_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitCreate_view_stmt(this);
		}
	}

	public final Create_view_stmtContext create_view_stmt() throws RecognitionException {
		Create_view_stmtContext _localctx = new Create_view_stmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_create_view_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(K_CREATE);
			setState(107);
			match(K_VIEW);
			setState(108);
			view_name();
			setState(109);
			match(K_AS);
			setState(110);
			select_stmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Drop_index_stmtContext extends ParserRuleContext {
		public TerminalNode K_DROP() { return getToken(SimpledbParser.K_DROP, 0); }
		public TerminalNode K_INDEX() { return getToken(SimpledbParser.K_INDEX, 0); }
		public Index_nameContext index_name() {
			return getRuleContext(Index_nameContext.class,0);
		}
		public Drop_index_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drop_index_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterDrop_index_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitDrop_index_stmt(this);
		}
	}

	public final Drop_index_stmtContext drop_index_stmt() throws RecognitionException {
		Drop_index_stmtContext _localctx = new Drop_index_stmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_drop_index_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(K_DROP);
			setState(113);
			match(K_INDEX);
			setState(114);
			index_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Drop_table_stmtContext extends ParserRuleContext {
		public TerminalNode K_DROP() { return getToken(SimpledbParser.K_DROP, 0); }
		public TerminalNode K_TABLE() { return getToken(SimpledbParser.K_TABLE, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Drop_table_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drop_table_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterDrop_table_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitDrop_table_stmt(this);
		}
	}

	public final Drop_table_stmtContext drop_table_stmt() throws RecognitionException {
		Drop_table_stmtContext _localctx = new Drop_table_stmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_drop_table_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(K_DROP);
			setState(117);
			match(K_TABLE);
			setState(118);
			table_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Drop_view_stmtContext extends ParserRuleContext {
		public TerminalNode K_DROP() { return getToken(SimpledbParser.K_DROP, 0); }
		public TerminalNode K_VIEW() { return getToken(SimpledbParser.K_VIEW, 0); }
		public View_nameContext view_name() {
			return getRuleContext(View_nameContext.class,0);
		}
		public Drop_view_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drop_view_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterDrop_view_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitDrop_view_stmt(this);
		}
	}

	public final Drop_view_stmtContext drop_view_stmt() throws RecognitionException {
		Drop_view_stmtContext _localctx = new Drop_view_stmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_drop_view_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(K_DROP);
			setState(121);
			match(K_VIEW);
			setState(122);
			view_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Delete_stmtContext extends ParserRuleContext {
		public TerminalNode K_DELETE() { return getToken(SimpledbParser.K_DELETE, 0); }
		public TerminalNode K_FROM() { return getToken(SimpledbParser.K_FROM, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(SimpledbParser.K_WHERE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Delete_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterDelete_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitDelete_stmt(this);
		}
	}

	public final Delete_stmtContext delete_stmt() throws RecognitionException {
		Delete_stmtContext _localctx = new Delete_stmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_delete_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(K_DELETE);
			setState(125);
			match(K_FROM);
			setState(126);
			table_name();
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(127);
				match(K_WHERE);
				setState(128);
				expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Insert_stmtContext extends ParserRuleContext {
		public TerminalNode K_INSERT() { return getToken(SimpledbParser.K_INSERT, 0); }
		public TerminalNode K_INTO() { return getToken(SimpledbParser.K_INTO, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode K_VALUES() { return getToken(SimpledbParser.K_VALUES, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public Insert_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insert_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterInsert_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitInsert_stmt(this);
		}
	}

	public final Insert_stmtContext insert_stmt() throws RecognitionException {
		Insert_stmtContext _localctx = new Insert_stmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_insert_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(K_INSERT);
			setState(132);
			match(K_INTO);
			setState(133);
			table_name();
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(134);
				match(T__0);
				setState(135);
				column_name();
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(136);
					match(T__1);
					setState(137);
					column_name();
					}
					}
					setState(142);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(143);
				match(T__2);
				}
			}

			setState(147);
			match(K_VALUES);
			setState(148);
			match(T__0);
			setState(149);
			expr(0);
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(150);
				match(T__1);
				setState(151);
				expr(0);
				}
				}
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(157);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Update_stmtContext extends ParserRuleContext {
		public TerminalNode K_UPDATE() { return getToken(SimpledbParser.K_UPDATE, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode K_SET() { return getToken(SimpledbParser.K_SET, 0); }
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode K_WHERE() { return getToken(SimpledbParser.K_WHERE, 0); }
		public Update_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterUpdate_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitUpdate_stmt(this);
		}
	}

	public final Update_stmtContext update_stmt() throws RecognitionException {
		Update_stmtContext _localctx = new Update_stmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_update_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(K_UPDATE);
			setState(160);
			table_name();
			setState(161);
			match(K_SET);
			setState(162);
			column_name();
			setState(163);
			match(T__3);
			setState(164);
			expr(0);
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(165);
				match(T__1);
				setState(166);
				column_name();
				setState(167);
				match(T__3);
				setState(168);
				expr(0);
				}
				}
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(175);
				match(K_WHERE);
				setState(176);
				expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Join_clauseContext extends ParserRuleContext {
		public List<Table_nameContext> table_name() {
			return getRuleContexts(Table_nameContext.class);
		}
		public Table_nameContext table_name(int i) {
			return getRuleContext(Table_nameContext.class,i);
		}
		public List<Join_operatorContext> join_operator() {
			return getRuleContexts(Join_operatorContext.class);
		}
		public Join_operatorContext join_operator(int i) {
			return getRuleContext(Join_operatorContext.class,i);
		}
		public Join_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterJoin_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitJoin_clause(this);
		}
	}

	public final Join_clauseContext join_clause() throws RecognitionException {
		Join_clauseContext _localctx = new Join_clauseContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_join_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			table_name();
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << K_JOIN) | (1L << K_INNER))) != 0)) {
				{
				{
				setState(180);
				join_operator();
				setState(181);
				table_name();
				}
				}
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Join_operatorContext extends ParserRuleContext {
		public TerminalNode K_JOIN() { return getToken(SimpledbParser.K_JOIN, 0); }
		public TerminalNode K_INNER() { return getToken(SimpledbParser.K_INNER, 0); }
		public Join_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterJoin_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitJoin_operator(this);
		}
	}

	public final Join_operatorContext join_operator() throws RecognitionException {
		Join_operatorContext _localctx = new Join_operatorContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_join_operator);
		int _la;
		try {
			setState(193);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(188);
				match(T__1);
				}
				break;
			case K_JOIN:
			case K_INNER:
				enterOuterAlt(_localctx, 2);
				{
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==K_INNER) {
					{
					setState(189);
					match(K_INNER);
					}
				}

				setState(192);
				match(K_JOIN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_stmtContext extends ParserRuleContext {
		public TerminalNode K_SELECT() { return getToken(SimpledbParser.K_SELECT, 0); }
		public List<Result_columnContext> result_column() {
			return getRuleContexts(Result_columnContext.class);
		}
		public Result_columnContext result_column(int i) {
			return getRuleContext(Result_columnContext.class,i);
		}
		public TerminalNode K_FROM() { return getToken(SimpledbParser.K_FROM, 0); }
		public Join_clauseContext join_clause() {
			return getRuleContext(Join_clauseContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(SimpledbParser.K_WHERE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode K_GROUP() { return getToken(SimpledbParser.K_GROUP, 0); }
		public List<TerminalNode> K_BY() { return getTokens(SimpledbParser.K_BY); }
		public TerminalNode K_BY(int i) {
			return getToken(SimpledbParser.K_BY, i);
		}
		public TerminalNode K_ORDER() { return getToken(SimpledbParser.K_ORDER, 0); }
		public List<Ordering_termContext> ordering_term() {
			return getRuleContexts(Ordering_termContext.class);
		}
		public Ordering_termContext ordering_term(int i) {
			return getRuleContext(Ordering_termContext.class,i);
		}
		public TerminalNode K_LIMIT() { return getToken(SimpledbParser.K_LIMIT, 0); }
		public TerminalNode K_OFFSET() { return getToken(SimpledbParser.K_OFFSET, 0); }
		public Select_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterSelect_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitSelect_stmt(this);
		}
	}

	public final Select_stmtContext select_stmt() throws RecognitionException {
		Select_stmtContext _localctx = new Select_stmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_select_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			match(K_SELECT);
			setState(196);
			result_column();
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(197);
				match(T__1);
				setState(198);
				result_column();
				}
				}
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(204);
			match(K_FROM);
			setState(205);
			join_clause();
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(206);
				match(K_WHERE);
				setState(207);
				expr(0);
				}
			}

			setState(220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_GROUP) {
				{
				setState(210);
				match(K_GROUP);
				setState(211);
				match(K_BY);
				setState(212);
				expr(0);
				setState(217);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(213);
					match(T__1);
					setState(214);
					expr(0);
					}
					}
					setState(219);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_ORDER) {
				{
				setState(222);
				match(K_ORDER);
				setState(223);
				match(K_BY);
				setState(224);
				ordering_term();
				setState(229);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(225);
					match(T__1);
					setState(226);
					ordering_term();
					}
					}
					setState(231);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_LIMIT) {
				{
				setState(234);
				match(K_LIMIT);
				setState(235);
				expr(0);
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1 || _la==K_OFFSET) {
					{
					setState(236);
					_la = _input.LA(1);
					if ( !(_la==T__1 || _la==K_OFFSET) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(237);
					expr(0);
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ordering_termContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode K_ASC() { return getToken(SimpledbParser.K_ASC, 0); }
		public TerminalNode K_DESC() { return getToken(SimpledbParser.K_DESC, 0); }
		public Ordering_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ordering_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterOrdering_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitOrdering_term(this);
		}
	}

	public final Ordering_termContext ordering_term() throws RecognitionException {
		Ordering_termContext _localctx = new Ordering_termContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_ordering_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			expr(0);
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_ASC || _la==K_DESC) {
				{
				setState(243);
				_la = _input.LA(1);
				if ( !(_la==K_ASC || _la==K_DESC) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Result_columnContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Result_columnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_result_column; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterResult_column(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitResult_column(this);
		}
	}

	public final Result_columnContext result_column() throws RecognitionException {
		Result_columnContext _localctx = new Result_columnContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_result_column);
		try {
			setState(252);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(246);
				match(T__4);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(247);
				table_name();
				setState(248);
				match(T__5);
				setState(249);
				match(T__4);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(251);
				expr(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_defContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Type_nameContext type_name() {
			return getRuleContext(Type_nameContext.class,0);
		}
		public Column_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterColumn_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitColumn_def(this);
		}
	}

	public final Column_defContext column_def() throws RecognitionException {
		Column_defContext _localctx = new Column_defContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_column_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			column_name();
			setState(255);
			type_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterType_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitType_name(this);
		}
	}

	public final Type_nameContext type_name() throws RecognitionException {
		Type_nameContext _localctx = new Type_nameContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_type_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public Literal_valueContext literal_value() {
			return getRuleContext(Literal_valueContext.class,0);
		}
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Function_nameContext function_name() {
			return getRuleContext(Function_nameContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode K_AND() { return getToken(SimpledbParser.K_AND, 0); }
		public TerminalNode K_OR() { return getToken(SimpledbParser.K_OR, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(260);
				literal_value();
				}
				break;
			case 2:
				{
				setState(264);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(261);
					table_name();
					setState(262);
					match(T__5);
					}
					break;
				}
				setState(266);
				column_name();
				}
				break;
			case 3:
				{
				setState(267);
				function_name();
				setState(268);
				match(T__0);
				setState(269);
				expr(0);
				setState(270);
				match(T__2);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(294);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(292);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(274);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(275);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__6) | (1L << T__7))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(276);
						expr(8);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(277);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(278);
						_la = _input.LA(1);
						if ( !(_la==T__8 || _la==T__9) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(279);
						expr(7);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(280);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(281);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(282);
						expr(6);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(283);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(284);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__14) | (1L << T__15) | (1L << T__16))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(285);
						expr(5);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(286);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(287);
						match(K_AND);
						setState(288);
						expr(4);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(289);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(290);
						match(K_OR);
						setState(291);
						expr(3);
						}
						break;
					}
					} 
				}
				setState(296);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Indexed_columnContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public TerminalNode K_ASC() { return getToken(SimpledbParser.K_ASC, 0); }
		public TerminalNode K_DESC() { return getToken(SimpledbParser.K_DESC, 0); }
		public Indexed_columnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexed_column; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterIndexed_column(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitIndexed_column(this);
		}
	}

	public final Indexed_columnContext indexed_column() throws RecognitionException {
		Indexed_columnContext _localctx = new Indexed_columnContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_indexed_column);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			column_name();
			setState(299);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_ASC || _la==K_DESC) {
				{
				setState(298);
				_la = _input.LA(1);
				if ( !(_la==K_ASC || _la==K_DESC) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Literal_valueContext extends ParserRuleContext {
		public TerminalNode NUMERIC_LITERAL() { return getToken(SimpledbParser.NUMERIC_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(SimpledbParser.STRING_LITERAL, 0); }
		public TerminalNode K_NULL() { return getToken(SimpledbParser.K_NULL, 0); }
		public Literal_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterLiteral_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitLiteral_value(this);
		}
	}

	public final Literal_valueContext literal_value() throws RecognitionException {
		Literal_valueContext _localctx = new Literal_valueContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_literal_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_NULL) | (1L << NUMERIC_LITERAL) | (1L << STRING_LITERAL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeywordContext extends ParserRuleContext {
		public TerminalNode K_AND() { return getToken(SimpledbParser.K_AND, 0); }
		public TerminalNode K_AS() { return getToken(SimpledbParser.K_AS, 0); }
		public TerminalNode K_ASC() { return getToken(SimpledbParser.K_ASC, 0); }
		public TerminalNode K_BY() { return getToken(SimpledbParser.K_BY, 0); }
		public TerminalNode K_CREATE() { return getToken(SimpledbParser.K_CREATE, 0); }
		public TerminalNode K_DELETE() { return getToken(SimpledbParser.K_DELETE, 0); }
		public TerminalNode K_DESC() { return getToken(SimpledbParser.K_DESC, 0); }
		public TerminalNode K_DROP() { return getToken(SimpledbParser.K_DROP, 0); }
		public TerminalNode K_FROM() { return getToken(SimpledbParser.K_FROM, 0); }
		public TerminalNode K_GROUP() { return getToken(SimpledbParser.K_GROUP, 0); }
		public TerminalNode K_INDEX() { return getToken(SimpledbParser.K_INDEX, 0); }
		public TerminalNode K_INSERT() { return getToken(SimpledbParser.K_INSERT, 0); }
		public TerminalNode K_INTO() { return getToken(SimpledbParser.K_INTO, 0); }
		public TerminalNode K_LIMIT() { return getToken(SimpledbParser.K_LIMIT, 0); }
		public TerminalNode K_NULL() { return getToken(SimpledbParser.K_NULL, 0); }
		public TerminalNode K_OFFSET() { return getToken(SimpledbParser.K_OFFSET, 0); }
		public TerminalNode K_ON() { return getToken(SimpledbParser.K_ON, 0); }
		public TerminalNode K_OR() { return getToken(SimpledbParser.K_OR, 0); }
		public TerminalNode K_ORDER() { return getToken(SimpledbParser.K_ORDER, 0); }
		public TerminalNode K_SELECT() { return getToken(SimpledbParser.K_SELECT, 0); }
		public TerminalNode K_SET() { return getToken(SimpledbParser.K_SET, 0); }
		public TerminalNode K_TABLE() { return getToken(SimpledbParser.K_TABLE, 0); }
		public TerminalNode K_UPDATE() { return getToken(SimpledbParser.K_UPDATE, 0); }
		public TerminalNode K_VALUES() { return getToken(SimpledbParser.K_VALUES, 0); }
		public TerminalNode K_VIEW() { return getToken(SimpledbParser.K_VIEW, 0); }
		public TerminalNode K_WHERE() { return getToken(SimpledbParser.K_WHERE, 0); }
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitKeyword(this);
		}
	}

	public final KeywordContext keyword() throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_AS) | (1L << K_AND) | (1L << K_ASC) | (1L << K_BY) | (1L << K_CREATE) | (1L << K_DELETE) | (1L << K_DESC) | (1L << K_DROP) | (1L << K_FROM) | (1L << K_GROUP) | (1L << K_INDEX) | (1L << K_INSERT) | (1L << K_INTO) | (1L << K_LIMIT) | (1L << K_NULL) | (1L << K_OFFSET) | (1L << K_ON) | (1L << K_OR) | (1L << K_ORDER) | (1L << K_SELECT) | (1L << K_SET) | (1L << K_TABLE) | (1L << K_UPDATE) | (1L << K_VALUES) | (1L << K_VIEW) | (1L << K_WHERE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Function_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterFunction_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitFunction_name(this);
		}
	}

	public final Function_nameContext function_name() throws RecognitionException {
		Function_nameContext _localctx = new Function_nameContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_function_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Table_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterTable_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitTable_name(this);
		}
	}

	public final Table_nameContext table_name() throws RecognitionException {
		Table_nameContext _localctx = new Table_nameContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Column_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterColumn_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitColumn_name(this);
		}
	}

	public final Column_nameContext column_name() throws RecognitionException {
		Column_nameContext _localctx = new Column_nameContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Index_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Index_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterIndex_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitIndex_name(this);
		}
	}

	public final Index_nameContext index_name() throws RecognitionException {
		Index_nameContext _localctx = new Index_nameContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_index_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class View_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public View_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_view_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterView_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitView_name(this);
		}
	}

	public final View_nameContext view_name() throws RecognitionException {
		View_nameContext _localctx = new View_nameContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_view_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Any_nameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SimpledbParser.IDENTIFIER, 0); }
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(SimpledbParser.STRING_LITERAL, 0); }
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Any_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_any_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).enterAny_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpledbListener ) ((SimpledbListener)listener).exitAny_name(this);
		}
	}

	public final Any_nameContext any_name() throws RecognitionException {
		Any_nameContext _localctx = new Any_nameContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_any_name);
		try {
			setState(322);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(315);
				match(IDENTIFIER);
				}
				break;
			case K_AS:
			case K_AND:
			case K_ASC:
			case K_BY:
			case K_CREATE:
			case K_DELETE:
			case K_DESC:
			case K_DROP:
			case K_FROM:
			case K_GROUP:
			case K_INDEX:
			case K_INSERT:
			case K_INTO:
			case K_LIMIT:
			case K_NULL:
			case K_OFFSET:
			case K_ON:
			case K_OR:
			case K_ORDER:
			case K_SELECT:
			case K_SET:
			case K_TABLE:
			case K_UPDATE:
			case K_VALUES:
			case K_VIEW:
			case K_WHERE:
				enterOuterAlt(_localctx, 2);
				{
				setState(316);
				keyword();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(317);
				match(STRING_LITERAL);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 4);
				{
				setState(318);
				match(T__0);
				setState(319);
				any_name();
				setState(320);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 19:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\64\u0147\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4M\n\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\7\5X\n\5\f\5\16\5[\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\7\6f\n\6\f\6\16\6i\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\5\13\u0084\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u008d\n\f\f\f\16\f\u0090"+
		"\13\f\3\f\3\f\5\f\u0094\n\f\3\f\3\f\3\f\3\f\3\f\7\f\u009b\n\f\f\f\16\f"+
		"\u009e\13\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00ad"+
		"\n\r\f\r\16\r\u00b0\13\r\3\r\3\r\5\r\u00b4\n\r\3\16\3\16\3\16\3\16\7\16"+
		"\u00ba\n\16\f\16\16\16\u00bd\13\16\3\17\3\17\5\17\u00c1\n\17\3\17\5\17"+
		"\u00c4\n\17\3\20\3\20\3\20\3\20\7\20\u00ca\n\20\f\20\16\20\u00cd\13\20"+
		"\3\20\3\20\3\20\3\20\5\20\u00d3\n\20\3\20\3\20\3\20\3\20\3\20\7\20\u00da"+
		"\n\20\f\20\16\20\u00dd\13\20\5\20\u00df\n\20\3\20\3\20\3\20\3\20\3\20"+
		"\7\20\u00e6\n\20\f\20\16\20\u00e9\13\20\5\20\u00eb\n\20\3\20\3\20\3\20"+
		"\3\20\5\20\u00f1\n\20\5\20\u00f3\n\20\3\21\3\21\5\21\u00f7\n\21\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\5\22\u00ff\n\22\3\23\3\23\3\23\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\5\25\u010b\n\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25"+
		"\u0113\n\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u0127\n\25\f\25\16\25\u012a\13\25"+
		"\3\26\3\26\5\26\u012e\n\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33"+
		"\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u0145"+
		"\n\36\3\36\2\3(\37\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\668:\2\n\4\2\4\4%%\4\2\26\26\32\32\4\2\7\7\t\n\3\2\13\f\3\2\r\20"+
		"\4\2\6\6\21\23\4\2$$\61\62\5\2\24\35\37\37!/\2\u0155\2<\3\2\2\2\4?\3\2"+
		"\2\2\6L\3\2\2\2\bN\3\2\2\2\n^\3\2\2\2\fl\3\2\2\2\16r\3\2\2\2\20v\3\2\2"+
		"\2\22z\3\2\2\2\24~\3\2\2\2\26\u0085\3\2\2\2\30\u00a1\3\2\2\2\32\u00b5"+
		"\3\2\2\2\34\u00c3\3\2\2\2\36\u00c5\3\2\2\2 \u00f4\3\2\2\2\"\u00fe\3\2"+
		"\2\2$\u0100\3\2\2\2&\u0103\3\2\2\2(\u0112\3\2\2\2*\u012b\3\2\2\2,\u012f"+
		"\3\2\2\2.\u0131\3\2\2\2\60\u0133\3\2\2\2\62\u0135\3\2\2\2\64\u0137\3\2"+
		"\2\2\66\u0139\3\2\2\28\u013b\3\2\2\2:\u0144\3\2\2\2<=\5\6\4\2=>\7\2\2"+
		"\3>\3\3\2\2\2?@\7\64\2\2@A\b\3\1\2A\5\3\2\2\2BM\5\b\5\2CM\5\n\6\2DM\5"+
		"\f\7\2EM\5\24\13\2FM\5\16\b\2GM\5\20\t\2HM\5\22\n\2IM\5\26\f\2JM\5\36"+
		"\20\2KM\5\30\r\2LB\3\2\2\2LC\3\2\2\2LD\3\2\2\2LE\3\2\2\2LF\3\2\2\2LG\3"+
		"\2\2\2LH\3\2\2\2LI\3\2\2\2LJ\3\2\2\2LK\3\2\2\2M\7\3\2\2\2NO\7\30\2\2O"+
		"P\7\37\2\2PQ\5\66\34\2QR\7&\2\2RS\5\62\32\2ST\7\3\2\2TY\5*\26\2UV\7\4"+
		"\2\2VX\5*\26\2WU\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\\\3\2\2\2[Y\3"+
		"\2\2\2\\]\7\5\2\2]\t\3\2\2\2^_\7\30\2\2_`\7+\2\2`a\5\62\32\2ab\7\3\2\2"+
		"bg\5$\23\2cd\7\4\2\2df\5$\23\2ec\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2"+
		"hj\3\2\2\2ig\3\2\2\2jk\7\5\2\2k\13\3\2\2\2lm\7\30\2\2mn\7.\2\2no\58\35"+
		"\2op\7\24\2\2pq\5\36\20\2q\r\3\2\2\2rs\7\33\2\2st\7\37\2\2tu\5\66\34\2"+
		"u\17\3\2\2\2vw\7\33\2\2wx\7+\2\2xy\5\62\32\2y\21\3\2\2\2z{\7\33\2\2{|"+
		"\7.\2\2|}\58\35\2}\23\3\2\2\2~\177\7\31\2\2\177\u0080\7\34\2\2\u0080\u0083"+
		"\5\62\32\2\u0081\u0082\7/\2\2\u0082\u0084\5(\25\2\u0083\u0081\3\2\2\2"+
		"\u0083\u0084\3\2\2\2\u0084\25\3\2\2\2\u0085\u0086\7!\2\2\u0086\u0087\7"+
		"\"\2\2\u0087\u0093\5\62\32\2\u0088\u0089\7\3\2\2\u0089\u008e\5\64\33\2"+
		"\u008a\u008b\7\4\2\2\u008b\u008d\5\64\33\2\u008c\u008a\3\2\2\2\u008d\u0090"+
		"\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0091\3\2\2\2\u0090"+
		"\u008e\3\2\2\2\u0091\u0092\7\5\2\2\u0092\u0094\3\2\2\2\u0093\u0088\3\2"+
		"\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\7-\2\2\u0096"+
		"\u0097\7\3\2\2\u0097\u009c\5(\25\2\u0098\u0099\7\4\2\2\u0099\u009b\5("+
		"\25\2\u009a\u0098\3\2\2\2\u009b\u009e\3\2\2\2\u009c\u009a\3\2\2\2\u009c"+
		"\u009d\3\2\2\2\u009d\u009f\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u00a0\7\5"+
		"\2\2\u00a0\27\3\2\2\2\u00a1\u00a2\7,\2\2\u00a2\u00a3\5\62\32\2\u00a3\u00a4"+
		"\7*\2\2\u00a4\u00a5\5\64\33\2\u00a5\u00a6\7\6\2\2\u00a6\u00ae\5(\25\2"+
		"\u00a7\u00a8\7\4\2\2\u00a8\u00a9\5\64\33\2\u00a9\u00aa\7\6\2\2\u00aa\u00ab"+
		"\5(\25\2\u00ab\u00ad\3\2\2\2\u00ac\u00a7\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae"+
		"\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b3\3\2\2\2\u00b0\u00ae\3\2"+
		"\2\2\u00b1\u00b2\7/\2\2\u00b2\u00b4\5(\25\2\u00b3\u00b1\3\2\2\2\u00b3"+
		"\u00b4\3\2\2\2\u00b4\31\3\2\2\2\u00b5\u00bb\5\62\32\2\u00b6\u00b7\5\34"+
		"\17\2\u00b7\u00b8\5\62\32\2\u00b8\u00ba\3\2\2\2\u00b9\u00b6\3\2\2\2\u00ba"+
		"\u00bd\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\33\3\2\2"+
		"\2\u00bd\u00bb\3\2\2\2\u00be\u00c4\7\4\2\2\u00bf\u00c1\7 \2\2\u00c0\u00bf"+
		"\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c4\7\36\2\2"+
		"\u00c3\u00be\3\2\2\2\u00c3\u00c0\3\2\2\2\u00c4\35\3\2\2\2\u00c5\u00c6"+
		"\7)\2\2\u00c6\u00cb\5\"\22\2\u00c7\u00c8\7\4\2\2\u00c8\u00ca\5\"\22\2"+
		"\u00c9\u00c7\3\2\2\2\u00ca\u00cd\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc"+
		"\3\2\2\2\u00cc\u00ce\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00cf\7\34\2\2"+
		"\u00cf\u00d2\5\32\16\2\u00d0\u00d1\7/\2\2\u00d1\u00d3\5(\25\2\u00d2\u00d0"+
		"\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00de\3\2\2\2\u00d4\u00d5\7\35\2\2"+
		"\u00d5\u00d6\7\27\2\2\u00d6\u00db\5(\25\2\u00d7\u00d8\7\4\2\2\u00d8\u00da"+
		"\5(\25\2\u00d9\u00d7\3\2\2\2\u00da\u00dd\3\2\2\2\u00db\u00d9\3\2\2\2\u00db"+
		"\u00dc\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd\u00db\3\2\2\2\u00de\u00d4\3\2"+
		"\2\2\u00de\u00df\3\2\2\2\u00df\u00ea\3\2\2\2\u00e0\u00e1\7(\2\2\u00e1"+
		"\u00e2\7\27\2\2\u00e2\u00e7\5 \21\2\u00e3\u00e4\7\4\2\2\u00e4\u00e6\5"+
		" \21\2\u00e5\u00e3\3\2\2\2\u00e6\u00e9\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7"+
		"\u00e8\3\2\2\2\u00e8\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00ea\u00e0\3\2"+
		"\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00f2\3\2\2\2\u00ec\u00ed\7#\2\2\u00ed"+
		"\u00f0\5(\25\2\u00ee\u00ef\t\2\2\2\u00ef\u00f1\5(\25\2\u00f0\u00ee\3\2"+
		"\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f3\3\2\2\2\u00f2\u00ec\3\2\2\2\u00f2"+
		"\u00f3\3\2\2\2\u00f3\37\3\2\2\2\u00f4\u00f6\5(\25\2\u00f5\u00f7\t\3\2"+
		"\2\u00f6\u00f5\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7!\3\2\2\2\u00f8\u00ff"+
		"\7\7\2\2\u00f9\u00fa\5\62\32\2\u00fa\u00fb\7\b\2\2\u00fb\u00fc\7\7\2\2"+
		"\u00fc\u00ff\3\2\2\2\u00fd\u00ff\5(\25\2\u00fe\u00f8\3\2\2\2\u00fe\u00f9"+
		"\3\2\2\2\u00fe\u00fd\3\2\2\2\u00ff#\3\2\2\2\u0100\u0101\5\64\33\2\u0101"+
		"\u0102\5&\24\2\u0102%\3\2\2\2\u0103\u0104\5:\36\2\u0104\'\3\2\2\2\u0105"+
		"\u0106\b\25\1\2\u0106\u0113\5,\27\2\u0107\u0108\5\62\32\2\u0108\u0109"+
		"\7\b\2\2\u0109\u010b\3\2\2\2\u010a\u0107\3\2\2\2\u010a\u010b\3\2\2\2\u010b"+
		"\u010c\3\2\2\2\u010c\u0113\5\64\33\2\u010d\u010e\5\60\31\2\u010e\u010f"+
		"\7\3\2\2\u010f\u0110\5(\25\2\u0110\u0111\7\5\2\2\u0111\u0113\3\2\2\2\u0112"+
		"\u0105\3\2\2\2\u0112\u010a\3\2\2\2\u0112\u010d\3\2\2\2\u0113\u0128\3\2"+
		"\2\2\u0114\u0115\f\t\2\2\u0115\u0116\t\4\2\2\u0116\u0127\5(\25\n\u0117"+
		"\u0118\f\b\2\2\u0118\u0119\t\5\2\2\u0119\u0127\5(\25\t\u011a\u011b\f\7"+
		"\2\2\u011b\u011c\t\6\2\2\u011c\u0127\5(\25\b\u011d\u011e\f\6\2\2\u011e"+
		"\u011f\t\7\2\2\u011f\u0127\5(\25\7\u0120\u0121\f\5\2\2\u0121\u0122\7\25"+
		"\2\2\u0122\u0127\5(\25\6\u0123\u0124\f\4\2\2\u0124\u0125\7\'\2\2\u0125"+
		"\u0127\5(\25\5\u0126\u0114\3\2\2\2\u0126\u0117\3\2\2\2\u0126\u011a\3\2"+
		"\2\2\u0126\u011d\3\2\2\2\u0126\u0120\3\2\2\2\u0126\u0123\3\2\2\2\u0127"+
		"\u012a\3\2\2\2\u0128\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129)\3\2\2\2"+
		"\u012a\u0128\3\2\2\2\u012b\u012d\5\64\33\2\u012c\u012e\t\3\2\2\u012d\u012c"+
		"\3\2\2\2\u012d\u012e\3\2\2\2\u012e+\3\2\2\2\u012f\u0130\t\b\2\2\u0130"+
		"-\3\2\2\2\u0131\u0132\t\t\2\2\u0132/\3\2\2\2\u0133\u0134\5:\36\2\u0134"+
		"\61\3\2\2\2\u0135\u0136\5:\36\2\u0136\63\3\2\2\2\u0137\u0138\5:\36\2\u0138"+
		"\65\3\2\2\2\u0139\u013a\5:\36\2\u013a\67\3\2\2\2\u013b\u013c\5:\36\2\u013c"+
		"9\3\2\2\2\u013d\u0145\7\60\2\2\u013e\u0145\5.\30\2\u013f\u0145\7\62\2"+
		"\2\u0140\u0141\7\3\2\2\u0141\u0142\5:\36\2\u0142\u0143\7\5\2\2\u0143\u0145"+
		"\3\2\2\2\u0144\u013d\3\2\2\2\u0144\u013e\3\2\2\2\u0144\u013f\3\2\2\2\u0144"+
		"\u0140\3\2\2\2\u0145;\3\2\2\2\36LYg\u0083\u008e\u0093\u009c\u00ae\u00b3"+
		"\u00bb\u00c0\u00c3\u00cb\u00d2\u00db\u00de\u00e7\u00ea\u00f0\u00f2\u00f6"+
		"\u00fe\u010a\u0112\u0126\u0128\u012d\u0144";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}