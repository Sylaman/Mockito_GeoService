import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class MessageSenderImplTest {
    String someIpAddress = "User's IP";

    @Mock
    private GeoServiceImpl geoService;

    @Mock
    private LocalizationServiceImpl localizationService;

    @Test
    void sendTestUSA() {
        String expectedMessage = "Welcome";
        String actualMessage;

        Mockito.when(geoService.byIp(someIpAddress)).thenReturn(new Location("New York", Country.USA,
                " 10th Avenue", 32));
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, someIpAddress);
        actualMessage = messageSender.send(headers);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void sendTestRussia() {
        String expectedMessage = "Добро пожаловать";
        String actualMessage;

        Mockito.when(geoService.byIp(someIpAddress)).thenReturn(new Location("Moscow", Country.RUSSIA,
                "Lenina", 15));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, someIpAddress);
        actualMessage = messageSender.send(headers);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
