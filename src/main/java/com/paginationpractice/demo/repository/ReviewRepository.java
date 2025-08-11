package com.paginationpractice.demo.repository;

import com.paginationpractice.demo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

}
