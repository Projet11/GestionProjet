/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.mail;

import be.esi.projet11.gestionprojet.exception.MailException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author g34771
 */
public class Mailer {

    final static String username = "noreplygestionprojet@gmail.com";
    final static String password = "a1z2e3r4!";

    public static void send(String destinataires, String sujet, String corp) throws MailException {

        if (sujet == null || corp == null || destinataires == null || sujet.equals("") || corp.equals("") || destinataires.equals("")) {
            throw new MailException("Le sujet, le corpt et le destinataire ne peuvent pas etre vide ou null");
        }
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinataires));
            message.setSubject(sujet);
            message.setText(corp);

            Transport.send(message);
        } catch (AddressException e) {
            throw new MailException("Adresse invalide\n" + e.getMessage());
        } catch (MessagingException e) {
            throw new MailException("Erreur dans le message\n" + e.getMessage());
        }
    }
}