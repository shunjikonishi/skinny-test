name := """skinny-test"""

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.9.9",
  "org.postgresql" % "postgresql" % "9.4.1209",
  "org.scalikejdbc" %% "scalikejdbc"                  % "2.5.+",
  "org.scalikejdbc" %% "scalikejdbc-config"           % "2.5.+",
  "org.skinny-framework" %% "skinny-orm"  % "2.3.+"
)

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

