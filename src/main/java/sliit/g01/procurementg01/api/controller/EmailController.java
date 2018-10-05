package sliit.g01.procurementg01.api.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sliit.g01.procurementg01.api.model.Payment;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author viraj
 **/

@RestController
@RequestMapping("/email")
public class EmailController {

    private static String USER_NAME = "vjanuradhawick";
    private static String PASSWORD = "Bcskaranawa";


    @RequestMapping(method = RequestMethod.POST)
    public void getData(@Validated @RequestBody final Payment payment) {
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { payment.getEmail()  };
        String subject = "Payment Notification From Procurement Company";
        String body =   "Dear Supplier,\n" +
                "For Order: "+payment.getOrderID()+"\n"+
                "Amount of Rs. "+payment.getTotAmount() +
                " is successfully deposited to Bank Account No: "+payment.getBankNo() +
                " on "+payment.getDate() ;
        sendFromGMail(from, pass, to, subject, body);
    }


    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";

        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");


        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {


            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }


            message.setSubject(subject);
            message.setText(body);


            Transport transport = session.getTransport("smtp");


            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}