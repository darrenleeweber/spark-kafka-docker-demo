// https://spark.apache.org/docs/latest/rdd-programming-guide.html
// For the Scala API, Spark 2.2.0 uses Scala 2.11. You will need to use a compatible Scala version (2.11.x).

// https://spark.apache.org/docs/latest/streaming-programming-guide.html#advanced-sources
//Spark Streaming 2.2.0 is compatible with Kafka broker versions 0.8.2.1 or higher.
// See the Kafka Integration Guide at https://spark.apache.org/docs/latest/streaming-kafka-integration.html

lazy val root = (project in file("."))
  .settings(
    name         := "direct_kafka_word_count",
    organization := "com.example",
    scalaVersion := "2.11.6",
    version      := "0.1.0-SNAPSHOT",
    libraryDependencies +=  "org.apache.spark" % "spark-core_2.11" % "2.2.0" % "provided",
    libraryDependencies +=  "org.apache.spark" % "spark-streaming_2.11" % "2.2.0" % "provided",
    libraryDependencies += ("org.apache.spark" % "spark-streaming-kafka-0-10_2.11" % "2.2.0") exclude ("org.spark-project.spark", "unused")
  )

assemblyJarName in assembly := name.value + ".jar"

