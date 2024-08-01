val scala3Version = "3.4.2"

lazy val root = project
  .in(file("."))
  .settings(
    name         := "exercises-fp-strategies-in-scala-with-cats",
    version      := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    run / fork   := true, // Makes exit codes work as expected
    // fp
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core"   % Versions.cats,
      "org.typelevel" %% "cats-effect" % Versions.catsEffect,
      "org.typelevel" %% "kittens"     % Versions.kittens
    ),
    // tests
    libraryDependencies ++= Seq(
      "com.disneystreaming" %% "weaver-cats"       % Versions.weaver,
      "com.disneystreaming" %% "weaver-scalacheck" % Versions.weaver
    ).map(_ % Test),
    // Scalafix
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision
  )
