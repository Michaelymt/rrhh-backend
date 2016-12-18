package pe.com.tss.runakuna.service;

/**
 * Created by josediaz on 13/12/2016.
 */
public interface MailService {

    public void prepareAndSend(String recipient, String subject , String message);
    public void sendEmail(String subject, String body, String sendToEmail);
}
