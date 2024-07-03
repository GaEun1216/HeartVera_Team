package com.sparta.heartvera.domain.like.entity;

import com.sparta.heartvera.common.Timestamped;
import com.sparta.heartvera.domain.comment.entity.Comment;
import com.sparta.heartvera.domain.post.entity.Post;
import com.sparta.heartvera.domain.post.entity.PublicPost;
import com.sparta.heartvera.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "likes")
public class Like extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = true) // null 허용
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = true) // null 허용
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pubpost_id")
    private PublicPost publicPost;

    @Column(name = "content_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private LikeEnum contentType;

    public Like(User user, Post post, PublicPost publicPost, Comment comment, LikeEnum contentType) {
        this.user = user;
        this.post = post;
        this.comment = comment;
        this.publicPost = publicPost;
        this.contentType = contentType;
    }
}
