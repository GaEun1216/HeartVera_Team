package com.sparta.heartvera.domain.comment.service;

import com.sparta.heartvera.common.exception.CustomException;
import com.sparta.heartvera.common.exception.ErrorCode;
import com.sparta.heartvera.domain.comment.dto.CommentRequestDto;
import com.sparta.heartvera.domain.comment.dto.CommentResponseDto;
import com.sparta.heartvera.domain.comment.dto.PublicCommentResponseDto;
import com.sparta.heartvera.domain.comment.entity.Comment;
import com.sparta.heartvera.domain.comment.repository.CommentRepository;
import com.sparta.heartvera.domain.like.repository.LikeRepository;
import com.sparta.heartvera.domain.post.entity.Post;
import com.sparta.heartvera.domain.post.service.PostService;
import com.sparta.heartvera.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final PostService postService;

    // 댓글 작성
    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto, User user) {
        Post post = findPostById(postId);
        Comment comment = commentRepository.save(new Comment(requestDto, post, user));
        return new CommentResponseDto(comment);
    }

    // 댓글 조회
    public List<CommentResponseDto> getComments(Long postId) {
        Post post = findPostById(postId);
        List<Comment> comments = post.getComments();
        List<CommentResponseDto> commentList = new ArrayList<>();
        for (Comment comment : comments) {
            commentList.add(new CommentResponseDto(comment));
        }
        return commentList;
    }

    // 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long postId, Long commentId, CommentRequestDto requestDto,
                                            User user) {
        Post post = findPostById(postId);
        Comment comment = findCommentById(commentId);
        if (!comment.getUser().getUserSeq().equals(user.getUserSeq())) {
            throw new CustomException(ErrorCode.COMMENT_NOT_USER);
        }
        comment.updateComment(requestDto);
        return new CommentResponseDto(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long postId, Long commentId, User user) {
        Comment comment = findCommentById(commentId);
        if (!comment.getUser().getUserSeq().equals(user.getUserSeq())) {
            throw new CustomException(ErrorCode.COMMENT_NOT_USER);
        }
        commentRepository.delete(comment);
    }

    // 좋아요 증감 함수
    @Transactional
    public void increaseCommentLike(Long commentId){
        Comment comment = findCommentById(commentId);
        comment.increaseLike();
    }

    @Transactional
    public void decreaseCommentLike(Long commentId){
        Comment comment =  findCommentById(commentId);
        comment.decreaseLike();
    }

    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new CustomException(ErrorCode.COMMENT_NOT_FOUND));
    }


    private Post findPostById(Long postId) {
        return postService.findById(postId);
    }

    public List<PublicCommentResponseDto> getAllCommentForAdmin() {
        return commentRepository.findAll().stream().map(PublicCommentResponseDto::new).toList();
    }

    //좋아요 유효성 검사
    public Comment validateCommentLike(User user, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->
                new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        if(comment.getUser().getUserSeq().equals(user.getUserSeq())){
            throw new CustomException(ErrorCode.COMMENT_SAME_USER);
        }
        return comment;
    }

}