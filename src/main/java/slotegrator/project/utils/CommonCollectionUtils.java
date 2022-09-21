package slotegrator.project.utils;

import java.util.*;

public class CommonCollectionUtils {

    public static <E> E getRandomCollectionElement(Collection<E> clazz) {
        return getRandomSubset(clazz, 0, 1).get(0);
    }

    public static <T> List<T> getRandomSubset(Collection<T> coll, int min, int max) {
        if (min < 0 || min > coll.size()) {
            throw new IllegalArgumentException("Minimal elements count is less than zero or greater than given collection");
        }
        if (max < min) {
            throw new IllegalArgumentException("Maximal elements count is less than minimal");
        }
        if (max > coll.size()) {
            max = coll.size();
        }

        List<T> list = new LinkedList<T>(coll);
        Collections.shuffle(list);
        return new ArrayList<>(list.subList(min, max));
    }
}
