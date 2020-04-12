package com.clownfish7.defaultmethod;

import org.junit.jupiter.api.Test;

public class DefaultInAction {

    @Test
    public void test() {
        C c = new C();
        c.hello();
    }

    private interface A {
        default void hello() {
            System.out.println("==A.hello==");
        }
    }

    private interface B {
        default void hello() {
            System.out.println("==B.hello==");
        }
    }

    private class C implements B, A {
        @Override
        public void hello() {
            B.super.hello();
        }
    }
}
