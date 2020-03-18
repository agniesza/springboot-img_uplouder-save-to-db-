package com.agacorporation.springbootimage_uplouder.repo;

import com.agacorporation.springbootimage_uplouder.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {

}
