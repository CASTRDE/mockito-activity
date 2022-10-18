package com.gtp.mock.mockingDemo;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private final List<String> currentOrders;
    private final Waiter waiter;


    public Customer(List<String> orders, Waiter waiter) {
        currentOrders = new ArrayList<>();
        if(orders!=null){
            currentOrders.addAll(orders);
        }
        this.waiter = waiter;
    }

    public String giveOrder(String order){
        waiter.takeOrder(order);
        System.out.println("Give order " + order);
        return "Give order " + order;
    }

    public boolean confirmOrder(){
        return currentOrders.contains(waiter.repeatOrder());
    }

    public String followupOrder() throws InterruptedException {
        if(currentOrders!=null){
            waiter.relayOrder();
            return "I would like to follow up my order";
        }
        return "Thanks for the follow-up";
    }

    public Boolean isOrderServed(){
        waiter.serveOrder();
        currentOrders.clear();
        return true;
    }

    public String eatOrder() throws InterruptedException {
        return isOrderServed().equals(true) ? "I will eat my meal" : followupOrder();
    }
}
