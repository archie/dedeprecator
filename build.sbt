name := "Akka DeDeprecator"

version := "0.1"

mainClass := Some("dedep.DeDeprecator")

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.2.3",
  "com.typesafe.akka" %% "akka-testkit" % "2.2.3" % "test",
  "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
)

scalacOptions in Compile ++= Seq("-feature", "-deprecation")

scalacOptions in Test ++= Seq("-Yrangepos", "-feature", "-deprecation")

resolvers ++= Seq(
  "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "releases"  at "http://oss.sonatype.org/content/repositories/releases"
)
