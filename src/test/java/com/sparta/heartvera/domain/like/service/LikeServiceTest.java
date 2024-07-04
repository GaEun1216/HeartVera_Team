package com.sparta.heartvera.domain.like.service;

import com.sparta.heartvera.common.exception.CustomException;
import com.sparta.heartvera.common.exception.ErrorCode;
import com.sparta.heartvera.domain.comment.repository.CommentRepository;
import com.sparta.heartvera.domain.like.entity.Like;
import com.sparta.heartvera.domain.like.entity.LikeEnum;
import com.sparta.heartvera.domain.like.repository.LikeRepository;
import com.sparta.heartvera.domain.post.entity.Post;
import com.sparta.heartvera.domain.post.entity.PublicPost;
import com.sparta.heartvera.domain.post.repository.PostRepository;
import com.sparta.heartvera.domain.post.repository.PublicPostRepository;
import com.sparta.heartvera.domain.post.service.PostService;
import com.sparta.heartvera.domain.user.entity.User;
import com.sparta.heartvera.domain.user.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LikeServiceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private PublicPostRepository publicPostRepository;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private LikeRepository likeRepository;

    @MockBean
    private PostService postService;

    @Autowired
    private LikeService likeService;

    private User user1;
    private User user2;
    private Post post1;
    private Post post2;
    private PublicPost publicPost;

    @BeforeEach
    public void setUpTestUser() {
        user1 = User.builder()
                .userId("userID1")
                .userName("username1")
                .userEmail("test1@gmail.com")
                .build();

        user2 = User.builder()
                .userId("userID2")
                .userName("username2")
                .userEmail("test2@gmail.com")
                .build();
        userRepository.save(user1);
        userRepository.save(user2);

        post1 = new Post("title","content", user2);
        //post2 = new Post("title","content", user1);
        postRepository.save(post1);
        //postRepository.save(post2);
        publicPost = new PublicPost("content", user1);
    }

    @Test
    @DisplayName("익명글 좋아요 추가")
    void testAddLike() {
        // Given
        Like like = new Like(user1, post1, null, null, LikeEnum.POST);

        when(userRepository.findByUserId(user1.getUserId())).thenReturn(Optional.of(user1));
        when(postRepository.findById(post1.getId())).thenReturn(Optional.of(post1));
        when(postService.validatePostLike(user1, post1.getId())).thenReturn(post1);

        when(likeRepository.findByUserAndPost(user1, post1)).thenReturn(Optional.empty());
        when(likeRepository.save(like)).thenReturn(like);

        // When
        String response = likeService.togglePostLike(user1, post1.getId());

        // Then
        assertThat(response.equals("좋아요를 눌렀습니다."));
    }

    @Test
    @DisplayName("익명글 좋아요 추가 - 본인이 추가하여 추가되지 않을 경우")
    void testNotAddLike() {
        // Given
        Like like = new Like(user1, post1, null, null, LikeEnum.POST);

        when(userRepository.findByUserId(user1.getUserId())).thenReturn(Optional.of(user1));
        when(postRepository.findById(post1.getId())).thenReturn(Optional.of(post1));
        when(postService.validatePostLike(user1, post1.getId())).thenThrow(new CustomException(ErrorCode.POST_SAME_USER));

        // When - Then
        CustomException exception = assertThrows(CustomException.class, () -> {
            likeService.togglePostLike(user1, post1.getId());
        });

        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.POST_SAME_USER);
    }

    @Test
    @DisplayName("익명글 좋아요 취소")
    void testDeleteLike() {
        // Given
        Like like = new Like(user1, post1, null, null, LikeEnum.POST);
        likeRepository.save(like);

        when(userRepository.findByUserId(user1.getUserId())).thenReturn(Optional.of(user1));
        when(postRepository.findById(post1.getId())).thenReturn(Optional.of(post1));
        when(postService.validatePostLike(user1, post1.getId())).thenReturn(post1);
        when(likeRepository.findByUserAndPost(user1, post1)).thenReturn(Optional.of(like));

        // When
        String response = likeService.togglePostLike(user1, post1.getId());

        // Then
        assertThat(response.equals("좋아요를 취소했습니다."));
    }

    /* https://jyami.tistory.com/124 */


}
