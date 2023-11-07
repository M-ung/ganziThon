package ganzithon.ganzithon.service.product;

import ganzithon.ganzithon.entity.product.Product;
import ganzithon.ganzithon.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProductsByArea(String area) {
        return productRepository.findByProductArea(area);
    }

    public Product productDetail(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()) {
            return product.get();
        }
        else {
            return null;
        }

    }
}