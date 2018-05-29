package com.sirdata.test

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory

class Recv() {
    var factory : ConnectionFactory? = null
    var connection : Connection?=null
    var channel_reception :  Channel?=null
    var channel_envoi :  Channel?=null
    var QUEUE_RETOUR : String ="retour"
    var QUEUE_ALLER : String = "aller"
    constructor( int : Int, host : String) : this(){
        factory = ConnectionFactory()
        factory!!.setHost(host)
        connection = factory!!.newConnection()
        channel_reception= connection!!.createChannel()
        channel_envoi = connection!!.createChannel()
        channel_envoi!!.queueDeclare(QUEUE_RETOUR,false,false,false,null)
        channel_reception!!.queueDeclare(QUEUE_ALLER,false,false,false,null)
        var consumer = ConsumerTest(channel_reception!!, int, this)
        channel_reception!!.basicConsume(QUEUE_ALLER, true, consumer);
    }
    fun send (message : String){
        channel_envoi!!.basicPublish("",QUEUE_RETOUR, null , message.toByteArray())

    }
    fun close(){
        if( channel_reception!= null) channel_reception!!.close();
        if( channel_envoi != null) channel_envoi!!.close();
        connection!!.close();
    }

}