package com.octo.ecom.repo;

import com.octo.ecom.entity.ProductBo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductBo, String> {

    Optional<ProductBo> findByRef(String ref);
}
