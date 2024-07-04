package com.sparta.heartvera.domain.post.entity;

import com.sparta.heartvera.common.Timestamped;
import com.sparta.heartvera.domain.like.entity.Like;
import com.sparta.heartvera.domain.post.dto.PostRequestDto;
import com.sparta.heartvera.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "public_post")
@NoArgsConstructor
public class PublicPost extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pubpost_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private int likeCount;

    @OneToMany(mappedBy = "publicPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes;

    @Builder
    public PublicPost(String content, User user){
        this.user = user;
        this.content = content;
        this.likes = new ArrayList<>();
        this.likeCount = 0;
    }

    public PublicPost(PostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.user = user;
        this.likes = new ArrayList<>();
        this.likeCount = 0;
    }

    public void update(PostRequestDto requestDto) {

        if (requestDto.getTitle() != null) {
            this.title = requestDto.getTitle();
        }
        if (requestDto.getContent() != null) {
            this.content = requestDto.getContent();
        }
    }

    public void increaseLike() { this.likeCount++; }

    public void decreaseLike() { this.likeCount--; }
}
