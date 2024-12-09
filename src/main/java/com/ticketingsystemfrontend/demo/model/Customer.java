package com.ticketingsystemfrontend.demo.model;

import com.ticketingsystemfrontend.demo.config.Logger;
import com.ticketingsystemfrontend.demo.service.TicketPool;

public class Customer implements Runnable {
    private TicketPool ticketPool;
    private int customerRetrievalRate;

    public Customer(TicketPool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        try {

            while (ticketPool.getCurrentTickets() > 0) {
                Thread.sleep(1000 / customerRetrievalRate); // Retrieval rate control
                if (ticketPool.removeTicket()) {
                    Logger.info("Customer bought a ticket. Current pool size: " + ticketPool.getCurrentTickets());
                } else {
                    Logger.info("Customer waiting for tickets.");
                }
            }
            Logger.info("Customer finished: No more tickets to buy.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
