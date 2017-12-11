package com.hopkins.simpledb.root;

import com.hopkins.simpledb.app.*;
import com.hopkins.simpledb.catalog.TableDescriptor;
import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.operations.*;
import com.hopkins.simpledb.predicates.EqualsLiteralPredicate;
import com.hopkins.simpledb.predicates.EqualsPredicate;
import com.hopkins.simpledb.predicates.Predicate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class IntegrationTest {

  private static final String TEAMS_TABLE_NAME = "teams";
  private static final Schema TEAMS_SCHEMA =
      new Schema(Arrays.asList(
          Column.ROW_ID,
          Column.newStringColumn("name", 40),
          Column.newStringColumn("city", 40)
      ));

  private static final String GAMES_TABLE_NAME = "games";
  private static final Schema GAMES_SCHEMA =
      new Schema(Arrays.asList(
          Column.ROW_ID,
          Column.newStringColumn("date", 20),
          Column.newIntColumn("home_team_id"),
          Column.newIntColumn("home_team_score"),
          Column.newIntColumn("away_team_id"),
          Column.newIntColumn("away_team_score")
      ));

  private App app;
  private Config config;
  private CatalogManager catalogManager;
  private HeapManager heapManager;
  private DiskFileManager diskFileManager;
  private String fileName;

  @Before
  public void setup() throws IOException {
    File file = File.createTempFile("test", ".db");
    System.err.println("file: " + file);
    fileName = file.getName();

    app = new App();
    app.init(fileName);
    ServiceLocator locator = app.getServiceLocator();

    config = locator.get(Config.class);
    catalogManager = locator.get(CatalogManager.class);
    heapManager = locator.get(HeapManager.class);
    diskFileManager = locator.get(DiskFileManager.class);
  }

  @After
  public void tearDown() {
    File file = new File(fileName);
    file.delete();
  }

  @Test
  public void createTables() {
    // is there a catalog table?
    assertThat(catalogManager.hasTable(config.getCatalogTableName())).isTrue();

    assertThat(catalogManager.hasTable(TEAMS_TABLE_NAME)).isFalse();
    assertThat(catalogManager.hasTable(GAMES_TABLE_NAME)).isFalse();

    // CREATE TABLE teams (_id INTEGER PRIMARY KEY, name VARCHAR(20), city VARCHAR(20));
    catalogManager.createTable(TEAMS_TABLE_NAME, TEAMS_SCHEMA);

    assertThat(catalogManager.hasTable(TEAMS_TABLE_NAME)).isTrue();
    assertThat(catalogManager.hasTable(GAMES_TABLE_NAME)).isFalse();

    // CREATE TABLE games
    catalogManager.createTable(GAMES_TABLE_NAME, GAMES_SCHEMA);

    assertThat(catalogManager.hasTable(TEAMS_TABLE_NAME)).isTrue();
    assertThat(catalogManager.hasTable(GAMES_TABLE_NAME)).isTrue();
    assertThat(catalogManager.hasTable("bogus")).isFalse();
  }

  @Test
  public void insertAndReadData() {
    // CREATE TABLE teams (_id INTEGER PRIMARY KEY, name VARCHAR(20), city VARCHAR(20));
    catalogManager.createTable(TEAMS_TABLE_NAME, TEAMS_SCHEMA);

    TableDescriptor teamsTable = catalogManager.getTable(TEAMS_TABLE_NAME);

    // INSERT INTO teams (name, city) VALUES
    // ('Maple Leafs', 'Toronto'),
    // ('Canadians', 'Montreal'),
    // ('Flames', 'Calgary'),
    // ('Oilers', 'Edmonton'),
    // ('Jets', 'Winnipeg'),
    // ('Canucks', 'Vancouver');
    Record record = new Record(TEAMS_SCHEMA);
    record.setAll(0, "Maple Leafs", "Toronto");
    heapManager.insert(teamsTable, record);
    record.setAll(0, "Canadians", "Montreal");
    heapManager.insert(teamsTable, record);
    record.setAll(0, "Flames", "Calgary");
    heapManager.insert(teamsTable, record);
    record.setAll(0, "Oilers", "Edmonton");
    heapManager.insert(teamsTable, record);
    record.setAll(0, "Jets", "Winnipeg");
    heapManager.insert(teamsTable, record);
    record.setAll(0, "Canucks", "Vancouver");
    heapManager.insert(teamsTable, record);

    // SELECT city FROM teams WHERE name = 'Maple Leafs'
    DbIterator scan = heapManager.getScan(teamsTable);
    Predicate predicate = new EqualsLiteralPredicate("name", "Maple Leafs");
    DbIterator filter = new Selection(scan, predicate);
    DbIterator projection = new Projection(filter, "city");

    List<Record> result = DbIteratorUtil.openReadAllClose(projection);
    assertThat(result).hasSize(1);
    assertThat(result.get(0).get(0)).isEqualTo("Toronto");
    assertThat(result.get(0).getSchema().getColumnName(0)).isEqualTo("city");
  }

  @Test
  public void insertAndRead_multiplePages() {
    String[] teams = new String[] {
        "Canucks", "Flames", "Oilers", "Jets", "Maple Leafs",
        "Canadians", "Red Wings", "Flyers", "Kings", "Rangers"};
    String[] cities = new String[] {
        "Vancouver",
        "Calgary", "Edmonton", "Winnipeg", "Toronto",
        "Montreal", "Detroit", "Philadelphia", "Los Angeles", "New York"};
    catalogManager.createTable(TEAMS_TABLE_NAME, TEAMS_SCHEMA);
    TableDescriptor teamsTable = catalogManager.getTable(TEAMS_TABLE_NAME);
    Record record = new Record(TEAMS_SCHEMA);

    // Insert
    for (String team : teams) {
      for (String city : cities) {
        record.setAll(0, team, city);
        heapManager.insert(teamsTable, record);
      }
    }

    // Read and verify number of records
    System.err.println("Page Count: " + diskFileManager.getPageCount());
    DbIterator teamsScan = heapManager.getScan(teamsTable);
    assertThat(DbIteratorUtil.openCountClose(teamsScan)).isEqualTo(teams.length * cities.length);
  }

  @Test
  public void testMultiTableJoin() {
    // CREATE TABLE teams (_id INTEGER PRIMARY KEY, name VARCHAR(20), city VARCHAR(20));
    catalogManager.createTable(TEAMS_TABLE_NAME, TEAMS_SCHEMA);

    // CREATE TABLE games (_id INTEGER PRIMARY KEY, date VARCHAR(20),
    // home_team_id INTEGER, home_team_score INTEGER,
    // away_team_id INTEGER, away_team_score INTEGER);
    catalogManager.createTable(GAMES_TABLE_NAME, GAMES_SCHEMA);

    TableDescriptor teamsTable = catalogManager.getTable(TEAMS_TABLE_NAME);
    TableDescriptor gamesTable = catalogManager.getTable(GAMES_TABLE_NAME);

    // INSERT INTO teams (name, city) VALUES
    // ('Maple Leafs', 'Toronto'),
    // ('Canadians', 'Montreal'),
    // ('Flames', 'Calgary');
    Record record = new Record(TEAMS_SCHEMA);
    record.setAll(0, "Maple Leafs", "Toronto");
    heapManager.insert(teamsTable, record);
    record.setAll(0, "Canadians", "Montreal");
    heapManager.insert(teamsTable, record);
    record.setAll(0, "Flames", "Calgary");
    heapManager.insert(teamsTable, record);

    int leafsTeamId = getTeamId("Maple Leafs");
    int canadiansTeamId = getTeamId("Canadians");
    int flamesTeamId = getTeamId("Flames");

    // INSERT INTO games (home_team_id, home_team_score, away_team_id, away_team_score) VALUES
    record = new Record(GAMES_SCHEMA);
    record.setAll(0, "2017-01-01", leafsTeamId, 1, canadiansTeamId, 2);
    heapManager.insert(gamesTable, record);
    record.setAll(0, "2017-01-02", leafsTeamId, 2, flamesTeamId, 5);
    heapManager.insert(gamesTable, record);
    record.setAll(0, "2017-01-03", canadiansTeamId, 0, flamesTeamId, 1);
    heapManager.insert(gamesTable, record);
    record.setAll(0, "2017-01-04", canadiansTeamId, 1, leafsTeamId, 1);
    heapManager.insert(gamesTable, record);
    record.setAll(0, "2017-01-05", flamesTeamId, 6, leafsTeamId, 3);
    heapManager.insert(gamesTable, record);
    record.setAll(0, "2017-01-06", flamesTeamId, 2, canadiansTeamId, 1);
    heapManager.insert(gamesTable, record);

    // SELECT city, away_team_score
    // FROM teams
    // INNER JOIN games ON teams._id = games.home_team_id
    // WHERE away_team_id = ?

    DbIterator teamsScan = heapManager.getScan(teamsTable);
    assertThat(DbIteratorUtil.openCountClose(teamsScan)).isEqualTo(3);
    DbIterator gamesScan = heapManager.getScan(gamesTable);
    assertThat(DbIteratorUtil.openCountClose(gamesScan)).isEqualTo(6);
    DbIterator gamesProjection = new Projection(gamesScan, "home_team_id", "away_team_id", "away_team_score");
    DbIterator gamesFilter =
        new Selection(gamesProjection, new EqualsLiteralPredicate("away_team_id", flamesTeamId));
    assertThat(DbIteratorUtil.openCountClose(gamesFilter)).isEqualTo(2);

    DbIterator join = new NestedLoopJoin(teamsScan, gamesFilter);
    assertThat(DbIteratorUtil.openCountClose(join)).isEqualTo(6);
    DbIterator joinFilter = new Selection(join, new EqualsPredicate("_id", "home_team_id"));
    assertThat(DbIteratorUtil.openCountClose(joinFilter)).isEqualTo(2);
    DbIterator joinProjection = new Projection(joinFilter, "city", "away_team_score");
    assertThat(DbIteratorUtil.openCountClose(joinProjection)).isEqualTo(2);
    printResult(joinProjection, "final");
    List<Record> recordList = DbIteratorUtil.openReadAllClose(joinProjection);
    assertThat(recordList.get(0).get("city")).isEqualTo("Toronto");
    assertThat(recordList.get(1).get("city")).isEqualTo("Montreal");
  }

  private void printResult(DbIterator iterator, String title) {
    System.err.println("RESULTS");
    System.err.println("=======");
    System.err.println("Title: " + title);
    System.err.println("Iterator: " + iterator);
    iterator.open();
    System.err.println("Schema: " + iterator.getSchema());
    iterator.close();
    System.err.println("Records: ");
    List<Record> recordList = DbIteratorUtil.openReadAllClose(iterator);
    for (Record item : recordList) {
      System.err.println(item);
    }
  }

  private int getTeamId(String teamName) {
    TableDescriptor teamsTable = catalogManager.getTable(TEAMS_TABLE_NAME);

    // SELECT _id FROM teams WHERE name = ?
    DbIterator scan = heapManager.getScan(teamsTable);
    Predicate predicate = new EqualsLiteralPredicate("name", teamName);
    DbIterator filter = new Selection(scan, predicate);
    DbIterator projection = new Projection(filter, Column.ROW_ID_NAME);

    int rowId = -1;
    projection.open();
    if (projection.hasNext()) {
      Record record = projection.next();
      rowId = record.getRowId();
    }
    projection.close();
    return rowId;
  }
}
