package org.example.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenMapper {

    void createRefreshToken(@Param("id") Long id, @Param("refreshToken") String refreshToken);

    String getRefreshToken(Long id);

    void deleteRefreshToken(@Param("isDeleted") Boolean isDeleted, @Param("id") Long id);

}
