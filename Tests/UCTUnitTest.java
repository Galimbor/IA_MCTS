import org.junit.Assert;
import org.junit.Test;

public class UCTUnitTest {

    @Test
    public void testComputerUCT() {
        double obtained = UCT.calculateUCT(3, 2.3, 3);

        double expected = 0.7666666667;

        Assert.assertEquals(expected, obtained, 10);


        obtained = UCT.calculateUCT(8226, 86, 7350);

        expected = 0.0015524687;

        Assert.assertEquals(expected, obtained, 10);


    }

    @Test
    public void testComputerUCT2() {
        double obtained = UCT.calculateUCT(3226, 3300, 3225);

        double expected = 1.030217988;

        Assert.assertEquals(expected, obtained, 10);

        obtained = UCT.calculateUCT(2379, -43, 50);

        expected = 3.278695447;

        Assert.assertEquals(expected, obtained, 10);
    }


}
