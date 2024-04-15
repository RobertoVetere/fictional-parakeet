package org.example;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    private static Queue<ClientOnQueue> cola;
    private static Bank bankOfNeverland;
    private static ClientGenerator clientGenerator;

    public static void main(String[] args) throws InterruptedException {
        initialize();
        runBankInterface();
    }

    private static void initialize() {
        scanner = new Scanner(System.in);
        cola = new LinkedList<>();
        bankOfNeverland = new Bank("Bank Of Neverland", cola);
        clientGenerator = new ClientGenerator(bankOfNeverland);
        Thread generatorThread = new Thread(clientGenerator);
        generatorThread.start();
        System.out.println("Bienvenido al banco: " + bankOfNeverland.getName());
        createClientsExamples(bankOfNeverland);
    }

    private static void runBankInterface() {
        while (true) {
            try {
                displayMenu();
                int opcion = scanner.nextInt();
                scanner.nextLine();
                processOption(opcion);
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, introduzca un número válido.");
                scanner.nextLine();
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n1. Coger número");
        System.out.println("2. Atender siguiente cliente");
        System.out.println("3. Mostrar clientes en cola");
        System.out.println("4. Salir");
        System.out.println("Seleccione una opción: ");
    }

    private static void processOption(int opcion) {
        switch (opcion) {
            case 1:
                takeNumberAndPrint();
                break;
            case 2:
                serveNextClient();
                break;
            case 3:
                displayQueue();
                break;
            case 4:
                exitProgram();
                break;
            default:
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
        }
    }

    private static void takeNumberAndPrint() {
        long newClientNumber = takeNumber(bankOfNeverland);
        System.out.println("Su número es " + newClientNumber);
    }

    private static void serveNextClient() {
        ClientOnQueue client = bankOfNeverland.getClientOnQueues().peek();
        if (client != null) {
            long clientId = client.getId();
            bankOfNeverland.getClientOnQueues().poll();
            System.out.println("Cliente atendido: " + clientId);
        } else {
            System.out.println("La cola de clientes está vacía.");
        }
    }

    private static void displayQueue() {
        for (ClientOnQueue cliente : bankOfNeverland.getClientOnQueues()) {
            System.out.println(cliente.getId());
        }
    }

    private static void exitProgram() {
        System.out.println("Saliendo del programa...");
        clientGenerator.stop();
        scanner.close();
        System.exit(0);
    }

    private static long takeNumber(Bank bankOfNeverland) {
        ClientOnQueue newClient = new ClientOnQueue();
        bankOfNeverland.getClientOnQueues().add(newClient);
        return newClient.getId();
    }

    private static void createClientsExamples(Bank bankOfNeverland) {
        for (int i = 0; i < 5; i++) {
            bankOfNeverland.getClientOnQueues().add(new ClientOnQueue());
        }
    }
}