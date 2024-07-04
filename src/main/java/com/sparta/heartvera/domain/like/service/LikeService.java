package com.sparta.heartvera.domain.like.service;

import com.sparta.heartvera.domain.comment.dto.CommentResponseDto;
import com.sparta.heartvera.domain.comment.entity.Comment;
import com.sparta.heartvera.domain.comment.service.CommentService;
import com.sparta.heartvera.domain.like.entity.Like;
import com.sparta.heartvera.domain.like.entity.LikeEnum;
import com.sparta.heartvera.domain.like.repository.LikeRepository;
import com.sparta.heartvera.domain.post.dto.PostResponseDto;
import com.sparta.heartvera.domain.post.dto.PublicPostResponseDto;
import com.sparta.heartvera.domain.post.entity.Post;
import com.sparta.heartvera.domain.post.entity.PublicPost;
import com.sparta.heartvera.domain.post.service.PostService;
import com.sparta.heartvera.domain.post.service.PublicPostService;
import com.sparta.heartvera.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;
    private final CommentService commentService;
    private final PublicPostService publicPostService;

    private final String LIKE_SUCCESS_MESSAGE = "좋아요를 눌렀습니다.";
    private final String UNLIKE_SUCCESS_MESSAGE = "좋아요를 취소했습니다.";

    // 익명 게시물별 좋아요 toggle 기능
    @Transactional
    public String togglePostLike(User user, Long postId) {
        Post post = postService.validatePostLike(user, postId);
        Optional<Like> likeOptional = likeRepository.findByUserAndPost(user,post);
        if (likeOptional.isPresent()) {
            Like like = likeOptional.get();
            post.getLikes().remove(like);
            postService.decreasePostLike(postId);
            likeRepository.delete(like);
            return UNLIKE_SUCCESS_MESSAGE;
        } else {
            Like like = new Like(user, post,null,null, LikeEnum.POST);
            post.getLikes().add(like);
            postService.increasePostLike(postId);
            likeRepository.save(like);
            return LIKE_SUCCESS_MESSAGE;
        }
    }

    // 공개 게시물별 좋아요 toggle 기능
    @Transactional
    public String togglePublicPostLike(User user, Long postId) {
        PublicPost post = publicPostService.validatePostLike(user, postId);
        Optional<Like> likeOptional = likeRepository.findByUserAndPublicPost(user,post);
        if (likeOptional.isPresent()) {
            Like like = likeOptional.get();
            post.getLikes().remove(like);
            publicPostService.decreasePostLike(postId);
            likeRepository.delete(like);
            return UNLIKE_SUCCESS_MESSAGE;
        } else {
            Like like = new Like(user, null, post,null, LikeEnum.PUBPOST);
            post.getLikes().add(like);
            publicPostService.increasePostLike(postId);
            likeRepository.save(like);
            return LIKE_SUCCESS_MESSAGE;
        }
    }

    // 댓글별 좋아요 toggle 기능
    @Transactional
    public String toggleCommentLike(User user, Long commentId) {
        Comment comment = commentService.validateCommentLike(user, commentId);
        Optional<Like> likeOptional = likeRepository.findByUserAndComment(user,comment);
        if (likeOptional.isPresent()) {
            Like like = likeOptional.get();
            comment.getLikes().remove(like);
            commentService.decreaseCommentLike(commentId);
            likeRepository.delete(like);
            return UNLIKE_SUCCESS_MESSAGE;
        } else {
            Like like = new Like(user, null, null,comment, LikeEnum.COMMENT);
            comment.getLikes().add(like);
            commentService.increaseCommentLike(commentId);
            likeRepository.save(like);
            return LIKE_SUCCESS_MESSAGE;
        }
    }

    public Page<PostResponseDto> getLikedPostsByUser(User user, Pageable pageable) {
        Page<Post> postList = likeRepository.LikesPost(user.getUserSeq(), pageable);
        return postList.map(PostResponseDto::new);
    }

    public Page<PublicPostResponseDto> getLikedPubPostsByUser(User user, Pageable pageable) {
        Page<PublicPost> postList = likeRepository.LikesPubPost(user.getUserSeq(), pageable);
        return postList.map(PublicPostResponseDto::new);
    }

    public Page<CommentResponseDto> getLikedCommentsByUser(User user, Pageable pageable) {
        Page<Comment> commentList = likeRepository.LikesComment(user.getUserSeq(), pageable);
        return commentList.map(CommentResponseDto::new);
    }
}