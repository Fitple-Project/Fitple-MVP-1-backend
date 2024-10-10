package com.sparta.gymspartaprojectbackend.product.controller;

import com.sparta.gymspartaprojectbackend.common.CommonResponse;
import com.sparta.gymspartaprojectbackend.product.dto.ProductRequest;
import com.sparta.gymspartaprojectbackend.product.dto.ProductResponse;
import com.sparta.gymspartaprojectbackend.product.entity.Product;
import com.sparta.gymspartaprojectbackend.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping("/pt")
  public ResponseEntity<CommonResponse<ProductResponse>> registerPT(
      @RequestBody ProductRequest productRequest) {

    Product product = productService.registerPTProduct(productRequest);

    ProductResponse productResponse = new ProductResponse(product);

    CommonResponse<ProductResponse> response = new CommonResponse<>(HttpStatus.OK.value(), "PT 등록 완료", productResponse);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/membership")
  public ResponseEntity<CommonResponse<ProductResponse>> registerMembership(
      @RequestBody ProductRequest productRequest) {

    Product product = productService.registerMembershipProduct(productRequest);

    ProductResponse productResponse = new ProductResponse(product);

    CommonResponse<ProductResponse> response = new CommonResponse<>(HttpStatus.OK.value(), "회원권 등록 완료", productResponse);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
