package com.bmf.sevice;

import com.bmf.model.DataWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by BMF on 2017/8/20.
 */
@Service
public class JdbcService {

    private static final Logger logger = LoggerFactory.getLogger(JdbcService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DataWrapper<Object> updateTest() {
//        int update = jdbcTemplate.update("update common_account.ac_feedback set feedbackContent = \"test\" where  feedbackId = 3;");
//        if (update == 1) {
//            int update1 = jdbcTemplate.update(
//                    "INSERT INTO `common_account`.`ac_feedback` ( `feedbackContent`,`feedbackConcatMsg`,`feedbackTel`,`feedbackTime`,`feedbacker`,`feedbackType`) VALUES ( ?,?,?,?,?,?);",
//                    "test", "test", "15011111111", new Date(), "test", "0");
////            if (update1 == 1) {
////                throw new RuntimeException("我要回滚");
////            }
//        }
//        Map<String, Object> maps = jdbcTemplate.queryForMap(" SELECT * FROM common_account.ac_base_info WHERE ubInfoId = 1 ; ");
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList(" SELECT * FROM common_account.ac_base_info LIMIT 0, 3000; ");
        int update = jdbcTemplate.update(
//                "INSERT INTO ac_feedback ( feedbackContent, feedbackConcatMsg, feedbackTel, feedbackTime, feedbacker, feedbackType) VALUES ( :feedbackContent, :feedbackConcatMsg, :feedbackTel, :feedbackTime, :feedbacker, :feedbackType )",)
                "INSERT INTO common_account.ac_feedback ( feedbackContent, feedbackConcatMsg, feedbackTel, feedbackTime, feedbacker, feedbackType) VALUES ( ?, ?, ?, ?, ?, ? )",
                "text", "msg", "15011111111", new Date(), "张三", 0);
        if (logger.isDebugEnabled()) {
//            logger.debug(maps.size() + "");
            logger.debug("sql insert {} rows;", update);
        }

        return new DataWrapper<>(1, "success").setData(null);
    }
}
