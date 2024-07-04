package com.sparta.heartvera.domain.post.service;

import com.sparta.heartvera.common.exception.CustomException;
import com.sparta.heartvera.common.exception.ErrorCode;
import com.sparta.heartvera.domain.follow.entity.Follow;
import com.sparta.heartvera.domain.follow.repository.FollowRepository;
import com.sparta.heartvera.domain.post.dto.PostRequestDto;
import com.sparta.heartvera.domain.post.dto.PublicPostResponseDto;
import com.sparta.heartvera.domain.post.entity.Post;
import com.sparta.heartvera.domain.post.entity.PublicPost;
import com.sparta.heartvera.domain.post.repository.PublicPostRepository;
import com.sparta.heartvera.domain.user.entity.User;
import com.sparta.heartvera.domain.user.service.UserService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PublicPostService {

  private final PublicPostRepository postRepository;
  private final UserService userService;
  private final FollowRepository followRepository;

  public PublicPostResponseDto savePost(PostRequestDto requestDto, User user) {
    PublicPost post = postRepository.save(new PublicPost(requestDto, user));

    return new PublicPostResponseDto(post);
  }

  public PublicPostResponseDto getPost(Long postId) {
    PublicPost post = findById(postId);

    return new PublicPostResponseDto(post);
  }

  @Transactional
  public PublicPostResponseDto editPost(Long postId, PostRequestDto requestDto, User user) {
    PublicPost post = findById(postId);
    checkUserSame(post, user);
    post.update(requestDto);

    return new PublicPostResponseDto(post);
  }

  public String deletePost(Long postId, User user) {
    PublicPost post = findById(postId);
    checkUserSame(post, user);
    postRepository.delete(post);

    return postId + "번 게시물 삭제 완료";
  }

  public Object getAllPost(int page, int amount) {
    Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
    Pageable pageable = PageRequest.of(page, amount, sort);
    Page<PublicPost> postList = postRepository.findAll(pageable);

    if (postList.getTotalElements() == 0) {
      return "먼저 작성하여 소식을 알려보세요!";
    }

    return postList.map(PublicPostResponseDto::new);
  }

  public Object getFollowedPosts(User user, int page, int pageSize) {
    Pageable pageable = PageRequest.of(page, pageSize);

    User currentUser = userService.findByUserSeq(user.getUserSeq());
    List<Follow> follows = followRepository.findByFromUser(currentUser);
    Set<Long> userIds = follows.stream().map((follow)-> follow.getToUser().getUserSeq()).collect(
        Collectors.toSet());

    Page<PublicPost> publicPosts = postRepository.findByUser_UserSeqInOrderByCreatedAtDesc(userIds, pageable);

    List<PublicPostResponseDto> publicPostResponseDtos = new ArrayList<>();

    for(PublicPost publicPost : publicPosts) {
        PublicPostResponseDto responseDto = new PublicPostResponseDto(publicPost);
        publicPostResponseDtos.add(responseDto);
    }

    if (publicPostResponseDtos.isEmpty()) {
      return "먼저 관심있는 사람들을 팔로우 해보세요!";
    }
    return publicPostResponseDtos;
  }


  // 좋아요 증감 함수
  @Transactional
  public void increasePostLike(Long postId){
    PublicPost post = findById(postId);
    post.increaseLike();
  }

  @Transactional
  public void decreasePostLike(Long postId){
    PublicPost post =  findById(postId);
    post.decreaseLike();
  }

  public PublicPost findById(Long postId) {
    return postRepository.findById(postId).orElseThrow(
            () -> new CustomException(ErrorCode.POST_NOT_FOUND)
    );
  }

  private void checkUserSame(PublicPost post, User user) {
    if (!(post.getUser().getUserSeq().equals(user.getUserSeq()))) {
      throw new CustomException(ErrorCode.POST_NOT_USER);
    }
  }

  public Object getAllPostForAdmin(int page, int amount) {
    Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
    Pageable pageable = PageRequest.of(page, amount, sort);
    Page<PublicPost> postList = postRepository.findAll(pageable);

        if (postList.getTotalElements() == 0) {
            return "먼저 작성하여 소식을 알려보세요!";
        }

    return postList.map(PublicPostResponseDto::new);
  }

  public void delete(PublicPost post) {
    postRepository.delete(post);
  }

  //좋아요 유효성 검사
  public PublicPost validatePostLike(User user, Long postId) {
    PublicPost post = postRepository.findById(postId).orElseThrow(()->
            new CustomException(ErrorCode.POST_NOT_FOUND));
    if(post.getUser().getUserSeq().equals(user.getUserSeq())){
      throw new CustomException(ErrorCode.POST_SAME_USER);
    }
    return post;
  }
}