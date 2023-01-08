package org.example.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenMapper {

    void createRefreshToken(@Param("id") Long id, @Param("refreshToken") String refreshToken);

    void deleteRefreshToken(Long id);

}
