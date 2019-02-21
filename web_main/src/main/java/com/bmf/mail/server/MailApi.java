package com.bmf.mail.server;

import com.bmf.mail.impl.MineHelperOrderManager;
import com.bmf.mail.impl.MineOrderManager;
import com.bmf.mail.impl.SpringOrderManager;
import com.bmf.mail.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sv1/mail")
public class MailApi {
//
//    @Autowired
//    private OrderManager orderManager;

    @Autowired
    private SpringOrderManager springOrderManager;

    @Autowired
    private MineOrderManager mineOrderManager;

    @Autowired
    private MineHelperOrderManager mineHelperOrderManager;

    @RequestMapping("/send")
    @ResponseBody
    public Object send() {
        return mineHelperOrderManager.placeOrder(new Order().setOrderNumber("1111")
                .setCustomer(new Order.Customer().setEmailAddress("2628649651@qq.com")
                        .setFirstName("张")
                        .setLastName("爱玲")));
    }
}
