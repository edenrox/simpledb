# Simple DB

## Overview

This is a project to build a simple DBMS system.  I watched [Joseph Hellerstein's](http://db.cs.berkeley.edu/jmh/) 
UC Berkeley CS 186 lectures on YouTube (now removed) and wanted to apply some of the concepts.  This isn't meant as a 
production database, just a learning tool.

## Current Support

* Data serialization/deserialization for:
  * Strings, primitives, records, schemas
* On Disk Tables + Catalog
* Buffer Pool - CacheManager
* Heap File - in progress
* Nested Loops Join (JOINs in FROM clause)
* Projection (SELECT clause)
* Limit (LIMIT clause)
* Union all (UNION ALL clause)
* Selection (WHERE clause)
* Reading data (FROM clause)
  * Heap Scan, Literal Scan, Record Iterator
* Predicates
  * Equality (literal and columnar)

## Todo List

* B-Tree
* Sort
* Group By
* Aggregates
* Additional predicates
