package com.sparta.heartvera.domain.like.controller;

import com.sparta.heartvera.domain.like.dto.LikeCountResponseDto;
import com.sparta.heartvera.domain.like.entity.LikeEnum;
import com.sparta.heartvera.domain.like.service.LikeService;
import com.sparta.heartvera.security.service.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "좋아요 API",description = "좋아요 관련된 기능을 담당하는 API 입니다.")
@RequestMapping("/api")
public class LikeController {

    private final LikeService likeService;

    // 익명 게시물별 좋아요 토글
    @Operation(summary = "익명 게시물별 좋아요 토글",description = "익명 게시물별 좋아요를 추가/삭제합니다.")
    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<String> togglePostLike(@PathVariable(name = "postId") long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likeService.togglePostLike(userDetails.getUser(), postId);
    }

    // 댓글별 좋아요 토글
    @Operation(summary = "댓글별 좋아요 토글",description = "댓글별 좋아요를 추가/삭제합니다.")
    @PostMapping("/posts/{postId}/comments/{commentId}/like")
    public ResponseEntity<String> toggleCommentLike(@PathVariable(name = "commentId") long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likeService.toggleCommentLike(userDetails.getUser(), commentId);
    }

    // 공개 게시물별 좋아요 토글
    @Operation(summary = "공개 게시물별 좋아요 토글",description = "공개 게시물별 좋아요를 추가/삭제합니다.")
    @PostMapping("/pubposts/{postId}/like")
    public ResponseEntity<String> togglePublicPostLike(@PathVariable(name = "postId") long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likeService.togglePublicPostLike(userDetails.getUser(), postId);
    }

}