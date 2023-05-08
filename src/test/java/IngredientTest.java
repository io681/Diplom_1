import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import site.nomoreparties.stellarburgers.Database;
import site.nomoreparties.stellarburgers.Ingredient;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class IngredientTest {
    private Ingredient ingredient;
    @Spy
    Database databaseSpy;
    @Before
    public void initData(){
        ingredient = databaseSpy.availableIngredients().get(0);
    }
    @Test
    public void getPriceTest (){
        assertEquals ("Ошибка сравнения", ingredient.price,ingredient.getPrice(),0.0);
    }
    @Test
    public void getNameTest(){
        assertEquals ("Ошибка сравнения", ingredient.name,ingredient.getName());
    }

    @Test
    public void getTypeTest(){
        assertEquals ("Ошибка сравнения", ingredient.type,ingredient.getType());
    }

}
