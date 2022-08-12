ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.0.0"

lazy val root = (project in file("."))
  .settings(
    name := "Challenge"
  )

libraryDependencies += "org.scalameta" %% "munit" % "0.7.26" % Test