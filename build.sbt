val scala3Version = "3.4.2"

lazy val root = project
  .in(file("."))
  .settings(
    name         := "exercises-fp-strategies-in-scala-with-cats",
    version      := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    run / fork   := true, // Makes exit codes work as expected

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test,

    // Scalafix
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision
  )
