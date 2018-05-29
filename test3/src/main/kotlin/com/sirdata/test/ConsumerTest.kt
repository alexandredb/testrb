package com.sirdata.test

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.Envelope
import java.nio.charset.Charset

class ConsumerTest  (channel : Channel, val int : Int, val r : Recv) : DefaultConsumer(channel)  {

    override fun handleDelivery(consumerTag : String, envelope : Envelope, properties : AMQP.BasicProperties, body : ByteArray) {

        var message = body.toString(Charset.defaultCharset())
        println(message)
        Thread.sleep(3000)
        r.send("bravo "+int)
        r.close()



    }

}