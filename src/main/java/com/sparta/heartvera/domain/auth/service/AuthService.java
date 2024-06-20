package com.sparta.heartvera.domain.auth.service;

import com.sparta.heartvera.domain.auth.dto.LoginRequestDto;
import com.sparta.heartvera.domain.auth.dto.SignupRequestDto;
import com.sparta.heartvera.domain.user.entity.User;
import com.sparta.heartvera.domain.user.entity.UserRoleEnum;
import com.sparta.heartvera.domain.user.repository.UserRepository;
import com.sparta.heartvera.security.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Value("${ADMIN_TOKEN}")
    private String adminToken;

    public ResponseEntity<String> signup(SignupRequestDto request) {
        String userId = request.getUserId();
        String userName = request.getUserName();
        String password = passwordEncoder.encode(request.getPassword());
        String email = request.getEmail();
        String description = request.getDescription();
        String adminPassword = request.getAdminPassword();

        // 회원 아이디 중복 확인
        Optional<User> existingUser = userRepository.findByUserId(userId);
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("중복된 아이디가 존재합니다.");
        }

        // 사용자 ROLE 기본 USER로 설정
        UserRoleEnum role = UserRoleEnum.USER;

        // adminPassword와 adminToken 비교하여 ADMIN 권한 설정
        if (adminPassword != null && adminPassword.equals(adminToken)) {
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = User.builder()
                .userId(userId)
                .userName(userName)
                .userPassword(password)
                .userEmail(email)
                .description(description)
                .authority(role)
                .build();

        userRepository.save(user);

        // 회원가입 성공 메시지 반환
        if (role == UserRoleEnum.ADMIN) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("어드민으로 회원가입이 성공적으로 완료되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("일반 사용자로 회원가입이 성공적으로 완료되었습니다.");
        }
    }

}