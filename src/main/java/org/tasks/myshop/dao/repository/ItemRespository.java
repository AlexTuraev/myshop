package org.tasks.myshop.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tasks.myshop.dao.model.ItemEntity;
import org.tasks.myshop.dao.model.ItemModel;

import java.util.List;

@Repository
public interface ItemRespository extends JpaRepository<ItemEntity, Long> {
    @Query("""
        SELECT item FROM ItemEntity item LEFT JOIN FETCH item.itemPics WHERE item.title LIKE :search%
    """)
    Page<ItemEntity> findByTitle(String search, Pageable pageable);


    /*@Query(value = """
        SELECT * FROM items it 
                LEFT JOIN item_pics ip ON it.id = ip.item_id
                     --LEFT JOIN cart ct ON it.id = ct.item_id
                     WHERE it.title LIKE :search% AND it.quantity >= :minQuantity --AND ct.cart_id = 1
    """,
    nativeQuery = true)*/
    @Query("""
        SELECT item FROM ItemEntity item LEFT JOIN FETCH item.itemPics WHERE item.title LIKE :search% AND item.quantity >= :minQuantity
    """)
    Page<ItemEntity> findByTitleAndOverMinQuantity(String search, Pageable pageable, int minQuantity);

    @Query("""
        SELECT new org.tasks.myshop.dao.model.ItemModel(item, coalesce(c.countItem, 0) )
            FROM ItemEntity item
            LEFT JOIN CartEntity c ON c.itemId = item.id
            LEFT JOIN FETCH item.itemPics
                WHERE item.title LIKE :search% AND item.quantity >= :minQuantity
                    AND c.cartId = 1
    """)
    Page<ItemModel> findByTitleAndOverMinQuantityNew(String search, Pageable pageable, int minQuantity);
//    List<ItemModel> findByTitleAndOverMinQuantityNew(String search, int minQuantity);
}
