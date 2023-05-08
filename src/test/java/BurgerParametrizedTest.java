import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import site.nomoreparties.stellarburgers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerParametrizedTest {
    Burger burgerSpy;
    Bun bun;
    List<Ingredient> ingredients;
    private final List<Integer> listNumbersIngredient;
    List<Ingredient> ingredientsForParametrizedTest = new ArrayList<>();
    List<Integer> listNumbersBun;
    public BurgerParametrizedTest (Integer[] listInteger, Integer[] numberBun){
        this.listNumbersIngredient = Arrays.asList(listInteger);
        this.listNumbersBun = Arrays.asList(numberBun);
    }
    @Parameterized.Parameters
    public static Integer[][][] getData() {
        return new Integer[][][]{
                {{5},{0}},
                {{2, 4, 5},{2}},
                {{3, 2},{1}}
        };
    }
    @Before
    public void initData(){
        //инициализация моков
        MockitoAnnotations.initMocks(this);

        //создаем шпионы для объектов классов Database и Burger
        Database databaseObjectForSpy = new Database();
        Database databaseSpy  = Mockito.spy(databaseObjectForSpy);

        Burger burgerObjectForSpy = new Burger();
        burgerSpy  = Mockito.spy(burgerObjectForSpy);

        bun = databaseSpy.availableBuns().get(listNumbersBun.get(0));
        //заполнение списка ингредиентов согласно параметрам теста
        for (Integer s : listNumbersIngredient) {
            ingredients = databaseSpy.availableIngredients();
            ingredientsForParametrizedTest.add(ingredients.get(s));
        }
    }
    @Test
    public void getReceiptTest (){
        //заполнение булкой и ингредиентами
        burgerSpy.setBuns(bun);
        for (Ingredient s : ingredientsForParametrizedTest) {
            burgerSpy.addIngredient(s);
        }
        //мок-шпион для метода getReceipt() в классе Burger
        Mockito.when(burgerSpy.getPrice()).thenReturn(380F);
        //получение фактического чека
        String actualReceipt = burgerSpy.getReceipt();

        // распечатка ожидаемого чека
        StringBuilder expectedReceipt = new StringBuilder(String.format("(==== %s ====)%n", bun.name));
        for (Ingredient s : ingredientsForParametrizedTest) {
            expectedReceipt.append(String.format("= %s %s =%n", s.type.toString().toLowerCase(),
                    s.name));
        }
        expectedReceipt.append(String.format("(==== %s ====)%n", bun.name));
        expectedReceipt.append(String.format("%nPrice: %f%n", burgerSpy.getPrice()));

        assertEquals ("Ошибка сравнения чеков",expectedReceipt.toString() ,actualReceipt);
    }

}
