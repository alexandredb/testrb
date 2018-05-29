package com.sirdata.test

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory



class Send (){
    val QUEUE_ALLER :String = "aller"
    var factory: ConnectionFactory  =ConnectionFactory()
    val QUEUE_RETOUR="retour"
    var connection : Connection?=null
    var channel_envoi : Channel ?=null
    var channel_reception : Channel ? = null

    constructor( host : String): this() {
        factory.setHost(host)
        connection = factory!!.newConnection()
        channel_envoi=connection!!.createChannel()
        channel_reception= connection!!.createChannel()
        channel_envoi!!.queueDeclare(QUEUE_ALLER,false,false,false,null)

        channel_reception!!.queueDeclare(QUEUE_RETOUR,false,false,false,null)
        var consumer = Consumeretour(channel_reception!!,this)
        channel_reception!!.basicConsume(QUEUE_RETOUR, true, consumer);
    }

    fun send ( message : String){
        channel_envoi!!.basicPublish("",QUEUE_ALLER, null , message.toByteArray())



    }

    fun close(){
        channel_envoi!!.close();
        channel_reception!!.close()
        connection!!.close();
    }

}