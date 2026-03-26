# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

Exercises from [Functional Programming Strategies In Scala with Cats](https://scalawithcats.com/). Scala 3 with cats, cats-effect, and weaver-test.

## Build Commands

- `sbt compile` — compile
- `sbt test` — run all tests
- `sbt testOnly exercises.ch2.TreeSuite` — run a single test suite
- `sbt styleFix` — fix formatting and linting (scalafmt + scalafix)
- `sbt styleCheck` — check formatting and linting without fixing
- `sbt dev` — allow compiler warnings without failing compilation
- `sbt ci` — strict mode (CI: warnings fail compilation)
- CI runs: `sbt ci compile styleCheck test`

## Architecture

Exercises are organized by book chapter under `src/main/scala/exercises/`. Tests mirror the structure under `src/test/scala/exercises/`. Dependency versions are centralized in [project/Versions.scala](project/Versions.scala).

## Code Conventions

- **Scala 3 syntax**: `enum` for ADTs, `given`/`using` for typeclasses, `derives` for derivation via Kittens
- **Stack safety**: use `@annotation.tailrec` where possible, otherwise `scala.util.control.TailCalls` for trampolining
- **Cats typeclasses**: use `Eq` instead of `==`/`!=` (universal equality is banned by scalafix)
- **Scalafix rules**: no vars, throws, nulls, returns, while loops, `asInstanceOf`, `isInstanceOf`, universal equality
- **Scalafmt**: max 120 columns, `align.preset = more`
- **Testing**: weaver-test (`FunSuite` for pure tests, `SimpleIOSuite` for effectful tests with logging)
