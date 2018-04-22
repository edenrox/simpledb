// Generated from C:/Users/ian_000/IdeaProjects/simpledb/src/main/antlr4/com/hopkins/simpledb/parser\Simpledb.g4 by ANTLR 4.7
package com.hopkins.simpledb.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpledbParser}.
 */
public interface SimpledbListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(SimpledbParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(SimpledbParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#error}.
	 * @param ctx the parse tree
	 */
	void enterError(SimpledbParser.ErrorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#error}.
	 * @param ctx the parse tree
	 */
	void exitError(SimpledbParser.ErrorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#sql_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSql_stmt(SimpledbParser.Sql_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#sql_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSql_stmt(SimpledbParser.Sql_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#create_index_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCreate_index_stmt(SimpledbParser.Create_index_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#create_index_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCreate_index_stmt(SimpledbParser.Create_index_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#create_table_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCreate_table_stmt(SimpledbParser.Create_table_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#create_table_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCreate_table_stmt(SimpledbParser.Create_table_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#create_view_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCreate_view_stmt(SimpledbParser.Create_view_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#create_view_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCreate_view_stmt(SimpledbParser.Create_view_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#drop_index_stmt}.
	 * @param ctx the parse tree
	 */
	void enterDrop_index_stmt(SimpledbParser.Drop_index_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#drop_index_stmt}.
	 * @param ctx the parse tree
	 */
	void exitDrop_index_stmt(SimpledbParser.Drop_index_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#drop_table_stmt}.
	 * @param ctx the parse tree
	 */
	void enterDrop_table_stmt(SimpledbParser.Drop_table_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#drop_table_stmt}.
	 * @param ctx the parse tree
	 */
	void exitDrop_table_stmt(SimpledbParser.Drop_table_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#drop_view_stmt}.
	 * @param ctx the parse tree
	 */
	void enterDrop_view_stmt(SimpledbParser.Drop_view_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#drop_view_stmt}.
	 * @param ctx the parse tree
	 */
	void exitDrop_view_stmt(SimpledbParser.Drop_view_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#delete_stmt}.
	 * @param ctx the parse tree
	 */
	void enterDelete_stmt(SimpledbParser.Delete_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#delete_stmt}.
	 * @param ctx the parse tree
	 */
	void exitDelete_stmt(SimpledbParser.Delete_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#insert_stmt}.
	 * @param ctx the parse tree
	 */
	void enterInsert_stmt(SimpledbParser.Insert_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#insert_stmt}.
	 * @param ctx the parse tree
	 */
	void exitInsert_stmt(SimpledbParser.Insert_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#update_stmt}.
	 * @param ctx the parse tree
	 */
	void enterUpdate_stmt(SimpledbParser.Update_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#update_stmt}.
	 * @param ctx the parse tree
	 */
	void exitUpdate_stmt(SimpledbParser.Update_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#join_clause}.
	 * @param ctx the parse tree
	 */
	void enterJoin_clause(SimpledbParser.Join_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#join_clause}.
	 * @param ctx the parse tree
	 */
	void exitJoin_clause(SimpledbParser.Join_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#join_operator}.
	 * @param ctx the parse tree
	 */
	void enterJoin_operator(SimpledbParser.Join_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#join_operator}.
	 * @param ctx the parse tree
	 */
	void exitJoin_operator(SimpledbParser.Join_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#select_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSelect_stmt(SimpledbParser.Select_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#select_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSelect_stmt(SimpledbParser.Select_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#ordering_term}.
	 * @param ctx the parse tree
	 */
	void enterOrdering_term(SimpledbParser.Ordering_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#ordering_term}.
	 * @param ctx the parse tree
	 */
	void exitOrdering_term(SimpledbParser.Ordering_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#result_column}.
	 * @param ctx the parse tree
	 */
	void enterResult_column(SimpledbParser.Result_columnContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#result_column}.
	 * @param ctx the parse tree
	 */
	void exitResult_column(SimpledbParser.Result_columnContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#column_def}.
	 * @param ctx the parse tree
	 */
	void enterColumn_def(SimpledbParser.Column_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#column_def}.
	 * @param ctx the parse tree
	 */
	void exitColumn_def(SimpledbParser.Column_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#type_name}.
	 * @param ctx the parse tree
	 */
	void enterType_name(SimpledbParser.Type_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#type_name}.
	 * @param ctx the parse tree
	 */
	void exitType_name(SimpledbParser.Type_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(SimpledbParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(SimpledbParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#indexed_column}.
	 * @param ctx the parse tree
	 */
	void enterIndexed_column(SimpledbParser.Indexed_columnContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#indexed_column}.
	 * @param ctx the parse tree
	 */
	void exitIndexed_column(SimpledbParser.Indexed_columnContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#literal_value}.
	 * @param ctx the parse tree
	 */
	void enterLiteral_value(SimpledbParser.Literal_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#literal_value}.
	 * @param ctx the parse tree
	 */
	void exitLiteral_value(SimpledbParser.Literal_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#keyword}.
	 * @param ctx the parse tree
	 */
	void enterKeyword(SimpledbParser.KeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#keyword}.
	 * @param ctx the parse tree
	 */
	void exitKeyword(SimpledbParser.KeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#function_name}.
	 * @param ctx the parse tree
	 */
	void enterFunction_name(SimpledbParser.Function_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#function_name}.
	 * @param ctx the parse tree
	 */
	void exitFunction_name(SimpledbParser.Function_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#table_name}.
	 * @param ctx the parse tree
	 */
	void enterTable_name(SimpledbParser.Table_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#table_name}.
	 * @param ctx the parse tree
	 */
	void exitTable_name(SimpledbParser.Table_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#column_name}.
	 * @param ctx the parse tree
	 */
	void enterColumn_name(SimpledbParser.Column_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#column_name}.
	 * @param ctx the parse tree
	 */
	void exitColumn_name(SimpledbParser.Column_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#index_name}.
	 * @param ctx the parse tree
	 */
	void enterIndex_name(SimpledbParser.Index_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#index_name}.
	 * @param ctx the parse tree
	 */
	void exitIndex_name(SimpledbParser.Index_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#view_name}.
	 * @param ctx the parse tree
	 */
	void enterView_name(SimpledbParser.View_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#view_name}.
	 * @param ctx the parse tree
	 */
	void exitView_name(SimpledbParser.View_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpledbParser#any_name}.
	 * @param ctx the parse tree
	 */
	void enterAny_name(SimpledbParser.Any_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpledbParser#any_name}.
	 * @param ctx the parse tree
	 */
	void exitAny_name(SimpledbParser.Any_nameContext ctx);
}