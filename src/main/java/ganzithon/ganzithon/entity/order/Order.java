package ganzithon.ganzithon.entity.order;

import ganzithon.ganzithon.entity.user.User;
import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId; // 유저 고유 식별자

    @ManyToOne
    @JoinColumn(name = "user_id") // 데이터베이스의 외래키 컬럼 이름
    private User user; // User 엔티티에 대한 참조

    @Column
    private Long productId;

    public Order(User user, Long productId) {
        this.user = user;
        this.productId = productId;
    }
}
