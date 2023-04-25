import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import site.nomoreparties.stellarburgers.Bun;
import site.nomoreparties.stellarburgers.Database;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BunTest {
    private Bun bun;
    @Spy
    Database databaseSpy;
    @Before
    public void initData(){
        bun = databaseSpy.availableBuns().get(0);
    }
    @Test
    public void getNameTest (){
        assertEquals ("Ошибка сравнения", bun.name,bun.getName());
    }
    @Test
    public void getPriceTest(){
        assertEquals ("Ошибка сравнения", bun.price,bun.getPrice(),0.0);
    }
}
