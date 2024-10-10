package com.sparta.gymspartaprojectbackend.product.repository;

import com.sparta.gymspartaprojectbackend.product.entity.Product;
import com.sparta.gymspartaprojectbackend.trainer.entity.Trainer;
import com.sparta.gymspartaprojectbackend.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findByUser(User user);
  List<Product> findByTrainer(Trainer trainer);
}
