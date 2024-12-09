package com.ticketingsystemfrontend.demo.config;

import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public void setup() {
        Scanner scanner = new Scanner(System.in);
        //Test

        this.totalTickets = validateInput(scanner, "Enter Total Tickets: ", 1, Integer.MAX_VALUE);
        this.ticketReleaseRate = validateInput(scanner, "Enter Ticket Release Rate (per second): ", 1, 100);
        this.customerRetrievalRate = validateInput(scanner, "Enter Customer Retrieval Rate (per second): ", 1, 100);
        this.maxTicketCapacity = validateInput(scanner, "Enter Max Ticket Capacity: ", 1, totalTickets);

        scanner.close();
    }

    private int validateInput(Scanner scanner, String prompt, int min, int max) {
        int input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. " + prompt);
                scanner.next();
            }
            input = scanner.nextInt();
        } while (input < min || input > max);
        return input;
    }

    public int getTotalTickets() { return totalTickets; }
    public int getTicketReleaseRate() { return ticketReleaseRate; }
    public int getCustomerRetrievalRate() { return customerRetrievalRate; }
    public int getMaxTicketCapacity() { return maxTicketCapacity; }
}
