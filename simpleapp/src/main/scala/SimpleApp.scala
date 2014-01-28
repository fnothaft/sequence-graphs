/*** SimpleApp.scala ***/
// See http://spark.incubator.apache.org/docs/latest/quick-start.html
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

object SimpleApp {
  def main(args: Array[String]) {
    val logFile = "simple.sbt" // Should be some file on your system
    val sc = new SparkContext("mesos://localhost:5050", "Simple App", System.getenv("SPARK_HOME"),
      List("target/scala-2.9.3/simple-project_2.9.3-1.0.jar"))
      
    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }
}
