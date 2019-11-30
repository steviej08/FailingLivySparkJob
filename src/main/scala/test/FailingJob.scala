package test

import org.apache.livy.scalaapi.ScalaJobContext
import org.apache.spark.sql.{DataFrame, SparkSession}


case class FailingJob(someSeq: Seq[String], filePath: String) {

  def call(scalaJobContext: ScalaJobContext): Unit = {

    theStuffToDo(scalaJobContext)

  }

  def theStuffToDo(scalaJobContext: ScalaJobContext): Unit = {


    // It doesn't really matter what I do here.
    // Main this is that the seq is used in some way.
    val mappedSeq = someSeq.map(s => s.toUpperCase())

    val ss: SparkSession = scalaJobContext.sparkSession

    val csv: DataFrame = ss
      .read
      .option("header", "true")
      .option("mode", "DROPMALFORMED")
      .csv(filePath)

    println(csv.count())


  }

}

object FailingJob {

  // Tried to return a function with no luck!

  def callRetFunc(someSeq: Seq[String], filePath: String): ScalaJobContext => Unit = {

    (sjc: ScalaJobContext) => {

      FailingJob(someSeq, filePath).theStuffToDo(sjc)

    }

  }

}
