package me.xyzlast.domain.services;

import me.xyzlast.domain.entities.Game;
import me.xyzlast.domain.entities.PlayerResult;
import me.xyzlast.domain.repositories.GameRepository;
import me.xyzlast.domain.vo.MonthGameTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by ykyoon on 14. 11. 6.
 */
@Service
public class GamePlayRecordServiceImpl implements GamePlayRecordService {
    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game recordNewGame(Date date, String matchingNumber, List<PlayerResult> results) {
        return null;
    }

    @Override
    public Game remove(long gameId) {
        return null;
    }

    @Override
    public Game update(long gameId, Date date, String matchingNumber, List<PlayerResult> results) {
        return null;
    }

    @Override
    public MonthGameTable getMonthResult(int year, int month) {
        return null;
    }
}
