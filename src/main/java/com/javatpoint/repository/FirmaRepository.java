package com.javatpoint.repository;
import org.springframework.data.repository.CrudRepository;

import com.javatpoint.model.Firma;

public interface FirmaRepository extends CrudRepository<Firma, Integer>
{
}
