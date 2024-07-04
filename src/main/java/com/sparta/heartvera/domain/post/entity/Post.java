package com.sparta.heartvera.domain.post.entity;

import com.sparta.heartvera.common.Timestamped;
import com.sparta.heartvera.domain.comment.entity.Comment;
import com.sparta.heartvera.domain.like.entity.Like;
import com.sparta.heartvera.domain.post.dto.PostRequestDto;
import com.sparta.heartvera.domain.user.entity.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "post")
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private int likeCount;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes;

    @Builder
    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.likes = new ArrayList<>();
        this.likeCount = 0;
    }

    public Post(PostRequestDto requestDto, User user) {
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
