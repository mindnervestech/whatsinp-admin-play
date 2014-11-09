name := "crawler"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  	"net.sf.supercsv" % "super-csv-dozer" % "2.1.0",
	"org.json"%"org.json"%"chargebee-1.0",
	"org.jsoup" % "jsoup" % "1.7.3",
  javaJdbc,
  javaEbean,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.18"
)     

play.Project.playJavaSettings
