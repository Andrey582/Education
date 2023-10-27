package edu.hw3.Task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {

    private final Object[] collection;
    private int currentIndex;

    public BackwardIterator(Collection<T> collection) {
        this.collection = collection.toArray();
        currentIndex = collection.size();
    }

    @Override
    public boolean hasNext() {
        return currentIndex > 0;
    }

    @Override
    public T next() {
        if (hasNext()) {
            currentIndex--;
            return (T) collection[currentIndex];
        } else {
            throw new NoSuchElementException();
        }
    }
}
