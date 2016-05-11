name := "play-java"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

routesGenerator := StaticRoutesGenerator

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final",
  "mysql" % "mysql-connector-java" % "5.1.38",
  "org.webjars" %% "webjars-play" % "2.5.0",
  "org.webjars" % "jquery" % "2.1.1",
  "org.webjars" % "bootstrap" % "3.3.6"
)
