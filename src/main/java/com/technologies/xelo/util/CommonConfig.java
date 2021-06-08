package com.technologies.xelo.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Map;
import java.util.Properties;

@Configuration
@EnableScheduling
public class CommonConfig {

    private static Log logger = LogFactory.getLog(CommonConfig.class);

    private final Map<String,String> props;

    public CommonConfig(@Value("#{${server.configs}}") Map<String,String> props) {
        this.props = props;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(props.get("mail.smtp.host"));
        mailSender.setPort(Integer.parseInt(props.get("mail.smtp.port")));
        mailSender.setUsername(props.get("username"));
        mailSender.setPassword(props.get("password"));
        Properties _props = mailSender.getJavaMailProperties();
        props.forEach((s, s2) -> _props.setProperty(s,s2) );
        _props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        _props.put("mail.smtp.socketFactory.fallback", "true");
        return mailSender;
    }

    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("This is the test email template for your email:\n%s\n");
        return message;
    }

    @Bean
    public ResourceBundleMessageSource emailMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("/mailMessages");
        return messageSource;
    }

}
