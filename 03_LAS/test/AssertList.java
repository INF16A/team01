import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class AssertList<E> {
    public static void AssertIntListContains(List<Integer> values, int... itemsToContain) {
        Assert.assertEquals(itemsToContain.length, values.size());
        for (int i = 0; i < itemsToContain.length; i++) {
            Assert.assertTrue(values.contains(itemsToContain[i]));
        }
    }

    public void AssertListContains(List<E> values, E... itemsToContain) {
        Assert.assertEquals(itemsToContain.length, values.size());
        for (int i = 0; i < itemsToContain.length; i++) {
            Assert.assertTrue(values.contains(itemsToContain[i]));
        }
    }
}



