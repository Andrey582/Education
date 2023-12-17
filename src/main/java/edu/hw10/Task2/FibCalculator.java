package edu.hw10.Task2;

public interface FibCalculator {

    @Cache(persist = false)
    long fib(int number);
}
