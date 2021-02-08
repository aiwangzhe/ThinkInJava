package utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class CustomList<E> extends ArrayList<E> {

    @Override
    public Iterator<E> iterator() {
        return super.iterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return super.listIterator();
    }
}
