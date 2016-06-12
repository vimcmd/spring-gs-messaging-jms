package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import java.io.File;

/**
 * POJO-driven approach (https://spring.io/understanding/POJO)
 */
@Component
public class Receiver {

    /**
     * Get a copy of application context
     */
    @Autowired
    ConfigurableApplicationContext context;

    /**
     * When you receive message, print it out, then shut down the application.
     * Finally, clean up any ActiveMQ server stuff.
     */
    @JmsListener(destination = "mailbox-destination", containerFactory = "myJmsContainerFactory")
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        context.close();
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
    }


}
