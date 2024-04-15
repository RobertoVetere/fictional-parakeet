package org.example;

public class ClientOnQueue {
    static long nextID = 1;
    private long id;

    private boolean clientDone = false;

    public ClientOnQueue() {
        this.id = nextID++;
    }

    public long getId() {
        return id;
    }

    public void setClientDone(boolean clientDone) {
        this.clientDone = clientDone;
    }
}
