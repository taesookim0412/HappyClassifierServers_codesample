// Java JDK 8
// Scala 2.12.16
// Spark 3.1.2
// Hadoop 3.2.2

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.16"

lazy val root = (project in file("."))
  .settings(
    name := "HappyClassifierData"
  )

val sparkVersion = "3.1.2"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.12" % sparkVersion,
  "org.apache.spark" % "spark-sql_2.12" % sparkVersion
)