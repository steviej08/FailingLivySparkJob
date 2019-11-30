package test

import java.io.IOException

import com.rabbitmq.client._
import org.apache.livy.scalaapi.LivyScalaClient

class RabbitTest(client: LivyScalaClient) {

//  lazy val factory = new ConnectionFactory()
//
//  lazy val connection: Connection = factory.newConnection(s"Test Connection")
//
//  def consumer(): Unit = {
//
//
//    val channel = connection.createChannel()
//
//    val queueName = "sj-test"
//
//    channel.queueDeclare(queueName, true, false, false, null)
//
//    channel.basicConsume(queueName, false, "myConsumerTag", new DefaultConsumer(channel) {
//      @throws[IOException]
//      override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]): Unit = {
//        val deliveryTag = envelope.getDeliveryTag
//        channel.basicAck(deliveryTag, false)
//
//        val testThread = new TestFuture(client).run()
//
//      }
//    })
//
//  }

}
