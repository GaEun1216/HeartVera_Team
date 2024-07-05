package com.sparta.heartvera.domain.post.dto;

import com.sparta.heartvera.domain.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    String title;
    String content;
    int likeCount;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.likeCount = post.getLikeCount();
    }


    public boolean equals(PostResponseDto response){
        if(response != null){
            return  this.title == response.title &&
                    this.content == response.content &&
                    this.likeCount == response.likeCount;
        }
        else {
            return false;
        }
    }
}
