name := """cosmic-sizing-tool"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.xerial" % "sqlite-jdbc" % "3.7.2",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)

// Needed for excel file import/export feature
libraryDependencies ++= Seq(
  "org.apache.poi" % "poi" % "3.13",
  "org.apache.poi" % "poi-ooxml" % "3.13",
  "org.apache.poi" % "poi-ooxml-schemas" % "3.13",
  "org.apache.xmlbeans" % "xmlbeans" % "2.6.0",
  "com.lambdaworks" % "scrypt" % "1.4.0"

)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator


fork in run := true

fork in run := true

fork in run := true

fork in run := true
