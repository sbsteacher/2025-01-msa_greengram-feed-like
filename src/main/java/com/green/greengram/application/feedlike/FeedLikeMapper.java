package com.green.greengram.application.feedlike;

import com.green.greengram.application.feedlike.model.FeedLikeGetReq;
import com.green.greengram.application.feedlike.model.FeedLikeGetRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedLikeMapper {
    List<FeedLikeGetRes> findAllLikeCountAndWhetherLikeByFeedIdInAndUserId(FeedLikeGetReq req);
}
