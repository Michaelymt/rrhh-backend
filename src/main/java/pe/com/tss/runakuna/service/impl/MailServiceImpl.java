package pe.com.tss.runakuna.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import pe.com.tss.runakuna.service.MailService;
import org.thymeleaf.context.Context;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by josediaz on 13/12/2016.
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class MailServiceImpl implements MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailContentBuilder mailContentBuilder;

    @Value("${spring.mail.host}")
    String host;

    @Value("${spring.mail.sender}")
    String sender;


    @Value("${spring.mail.fromIP}")
    String hostIpAddress;


    public void prepareAndSend(String recipient, String subject , String message) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(sender);
            messageHelper.setTo(recipient);
            messageHelper.setSubject(subject);
            String content = mailContentBuilder.build(message);
            messageHelper.setText(content, true);
        };
        try {
            javaMailSender.send(messagePreparator);
        } catch (MailException e) {
            LOGGER.error("Error sending html email", e);
        }
    }

    @Override
    public void sendEmail(String subject, String body, String sendToEmail) {
        LOGGER.info("Sending email to {}", sendToEmail);
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(sendToEmail);
            helper.setReplyTo(sender);
            helper.setFrom(sender);
            helper.setSubject(subject);
            helper.setText(body);
        } catch (MessagingException e) {
            LOGGER.error("Error Building email body", e);
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("Email host: {}", host);
        }
        javaMailSender.send(mail);
    }


}
