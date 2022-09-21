package slotegrator.project.helper;

import java.util.Arrays;
import java.util.List;

import static slotegrator.project.utils.CommonCollectionUtils.getRandomCollectionElement;

public class DataHelper {

    public static final List<String> FIRST_NAME_LIST = Arrays.asList("Liam", "Noah", "Oliver", "Elijah", "James",
            "William", "Benjamin", "Lucas");

    public static final List<String> SURNAME_LIST = Arrays.asList("Smith", "Johnson", "Williams", "Brown", "Jones",
            "Garcia", "Miller", "Davis");

    public static String getRandomName() {
       return getRandomCollectionElement(FIRST_NAME_LIST);
    }

    public static String getRandomSurName() {
        return getRandomCollectionElement(SURNAME_LIST);
    }
}
