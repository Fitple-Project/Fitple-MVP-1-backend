package com.sparta.fitpleprojectbackend.trainer.repository;

import com.sparta.fitpleprojectbackend.trainer.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

  Optional<Trainer> findByAccountId(String accountId);

  Optional<Trainer> findByEmailAndTrainerStatus(String email, String status);

  Optional<Trainer> findByTrainerPhoneNumberAndTrainerStatus(String phoneNumber, String status);

  Optional<Trainer> findByAccountIdAndTrainerStatus(String username, String active);
}