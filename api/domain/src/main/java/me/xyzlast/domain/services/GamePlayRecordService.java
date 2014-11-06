package me.xyzlast.domain.services;

import me.xyzlast.domain.entities.Game;
import me.xyzlast.domain.entities.PlayerResult;
import me.xyzlast.domain.vo.MonthGameTable;

import java.util.Date;
import java.util.List;

/**
 * Created by ykyoon on 14. 11. 6.
 */
public interface GamePlayRecordService {
    Game recordNewGame(Date date, String matchingNumber, List<PlayerResult> results);
    Game remove(long gameId);
    Game update(long gameId, Date date, String matchingNumber, List<PlayerResult> results);

    MonthGameTable getMonthResult(int year, int month);
}
