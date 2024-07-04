package com.sparta.heartvera.domain.comment.entity;

import com.sparta.heartvera.domain.comment.dto.CommentRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentTest {
    @Test
    @DisplayName("updateComment() 테스트")
    void test1() {
        // Given
        CommentRequestDto requestDto = CommentRequestDto.builder()
                .contents("contents")
                .build();
        Comment comment = new Comment().builder()
                .requestDto(requestDto)
                .build();

        CommentRequestDto updateString = CommentRequestDto.builder()
                .contents("different contents")
                .build();
        // When
        comment.updateComment(updateString);

        // Then
        assertEquals("different contents", comment.getContents());
    }
}