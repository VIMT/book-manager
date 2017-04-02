package me.vimt.book.repository;

import me.vimt.book.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2016/10/24 20:18
 * Description:
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity getFirstByEmail(String email);

    UserEntity getFirstByUsername(String username);

}
