name := """playCapstone"""
organization := "com.capstone.play"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.1"


libraryDependencies += guice

libraryDependencies += jdbc
libraryDependencies += javaJdbc
libraryDependencies += javaJpa

libraryDependencies += "com.h2database" % "h2" % "1.4.200"
libraryDependencies += "org.hibernate" % "hibernate-core" % "5.4.13.Final"
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.19"
libraryDependencies += "org.modelmapper" % "modelmapper" % "2.3.7"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.6.1"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.6.1"
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.1.11"
libraryDependencies += "com.typesafe.akka" %% "akka-http-jackson" % "10.1.11"

PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"
