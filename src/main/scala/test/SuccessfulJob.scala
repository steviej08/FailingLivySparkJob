package test

import org.apache.livy.scalaapi.ScalaJobContext
import org.apache.spark.sql.{DataFrame, SparkSession}

case class SuccessfulJob(someArray: Array[String], filePath: String) {

  def call(scalaJobContext: ScalaJobContext): Unit = {

    theStuffToDo(scalaJobContext)

  }

  def theStuffToDo(scalaJobContext: ScalaJobContext): Unit = {


    // All I have changed is the parameter is of type array instead of a seq!!?! Ahh
    val mappedArray = someArray.map(s => s.toUpperCase())

    val ss: SparkSession = scalaJobContext.sparkSession

    val csv: DataFrame = ss
      .read
      .option("header", "true")
      .option("mode", "DROPMALFORMED")
      .csv(filePath)

    println(csv.count())


  }

}

object SuccessfulJob {

  // Tried to return a function with no luck!

  def callRetFunc(someSeq: Array[String], filePath: String): ScalaJobContext => Unit = {

    (sjc: ScalaJobContext) => {

      FailingJob(someSeq, filePath).theStuffToDo(sjc)

    }

  }

}
