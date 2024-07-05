package com.sparta.heartvera.domain.user.service;

import com.sparta.heartvera.domain.comment.repository.CommentRepository;
import com.sparta.heartvera.domain.like.repository.LikeRepository;
import com.sparta.heartvera.domain.like.service.LikeService;
import com.sparta.heartvera.domain.post.entity.Post;
import com.sparta.heartvera.domain.post.repository.PostRepository;
import com.sparta.heartvera.domain.post.service.PostService;
import com.sparta.heartvera.domain.user.dto.UserResponseDto;
import com.sparta.heartvera.domain.user.entity.User;
import com.sparta.heartvera.domain.user.entity.UserRoleEnum;
import com.sparta.heartvera.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ActiveProfiles("test")
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private LikeService likeService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentRepository commentRepository;

    private User user1;
    private User user2;
    private Post post1;
    private Post post2;
    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUpTestUser() {
        user1 = User.builder()
                .userSeq(1L)
                .userId("userID1")
                .userName("username1")
                .userEmail("test1@gmail.com")
                .passwordHistories(new ArrayList<>())
                .userPassword("0000")
                .authority(UserRoleEnum.USER)
                .build();

        user2 = User.builder()
                .userSeq(2L)
                .userId("userID2")
                .userName("username2")
                .userEmail("test2@gmail.com")
                .passwordHistories(new ArrayList<>())
                .userPassword("0000")
                .authority(UserRoleEnum.USER)
                .build();
        userRepository.save(user1);
        userRepository.save(user2);

        post1 = new Post(1L,"title","content", user2);
        post2 = new Post(2L,"title","content", user2);
        postRepository.save(post1);
        postRepository.save(post2);
    }

    @Test
    @DisplayName("프로필 조회시 응답필드에 내가 좋아요한 게시글 수 필드를 추가")
    void ViewLikedPost(){
        // Given
        likeService.togglePostLike(user1,post1.getId()); // 좋아요 객체가 추가됨

        // When
        UserResponseDto response = userService.getUser(user1.getUserSeq()); // 0 -> 1 변경

        // Then
        assertEquals(response.getPostLikes(),1);
    }

    @Test
    @DisplayName("프로필 조회시 응답필드에 내가 좋아요한 댓글 수 필드를 추가")
    void ViewLikedComment(){
        // Given
        likeService.togglePostLike(user1,post1.getId()); // 좋아요 객체가 추가됨

        // When
        UserResponseDto response = userService.getUser(user1.getUserSeq()); // 0 -> 1 변경

        // Then
        assertEquals(response.getPostLikes(),1);
    }

}