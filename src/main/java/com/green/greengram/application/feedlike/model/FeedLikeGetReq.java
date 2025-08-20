package com.green.greengram.application.feedlike.model;

import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;
import java.util.List;

@Getter
@ToString
public class FeedLikeGetReq {
    private Long signedUserId;
    private List<Long> feedIdList;

    @ConstructorProperties( {"signed_user_id", "feed_id"} )
    public FeedLikeGetReq(Long signedUserId, List<Long> feedIdList) {
        this.signedUserId = signedUserId;
        this.feedIdList = feedIdList;
    }
}
