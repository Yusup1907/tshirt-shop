package org.commerce.repository;

import org.commerce.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SizeRepository extends JpaRepository<Size, String> {

    @Query("SELECT s FROM Size s WHERE s.productId = :idProduct AND s.size = :size")
    Optional<Size> findByIdProductAndBySize(@Param("idProduct") String idProduct, @Param("size") String size);

    @Modifying
    @Transactional
    @Query("UPDATE Size s SET s.stock = :newStock WHERE s.productId = :idProduct AND s.size = :size")
    int updateStockSizeByIdAndSize(@Param("idProduct") String idProduct, @Param("size") String size, @Param("newStock") int newStock);

}
