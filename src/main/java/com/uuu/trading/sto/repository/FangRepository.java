package com.uuu.trading.sto.repository;

import com.uuu.trading.sto.model.Fang;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FangRepository extends CrudRepository<Fang, Long> {
}
