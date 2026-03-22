package exercise1;

public interface PositionalList<E> {

    interface Position<E> {
        E getElement();
    }

    int size();

    boolean isEmpty();

    Position<E> first();

    Position<E> last();

    Position<E> before(Position<E> p);

    Position<E> after(Position<E> p);

    Position<E> addFirst(E e);

    Position<E> addLast(E e);

    Position<E> addBefore(Position<E> p, E e);

    Position<E> addAfter(Position<E> p, E e);

    E replace(Position<E> p, E e);

    E remove(Position<E> p);

    default int indexOf(Position<E> p) {
        if (p == null) {
            return -1;
        }
        int index = 0;
        for (Position<E> walk = first(); walk != null; walk = after(walk)) {
            if (walk.equals(p)) {
                return index;
            }
            index++;
        }
        return -1;
    }
}

