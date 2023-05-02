import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import site.nomoreparties.stellarburgers.Bun;

import static org.junit.Assert.assertEquals;
@RunWith(Parameterized.class)
public class BunParametrizedTest {
    private String name;
    private float price;
    private Bun bun;
    public BunParametrizedTest (String name,float price){
        this.name = name;
        this.price = price;
    }
    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object [][]{
                {"S",0},
                {"StringVeryLong StringVeryLong StringVeryLong",1.4E-45f},
                {"String  =with#special!Characters",3.4E+38f},
                {"",-1.4E-45f},
                {null,-3.4E+38f},
                {"!@#$%^&*()?><}{][",3.4E+38f},
        };
    }
    @Before
    public void initData(){
        bun = new Bun(name,price);
    }
    @Test
    public void getNameTest (){
        assertEquals ("Ошибка сравнения name", name,bun.getName());
    }
    @Test
    public void getPriceTest(){
        assertEquals ("Ошибка сравнения price", price,bun.getPrice(),0.0);
    }
}
