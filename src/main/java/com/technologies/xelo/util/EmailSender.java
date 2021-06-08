package com.technologies.xelo.util;

import com.technologies.xelo.enums.EmailTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;


@Service("EmailService")
public class EmailSender {

    private static Log logger = LogFactory.getLog(EmailSender.class);
    private JavaMailSender javaMailSender;
    private SpringTemplateEngine thymeleafTemplateEngine;

    private final Map<String,String> props;

    public EmailSender(@Value("#{${server.configs}}") Map<String,String> props,
                       JavaMailSender javaMailSender,
                       SpringTemplateEngine thymeleafTemplateEngine
                       ) {
        this.props = props;
        this.javaMailSender = javaMailSender;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    }

    public void sendSimpleMessage(String to, String subject, String text, String...cc) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(this.props.get("username"));
            message.setTo(to);
            if(cc != null && cc.length > 0){
                message.setCc(cc);
            }
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

    public void sendMessageUsingThymeleafTemplate(
            String to, String[] cc,  String subject, Map<String, Object> templateModel, EmailTemplate template)
            throws MessagingException {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        String htmlBody = thymeleafTemplateEngine.process(template.getValue(), thymeleafContext);
        logger.info("<<<<<<<<<<<<<<<<<<<<<< Template Body Thymleaf >>>>>>>>>>>>>>>>>>> ");
        logger.info( " HTML Body  " + htmlBody);
        sendHtmlMessage(to, cc, subject, htmlBody);
    }


    private void sendHtmlMessage(String to, String[] cc, String subject, String htmlBody) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(this.props.get("noreply"));
        helper.setTo(to);
        if(cc != null ){
            helper.setCc(cc);
        }
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        //helper.addInline("attachment.png", resourceFile);
        javaMailSender.send(message);
        logger.info("<<<<<<<<<<< EMAIL SEND SEND  >>>>>>>>>>>>>>>");

    }
}
