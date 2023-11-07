package ganzithon.ganzithon.controller.product;


import ganzithon.ganzithon.entity.product.Product;
import ganzithon.ganzithon.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    // 상품 ID를 받아서 상품 정보를 반환하는 메소드
    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        Product getProductResult = productService.productDetail(productId);

        if (getProductResult != null)
        {
            return ResponseEntity.ok(getProductResult);
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
