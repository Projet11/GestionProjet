package be.esi.projet11.gestionprojet.test.mail;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import be.esi.projet11.gestionprojet.exception.MailException;
import be.esi.projet11.gestionprojet.mail.Mailer;
import org.junit.Test;

/**
 *
 * @author g34771
 */
public class MailerTest {
    
    
    @Test (expected=MailException.class)
    public void testDestinataire() throws MailException{
        String destinataires="nimportequoi";
        String body= "coucou";
        String subject = "test";
        Mailer.send(destinataires, subject,body);
    }
    
    @Test (expected=MailException.class)
    public void testChampNull() throws MailException{
        String destinataires="so-tino@dwebconsulting.eu";
        String body= null;
        String subject = "test";
        Mailer.send(destinataires, subject,body);
    }
    
    
    @Test (expected=MailException.class)
    public void testChampVide() throws MailException{
        String destinataires="so-tino@dwebconsulting.eu";
        String body= "";
        String subject = "";
        Mailer.send(destinataires, subject,body);
    }
    
    @Test 
    public void testMultipleAdresse() throws MailException{
        String destinataires="so-tino@dwebconsulting.eu,yahoo@yahoo.fr,gmail@gamil.com";
        String body= "test";
        String subject = "test";
        Mailer.send(destinataires, subject,body);
    }
    
    @Test
    public void testSimpleAdresse() throws MailException{
        String destinataires="so-tino@dwebconsulting.eu";
        String body= "teston";
        String subject = "test";
        Mailer.send(destinataires, subject,body);
    }
    
}
