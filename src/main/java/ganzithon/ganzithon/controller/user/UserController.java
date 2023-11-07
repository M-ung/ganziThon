package ganzithon.ganzithon.controller.user;

import ganzithon.ganzithon.dto.user.UserDto;
import ganzithon.ganzithon.service.token.TokenBlacklistService;
import ganzithon.ganzithon.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenBlacklistService tokenBlacklistService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        String loginResult = userService.login(userDto);
        if(loginResult == "error 1") {
            // 이메일이 존재하지 않는다면
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("이메일이 존재하지 않습니다.");
        }
        else if (loginResult == "error 2") {
            // 이메일이 존재하지 않는다면
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("비밀번호가 틀렸습니다.");
        }
        return ResponseEntity.ok().body(loginResult);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> sign(@RequestBody UserDto userDto) {
        String signupResult = userService.save(userDto);
        if(signupResult == "error 1") {
            // 이메일이 이미 존재한다면
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하는 이메일 입니다.");
        }
        return ResponseEntity.ok(signupResult);
    }

    @PostMapping("/out")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklistService.blacklistToken(token); // 토큰을 블랙리스트에 추가
            return ResponseEntity.ok("로그아웃 성공");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰을 찾을 수 없다");
        }
    }

}
