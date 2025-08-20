package com.green.greengram.application.feedlike;

import com.green.greengram.application.feedlike.model.FeedLikeGetReq;
import com.green.greengram.application.feedlike.model.FeedLikeGetRes;
import com.green.greengram.configuration.model.ResultResponse;
import com.green.greengram.configuration.model.UserPrincipal;
import com.green.greengram.application.feedlike.model.FeedLikeToggleReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FeedLikeController {
    private final FeedLikeService feedLikeService;

    @PostMapping
    public ResultResponse<?> toggle(@AuthenticationPrincipal UserPrincipal userPrincipal
                                  , @Valid @RequestBody FeedLikeToggleReq req) {
        log.info("signedUserId: {}", userPrincipal.getSignedUserId());
        log.info("req: {}", req);
        boolean result = feedLikeService.toggle(userPrincipal.getSignedUserId(), req);
        return new ResultResponse<>(result ? "좋아요 처리" : "좋아요 취소", result); //true: 좋아요 처리, false: 좋아요 취소
    }

    @GetMapping
    public ResultResponse<?> getFeedLikeList(@ModelAttribute FeedLikeGetReq req) {
        log.info("req: {}", req);
        Map<Long, FeedLikeGetRes> result = feedLikeService.getFeedLikeList(req);
        return new ResultResponse<>(String.format("rows: %d", result.size()), result); //true: 좋아요 처리, false: 좋아요 취소
    }
}
