package me.xyzlast.domain.repositories;

import me.xyzlast.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by ykyoon on 14. 11. 6.
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long>, QueryDslPredicateExecutor<Game> {
    List<Game> findByDateBetweenOrderByDateAsc(Date startDate, Date endDate);
}
