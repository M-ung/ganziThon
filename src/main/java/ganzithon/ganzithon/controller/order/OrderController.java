package ganzithon.ganzithon.controller.order;

import ganzithon.ganzithon.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/product/{productId}")
    public ResponseEntity<String> order(@PathVariable Long productId) {
        // 현재 인증된 유저의 이메일 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        String orderResult = orderService.order(productId, currentUserEmail);

        if (orderResult == "success")
        {
            return ResponseEntity.ok("상품이 주문 되었습니다.");
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
