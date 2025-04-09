package org.tasks.myshop.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.tasks.myshop.dao.model.ItemEntity;

import java.util.List;

@Repository
public interface ItemRespository extends JpaRepository<ItemEntity, Long> {
}
