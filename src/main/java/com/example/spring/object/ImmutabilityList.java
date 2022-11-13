package com.example.spring.object;


import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ImmutabilityList<E> extends ArrayList<E> implements List<E> {


    public ImmutabilityList() {
        super();
    }

    public ImmutabilityList(Collection<? extends E> c) {
        super(c);
    }


    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("can not use set method");
    }

    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException("can not use add method");
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("can not use add method");
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("can not use remove method");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("can not use remove method");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("can not use addAll method");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("can not use addAll method");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("can not use removeAll method");
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException("can not use removeIf method");
    }

    @Override
    public Stream<E> stream() {
        return super.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        return super.parallelStream();
    }
}
