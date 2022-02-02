package linked_list;

public final class Node<T> {
    private T data;
    private Node<T> prev;
    private Node<T> next;

    public Node() {

    }

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> prev, Node<T> next) {
        setData(data);
        setPrev(prev);
        setNext(next);
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setNext(Node<T> next) {
        if (next != null) {
            next.prev = this;
        }
        this.next = next;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setPrev(Node<T> prev) {
        if (prev != null) {
            prev.next = this;
        }
        this.prev = prev;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public boolean hasPrev() {
        return prev != null;
    }

    public boolean hasNext() {
        return next != null;
    }

    public boolean isHead() {
        return !hasPrev();
    }

    public boolean isTail() {
        return !hasNext();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
