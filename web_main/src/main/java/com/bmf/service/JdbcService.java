package com.bmf.service;

import com.bmf.model.ModelAcFeedback;
import com.bmf.model.base.DataWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BMF on 2017/8/20.
 */
@Service
public class JdbcService {

    private static final Logger logger = LoggerFactory.getLogger(JdbcService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public DataWrapper<Object> test() {
//        int update = jdbcTemplate.update("update common_account.ac_feedback set feedbackContent = \"test\" where  feedbackId = 19;");
//        if (update == 1) {
//            int update1 = jdbcTemplate.update(
//                    "INSERT INTO `common_account`.`ac_feedback` ( `feedbackContent`,`feedbackConcatMsg`,`feedbackTel`,`feedbackTime`,`feedbacker`,`feedbackType`) VALUES ( ?,?,?,?,?,?);",
//                    "test", "test", "15011111111", new Date(), "test", "0");
//            if (update1 == 1) {
//                throw new RuntimeException("我要回滚");
//            }
//        }
//        Map<String, Object> maps = jdbcTemplate.queryForMap(" SELECT * FROM common_account.ac_base_info WHERE ubInfoId = 1 ; ");
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList(" SELECT * FROM common_account.ac_base_info LIMIT 0, 3000; ");
//        int update = jdbcTemplate.update(
////                "INSERT INTO ac_feedback ( feedbackContent, feedbackConcatMsg, feedbackTel, feedbackTime, feedbacker, feedbackType) VALUES ( :feedbackContent, :feedbackConcatMsg, :feedbackTel, :feedbackTime, :feedbacker, :feedbackType )",)
//                "INSERT INTO common_account.ac_feedback ( feedbackContent, feedbackConcatMsg, feedbackTel, feedbackTime, feedbacker, feedbackType) VALUES ( ?, ?, ?, ?, ?, ? )",
//                "text", "msg", "15011111111", new Date(), "张三", 0);
//        int update = jdbcTemplate.update("UPDATE common_account.ac_feedback SET feedbackContent = 'update'");
        ModelAcFeedback modelAcFeedback = jdbcTemplate.queryForObject(
                " SELECT * FROM common_account.ac_feedback WHERE feedbackId = ? ; ",
                new Object[]{20},
                new int[]{Types.INTEGER},
                ModelAcFeedback.Mapper.getRowMapper());
//        for (int i = 0; i < 10; i++) {
//            System.out.println(ModelAcFeedback.Mapper.getRowMapper());
//        }
//        jdbcTemplate.up
        if (logger.isDebugEnabled()) {
//            logger.debug(maps.size() + "");
//            logger.debug("sql insert {} rows;", update);
            logger.debug("select result [{}]", modelAcFeedback.getFeedbackId());
        }

//        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
//            @Override
//            protected void doInTransactionWithoutResult(TransactionStatus status) {
//                Object savepoint = status.createSavepoint();
//                try {
        updateJdbc();
//                    status.releaseSavepoint(savepoint);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    status.rollbackToSavepoint(savepoint);
//                }
//            }
//        });
        updateJdbc();

//        myWay();

        return new DataWrapper<>(1, "success").setData(modelAcFeedback)
                .setTime(System.currentTimeMillis());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT, timeout = 3000, rollbackFor = {RuntimeException.class})
    private void updateJdbc() {
        int update = jdbcTemplate.update("update common_account.ac_feedback set feedbackContent = \"te555555555st\" where  feedbackId = 19;");
        if (update == 1) {
            int update1 = jdbcTemplate.update(
                    "INSERT INTO `common_account`.`ac_feedback` ( `feedbackContent`,`feedbackConcatMsg`,`feedbackTel`,`feedbackTime`,`feedbacker`,`feedbackType`) VALUES ( ?,?,?,?,?,?);",
                    "tes345353t", "tes54353t", "15011111111", new Date(), "test", "0");
            if (update1 == 1) {
                throw new RuntimeException("我要回滚");
            }
        }
    }

    public void myWay() {
        int update = jdbcTemplate.update("update common_account.ac_feedback set feedbackContent = \"test\" where  feedbackId = 20;");
        if (update == 1) {
            int update1 = jdbcTemplate.update(
                    "INSERT INTO `common_account`.`ac_feedback` ( `feedbackContent`,`feedbackConcatMsg`,`feedbackTel`,`feedbackTime`,`feedbacker`,`feedbackType`) VALUES ( ?,?,?,?,?,?);",
                    "test", "test", "15011111111", new Date(), "test", "0");
            if (update1 == 1) {
                throw new RuntimeException("我要回滚");
            }
        }
    }

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DataWrapper<Object> testName() {
        HashMap<String, Integer> hashMap = new HashMap<>(1);
        hashMap.put("ubInfoId", 1);
        Map<String, Object> stringObjectMap = namedParameterJdbcTemplate.queryForMap(" SELECT * FROM common_account.ac_base_info WHERE ubInfoId = :ubInfoId ; ",
                hashMap);
        return new DataWrapper<>(1, "succ").setData(stringObjectMap);
    }
}
