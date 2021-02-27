package com.sfl.cafemanager.repository;

import com.sfl.cafemanager.entity.TableEntity;
import com.sfl.cafemanager.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long> {
    List<TableEntity> findByWaiter(UserEntity userEntity);
}
