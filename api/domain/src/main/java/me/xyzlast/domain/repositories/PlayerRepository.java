package me.xyzlast.domain.repositories;

import me.xyzlast.domain.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ykyoon on 14. 11. 6.
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>, QueryDslPredicateExecutor<Player> {
    List<Player> findByDeletedFalse();
}
