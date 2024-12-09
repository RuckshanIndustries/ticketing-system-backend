package com.ticketingsystemfrontend.demo.model;
import com.ticketingsystemfrontend.demo.config.Logger;
import com.ticketingsystemfrontend.demo.service.TicketPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class TicketSystem {
    public static int vendorCount;


    public static void main(String[] args) {


            Logger.enableFileLogging(true);
            Scanner scanner = new Scanner(System.in);
            try {
                // Input configuration
                System.out.print("Enter total tickets: ");
                int totalTickets = scanner.nextInt();

                System.out.print("Enter max ticket capacity: ");
                int maxCapacity = scanner.nextInt();

                System.out.print("Enter ticket release rate (per second): ");
                int ticketReleaseRate = scanner.nextInt();

                System.out.print("Enter customer retrieval rate (per second): ");
                int customerRetrievalRate = scanner.nextInt();

                System.out.print("Enter number of vendors: ");
                 vendorCount = scanner.nextInt();

                System.out.print("Enter number of customers: ");
                int customerCount = scanner.nextInt();

                // Initialize components
                TicketPool ticketPool = new TicketPool(totalTickets, maxCapacity);
                List<Thread> threads = new ArrayList<>();

                // Start vendor threads
                for (int i = 0; i < vendorCount; i++) {
                    threads.add(new Thread(new Vendor(ticketPool, ticketReleaseRate)));
                }

                // Start customer threads
                for (int i = 0; i < customerCount; i++) {
                    threads.add(new Thread(new Customer(ticketPool, customerRetrievalRate)));
                }

                System.out.println("Starting the system...");
                for (Thread thread : threads) {
                    thread.start();
                }

                for (Thread thread : threads) {
                    thread.join();
                }

                System.out.println("System stopped. Check log.txt for transaction details.");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                scanner.close();
            }
        }
    }
