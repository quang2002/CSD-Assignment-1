package linked_list;

public interface ReduceCallback<E, T> {
    public E callback(E result, T value);
}
