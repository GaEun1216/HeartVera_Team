package com.sparta.heartvera.domain.post.dto;

import com.sparta.heartvera.domain.post.entity.PublicPost;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PublicPostResponseDto {
    String title;
    String content;
    String userName;
    int likeCount;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;

    public PublicPostResponseDto(PublicPost post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userName = post.getUser().getUserName();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.likeCount = post.getLikeCount();
    }
}
