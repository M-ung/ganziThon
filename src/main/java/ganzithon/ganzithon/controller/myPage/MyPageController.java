package ganzithon.ganzithon.controller.myPage;

import ganzithon.ganzithon.dto.myPage.MyPageDto;
import ganzithon.ganzithon.dto.user.UpdateDto;
import ganzithon.ganzithon.service.myPage.MyPageService;
import ganzithon.ganzithon.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MyPageController {
    private final UserService userService;
    private final MyPageService myPageService;

    // 마이페이지 정보 조회
    @GetMapping("/myPage/{userId}")
    public ResponseEntity<MyPageDto> getMyPage(@PathVariable Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        MyPageDto myPageDto = myPageService.getMyPageInfo(userId, currentUserEmail);

        if (myPageDto != null) {
            return ResponseEntity.ok(myPageDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/myPage/update")
    public ResponseEntity<String> updateAddress(@RequestBody UpdateDto updateDTO) {
        // 현재 인증된 유저의 이메일 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        String updateResult = userService.update(currentUserEmail, updateDTO.getNewAddress());


        if (updateResult == "error") {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("유저를 찾을 수 없습니다.");
        }
        else {
            return ResponseEntity.ok("주소를 바꿨습니다.");
        }
    }

    @PostMapping("/myPage/delete")
    public ResponseEntity<String> delete() {
        // 현재 인증된 유저의 이메일 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        String deleteResult = userService.delete(currentUserEmail);

        if (deleteResult == "error") {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("유저를 찾을 수 없습니다.");
        }
        else {
            return ResponseEntity.ok("회원탈퇴 완료");
        }
    }

}
