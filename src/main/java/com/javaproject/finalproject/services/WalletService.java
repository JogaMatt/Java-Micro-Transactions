package com.javaproject.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaproject.finalproject.models.Wallet;
import com.javaproject.finalproject.repositories.WalletRepository;

@Service
public class WalletService
{
	@Autowired
	private WalletRepository walletRepo;
	
//	CREATE WALLET
	public Wallet addWallet(Wallet w)
	{
		return walletRepo.save(w);
	}
	
//	RETRIEVE WALLET
	public Wallet findMyWallet(Long id)
	{
		return walletRepo.findByUserId(id);
	}
	
//	UPDATE WALLET
	public Wallet updateWallet(Wallet w)
	{
		return walletRepo.save(w);
	}
	
//	DELETE WALLET
	public void deleteWallet(Long id)
	{
		walletRepo.deleteById(id);	
	}
	
}
