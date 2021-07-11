package com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository;

import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.EnumValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnumValueJpaRepository extends JpaRepository<EnumValueEntity, Long> {
    EnumValueEntity findByFileAndFieldAndCode(String file, String field, String code);
}
