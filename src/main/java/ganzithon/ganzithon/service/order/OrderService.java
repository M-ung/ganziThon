package ganzithon.ganzithon.service.order;

import ganzithon.ganzithon.entity.order.Order;
import ganzithon.ganzithon.entity.product.Product;
import ganzithon.ganzithon.entity.user.User;
import ganzithon.ganzithon.repository.order.OrderRepository;
import ganzithon.ganzithon.repository.product.ProductRepository;
import ganzithon.ganzithon.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.hibernate.Hibernate;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public String order(Long productId, String currentUserEmail) {
        Optional<User> userOptional = userRepository.findByUserEmail(currentUserEmail);
        if (!userOptional.isPresent()) {
            return "error: user not found";
        }

        Optional<Product> productOptional = productRepository.findById(productId);
        if (!productOptional.isPresent()) {
            return "error: product not found";
        }

        User user = userOptional.get();

        // 새로운 주문 객체를 만들어서
        Order newOrder = new Order(user, productId);

        // 주문을 저장합니다.
        orderRepository.save(newOrder);

        user.getOrderList().add(newOrder);

        userRepository.save(user);

        for(int i=0; i<user.getOrderList().size(); i++) {
            System.out.println(user.getOrderList().get(i));
        }

        return "success";
    }
}
