package com.ecommerce.core.mapper;

import com.ecommerce.core.dto.response.ProductDTO;
import com.ecommerce.core.dto.response.export.ExportProductDTO;
import com.ecommerce.core.entity.Product;
import com.ecommerce.core.exception.BadRequestException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.http.HttpStatus;

@Mapper (componentModel = "spring")
public interface ProductMapper {


//    @Mapping(target = "imageUrl", source = "product", qualifiedByName = "imageUrlToBytes")
    @Mapping(target = "imageUrl", source = "product.imageUrl")
    @Mapping(target = "categoryId", source = "product.category.id")
    @Mapping(target = "categoryName", source = "product.category.categoryName")
    ProductDTO productToProductDTO(Product product
//            , @Context String uploadDir // pass external parameter from service layer, in service inject properties value
    );

    @Mapping(target = "categoryId", source = "product.category.id")
    @Mapping(target = "categoryName", source = "product.category.categoryName")
    ExportProductDTO productToExportProductDTO(Product product);

    // Reverse mapping method
    @Mapping(target = "imageUrl", ignore = true)
    @Mapping(target = "category.id", ignore = true)
    @Mapping(target = "category.categoryName", ignore = true)
    Product productDTOToProduct(ProductDTO productDTO);

    //    @Mapping(target = "imageUrl", source = "product.imageUrl")
    default byte[] mapImageUrl(String imageUrl) {
        if (StringUtils.isBlank(imageUrl)) {
            return null;
        }
        try {
            String currentWorkingDir = System.getProperty("user.dir");
            Path filePath;
            // If the working directory ends with "core-service", move up to the root directory
            if (currentWorkingDir.endsWith("core-service")) {
                // Move two levels up to the project root
                Path rootDir = Paths.get(currentWorkingDir).getParent().getParent();
                filePath = Paths.get(rootDir.toString(), imageUrl);
            } else {
                filePath = Paths.get(System.getProperty("user.dir"), imageUrl);
            }
            if (!Files.exists(filePath)) {
                throw new BadRequestException("File does not exist in this path", HttpStatus.BAD_REQUEST);
            }
            return Files.readAllBytes(filePath);
        }
        catch (IOException e) {
            System.out.println("IO error occured: " + imageUrl);
            return null;
        }
    }

    // Custom method to convert imageUrl to byte[] using MapStruct
//    @Named("imageUrlToBytes")
//    default String imageUrlToBytes(Product product) {
//        if (product == null || StringUtils.isBlank(product.getImageUrl())) {
//            return null;
//        }
//        try {
//            // Print the current working directory
////            System.out.println("Current directory: " + System.getProperty("user.dir"));
//            Path filePath = new File(AppConstants.uploadDir + "/" + product.getImageUrl()).toPath();
//            return Base64.getEncoder().encodeToString(Files.readAllBytes(filePath));
//        } catch (IOException e) {
//            // Handle error or log it if needed
//            System.out.println("File not found in the path: " + product.getImageUrl());
//            return null;
//        }
//    }
}

