val scala3Version = "3.4.2"

lazy val root = project
  .in(file("."))
  .settings(
    name         := "exercises-fp-strategies-in-scala-with-cats",
    version      := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    run / fork   := true, // Makes exit codes work as expected

    libraryDependencies += "org.typelevel"       %% "cats-core"         % "2.12.0",
    libraryDependencies += "org.typelevel"       %% "cats-effect"       % "3.5.4",
    libraryDependencies += "com.disneystreaming" %% "weaver-cats"       % "0.8.4" % Test,
    libraryDependencies += "com.disneystreaming" %% "weaver-scalacheck" % "0.8.4" % Test,

    // Scalafix
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision
  )
