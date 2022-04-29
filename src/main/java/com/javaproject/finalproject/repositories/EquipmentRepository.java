package com.javaproject.finalproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaproject.finalproject.models.Equipment;

@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, Long>
{
	List<Equipment> findAll();
}
