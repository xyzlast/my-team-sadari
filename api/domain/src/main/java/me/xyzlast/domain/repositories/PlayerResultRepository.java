package me.xyzlast.domain.repositories;

import me.xyzlast.domain.entities.PlayerResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ykyoon on 14. 11. 14.
 */
@Repository
public interface PlayerResultRepository extends JpaRepository<PlayerResult, Long> {

}
