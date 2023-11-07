package ganzithon.ganzithon.entity.product;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="product_table")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId; // 상품 고유 식별자

    @Column(nullable = false, length = 50)
    private String productName; // 상품 이름

    @Column(nullable = false)
    private Integer productPrice; // 상품 가격

    @Column(nullable = false)
    private String productImg; // 상품 이미지

    @Column(nullable = false, length = 50)
    private String productArea; // 상품 지역

    @Column(nullable = false)
    private String productDetail; // 상품 설명
}
