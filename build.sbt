import sbt.Keys.libraryDependencies
import sbtassembly.AssemblyPlugin.autoImport.assemblyJarName

val sparkVersion = "3.2.0"

lazy val root = (project in file("."))
  .settings(
    name := "scala-spark-example",
    version := "0.1",
    scalaVersion := "2.12.10",
    libraryDependencies ++= libraries,
    assemblySettings
  )

lazy val libraries = {
  Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
    "org.apache.spark" %% "spark-sql"  % sparkVersion % "provided"
  )
}

lazy val assemblySettings = Seq(
  assembly / assemblyJarName := "scala-spark-example.jar",
  assembly / assemblyMergeStrategy := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x                             => MergeStrategy.first
  }
)
