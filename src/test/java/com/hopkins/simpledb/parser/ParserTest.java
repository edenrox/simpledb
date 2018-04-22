package com.hopkins.simpledb.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ParserTest {

    private void parseOrThrow(String sql) {
        SimpledbParser parser = new SimpledbParser(new BufferedTokenStream(new SimpledbLexer(new ANTLRInputStream(sql))));
        parser.setErrorHandler(new BailErrorStrategy());
        SimpledbParser.ParseContext context = parser.parse();
    }

    @Test(expected = ParseCancellationException.class)
    public void createBogus() {
        String sql = "CREATE BOGUS people (id INTEGER, name TEXT)";
        parseOrThrow(sql);
    }

    @Test
    public void createTable() {
        String sql = "CREATE TABLE people (id INTEGER, name TEXT)";
        parseOrThrow(sql);
    }

    @Test
    public void dropTable() {
        String sql = "DROP TABLE nameIdx";
        parseOrThrow(sql);
    }

    @Test
    public void createIndex() {
        String sql = "CREATE INDEX nameIdx ON people (name ASC)";
        parseOrThrow(sql);
    }

    @Test
    public void dropIndex() {
        String sql = "DROP INDEX nameIdx";
        parseOrThrow(sql);
    }

    @Test
    public void insert() {
        String sql = "INSERT INTO people (id, name) VALUES (1, 'Jacko Flante')";
        parseOrThrow(sql);

        sql = "INSERT INTO people VALUES (1, 'Jacko Flante')";
        parseOrThrow(sql);
    }

    @Test
    public void delete() {
        String sql = "DELETE FROM people WHERE name = 'Jacko Flante'";
        parseOrThrow(sql);

        sql = "DELETE FROM people";
        parseOrThrow(sql);
    }

    @Test
    public void update() {
        String sql = "UPDATE people SET name = 'Johnny Bobbers' WHERE id = 1";
        parseOrThrow(sql);

        sql = "UPDATE people SET name='Test'";
        parseOrThrow(sql);
    }

    @Test
    public void select() {
        String sql = "SELECT * FROM people";
        parseOrThrow(sql);

        sql = "SELECT id, name FROM people";
        parseOrThrow(sql);

        sql = "SELECT id, name FROM people WHERE id = 1";
        parseOrThrow(sql);

        sql = "SELECT name FROM people ORDER BY name ASC";
        parseOrThrow(sql);

        sql = "SELECT id, name FROM people JOIN city WHERE city_id = home_city_id ORDER BY name ASC";
        parseOrThrow(sql);
    }
}
