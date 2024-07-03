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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;
    private final CommentService commentService;
    private final PublicPostService publicPostService;

    // 익명 게시물별 좋아요 toggle 기능
    @Transactional
    public ResponseEntity<String> togglePostLike(User user, Long postId) {
        Post post = postService.validatePostLike(user, postId);
        Optional<Like> likeOptional = likeRepository.findByUserAndPost(user,post);
        if (likeOptional.isPresent()) {
            Like like = likeOptional.get();
            post.getLikes().remove(like);
            likeRepository.delete(like);
            return ResponseEntity.ok("좋아요를 취소했습니다.");
        } else {
            Like like = new Like(user, post,null,null, LikeEnum.POST);
            post.getLikes().add(like);
            likeRepository.save(like);
            return ResponseEntity.ok("좋아요를 눌렀습니다.");
        }
    }

    // 공개 게시물별 좋아요 toggle 기능
    @Transactional
    public ResponseEntity<String> togglePublicPostLike(User user, Long postId) {
        PublicPost post = publicPostService.validatePostLike(user, postId);
        Optional<Like> likeOptional = likeRepository.findByUserAndPublicPost(user,post);
        if (likeOptional.isPresent()) {
            Like like = likeOptional.get();
            post.getLikes().remove(like);
            likeRepository.delete(like);
            return ResponseEntity.ok("좋아요를 취소했습니다.");
        } else {
            Like like = new Like(user, null, post,null, LikeEnum.POST);
            post.getLikes().add(like);
            likeRepository.save(like);
            return ResponseEntity.ok("좋아요를 눌렀습니다.");
        }
    }

    // 댓글별 좋아요 toggle 기능
    @Transactional
    public ResponseEntity<String> toggleCommentLike(User user, Long commentId) {
        Comment comment = commentService.validateCommentLike(user, commentId);
        Optional<Like> likeOptional = likeRepository.findByUserAndComment(user,comment);
        if (likeOptional.isPresent()) {
            Like like = likeOptional.get();
            comment.getLikes().remove(like);
            likeRepository.delete(like);
            return ResponseEntity.ok("좋아요를 취소했습니다.");
        } else {
            Like like = new Like(user, null, null,comment, LikeEnum.POST);
            comment.getLikes().add(like);
            likeRepository.save(like);
            return ResponseEntity.ok("좋아요를 눌렀습니다.");
        }
    }

    public Page<PostResponseDto> getLikedPostsByUser(User user,int page, int size) {
        Page<Post> postList = likeRepository.LikesPost(user.getUserSeq(), page, size);
        return postList.map(PostResponseDto::new);
    }

    public Page<PublicPostResponseDto> getLikedPubPostsByUser(User user, int page, int size) {
        Page<PublicPost> postList = likeRepository.LikesPubPost(user.getUserSeq(), page, size);
        return postList.map(PublicPostResponseDto::new);
    }

    public Page<CommentResponseDto> getLikedCommentsByUser(User user, int page, int size) {
        Page<Comment> commentList = likeRepository.LikesComment(user.getUserSeq(), page, size);
        return commentList.map(CommentResponseDto::new);
    }
}