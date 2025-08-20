package com.green.greengram.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class FeedLikeIds implements Serializable {
    @Column(name = "feed_id")
    private Long feedId;
    @Column(name = "user_id")
    private Long userId;
}
