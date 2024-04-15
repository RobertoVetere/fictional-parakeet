package org.example;

import java.util.ArrayList;
import java.util.Queue;

public class Bank {
    private String name;
    private Queue<ClientOnQueue> clientOnQueues;

    public Bank(String name, Queue<ClientOnQueue> clientOnQueues) {
        this.name = name;
        this.clientOnQueues = clientOnQueues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Queue<ClientOnQueue> getClientOnQueues() {
        return clientOnQueues;
    }

    public void setClientOnQueues(Queue<ClientOnQueue> clientOnQueues) {
        this.clientOnQueues = clientOnQueues;
    }
}
