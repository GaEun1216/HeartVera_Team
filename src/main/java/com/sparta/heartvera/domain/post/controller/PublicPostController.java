package com.sparta.heartvera.domain.post.controller;

import com.sparta.heartvera.domain.post.dto.PostRequestDto;
import com.sparta.heartvera.domain.post.dto.PublicPostResponseDto;
import com.sparta.heartvera.domain.post.service.PublicPostService;
import com.sparta.heartvera.security.service.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "공개 게시판 API",description = "공개 게시판 관련된 기능을 담당하는 API 입니다.")
@RequestMapping("/api/pubposts")
public class PublicPostController {

  private final PublicPostService postService;

    @Operation(summary = "공개글 작성",description = "공개 게시글을 작성합니다.")
    @PostMapping("/")
    public ResponseEntity savePost(@Valid @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.savePost(requestDto, userDetails.getUser()));
    }

    @Operation(summary = "공개글 선택 조회",description = "선택한 공개 게시글을 조회합니다.")
    @GetMapping("/{postId}")
    public ResponseEntity getPost(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(postId));
    }

    @Operation(summary = "공개글 수정",description = "공개 게시글을 수정합니다.")
    @PatchMapping("/{postId}")
    public ResponseEntity editPost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.editPost(postId, requestDto, userDetails.getUser()));
    }

    @Operation(summary = "공개글 삭제",description = "공개 게시글을 삭제합니다.")
    @DeleteMapping("/{postId}")
    public ResponseEntity deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.deletePost(postId, userDetails.getUser()));
    }

    @Operation(summary = "공개글 전체 조회",description = "공개 게시글을 전체조회합니다.(한페이지당 5개씩 조회)")
    @GetMapping("/")
    public ResponseEntity getAllPost(@RequestParam("page") int page, @RequestParam(value = "amount", defaultValue = "5") int amount) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPost(page - 1, amount));
    }

    @Operation(summary = "팔로우한 사람들의 공개글 전체 조회 (Spring - JPA)",description = "내가 팔로우한 사람들의 글을 전체 조회합니다.(한페이지당 5개씩 조회)")
    @GetMapping("/followed")
    public ResponseEntity getFollowedPosts(
      @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam("page") int page, @RequestParam(value = "size", defaultValue = "5") int pageSize) {
     return ResponseEntity.status(HttpStatus.OK)
        .body(postService.getFollowedPosts(userDetails.getUser(), page - 1, pageSize));
     }

    @Operation(summary = "팔로우한 사람들의 공개글 전체 조회 (QueryDsl)",description = "내가 팔로우한 사람들의 글을 전체 조회합니다.(한페이지당 5개씩 조회)")
    @GetMapping("/followedPosts")
    public ResponseEntity<Page<PublicPostResponseDto>> getFollowedPubPosts(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "sortBy", defaultValue = "createAt") String sortBy) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(postService.getFollowedPubPosts(userDetails.getUser(), pageable, sortBy));
    }

}
