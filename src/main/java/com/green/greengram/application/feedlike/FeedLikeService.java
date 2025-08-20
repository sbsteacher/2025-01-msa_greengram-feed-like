package com.green.greengram.application.feedlike;


import com.green.greengram.application.feedlike.model.FeedLikeGetReq;
import com.green.greengram.application.feedlike.model.FeedLikeGetRes;
import com.green.greengram.entity.FeedLike;
import com.green.greengram.entity.FeedLikeIds;
import com.green.greengram.application.feedlike.model.FeedLikeToggleReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedLikeService {
    private final FeedLikeRepository feedLikeRepository;
    private final FeedLikeMapper feedLikeMapper;

    public boolean toggle(Long signedUserId, FeedLikeToggleReq req) {
        FeedLikeIds feedLikeIds = FeedLikeIds.builder()
                .feedId(req.getFeedId())
                .userId(signedUserId)
                .build();

        FeedLike feedLike = feedLikeRepository.findById(feedLikeIds).orElse(null);
        if(feedLike == null) { //좋아요가 아닌 상태   >>   좋아요인 상태로 변경
            FeedLike feedLikeSave = FeedLike.builder()
                    .id(feedLikeIds)
                    .build();

            feedLikeRepository.save(feedLikeSave);
            return true;
        }
        //좋아요인 상태
        feedLikeRepository.delete(feedLike);
        return false;
    }

    public Map<Long, FeedLikeGetRes> getFeedLikeList(FeedLikeGetReq req) {
        List<FeedLikeGetRes> feedLikeList = feedLikeMapper.findAllLikeCountAndWhetherLikeByFeedIdInAndUserId(req);
        return feedLikeList.stream().collect(
                Collectors.toMap(
                        item -> item.getFeedId(),
                        item -> item
                )
        );
    }
}
