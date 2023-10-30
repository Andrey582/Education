package edu.hw3.Task6;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Market implements StockMarket {

    private final Queue<Stock> stockStorage;

    public Market() {
        this.stockStorage = new PriorityQueue<>(Comparator.comparing(Stock::getPrice).reversed());
    }

    @Override
    public void add(Stock stock) {
        stockStorage.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stockStorage.poll();
    }

    @Override
    public Stock mostValuableStock() {
        return stockStorage.peek();
    }
}
