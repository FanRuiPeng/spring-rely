package com.bmf.mail.impl;

import com.bmf.mail.func.OrderManager;
import com.bmf.mail.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class MineOrderManager implements OrderManager {

    @Autowired
    private JavaMailSender mailSender;

//    public void setMailSender(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }

    public String placeOrder(final Order order) {

        // Do the business calculations...

        // Call the collaborators to persist the order...

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(order.getCustomer().getEmailAddress()));
                mimeMessage.setFrom(new InternetAddress("752294972@qq.com"));
                mimeMessage.setText(
                        "Dear " + order.getCustomer().getFirstName() + " "
                                + order.getCustomer().getLastName()
                                + ", thank you for placing order. Your order number is "
                                + order.getOrderNumber());
            }
        };

        try {
            this.mailSender.send(preparator);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
