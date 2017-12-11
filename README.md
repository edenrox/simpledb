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
* Heap File - in progress
* Nested Loops Join (JOINs in FROM clause)
* Projection (SELECT clause)
* Limit (LIMIT + OFFSET clause)
* Union all (UNION ALL clause)
* Selection (WHERE clause)
* Sort (ORDER BY clause) - internal memory
* Reading data (FROM clause)
  * Heap Scan, Literal Scan, Record Iterator
* Predicates
  * Equality (literal and columnar)

## Todo List

* B-Tree
* Sort - external sort
* Group By
* Aggregates
* Additional predicates
