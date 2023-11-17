package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleDao extends JpaRepository<SampleEntity, Long> {
}
