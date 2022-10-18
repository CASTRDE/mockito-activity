package com.gtp.mock.mockingDemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class CookTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private Cook cook;

    @BeforeEach
    public void initialize(){
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void readOrder_should_add_to_queue(){
        cook = new Cook();
        cook.readOrder(Collections.singletonList("Cheesecake"));
        assertThat(outputStream.toString().trim()).isEqualTo("Order has been added to queue");
    }

    @Test
    public void isBusy_should_return_true_when_order_is_queued(){
        cook = new Cook(Collections.singletonList("Cheesecake"));
        assertThat(cook.isBusy()).isEqualTo(true);
    }

    @Test
    public void plateOrder_should_return_null_when_completedOrders_is_empty(){
        cook = new Cook(Collections.singletonList("Cheesecake"));
        List<String> output = cook.plateOrder();

        assertThat(outputStream.toString().trim()).isEqualTo("All orders are not ready yet. Please wait");
        assertThat(output).isNull();
    }

    @Test
    public void cookOrder_should_return_order_when_order_is_cooked() throws InterruptedException {
        cook = new Cook(Collections.singletonList("Cheesecake"));
        cook.cookOrder("Cheesecake");
        assertThat(outputStream.toString().trim()).isEqualTo("Cooking Cheesecake");
    }

}