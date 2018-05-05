package com.hopkins.simpledb.root;

import static com.hopkins.simpledb.root.HockeyData.*;

import com.hopkins.simpledb.app.*;
import com.hopkins.simpledb.catalog.TableDescriptor;
import com.hopkins.simpledb.data.ColumnType;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.expression.*;
import com.hopkins.simpledb.operations.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class IntegrationTest {

  private App app;
  private Config config;
  private CatalogManager catalogManager;
  private HeapManager heapManager;
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
  }

  @After
  public void tearDown() {
    File file = new File(fileName);
    file.delete();
  }

  private void createHockeyTables() {
    catalogManager.createTable(DivisionsTable.TABLE_NAME, DivisionsTable.SCHEMA);
    catalogManager.createTable(TeamsTable.TABLE_NAME, TeamsTable.SCHEMA);
    catalogManager.createTable(GamesTable.TABLE_NAME, GamesTable.SCHEMA);
  }

  private void fillHockeyData() {
    // INSERT INTO divisions
    TableDescriptor divisionsTable = catalogManager.getTable(DivisionsTable.TABLE_NAME);
    Record record = new Record(divisionsTable.getSchema());
    for (Object[] row : DIVISIONS_DATA) {
      record.setAll(row);
      heapManager.insert(divisionsTable, record);
    }

    // INSERT INTO teams
    TableDescriptor teamsTable = catalogManager.getTable(TeamsTable.TABLE_NAME);
    record = new Record(teamsTable.getSchema());
    for (Object[] row : TEAMS_DATA) {
      record.setAll(row);
      heapManager.insert(teamsTable, record);
    }

    // INSERT INTO games
    TableDescriptor gamesTable = catalogManager.getTable(GamesTable.TABLE_NAME);
    record = new Record(gamesTable.getSchema());
    for (Object[] sourceRow : GAMES_DATA) {
      Object[] row = Arrays.copyOf(sourceRow, sourceRow.length);

      // Transform team names into team_ids
      String homeTeam = (String) row[2];
      String awayTeam = (String) row[4];
      int homeTeamId = findTeamId(homeTeam);
      int awayTeamId = findTeamId(awayTeam);
      row[2] = homeTeamId;
      row[4] = awayTeamId;
      record.setAll(row);
      heapManager.insert(gamesTable, record);
    }
  }

  private int findTeamId(String name) {
    if (name.startsWith("New York")) {
      String city = "New York";
      String team = name.substring("New York ".length());
      return findTeamIdByCityAndTeam(city, team);
    } else {
      return findTeamIdByCity(name);
    }
  }

  private int findTeamIdByCity(String cityName) {
    TableDescriptor teamsTable = catalogManager.getTable(TeamsTable.TABLE_NAME);
    DbIterator scan = heapManager.getScan(teamsTable);
    Expression expression =
        new ComparisonExpression(
            new ColumnExpression(TeamsTable.Columns.CITY),
            ComparisonExpression.ComparisonOperator.EQUAL,
            new LiteralExpression(ColumnType.STRING, cityName));
    DbIterator filter = new Selection(scan, expression);
    DbIterator projection = new Projection(filter, TeamsTable.Columns.ID);

    List<Object> ids = DbIteratorUtil.openReadAllColumnClose(projection, TeamsTable.Columns.ID);
    assertThat(ids).hasSize(1);
    return (Integer) ids.get(0);
  }

  private int findTeamIdByCityAndTeam(String cityName, String teamName) {
    TableDescriptor teamsTable = catalogManager.getTable(TeamsTable.TABLE_NAME);
    DbIterator scan = heapManager.getScan(teamsTable);
    Expression expression =
        new BooleanExpression(
            new ComparisonExpression(
                new ColumnExpression(TeamsTable.Columns.CITY),
                ComparisonExpression.ComparisonOperator.EQUAL,
                new LiteralExpression(ColumnType.STRING, cityName)),
            BooleanExpression.BooleanOperator.AND,
            new ComparisonExpression(
                new ColumnExpression(TeamsTable.Columns.NAME),
                ComparisonExpression.ComparisonOperator.EQUAL,
                new LiteralExpression(ColumnType.STRING, teamName)));
    DbIterator filter = new Selection(scan, expression);
    DbIterator projection = new Projection(filter, TeamsTable.Columns.ID);

    List<Object> ids = DbIteratorUtil.openReadAllColumnClose(projection, TeamsTable.Columns.ID);
    assertThat(ids).hasSize(1);
    return (Integer) ids.get(0);
  }

  @Test
  public void hasCatalogTable() {
    assertThat(catalogManager.hasTable(config.getCatalogTableName())).isTrue();
  }

  @Test
  public void createTeamsTable() {
    // Initially no teams table
    assertThat(catalogManager.hasTable(TeamsTable.TABLE_NAME)).isFalse();

    // CREATE TABLE teams
    catalogManager.createTable(TeamsTable.TABLE_NAME, TeamsTable.SCHEMA);

    assertThat(catalogManager.hasTable(TeamsTable.TABLE_NAME)).isTrue();
  }

  @Test
  public void dataDefinitionsDoNotLeakPages() {
    createHockeyTables();

    CacheManager cacheManager = app.getServiceLocator().get(CacheManager.class);
    assertThat(cacheManager.getNumPinnedPages()).isEqualTo(0);
  }

  @Test
  public void dataMutationsDoNotLeakPages() {
    createHockeyTables();
    fillHockeyData();

    CacheManager cacheManager = app.getServiceLocator().get(CacheManager.class);
    assertThat(cacheManager.getNumPinnedPages()).isEqualTo(0);
  }

  @Test
  public void selectDivisionNames() {
    createHockeyTables();
    fillHockeyData();

    // SELECT name FROM divisions
    TableDescriptor divisionsTable = catalogManager.getTable(DivisionsTable.TABLE_NAME);
    DbIterator divisionsIterator = new Projection(heapManager.getScan(divisionsTable), DivisionsTable.Columns.NAME);
    List<Object> items = DbIteratorUtil.openReadAllColumnClose(divisionsIterator, DivisionsTable.Columns.NAME);
    assertThat(items).containsExactly("Atlantic", "Metropolitan", "Central", "Pacific");
  }

  @Test
  public void selectNumTeams() {
    createHockeyTables();
    fillHockeyData();

    // SELECT * FROM teams -> num rows
    TableDescriptor teamsTable = catalogManager.getTable(TeamsTable.TABLE_NAME);
    DbIterator teamsScan = heapManager.getScan(teamsTable);
    int teamsCount = DbIteratorUtil.openCountClose(teamsScan);
    assertThat(teamsCount).isEqualTo(31);
  }

  @Test
  public void selectNumOctoberGames() {
    createHockeyTables();
    fillHockeyData();

    // SELECT * FROM games WHERE date >= '2017-10-01' AND date < '2018-11-01' -> num rows
    TableDescriptor gamesTable = catalogManager.getTable(GamesTable.TABLE_NAME);
    DbIterator gamesScan = heapManager.getScan(gamesTable);
    Expression expression =
        new BooleanExpression(
            new ComparisonExpression(
                new ColumnExpression(GamesTable.Columns.DATE),
                ComparisonExpression.ComparisonOperator.GREATER_THAN_OR_EQUAL,
                new LiteralExpression(ColumnType.STRING, "2017-10-01")),
            BooleanExpression.BooleanOperator.AND,
            new ComparisonExpression(
                new ColumnExpression(GamesTable.Columns.DATE),
                ComparisonExpression.ComparisonOperator.LESS_THAN,
                new LiteralExpression(ColumnType.STRING, "2017-11-01")));
    DbIterator selection = new Selection(gamesScan, expression);
    int teamsCount = DbIteratorUtil.openCountClose(selection);
    assertThat(teamsCount).isEqualTo(45);
  }

  @Test
  public void selectNumNovemberHomeWinGames() {
    createHockeyTables();
    fillHockeyData();

    // SELECT _id FROM teams WHERE city = 'Calgary';
    int calgaryTeamId = findTeamIdByCity("Calgary");

    // SELECT * FROM games WHERE date >= '2017-11-01' AND date < '2018-12-01' -> num rows
    TableDescriptor gamesTable = catalogManager.getTable(GamesTable.TABLE_NAME);
    DbIterator gamesScan = heapManager.getScan(gamesTable);
    Expression expression =
        new BooleanExpression(
          new BooleanExpression(
              new ComparisonExpression(
                  new ColumnExpression(GamesTable.Columns.DATE),
                  ComparisonExpression.ComparisonOperator.GREATER_THAN_OR_EQUAL,
                  new LiteralExpression(ColumnType.STRING, "2017-11-01")),
              BooleanExpression.BooleanOperator.AND,
              new ComparisonExpression(
                  new ColumnExpression(GamesTable.Columns.DATE),
                  ComparisonExpression.ComparisonOperator.LESS_THAN,
                  new LiteralExpression(ColumnType.STRING, "2017-12-01"))),
        BooleanExpression.BooleanOperator.AND,
            new BooleanExpression(
                new ComparisonExpression(
                    new ColumnExpression(GamesTable.Columns.HOME_TEAM_ID),
                    ComparisonExpression.ComparisonOperator.EQUAL,
                    new LiteralExpression(ColumnType.INTEGER, calgaryTeamId)),
                BooleanExpression.BooleanOperator.AND,
                new ComparisonExpression(
                    new ColumnExpression(GamesTable.Columns.HOME_TEAM_SCORE),
                    ComparisonExpression.ComparisonOperator.GREATER_THAN,
                    new ColumnExpression(GamesTable.Columns.AWAY_TEAM_SCORE))));
    DbIterator selection = new Selection(gamesScan, expression);
    int teamsCount = DbIteratorUtil.openCountClose(selection);
    assertThat(teamsCount).isEqualTo(5);
  }

  @Test
  public void joinDivisionsToTeamsAndSortCityNames() {
    createHockeyTables();
    fillHockeyData();

    TableDescriptor divisionsTable = catalogManager.getTable(DivisionsTable.TABLE_NAME);
    TableDescriptor teamsTable = catalogManager.getTable(TeamsTable.TABLE_NAME);

    // SELECT city
    // FROM divisions
    //   INNER JOIN teams ON team.division_id = divisions._id
    // WHERE divisions.name = 'Atlantic'

    DbIterator divisionsScan = heapManager.getScan(divisionsTable);
    Expression divisionFilter =
        new ComparisonExpression(
            new ColumnExpression(DivisionsTable.Columns.NAME),
            ComparisonExpression.ComparisonOperator.EQUAL,
            new LiteralExpression(ColumnType.STRING, "Atlantic"));
    DbIterator divisionSelection = new Selection(divisionsScan, divisionFilter);
    DbIterator divisionProjection = new Projection(divisionSelection, DivisionsTable.Columns.ID);

    DbIterator teamsScan = heapManager.getScan(teamsTable);
    DbIterator teamsProjection = new Projection(teamsScan, TeamsTable.Columns.CITY, TeamsTable.Columns.DIVISION_ID);

    DbIterator joinIter = new NestedLoopJoin(divisionProjection, teamsProjection);
    Expression joinCondition =
        new ComparisonExpression(
            new ColumnExpression(DivisionsTable.Columns.ID),
            ComparisonExpression.ComparisonOperator.EQUAL,
            new ColumnExpression(TeamsTable.Columns.DIVISION_ID));
    DbIterator joinWithOnClause = new Selection(joinIter, joinCondition);
    DbIterator sortedCities =
        new Sort(
            new Projection(joinWithOnClause, TeamsTable.Columns.CITY),
            new SortColumn(TeamsTable.Columns.CITY, SortColumn.SortDirection.DESCENDING));

    List<Object> cities = DbIteratorUtil.openReadAllColumnClose(sortedCities, TeamsTable.Columns.CITY);
    assertThat(cities)
        .containsExactly( "Toronto", "Tampa Bay", "Ottawa", "Montreal", "Florida", "Detroit", "Buffalo", "Boston")
        .inOrder();
  }

  @Test
  public void selectHighScoringTeams() {
    createHockeyTables();
    fillHockeyData();

    TableDescriptor teamsTable = catalogManager.getTable(TeamsTable.TABLE_NAME);
    TableDescriptor gamesTable = catalogManager.getTable(GamesTable.TABLE_NAME);

    // SELECT games.date, teams.name
    // FROM games
    //   INNER JOIN teams ON teams._id = games.home_team_id
    // WHERE home_team_score + away_team_score > 8

    DbIterator teamsScan = heapManager.getScan(teamsTable);
    DbIterator teamsProjected = new Projection(teamsScan, TeamsTable.Columns.ID, TeamsTable.Columns.NAME);

    DbIterator gamesScan = heapManager.getScan(gamesTable);
    Expression gamesFilter =
        new ComparisonExpression(
            new ArithmeticExpression(
                new ColumnExpression(GamesTable.Columns.HOME_TEAM_SCORE),
                ArithmeticExpression.ArithmeticOperation.ADDITION,
                new ColumnExpression(GamesTable.Columns.AWAY_TEAM_SCORE)),
            ComparisonExpression.ComparisonOperator.GREATER_THAN,
            new LiteralExpression(ColumnType.INTEGER, 8)
        );
    DbIterator gamesFiltered = new Selection(gamesScan, gamesFilter);
    DbIterator gamesProjected = new Projection(gamesFiltered, GamesTable.Columns.DATE, GamesTable.Columns.HOME_TEAM_ID);

    DbIterator joined = new NestedLoopJoin(gamesProjected, teamsProjected);
    Expression joinFilter =
        new ComparisonExpression(
            new ColumnExpression(GamesTable.Columns.HOME_TEAM_ID),
            ComparisonExpression.ComparisonOperator.EQUAL,
            new ColumnExpression(TeamsTable.Columns.ID));
    DbIterator joinFiltered = new Selection(joined, joinFilter);
    DbIterator joinProjected = new Projection(joinFiltered, GamesTable.Columns.DATE, TeamsTable.Columns.NAME);
    DbIterator sortedIterator =
        new Sort(joinProjected, new SortColumn(GamesTable.Columns.DATE, SortColumn.SortDirection.DESCENDING));

    List<Object> dateList = DbIteratorUtil.openReadAllColumnClose(sortedIterator, GamesTable.Columns.DATE);
    assertThat(dateList)
        .containsAllOf("2018-03-16", "2018-02-21", "2018-02-17", "2018-02-01")
        .inOrder();

    List<Object> teamNameList = DbIteratorUtil.openReadAllColumnClose(sortedIterator, TeamsTable.Columns.NAME);
    assertThat(teamNameList)
        .containsAllOf("Flames", "Golden Knights", "Flames", "Flames", "Flames", "Stars", "Flyers", "Red Wings")
        .inOrder();
  }
}
