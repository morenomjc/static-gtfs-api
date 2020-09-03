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
        EnumValueEntity enumValueEntity = enumValueJpaRepository.findByFileAndFieldAndCode(file, field, code);
        if (Objects.isNull(enumValueEntity)){
            String message = "Enum value [%s, %s, %s] not found.";
            throw new DataNotFoundException(String.format(message, file, field, code));
        }
        return enumValueEntityMapper.fromEntity(enumValueEntity);
    }
}
