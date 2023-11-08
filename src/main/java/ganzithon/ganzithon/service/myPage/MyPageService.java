package ganzithon.ganzithon.service.myPage;

import ganzithon.ganzithon.dto.myPage.MyPageDto;
import ganzithon.ganzithon.dto.order.OrderDto;
import ganzithon.ganzithon.dto.user.UserMyPageDto;
import ganzithon.ganzithon.entity.order.Order;
import ganzithon.ganzithon.entity.user.User;
import ganzithon.ganzithon.repository.order.OrderRepository;
import ganzithon.ganzithon.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyPageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    public MyPageDto getMyPageInfo(Long userId, String currentUserEmail) {
        Optional<User> user = userRepository.findById(userId);
        List<Order> orders = orderRepository.findByUserUserId(userId);

        if (user.isPresent() && Objects.equals(user.get().getUserEmail(), currentUserEmail)) {
            MyPageDto myPageDto = new MyPageDto();
            myPageDto.setUserMyPageDto(convertToUserDTO(user.get()));
            myPageDto.setOrders(orders.stream()
                    .map(this::convertToOrderDTO)
                    .collect(Collectors.toList()));
            return myPageDto;
        }

        return null; // or you can throw a custom exception if user is not found
    }

    private UserMyPageDto convertToUserDTO(User user) {
        return modelMapper.map(user, UserMyPageDto.class);
    }

    private OrderDto convertToOrderDTO(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }
}
