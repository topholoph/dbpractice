name := """db1"""
organization := "db1"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.8"

libraryDependencies += guice

libraryDependencies ++= Seq(
  javaJdbc
)

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.41"
)
