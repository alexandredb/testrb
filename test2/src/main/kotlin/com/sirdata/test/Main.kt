package com.sirdata.test

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.sun.org.apache.xerces.internal.xs.StringList


import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import java.io.IOException


fun main(args: Array<String>) {
    var host: String = "192.168.1.19"
    var s: Send = Send(host)

    s.send("lalala")




}

