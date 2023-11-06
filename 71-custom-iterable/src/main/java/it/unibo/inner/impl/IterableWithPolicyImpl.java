package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

  private final List<T> data;
  Predicate<T> filter;

  public IterableWithPolicyImpl(T[] data) {
    this(
        data,
        new Predicate<T>() {
          public boolean test(T t) {
            return true;
          }
        });
  }

  public IterableWithPolicyImpl(T[] data, Predicate<T> predicate) {
    this.data = List.of(data);
    this.filter = predicate;
  }

  public class DataIterator implements Iterator<T> {

    private int i = 0;

    private boolean isArrayFinished() {
      return data.size() == i;
    }


    public boolean hasNext() {
      for (; !isArrayFinished(); i++) {
        if (filter.test(data.get(i))) {
          return true;
        }
      }
      return false;
    }

    public T next() {
      if (isArrayFinished()) {
        throw new NoSuchElementException();
      }
      return data.get(i++);
    }
  }

  @Override
  public Iterator<T> iterator() {
    return new DataIterator();
  }

  @Override
  public void setIterationPolicy(Predicate<T> filter) {
    this.filter = filter;
  }

}
