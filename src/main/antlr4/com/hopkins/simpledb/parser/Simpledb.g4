grammar Simpledb;

parse
 : sql_stmt EOF
 ;

error
 : UNEXPECTED_CHAR
   {
     throw new RuntimeException("UNEXPECTED_CHAR=" + $UNEXPECTED_CHAR.text);
   }
 ;

sql_stmt
 : ( create_index_stmt
   | create_table_stmt
   | create_view_stmt
   | delete_stmt
   | drop_index_stmt
   | drop_table_stmt
   | drop_view_stmt
   | insert_stmt
   | select_stmt
   | update_stmt )
 ;

create_index_stmt
 : K_CREATE K_INDEX index_name K_ON table_name '(' indexed_column (',' indexed_column )* ')'
 ;

create_table_stmt
 : K_CREATE K_TABLE table_name '(' column_def ( ',' column_def )* ')'
 ;

create_view_stmt
 : K_CREATE K_VIEW view_name K_AS select_stmt
 ;

drop_index_stmt
 : K_DROP K_INDEX index_name
 ;

drop_table_stmt
 : K_DROP K_TABLE table_name
 ;

drop_view_stmt
 : K_DROP K_VIEW view_name
 ;

delete_stmt
 : K_DELETE K_FROM table_name ( K_WHERE expr )?
 ;

insert_stmt
 : K_INSERT K_INTO table_name ( '(' column_name ( ',' column_name )* ')' )?
   K_VALUES '(' expr ( ',' expr )* ')'
 ;

update_stmt
 : K_UPDATE table_name
   K_SET column_name '=' expr ( ',' column_name '=' expr )*
   ( K_WHERE expr )?
 ;

join_clause
 : table_name ( join_operator table_name )*
 ;

join_operator
 : ','
 | ( K_INNER )? K_JOIN
 ;

select_stmt
 : K_SELECT result_column ( ',' result_column )*
   K_FROM join_clause
   ( K_WHERE expr )?
   ( K_GROUP K_BY expr ( ',' expr )* )?
   ( K_ORDER K_BY ordering_term ( ',' ordering_term )* )?
   ( K_LIMIT expr ( ( K_OFFSET | ',' ) expr )? )?
  ;

ordering_term
 : expr ( K_ASC | K_DESC )?
 ;

result_column
 : '*'
 | table_name '.' '*'
 | expr
 ;

column_def
 : column_name type_name
 ;

type_name
 : any_name
 ;

expr
 : literal_value
 | ( table_name '.' )? column_name
 | expr ( '*' | '/' | '%' ) expr
 | expr ( '+' | '-' ) expr
 | expr ( '<' | '<=' | '>' | '>=' ) expr
 | expr ( '=' | '==' | '!=' | '<>' ) expr
 | expr K_AND expr
 | expr K_OR expr
 | function_name '(' expr ')'
 ;

indexed_column
 : column_name ( K_ASC | K_DESC )?
 ;

literal_value
 : NUMERIC_LITERAL
 | STRING_LITERAL
 | K_NULL
 ;

keyword
 : K_AND
 | K_AS
 | K_ASC
 | K_BY
 | K_CREATE
 | K_DELETE
 | K_DESC
 | K_DROP
 | K_FROM
 | K_GROUP
 | K_INDEX
 | K_INSERT
 | K_INTO
 | K_LIMIT
 | K_NULL
 | K_OFFSET
 | K_ON
 | K_OR
 | K_ORDER
 | K_SELECT
 | K_SET
 | K_TABLE
 | K_UPDATE
 | K_VALUES
 | K_VIEW
 | K_WHERE
 ;

function_name
 : any_name
 ;

table_name
 : any_name
 ;

column_name
 : any_name
 ;

index_name
 : any_name
 ;

view_name
 : any_name
 ;

any_name
 : IDENTIFIER
 | keyword
 | STRING_LITERAL
 | '(' any_name ')'
 ;

K_AS : A S;
K_AND : A N D;
K_ASC : A S C;
K_BY : B Y;
K_CREATE : C R E A T E;
K_DELETE : D E L E T E;
K_DESC : D E S C;
K_DROP : D R O P;
K_FROM : F R O M;
K_GROUP : G R O U P;
K_JOIN : J O I N;
K_INDEX : I N D E X;
K_INNER : I N N E R;
K_INSERT : I N S E R T;
K_INTO : I N T O;
K_LIMIT : L I M I T;
K_NULL : N U L L;
K_OFFSET : O F F S E T;
K_ON : O N;
K_OR : O R;
K_ORDER : O R D E R;
K_SELECT : S E L E C T;
K_SET : S E T;
K_TABLE : T A B L E;
K_UPDATE : U P D A T E;
K_VALUES : V A L U E S;
K_VIEW : V I E W;
K_WHERE : W H E R E;

IDENTIFIER
 : [a-zA-Z_] [a-zA-Z_0-9]*
 ;

NUMERIC_LITERAL
 : DIGIT+ ( '.' DIGIT* )? ( E [-+]? DIGIT+ )?
 | '.' DIGIT+ ( E [-+]? DIGIT+ )?
 ;

STRING_LITERAL
 : '\'' ( ~'\'' | '\'\'' )* '\''
 ;

SPACES
 : [ \u000B\t\r\n] -> skip
 ;

UNEXPECTED_CHAR
 : .
 ;

fragment DIGIT : [0-9];

fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];
