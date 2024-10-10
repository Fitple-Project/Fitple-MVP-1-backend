package com.sparta.gymspartaprojectbackend.product.service;

import com.sparta.gymspartaprojectbackend.payment.enums.PtTimes;
import com.sparta.gymspartaprojectbackend.product.dto.ProductRequest;
import com.sparta.gymspartaprojectbackend.product.entity.Product;
import com.sparta.gymspartaprojectbackend.product.repository.ProductRepository;
import com.sparta.gymspartaprojectbackend.store.entity.Store;
import com.sparta.gymspartaprojectbackend.store.repository.StoreRepository;
import com.sparta.gymspartaprojectbackend.trainer.entity.Trainer;
import com.sparta.gymspartaprojectbackend.trainer.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final TrainerRepository trainerRepository;
  private final StoreRepository storeRepository;

  // PT 상품 등록
  public Product registerPTProduct(ProductRequest productRequest) {
    // Trainer를 repository에서 조회
    Trainer trainer = trainerRepository.findById(productRequest.getTrainerId())
        .orElseThrow(() -> new RuntimeException("Trainer not found"));

    // expiryDate를 LocalDateTime으로 변환
    // LocalDateTime expiryDate = LocalDateTime.parse(productRequest.getExpiryDate(), DateTimeFormatter.ISO_DATE);

    // Product 엔티티 생성
    Product product = new Product(
        trainer,
        PtTimes.valueOf(productRequest.getPtTimes()),
        productRequest.getAmount(),
        productRequest.getProductPrice(),
        true                    // 상품 활성화 상태
    );

    return productRepository.save(product);
  }

  // 회원권 등록
  public Product registerMembershipProduct(ProductRequest productRequest) {
    Store store =storeRepository.findById(productRequest.getStoreId())
        .orElseThrow(() -> new RuntimeException("없는 매장입니다."));

    // 회원권 Product 엔티티 생성
    Product product = new Product(
        store,
        productRequest.getAmount(),
        productRequest.getProductPrice(),
        true                    // 상품 활성화 상태
    );

    return productRepository.save(product);
  }

  // 상품 비활성화
  public void deactivateProduct(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new RuntimeException("Product not found"));
    product.deactivate(); // 비활성화 메소드 구현
    productRepository.save(product);
  }
}


