# Simple DB

## Overview

This is a project to build a simple DBMS system.  I watched [Joseph Hellerstein's](http://db.cs.berkeley.edu/jmh/) 
UC Berkeley CS 186 lectures on YouTube (now removed) and wanted to apply some of the concepts.  This isn't meant as a 
production database, just a learning tool.

## Current Support

* Data serialization/deserialization for:
  * Strings, primitives (bool, int, double), records, schemas
* On Disk Tables + Catalog
* Buffer Pool - CacheManager
* Heap File
* Nested Loops Join (JOINs in FROM clause)
* Projection (SELECT clause)
* Limit (LIMIT + OFFSET clause)
* Union all (UNION ALL clause)
* Selection (WHERE clause)
* Sort (ORDER BY clause) - internal memory
* Reading data (FROM clause)
  * Heap Scan, Literal Scan, Record Iterator
* Expressions
  * Literals
  * Columns
  * Arithmetic (+,-,*,/,%)
  * Comparison
  * Boolean (AND/OR)
* B-Tree indexes - in progress
  * Index scan, index lookup
* Group By (GROUP BY clause)
* Aggregate functions
  * COUNT, SUM, AVG, MIN, MAX, GROUP_CONCAT
* Basic SQL grammar

## Todo List

* Sort - external sort
* SQL parsing to query plan