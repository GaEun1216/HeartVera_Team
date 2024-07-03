package com.sparta.heartvera.domain.user.dto;

import com.sparta.heartvera.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

  private String userId;
  private String userName;
  private String userEmail;
  private String description;
  private int postLikes;
  private int pubPostLikes;
  private int commentLikes;

  public UserResponseDto(User user, int postLikes, int pubPostLikes, int commentLikes) {
    this.userId = user.getUserId();
    this.userName = user.getUserName();
    this.userEmail = user.getUserEmail();
    this.description = user.getDescription();
    this.postLikes = postLikes;
    this.pubPostLikes = pubPostLikes;
    this.commentLikes = commentLikes;
  }

  public UserResponseDto(User user) {
    this.userId = user.getUserId();
    this.userName = user.getUserName();
    this.userEmail = user.getUserEmail();
    this.description = user.getDescription();
  }
}
