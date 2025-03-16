package co.com.ancas.postgres.repositories;

import co.com.ancas.postgres.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    @Query("SELECT u FROM UserEntity u WHERE u.id != :publicId")
    Page<UserEntity> findUsersExcerpSelf(@Param("publicId") String id, Pageable pageable);
    @Query("SELECT u FROM UserEntity u WHERE u.id = :publicId")
    Optional<UserEntity> findUserByPublicId(@Param("publicId") String id);
    boolean existsByEmail(String s);
}
