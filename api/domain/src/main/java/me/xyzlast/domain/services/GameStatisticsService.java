package me.xyzlast.domain.services;

import me.xyzlast.domain.entities.Player;
import me.xyzlast.domain.vo.DayGameTable;
import me.xyzlast.domain.vo.MatchNumberCalculateResult;

import java.util.Date;
import java.util.List;

/**
 * Created by ykyoon on 14. 11. 7.
 */
public interface GameStatisticsService {
    List<MatchNumberCalculateResult> calculateByMachingNumber(Date startDate, Date endDate);
    List<Player> calculateByPlayer(Date startDate, Date endDate);
    List<DayGameTable> listupResult(Date startDate, Date endDate);
}
