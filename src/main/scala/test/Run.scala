package test

import java.io.File
import java.net.URI

import org.apache.livy.LivyClientBuilder
import org.apache.livy.scalaapi.LivyScalaClient
import org.apache.livy.scalaapi._
import org.apache.spark.sql.{DataFrame, SparkSession}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.util.Try
import scala.concurrent.ExecutionContext.Implicits.global


object Run {

  val uri = "http://localhost:8998"

  def main(args: Array[String]): Unit = {

    val builder = new LivyClientBuilder(false).setURI(new URI(uri))

    val client: LivyScalaClient = builder.build().asScalaClient

    uploadJar(client)

    val someSeq: Seq[String] = Seq("some", "awesome", "seq")

    Await.result(Service.testProvider(client, someSeq, someSeq.toArray).recover({case ex: Exception =>
      println(s"Something went wrong: ${ex.getMessage}")

      Thread.sleep(10000)
      client.stop(true)
    }).andThen({

      case _ =>

        Thread.sleep(10000)

        println("Shutting down livy...")
        client.stop(true)
        println("Livy shutdown.")

    }), Duration.Inf)

  }


  def uploadJar(client: LivyScalaClient): Unit = {

    Await.result(client.uploadJar(new File("C:\\Git\\Test\\target\\test.jar")), Duration.Inf)
    Await.result(client.uploadJar(new File("C:\\Git\\Test\\lib\\livy-scala-api_2.11-0.6.0-incubating.jar")), Duration.Inf)

  }

  val hdfsUri = "hdfs://localhost:9000"



}
