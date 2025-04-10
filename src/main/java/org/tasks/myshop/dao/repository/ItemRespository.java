package org.tasks.myshop.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.tasks.myshop.dao.model.ItemEntity;

import java.util.List;

@Repository
public interface ItemRespository extends JpaRepository<ItemEntity, Long> {
    @Query("SELECT item FROM ItemEntity item WHERE item.title LIKE :search%")
    Page<ItemEntity> findByTitle(String search, Pageable pageable);
}
