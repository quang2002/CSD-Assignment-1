package linked_list;

import java.io.Serializable;

public class LinkedList<T> implements Serializable {
    private Node<T> head;
    private Node<T> tail;

    public LinkedList() {
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void addHead(T data) {
        Node<T> node = new Node<>(data, null, head);
        head = node;

        if (head.isTail()) {
            tail = head;
        }
    }

    public void addTail(T data) {
        Node<T> node = new Node<>(data, tail, null);
        tail = node;

        if (tail.isHead()) {
            head = tail;
        }
    }

    public void addAfter(Node<T> pos, T data) {
        if (pos == null) {
            return;
        }

        if (pos.isTail()) {
            addTail(data);
            return;
        }

        new Node<>(data, pos, pos.getNext());
    }

    public void addBefore(Node<T> pos, T data) {
        if (pos == null) {
            return;
        }

        if (pos.isHead()) {
            addHead(data);
            return;
        }

        new Node<>(data, pos.getPrev(), pos);
    }

    public Node<T> find(T data) {
        Node<T> it = head;

        while (it != null && !it.getData().equals(data))
            it = it.getNext();

        return it;
    }

    public boolean removeHead() {
        if (isEmpty()) {
            return false;
        }

        head = head.getNext();

        if (head != null) {
            head.setPrev(null);
        } else {
            clear();
        }

        return true;
    }

    public boolean removeTail() {
        if (isEmpty()) {
            return false;
        }

        tail = tail.getPrev();

        if (tail != null) {
            tail.setNext(null);
        } else {
            clear();
        }

        return true;
    }

    public boolean remove(Node<T> node) {
        if (node == null)
            return false;

        if (node.isHead()) {
            return removeHead();
        }

        if (node.isTail()) {
            return removeTail();
        }

        Node<T> next = node.getNext();
        Node<T> prev = node.getPrev();
        prev.setNext(next);

        return true;
    }

    public boolean remove(T data) {
        return remove(find(data));
    }

    public boolean removeAll(T data) {
        boolean result = false;

        Node<T> it = head;

        while (it != null) {
            if (it.getData().equals(data)) {
                remove(it);
                result = true;
            }
            it = it.getNext();
        }

        return result;
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }

    public void clear() {
        head = tail = null;
    }

    public void forEach(TraversalCallback<T> callback) {
        Node<T> it = head;

        while (it != null) {
            callback.callback(it.getData());
            it = it.getNext();
        }
    }

    public <E> E reduce(ReduceCallback<E, T> callback, E initialize) {
        E result = initialize;

        Node<T> it = head;

        while (it != null) {
            result = callback.callback(result, it.getData());
            it = it.getNext();
        }

        return result;
    }

    public void sort(ComparatorCallback<T> callback) {
        if (isEmpty()) {
            return;
        }

        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            Node<T> it = head;

            while (it.hasNext()) {
                if (callback.compare(it.getData(), it.getNext().getData()) > 0) {
                    T tmp = it.getData();
                    it.setData(it.getNext().getData());
                    it.getNext().setData(tmp);
                    isSorted = false;
                }
                it = it.getNext();
            }
        }
    }

    public int getSize() {
        return reduce(((result, value) -> result + 1), 0);
    }

    public Node<T> nodeAt(int position) {
        // find node at position
        // if position is negative, find node at position from tail
        // if position is positive, find node at position from head

        int size = getSize();

        if (position < 0) {
            position = size + position;
        }

        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> it = head;

        while (position-- > 0) {
            it = it.getNext();
        }

        return it;
    }

    public T get(int position) {
        return nodeAt(position).getData();
    }
}
