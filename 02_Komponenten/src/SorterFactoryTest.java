import org.junit.Assert;
import org.junit.Test;

public class SorterFactoryTest {
    @Test
    public void testFactory() {
        Configuration.instance.getSorterType();
        Assert.assertNotNull(SorterFactory.create());
    }

    @Test
    public  void testFactoryFail(){
        Configuration.instance.className=null;
        Assert.assertNull(SorterFactory.create());
    }
}
