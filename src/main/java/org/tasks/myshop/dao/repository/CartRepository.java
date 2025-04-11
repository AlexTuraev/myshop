package org.tasks.myshop.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tasks.myshop.dao.model.CartEntity;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findByItemIdAndCartId(Long itemId, Long cartId);

    void deleteByItemIdAndCartId(Long itemId, Long cartId);

    @Query("""
        SELECT ct FROM CartEntity ct 
            JOIN FETCH ct.item 
                LEFT JOIN FETCH ct.item.itemPics WHERE ct.cartId=:cartId
    """)
    /*@Query(value = """
        SELECT ct.item_id as itemId, ct.count_item as countItem, it.price as price, ip.image_type as imageType, ip.image as image
            FROM cart ct 
                    JOIN items it ON it.id= ct.item_id
                    LEFT JOIN item_pics ip ON it.id = ip.item_id
                WHERE ct.cart_id = :cartId;
    """,
    nativeQuery = true)*/
    List<CartEntity> getCartModelByCartId(Long cartId);
}
