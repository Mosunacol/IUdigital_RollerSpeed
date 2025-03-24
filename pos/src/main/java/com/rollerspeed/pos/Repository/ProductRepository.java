package com.rollerspeed.pos.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rollerspeed.pos.Model.ProductModel;

@Repository

public interface ProductRepository extends CrudRepository<ProductModel, Long> {
}
