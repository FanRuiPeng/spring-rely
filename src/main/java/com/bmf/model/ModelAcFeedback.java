package com.bmf.model;

import com.bmf.model.base.BaseModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by BMF on 2017/8/27.
 */
public class ModelAcFeedback extends BaseModel {
    private static final long serialVersionUID = -3132458078306139166L;
    private Integer feedbackId;
    private String feedbackContent;
    private String feedbackConcatMsg;
    private String feedbackTel;
    private Date feedbackTime;
    private String feedbacker;
    private Integer feedbackType;

    public final static String FI_FeedbackId = "feedbackId";
    public final static String FI_FeedbackContent = "feedbackContent";
    public final static String FI_FeedbackConcatMsg = "feedbackConcatMsg";
    public final static String FI_FeedbackTel = "feedbackTel";
    public final static String FI_FeedbackTime = "feedbackTime";
    public final static String FI_Feedbacker = "feedbacker";
    public final static String FI_FeedbackType = "feedbackType";

    public ModelAcFeedback() {
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public ModelAcFeedback setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
        return this;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public ModelAcFeedback setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
        return this;
    }

    public String getFeedbackConcatMsg() {
        return feedbackConcatMsg;
    }

    public ModelAcFeedback setFeedbackConcatMsg(String feedbackConcatMsg) {
        this.feedbackConcatMsg = feedbackConcatMsg;
        return this;
    }

    public String getFeedbackTel() {
        return feedbackTel;
    }

    public ModelAcFeedback setFeedbackTel(String feedbackTel) {
        this.feedbackTel = feedbackTel;
        return this;
    }

    public Date getFeedbackTime() {
        return feedbackTime;
    }

    public ModelAcFeedback setFeedbackTime(Date feedbackTime) {
        this.feedbackTime = feedbackTime;
        return this;
    }

    public String getFeedbacker() {
        return feedbacker;
    }

    public ModelAcFeedback setFeedbacker(String feedbacker) {
        this.feedbacker = feedbacker;
        return this;
    }

    public Integer getFeedbackType() {
        return feedbackType;
    }

    public ModelAcFeedback setFeedbackType(Integer feedbackType) {
        this.feedbackType = feedbackType;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModelAcFeedback that = (ModelAcFeedback) o;

        if (feedbackId != null ? !feedbackId.equals(that.feedbackId) : that.feedbackId != null) return false;
        if (feedbackContent != null ? !feedbackContent.equals(that.feedbackContent) : that.feedbackContent != null)
            return false;
        if (feedbackConcatMsg != null ? !feedbackConcatMsg.equals(that.feedbackConcatMsg) : that.feedbackConcatMsg != null)
            return false;
        if (feedbackTel != null ? !feedbackTel.equals(that.feedbackTel) : that.feedbackTel != null) return false;
        if (feedbackTime != null ? !feedbackTime.equals(that.feedbackTime) : that.feedbackTime != null) return false;
        if (feedbacker != null ? !feedbacker.equals(that.feedbacker) : that.feedbacker != null) return false;
        return feedbackType != null ? feedbackType.equals(that.feedbackType) : that.feedbackType == null;
    }

    @Override
    public int hashCode() {
        int result = feedbackId != null ? feedbackId.hashCode() : 0;
        result = 31 * result + (feedbackContent != null ? feedbackContent.hashCode() : 0);
        result = 31 * result + (feedbackConcatMsg != null ? feedbackConcatMsg.hashCode() : 0);
        result = 31 * result + (feedbackTel != null ? feedbackTel.hashCode() : 0);
        result = 31 * result + (feedbackTime != null ? feedbackTime.hashCode() : 0);
        result = 31 * result + (feedbacker != null ? feedbacker.hashCode() : 0);
        result = 31 * result + (feedbackType != null ? feedbackType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ModelAcFeedback{" +
                "feedbackId=" + feedbackId +
                ", feedbackContent='" + feedbackContent + '\'' +
                ", feedbackConcatMsg='" + feedbackConcatMsg + '\'' +
                ", feedbackTel='" + feedbackTel + '\'' +
                ", feedbackTime=" + feedbackTime +
                ", feedbacker='" + feedbacker + '\'' +
                ", feedbackType=" + feedbackType +
                '}';
    }


    public final static class Mapper implements RowMapper<ModelAcFeedback> {
        private volatile static Mapper mapper;

        @Override
        public ModelAcFeedback mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ModelAcFeedback()
                    .setFeedbackId(rs.getInt(ModelAcFeedback.FI_FeedbackId))
                    .setFeedbackConcatMsg(rs.getString(ModelAcFeedback.FI_FeedbackConcatMsg))
                    .setFeedbackContent(rs.getString(ModelAcFeedback.FI_FeedbackContent))
                    .setFeedbacker(rs.getString(ModelAcFeedback.FI_Feedbacker))
                    .setFeedbackTel(rs.getString(ModelAcFeedback.FI_FeedbackTel))
                    .setFeedbackTime(rs.getTimestamp(ModelAcFeedback.FI_FeedbackTime))
                    .setFeedbackType(rs.getInt(ModelAcFeedback.FI_FeedbackType));
        }

        public static Mapper getRowMapper() {
            if (null == mapper) {
                synchronized (Mapper.class) {
                    if (null == mapper) {
                        mapper = new Mapper();
                    }
                }
            }
            return mapper;
        }
    }
}
