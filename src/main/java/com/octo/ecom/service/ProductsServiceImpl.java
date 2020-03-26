package com.octo.ecom.service;

import com.octo.ecom.dto.ProductDto;
import com.octo.ecom.entity.ProductBo;
import com.octo.ecom.exception.BusinessException;
import com.octo.ecom.exception.ProductNotFoundException;
import com.octo.ecom.mapper.ProductMapper;
import com.octo.ecom.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductsServiceImpl(ProductRepository plateformUserRepository) {
        this.productRepository = plateformUserRepository;
    }


    @Override
    public ProductDto addProduct(ProductDto productDto) throws BusinessException {
        Optional<ProductBo> existingRef = productRepository.findByRef(productDto.getProductRef());

        if (existingRef.isPresent())
            throw new BusinessException("Product With Same Ref exists");
        return ProductMapper.mapBoToDto(productRepository.save(ProductMapper.mapDtoToBo(new ProductBo(), productDto)));
    }

    @Override
    public ProductDto editProduct(String productId, ProductDto productDto) throws ProductNotFoundException, BusinessException {

        ProductBo productBo = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        Optional<ProductBo> existingRef = productRepository.findByRef(productDto.getProductRef());

        if (existingRef.isPresent())
            if (existingRef.get().getId().equalsIgnoreCase(productId))
                throw new BusinessException("Product With Same Ref exists");

        return ProductMapper.mapBoToDto(productRepository.save(ProductMapper.mapDtoToBo(productBo, productDto)));

    }

    @Override
    public void deleteProduct(String productId) throws ProductNotFoundException {

        ProductBo productBo = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        productRepository.delete(productBo);
    }

    @Override
    public ProductDto getProduct(String productId) throws ProductNotFoundException {
        return productRepository.findById(productId)
                .map(productBo -> ProductMapper.mapBoToDto(productBo))
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @Override
    public List<ProductDto> listProducts() {
        return productRepository.findAll()
                .stream()
                .map(productBo -> ProductMapper.mapBoToDto(productBo))
                .collect(Collectors.toList());
    }
}
