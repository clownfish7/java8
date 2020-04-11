package com.clownfish7.optional;

import com.clownfish7.Car;
import com.clownfish7.Insurance;
import com.clownfish7.Person;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author You
 * @create 2020-04-12 0:47
 */
public class OptionalTest {

    @Test
    public void testOptionalEmpty() {
        Optional<Person> optional = Optional.<Person>empty();
        assert !optional.isPresent() : "err";
    }

    @Test
    public void testOptionalofNullable() {
        Optional<Person> optional = Optional.ofNullable(new Person());
        assert optional.isPresent() : "err";
    }

    @Test
    public void testOptionalof() {
        Optional<Person> optional = Optional.of(new Person());
        assert optional.isPresent() : "err";
    }

    @Test
    public void testOptionalIfPresent() {
        Optional<Person> optional = Optional.of(new Person());
        optional.ifPresent(System.out::println);
    }

    @Test
    public void testOptionalOrElse(){
        Optional<Person> optional = Optional.empty();
        Person person = optional.orElse(new Person());
    }

    @Test
    public void testOptionalOrElseGet(){
        Optional<Person> optional = Optional.empty();
        Person person = optional.orElseGet(Person::new);
    }

    @Test
    public void testOptionalOrElseThrow(){
        Optional<Person> optional = Optional.empty();
//        Person person = optional.orElseThrow(RuntimeException::new);
        Person person = optional.orElseThrow(()->new RuntimeException("err"));
    }

    @Test
    public void testOptionalFilter(){
        Optional<Person> optional = Optional.of(new Person());
        Optional<Person> person = optional.filter(p -> null == p.getCar());
        person.ifPresent(System.out::println);
    }

    @Test
    public void testOptionalMap(){
        Optional<Person> optional = Optional.of(new Person());
        Optional<Optional<Car>> car = optional.map(Person::getCar);
        assert !car.isPresent() : "err";
    }

    @Test
    public void testOptionalFlatMap(){
        Optional<Person> optional = Optional.empty();
        String result = optional.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName).orElse("unknow");
        assert "unknow".equals(result) : "err";
    }

    @Test
    public void testOptionalFlatMap2(){
        Optional<Person> optional = Optional.of(new Person());
        Optional<Car> car = optional.flatMap(Person::getCar);
        Optional<Insurance> insurance = car.flatMap(Car::getInsurance);
    }
}
