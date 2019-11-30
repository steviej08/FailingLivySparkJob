package test

import org.apache.livy.scalaapi.LivyScalaClient

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait Service {

  def testProvider(livyClient: LivyScalaClient, someSeq: Seq[String], someArray: Array[String]): Future[Unit]

}

object Service extends Service {

  def testProvider(livyClient: LivyScalaClient, someSeq: Seq[String], someArray: Array[String]): Future[Unit] = {

    val path: String = "/transient/TestCSV.csv"

    for {
      _ <- livyClient.submit(SuccessfulJob(someArray, path).call)
      _ <- livyClient.submit(FailingJob(someSeq, path).call)
    } yield {

    }

  }

}
