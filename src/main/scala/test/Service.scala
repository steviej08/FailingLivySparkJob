package test

import org.apache.livy.scalaapi.LivyScalaClient

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait Service {

  def testProvider(livyClient: LivyScalaClient, someSeq: Seq[String], someArray: Array[String]): Future[Unit]

}

object Service extends Service {

  def testProvider(livyClient: LivyScalaClient, someSeq: Seq[String], someArray: Array[String]): Future[Unit] = {

    val path: String = "/transient/source/d557feda-410f-4950-9744-a40ae65693a0/elements/TestCSV.csv"

    for {
      _ <- livyClient.submit(SuccessfulJob(someArray, path).call)
      _ <- livyClient.submit(FailingJob(someSeq, path).call)
    } yield {

    }

  }

}
