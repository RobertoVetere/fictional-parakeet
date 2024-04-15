package org.example;

import java.util.Random;

public class ClientGenerator implements Runnable {
    private final Bank bank;
    private final Random random;
    private volatile boolean running = true;
    public ClientGenerator(Bank bank) {
        this.bank = bank;
        this.random = new Random();
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                int waitTimeMinutes = random.nextInt(5) + 1;
                Thread.sleep(waitTimeMinutes * 60 * 1000);

                ClientOnQueue newClient = new ClientOnQueue();
                bank.getClientOnQueues().add(newClient);

                System.out.println("Un nuevo cliente ha entrado en el banco. ID del cliente: " + newClient.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }
}
