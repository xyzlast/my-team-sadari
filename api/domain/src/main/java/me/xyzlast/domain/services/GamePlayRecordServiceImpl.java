package me.xyzlast.domain.services;

import me.xyzlast.domain.entities.Game;
import me.xyzlast.domain.entities.PlayerResult;
import me.xyzlast.domain.repositories.GameRepository;
import me.xyzlast.domain.repositories.PlayerResultRepository;
import me.xyzlast.domain.vo.DayGameTable;
import me.xyzlast.domain.vo.MonthGameTable;
import org.hibernate.Hibernate;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ykyoon on 14. 11. 6.
 */
@Service
public class GamePlayRecordServiceImpl implements GamePlayRecordService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerResultRepository playerResultRepository;

    @Override
    @Transactional(readOnly = false)
    public Game recordNewGame(Date date, String matchingNumber, long cost, String description, List<PlayerResult> results) {
        Game game = new Game();
        game.setDate(date);
        game.setMatchingNumber(matchingNumber);
        game.setCost(cost);
        game.setDescription(description);
        results.forEach(r->r.setGame(game));
        game.getResults().addAll(results);
        return gameRepository.save(game);
    }

    @Override
    @Transactional(readOnly = false)
    public Game remove(long gameId) {
        Game game = gameRepository.findOne(gameId);
        game.setDeleted(true);
        return gameRepository.save(game);
    }

    @Override
    @Transactional(readOnly = false)
    public Game update(long gameId, Date date, String matchingNumber, long cost, String description, List<PlayerResult> results) {
        Game game = gameRepository.findOne(gameId);
        game.setDate(date);
        game.setMatchingNumber(matchingNumber);
        game.setCost(cost);
        game.setDescription(description);
        game.getResults().forEach(playerResultRepository::delete);
        game.setResults(new ArrayList<>());
        results.forEach(r->r.setGame(game));
        game.getResults().addAll(results);
        Game updatedGame = gameRepository.save(game);
        Hibernate.initialize(updatedGame.getResults());
        return updatedGame;
    }

    @Override
    @Transactional(readOnly = true)
    public Game read(Long id) {
        Game game = gameRepository.findOne(id);
        if(game != null) {
            Hibernate.initialize(game.getResults());
        }
        return game;
    }

    @Override
    @Transactional(readOnly = true)
    public MonthGameTable getMonthResult(int year, int month) {
        DateTime dateTime = new DateTime(year, month, 1, 0, 0, 0);
        Date start = dateTime.toDate();
        Date end = dateTime.plusMonths(1).toDate();
        List<Game> games = gameRepository.findByDeletedFalseAndDateBetweenOrderByDateAsc(start, end);

        MonthGameTable monthGameTable = new MonthGameTable();
        monthGameTable.setYear(year);
        monthGameTable.setMonth(month);
        monthGameTable.setDayGames(new ArrayList<>());

        for(Game game : games) {
            DayGameTable dayGameTable = new DayGameTable(game);
            monthGameTable.getDayGames().add(dayGameTable);
        }
        return monthGameTable;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Game> getGamesByDays(int year, int month, int day) {
        DateTime dateTime = new DateTime(year, month, day, 0, 0);
        Date start = dateTime.toDate();
        Date end = dateTime.plusDays(1).minusSeconds(1).toDate();
        List<Game> games = gameRepository.findByDeletedFalseAndDateBetweenOrderByDateAsc(start, end);
        games.forEach(g->Hibernate.initialize(g.getResults()));
        return games;
    }
}
