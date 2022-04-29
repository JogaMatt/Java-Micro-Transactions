package com.javaproject.finalproject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaproject.finalproject.models.Wallet;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long>
{
	Wallet findByUserId(Long id);
}
