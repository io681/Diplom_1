import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import site.nomoreparties.stellarburgers.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    Burger burger;
    Bun bun;
    Ingredient ingredient,ingredientNext;
    @Spy
    Database databaseSpy;
    @Spy
    Burger burgerSpy;
    @Before
    public void initData(){
        bun = databaseSpy.availableBuns().get(0);
        ingredient = databaseSpy.availableIngredients().get(0);
        ingredientNext = databaseSpy.availableIngredients().get(1);
    }
    @Test
    public void setBunTest (){
        burger = new Burger();
        burger.setBuns(bun);
        assertEquals ("Ошибка сравнения", bun,burger.bun);
    }
    @Test
    public void addIngredientTest (){
        burgerSpy.addIngredient(ingredient);
        Mockito.verify(burgerSpy, Mockito.times(1)).addIngredient(ingredient);
        assertEquals("Ошибка сравнения",ingredient,burgerSpy.ingredients.get(0));
    }
    @Test
    public void removeIngredientTest(){
        burgerSpy.ingredients.add(ingredient);
        burgerSpy.removeIngredient(0);
        Mockito.verify(burgerSpy, Mockito.times(1)).removeIngredient(0);
        List<Ingredient> expectedIngredients = new ArrayList<>();
        assertEquals("Ошибка сравнения", expectedIngredients,burgerSpy.ingredients);
    }
    @Test
    public void moveIngredientTest (){
        burgerSpy.ingredients.add(ingredient);
        burgerSpy.ingredients.add(ingredientNext);

        burgerSpy.moveIngredient(0,1);
        Mockito.verify(burgerSpy, Mockito.times(1)).moveIngredient(0,1);

        List<Ingredient> expectedIngredients = new ArrayList<>();
        expectedIngredients.add(ingredientNext);
        expectedIngredients.add(ingredient);

        assertEquals("Ошибка сравнения", expectedIngredients,burgerSpy.ingredients);
    }
    @Test
    public void getPriceTest (){
        // Создание стабов для методов bun.getPrice() и ingredient.getPrice() внутри  метода Burger.getPrice()
        Bun bunStub = Mockito.mock(Bun.class);
        Ingredient ingredientStub = Mockito.mock(Ingredient.class);
        Mockito.when(bunStub.getPrice()).thenReturn(50F);
        Mockito.when(ingredientStub.getPrice()).thenReturn(100F);

        burger = new Burger();
        burger.setBuns(bunStub);
        burger.addIngredient(ingredientStub);
        assertEquals ("Ошибка сравнения",200 ,burger.getPrice(),0.0);
    }
}
