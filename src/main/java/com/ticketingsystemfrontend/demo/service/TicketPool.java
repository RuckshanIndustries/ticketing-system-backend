package com.ticketingsystemfrontend.demo.service;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {
    private int totalTickets;
    private int maxCapacity;
    public static int currentTickets = 0;
    private final Lock lock = new ReentrantLock();

    public TicketPool(int totalTickets, int maxCapacity) {
        this.totalTickets = totalTickets;
        this.maxCapacity = maxCapacity;
    }

    public synchronized boolean addTicket() {
        if (currentTickets < maxCapacity && totalTickets > 0) {
            currentTickets++;
            totalTickets--;
            return true;
        }
        return false;
    }

    public synchronized boolean removeTicket() {
        if (currentTickets > 0) {
            currentTickets--;
            return true;
        }
        return false;
    }

    public synchronized int getCurrentTickets() {
        return currentTickets;
    }

    public synchronized int getTotalTickets() {
        return totalTickets;
    }
}
