package com.sparta.heartvera.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentRequestDto {
  @NotBlank(message = "댓글 내용을 입력해주세요.")
  private String contents;

}
