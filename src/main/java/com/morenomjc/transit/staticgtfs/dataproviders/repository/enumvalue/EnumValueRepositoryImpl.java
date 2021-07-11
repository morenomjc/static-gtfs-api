package com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue;

import com.morenomjc.transit.staticgtfs.core.constants.EnumValue;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.EnumValueEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.EnumValueJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Slf4j
@Repository
@AllArgsConstructor
public class EnumValueRepositoryImpl implements EnumValueRepository {

    private EnumValueJpaRepository enumValueJpaRepository;
    private EnumValueEntityMapper enumValueEntityMapper;

    @Override
    @Cacheable(value = "enumvalues")
    public EnumValue findEnumValue(String file, String field, String code) {
        EnumValueEntity enumValueEntity = enumValueJpaRepository.findByFileAndFieldAndCode(file, field, code);
        if (Objects.isNull(enumValueEntity)){
            log.warn("Enum value [{}, {}, {}] not found.", file, field, code);
            return null;
        }
        return enumValueEntityMapper.fromEntity(enumValueEntity);
    }
}
