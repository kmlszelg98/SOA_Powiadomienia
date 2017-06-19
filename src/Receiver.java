import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Enumeration;

/**
 * Created by Kamil on 29.04.2017.
 */

public class Receiver implements MessageListener{

    @Resource(mappedName = "java:/ConnectionFactory")
    private TopicConnectionFactory connectionFactory;

    static Client client = new Client();

    public void receiveMessage() {
        try {
            InitialContext context = new InitialContext();
            Topic queue = (Topic) context.lookup("java:/jms/topics/Topic");
            TopicConnection connection = connectionFactory.createTopicConnection();
            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicSubscriber subscriber = session.createSubscriber(queue);
            connection.start();
            subscriber.setMessageListener(this);
            System.out.println("Czekam na wiadomosc");
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMessage(Message message) {
        TextMessage msg=(TextMessage)message;
        Wiadomosc wiadomosc = null;
        try {
            String tekst = msg.getText();
            String typ = msg.getStringProperty("typ");
            wiadomosc = new Wiadomosc(tekst,typ);
        } catch (JMSException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Receiver:"+msg.getText());
            WebResource webResource = client
                    .resource("http://localhost:8080/Mobilne/api/mobile/post");


            Gson gson = new Gson();
            String string = gson.toJson(wiadomosc);
            System.out.println(string);
            ClientResponse resp = webResource.type("application/json").post(ClientResponse.class, string);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
