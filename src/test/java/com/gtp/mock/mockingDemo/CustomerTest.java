package com.gtp.mock.mockingDemo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CustomerTest {

    private Customer customer;

    @Test
    public void giveOrder_should_return_orders(){
        Waiter mockedWaiter = Mockito.mock(Waiter.class);

        when(mockedWaiter.takeOrder("Cheesecake")).thenReturn("Cheesecake");

        customer = new Customer(Collections.singletonList("Cheesecake"), mockedWaiter);
        assertThat(customer.giveOrder("Cheesecake")).isEqualTo("Give order Cheesecake");
    }

    @Test
    public void confirmOrder_should_return_true_when_order_is_match(){
        Waiter mockedWaiter = Mockito.mock(Waiter.class);

        when(mockedWaiter.repeatOrder()).thenReturn("Cheesecake");

        customer = new Customer(Collections.singletonList("Cheesecake"), mockedWaiter);
        assertTrue(customer.confirmOrder());
    }

    @Test
    public void followupOrder_should_return_statement_when_order_is_not_served() throws InterruptedException {
        Waiter mockedWaiter = Mockito.mock(Waiter.class);

        when(mockedWaiter.takeOrder("Cheesecake")).thenReturn("Cheesecake");

        customer = new Customer(Collections.singletonList("Cheesecake"), mockedWaiter);
        assertThat(customer.followupOrder()).isEqualTo("I would like to follow up my order");
    }

    @Test
    public void eatOrder_should_return_statement_when_order_is_completed() throws InterruptedException {
        Waiter mockedWaiter = Mockito.mock(Waiter.class);

        when(mockedWaiter.serveOrder()).thenReturn(String.valueOf(true));

        customer = new Customer(Collections.singletonList("Cheesecake"), mockedWaiter);
        assertThat(customer.eatOrder()).isEqualTo("I will eat my meal");
    }


}