package edu.touro.mcon264.apps.collections;

import java.util.Iterator;

public class ArrayBasedList<T> extends ArrayCollection<T> implements ListInterface<T>{
    private void indexChecker(int index) {
        if (index < 0 || index >= numElements) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + numElements);
        }
    }
    @Override
    public void add(int index, T element) {
        // 1. Index check: allow inserting at the end (index == numElements)
        if (index < 0 || index > numElements) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + numElements);
        }

        // 2. Ensure capacity (these names may differ in your ArrayCollection)
        if (isFull()) {      // or: if (numElements == elements.length)
            enlarge();       // or whatever your grow method is called
        }

        // 3. Shift elements one position to the right
        // from the last used index down to `index`
        for (int i = numElements; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        // 4. Insert new element
        elements[index] = element;

        // 5. Update size
        numElements++;
    }

    @Override
    public T get(int index) {
        indexChecker(index);
        return elements[index];
    }

    @Override
    public T remove(int index) {
        indexChecker(index);
        T removedElement = elements[index];

        // Shift elements left to fill the gap at `index`
        for (int i = index; i < numElements - 1; i++) {
            elements[i] = elements[i + 1];
        }
        // Clear the now-unused last slot
        elements[numElements - 1] = null;

        // Decrement size exactly once
        numElements--;
        return removedElement;
    }

    @Override
    public int indexOf(T target) {
        find(target);
        if (found) {
            return location;
        } else {
            return -1;
        }
    }

    @Override
    public boolean set(int index, T element) {
        indexChecker(index);
        elements[index] = element;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < numElements;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                return elements[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

    }
}
