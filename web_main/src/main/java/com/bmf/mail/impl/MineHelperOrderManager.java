package com.bmf.mail.impl;

import com.bmf.mail.func.OrderManager;
import com.bmf.mail.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MineHelperOrderManager implements OrderManager {

    @Autowired
    private JavaMailSender mailSender;

//    public void setMailSender(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }

    public String placeOrder(final Order order) {

        // Do the business calculations...

        // Call the collaborators to persist the order...

        try {
            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(order.getCustomer().getEmailAddress());
            helper.setFrom("752294972@qq.com");
//            helper.setText("<html><body><div>Thank you for ordering</div><img src='cid:text.jpg'></body></html>!");
            helper.setText("<html><body><div>Thank you for ordering</div><img src='cid:text.jpg'></body></html>!", true);
//            helper.addAttachment("text.jpg",
//                    new FileSystemResource(new File("C:\\Users\\BMF\\Desktop\\file\\Cgo8PFU5GPyAR1JlAAAxs2bgWiU606.jpg")));
            helper.addInline("text.jpg",
                    new FileSystemResource(new File("C:\\Users\\BMF\\Desktop\\file\\Cgo8PFU5GPyAR1JlAAAxs2bgWiU606.jpg")));
            this.mailSender.send(message);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
