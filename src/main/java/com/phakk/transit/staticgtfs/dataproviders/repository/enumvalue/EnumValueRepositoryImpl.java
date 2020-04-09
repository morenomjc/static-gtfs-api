package com.phakk.transit.staticgtfs.dataproviders.repository.enumvalue;

import com.phakk.transit.staticgtfs.core.constants.EnumValue;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.EnumValueEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.EnumValueJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Slf4j
@Repository
@AllArgsConstructor
public class EnumValueRepositoryImpl implements EnumValueRepository {

    private EnumValueJpaRepository enumValueJpaRepository;
    private EnumValueEntityMapper enumValueEntityMapper;

    @Override
    public EnumValue findEnumValue(String file, String field, String code) {
        log.info("Find Enum Value with file={}, field={}, code={}", file, field, code);
        EnumValueEntity enumValueEntity = enumValueJpaRepository.findByFileAndFieldAndCode(file, field, code);
        if (Objects.isNull(enumValueEntity)){
            throw new DataNotFoundException("Enum value not found.");
        }
        return enumValueEntityMapper.fromEntity(enumValueEntity);
    }
}