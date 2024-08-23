package org.commerce.repository;

import org.commerce.entity.Size;
import org.commerce.model.size.ProductSizeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SizeRepository extends JpaRepository<Size, String> {

//    @Query("SELECT s FROM Size s WHERE s.productId = :idProduct AND s.size = :size")
//    Optional<Size> findByIdProductAndBySize(@Param("idProduct") String idProduct, @Param("size") String size);

    Optional<Size> findByProductId_IdAndSize(@Param("idProduct") String idProduct, @Param("size") String size);


//    @Modifying
//    @Transactional
//    @Query("UPDATE Size s SET s.stock = :newStock WHERE s.productId = :idProduct AND s.size = :size")
//    int updateStockSizeByIdAndSize(@Param("idProduct") String idProduct, @Param("size") String size, @Param("newStock") int newStock);

//    @Modifying
//    @Transactional
//    int updateStockByProductIdAndSize(String productId, String size, int newStock);

    //    @Query(value = "SELECT u FROM Product u WHERE u.name = :name")
//    Optional<Size> findById(@Param("name") String name);
//    @Query("SELECT new org.commerce.model.size.ProductSizeDTO(p.id, p.name, p.desc, p.price, p.img, s.size, s.stock) FROM Product p JOIN Size s ON s.productId = p.id WHERE p.id = :id")
//    List<ProductSizeDTO> findProductSizesByProductId(@Param("id") String id);

    List<ProductSizeDTO> findByProductId_Id(@Param("id") String id);


//    @Query("SELECT new org.commerce.model.size.ProductSizeDTO(p.id, p.name, p.desc, p.price, p.img, s.size, s.stock) FROM Product p JOIN Size s ON s.productId = p.id WHERE s.id = :id")
//    List<ProductSizeDTO> findSizesById(@Param("id") String id);

    Optional<Size> findById(@Param("id") String id);


//    Optional<Object> findById(Size sizeId);
}
