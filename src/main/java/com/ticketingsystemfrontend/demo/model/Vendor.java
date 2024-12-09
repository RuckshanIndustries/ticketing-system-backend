package com.ticketingsystemfrontend.demo.model;

import com.ticketingsystemfrontend.demo.config.Logger;
import com.ticketingsystemfrontend.demo.service.TicketPool;

public class Vendor implements Runnable {
    private TicketPool ticketPool;
    private int ticketReleaseRate;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        try {

            while (ticketPool.getTotalTickets() > 0) {
                Thread.sleep(1000 / ticketReleaseRate); // Release rate control
                if (ticketPool.addTicket()) {
                    Logger.info("Vendor added a ticket. Current pool size: " + ticketPool.getCurrentTickets());
                } else {
                    Logger.info("Vendor waiting for space in the pool.");
                }
            }
            Logger.info("Vendor finished: No more tickets to add.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
