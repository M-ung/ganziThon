package ganzithon.ganzithon.service.user;

import ganzithon.ganzithon.dto.user.UpdateDto;
import ganzithon.ganzithon.dto.user.UserDto;
import ganzithon.ganzithon.entity.user.User;
import ganzithon.ganzithon.repository.user.UserRepository;
import ganzithon.ganzithon.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secretKey;

    private Long expiredMs = 1000 * 60 * 60L; // 1시간

    public String save(UserDto userDto) {
        Optional<User> byUserEmail = userRepository.findByUserEmail(userDto.getUserEmail());
        if (byUserEmail.isPresent()) {
            return "error 1";
        }
        User user = User.builder()
                .userName(userDto.getUserName())
                .userEmail(userDto.getUserEmail())
                .userPassword(passwordEncoder.encode(userDto.getUserPassword())) // 암호
                .userAddress(userDto.getUserAddress())
                .userMileage(0)
                .build();

        userRepository.save(user);
        return "success";
    }

    public String login(UserDto userDto) {
        Optional<User> byUserEmail = userRepository.findByUserEmail(userDto.getUserEmail());
        if(byUserEmail.isPresent()) {
            if(passwordEncoder.matches(userDto.getUserPassword(), byUserEmail.get().getUserPassword())) {
                System.out.println("[LOIGN]");
                System.out.println(userDto.getUserEmail());
                System.out.println(secretKey);
                System.out.println(expiredMs);
                return JwtUtil.createJwt(userDto.getUserEmail(), secretKey, expiredMs);
            }
            return "error 2";
        }
        else{
            return "error 1";
        }
    }


    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public String update(String currentUserEmail, String newAddress) {
        Optional<User> user = userRepository.findByUserEmail(currentUserEmail);
        if(!user.isPresent()) {
            return "error";
        }
        else {
            user.get().setUserAddress(newAddress);
            return "success";
        }
    }
}
