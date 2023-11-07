package ganzithon.ganzithon.controller.product;


import ganzithon.ganzithon.entity.product.Product;
import ganzithon.ganzithon.entity.user.User;
import ganzithon.ganzithon.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @GetMapping("/shop")
    public ResponseEntity<List<Product>> getProductsByArea(@RequestParam("area") String area) {
        List<Product> products = productService.getProductsByArea(area);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

//    @GetMapping("/product/shop/{product_id}")
//    public ResponseEntity<Product> getProduct() {
//    }
}
