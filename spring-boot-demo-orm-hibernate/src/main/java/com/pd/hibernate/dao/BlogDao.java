package com.pd.hibernate.dao;

import com.pd.hibernate.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogDao extends JpaRepository<Blog, Integer> {

}