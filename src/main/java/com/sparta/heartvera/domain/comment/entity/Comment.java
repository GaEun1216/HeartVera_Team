package com.sparta.heartvera.domain.comment.entity;

import com.sparta.heartvera.common.Timestamped;
import com.sparta.heartvera.domain.comment.dto.CommentRequestDto;
import com.sparta.heartvera.domain.like.entity.Like;
import com.sparta.heartvera.domain.post.entity.Post;
import com.sparta.heartvera.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends Timestamped {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id")
  private Long id;

  @Column(name = "contents", nullable = false)
  private String contents;

  private int likeCount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Like> likes;

  @Builder
  public Comment(CommentRequestDto requestDto, Post post, User user){
    this.contents = requestDto.getContents();
    this.post = post;
    this.user = user;
    this.likes = new ArrayList<>();
    this.likeCount = 0;
  }

  public void updateComment(CommentRequestDto requestDto){
    this.contents = requestDto.getContents();
  }

  public void increaseLike() { this.likeCount++; }

  public void decreaseLike() { this.likeCount--; }
}
