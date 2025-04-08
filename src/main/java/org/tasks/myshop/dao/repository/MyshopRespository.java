package org.tasks.myshop.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.tasks.myshop.dao.model.ItemEntity;

@Repository
public interface MyshopRespository extends PagingAndSortingRepository<ItemEntity, Long> {

}
