package com.clownfish7.lambda;

import com.clownfish7.Apple;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

/**
 * @author You
 * @create 2020-04-11 14:56
 */
public class SupplierTest {

    @Test
    public void testSupplier() {
        Supplier<Apple> supplier = Apple::new;
        System.out.println(supplier.get());
        supplier = () -> new Apple("red", 100L);
        System.out.println(supplier.get());
    }
}
