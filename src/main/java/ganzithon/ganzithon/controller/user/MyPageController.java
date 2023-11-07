package ganzithon.ganzithon.controller.user;

import ganzithon.ganzithon.dto.user.UpdateDto;
import ganzithon.ganzithon.entity.user.User;
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

    @GetMapping("/myPage/{userId}") // HTTP GET 요청을 처리
    // 유저 정보 찾기
    public ResponseEntity<User> getMyPage(@PathVariable Long userId) {
        // 현재 인증된 유저의 이메일 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        User getMyPageResult = userService.getMyPage(userId, currentUserEmail);

        // 여기까진 오류 없음 xx
        if(getMyPageResult != null) {
            System.out.println("ok");
            System.out.println(getMyPageResult.getUserEmail());
            System.out.println(getMyPageResult.getUserName());
            System.out.println(getMyPageResult.getUserPassword());
            System.out.println(getMyPageResult.getUserAddress());
            System.out.println(getMyPageResult.getUserMileage());
            System.out.println(getMyPageResult.getOrderList());
            return ResponseEntity.ok(getMyPageResult);
        }
        else {
            System.out.println("no");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
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
