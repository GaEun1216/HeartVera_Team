package com.sparta.heartvera.domain.auth.service;

import com.sparta.heartvera.common.exception.CustomException;
import com.sparta.heartvera.common.exception.ErrorCode;
import com.sparta.heartvera.domain.auth.dto.SignupRequestDto;
import com.sparta.heartvera.domain.auth.dto.SignupResponseDto;
import com.sparta.heartvera.domain.auth.dto.TokenResponseDto;
import com.sparta.heartvera.domain.user.entity.User;
import com.sparta.heartvera.domain.user.entity.UserRoleEnum;
import com.sparta.heartvera.domain.user.service.UserService;
import com.sparta.heartvera.security.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Value("${ADMIN_TOKEN}")
    private String adminToken;

    public SignupResponseDto signup(SignupRequestDto request) {
        String userId = request.getUserId();
        String adminPassword = request.getAdminPassword();
        String pw = passwordEncoder.encode(request.getPassword());

        // 회원 아이디 중복 확인
        userService.checkDulicateUserId(userId);

        // 사용자 ROLE 기본 USER로 설정
        UserRoleEnum authority = determineUserRole(adminPassword);

        // 사용자 등록
        User user = userService.createUser(request, authority, pw); // user service

        return new SignupResponseDto(user);
    }

    @Transactional
    public TokenResponseDto reAuth(String refreshToken) {
        String subToken = jwtUtil.substringToken(refreshToken);
        User user = userService.findByRefreshToken(refreshToken); // 리프레쉬 토큰 검증
        if(!jwtUtil.validateToken(subToken)) {
            throw new CustomException(ErrorCode.TOKEN_EXPIRED);
        } // 올바른 토큰이 아닐 경우
        if(jwtUtil.substringToken(refreshToken).equals(user.getRefreshToken())) {
            throw new CustomException(ErrorCode.TOKEN_NOT_FOUND);
        }

        // 리프레쉬 토큰에서 userId 꺼내오기
        String userId = jwtUtil.getUserInfoFromToken(subToken).getSubject();
        // jwtUtil.createToken 에서 액세스 토큰, 리프레쉬 토큰 재생성
        TokenResponseDto token = jwtUtil.createToken(userId, user.getAuthority());
        // 유저 DB refresh token 값 변경
        user.setRefreshToken(token.getRefreshToken());
        userService.updateUser(user);

        return jwtUtil.createToken(userId, user.getAuthority());
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private UserRoleEnum determineUserRole(String adminPassword) {
        if (adminPassword != null && adminPassword.equals(adminToken)) {
            return UserRoleEnum.ADMIN;
        }
        return UserRoleEnum.USER;
    }

}