import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImpTest {

    @Test
    void localeTest() {
        LocalizationServiceImpl localizationServiceImpl = new LocalizationServiceImpl();
        String expectedMessage = "Welcome";
        String actualMessage = localizationServiceImpl.locale(Country.BRAZIL);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
