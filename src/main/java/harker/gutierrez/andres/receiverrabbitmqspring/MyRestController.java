package harker.gutierrez.andres.receiverrabbitmqspring;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MyRestController {

    private final static String QUEUE_NAME = "messageQueueAHG";

    @Autowired
    MyBeanSingleton myBeanSingleton;

    @RequestMapping("/")
    public String index() {
        return "Hola Springboot AHG";
    }

    @RequestMapping("/recibedMessages")
    public String recibedMessages() throws IOException, TimeoutException {

        return "[AHG] Estos son los mensajes recibidos hasta el momento: " + myBeanSingleton.getMensajesRecibidos();
    }

}
