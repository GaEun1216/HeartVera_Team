package com.sparta.heartvera.domain.like.controller;

import com.sparta.heartvera.domain.comment.dto.CommentResponseDto;
import com.sparta.heartvera.domain.like.dto.LikeCountResponseDto;
import com.sparta.heartvera.domain.like.entity.LikeEnum;
import com.sparta.heartvera.domain.like.service.LikeService;
import com.sparta.heartvera.domain.post.dto.PostResponseDto;
import com.sparta.heartvera.domain.post.dto.PublicPostResponseDto;
import com.sparta.heartvera.security.service.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "좋아요 API",description = "좋아요 관련된 기능을 담당하는 API 입니다.")
@RequestMapping("/api")
public class LikeController {

    private final LikeService likeService;

    // 익명 게시물별 좋아요 토글
    @Operation(summary = "익명 게시물별 좋아요 토글",description = "익명 게시물별 좋아요를 추가/삭제합니다.")
    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<String> togglePostLike(@PathVariable(name = "postId") long postId,
                                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(likeService.togglePostLike(userDetails.getUser(), postId));
    }

    // 댓글별 좋아요 토글
    @Operation(summary = "댓글별 좋아요 토글",description = "댓글별 좋아요를 추가/삭제합니다.")
    @PostMapping("/posts/{postId}/comments/{commentId}/like")
    public ResponseEntity<String> toggleCommentLike(@PathVariable(name = "commentId") long commentId,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(likeService.toggleCommentLike(userDetails.getUser(), commentId));
    }

    // 공개 게시물별 좋아요 토글
    @Operation(summary = "공개 게시물별 좋아요 토글",description = "공개 게시물별 좋아요를 추가/삭제합니다.")
    @PostMapping("/pubposts/{postId}/like")
    public ResponseEntity<String> togglePublicPostLike(@PathVariable(name = "postId") long postId,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(likeService.togglePublicPostLike(userDetails.getUser(), postId));
    }

    // 내가 좋아요 누른 게시글 확인
    @Operation(summary = "내가 좋아요 누른 게시글 목록",description = "내가 좋아요 누른 게시글의 목록을 확인합니다.")
    @GetMapping("/posts/like")
    public ResponseEntity<Page<PostResponseDto>> getLikedPosts(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                               @RequestParam(value = "page", defaultValue = "1") int page,
                                                               @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return ResponseEntity.status(HttpStatus.OK).body(
                likeService.getLikedPostsByUser(userDetails.getUser(),pageable));
    }

    // 내가 좋아요 누른 공개게시글 확인
    @Operation(summary = "내가 좋아요 누른 공개 게시글 목록",description = "내가 좋아요 누른 공개 게시글의 목록을 확인합니다.")
    @GetMapping("/pubposts/like")
    public ResponseEntity<Page<PublicPostResponseDto>> getLikedPubPosts(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                        @RequestParam(value = "page", defaultValue = "1") int page,
                                                                        @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return ResponseEntity.status(HttpStatus.OK).body(
                likeService.getLikedPubPostsByUser(userDetails.getUser(),pageable));
    }


    // 내가 좋아요 누른 댓글 확인
    @Operation(summary = "내가 좋아요 누른 댓글 목록",description = "내가 좋아요 누른 댓글의 목록을 확인합니다.")
    @GetMapping("/comments/like")
    public ResponseEntity<Page<CommentResponseDto>> getLikedComments(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                     @RequestParam(value = "page", defaultValue = "1") int page,
                                                                     @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return ResponseEntity.status(HttpStatus.OK).body(
                likeService.getLikedCommentsByUser(userDetails.getUser(),pageable));
    }


}