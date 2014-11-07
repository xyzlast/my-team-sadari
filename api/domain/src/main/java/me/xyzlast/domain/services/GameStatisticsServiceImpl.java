package me.xyzlast.domain.services;

import com.google.common.collect.Lists;
import me.xyzlast.domain.entities.Game;
import me.xyzlast.domain.entities.Player;
import me.xyzlast.domain.entities.QGame;
import me.xyzlast.domain.repositories.GameRepository;
import me.xyzlast.domain.repositories.PlayerRepository;
import me.xyzlast.domain.vo.DayGameTable;
import me.xyzlast.domain.vo.MatchNumberCalculateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ykyoon on 14. 11. 7.
 */
@Service
public class GameStatisticsServiceImpl implements GameStatisticsService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MatchNumberCalculateResult> calculateByMachingNumber(Date startDate, Date endDate) {
        List<Game> games = gameRepository.findByDeletedFalseAndDateBetweenOrderByDateAsc(startDate, endDate);

        List<MatchNumberCalculateResult> result = new ArrayList<>();
        for(Game game : games) {
            String matchNumber = game.getMatchingNumber();
            Optional<MatchNumberCalculateResult> findResult = result.stream().filter(r -> r.getNumber().equals(matchNumber)).findFirst();
            MatchNumberCalculateResult matchResult;
            if(findResult.isPresent()) {
                matchResult = findResult.get();
            } else {
                matchResult = new MatchNumberCalculateResult(matchNumber);
                result.add(matchResult);
            }
            matchResult.increase();
        }
        result.sort((o1, o2) -> {
           return Integer.compare(o2.getCount(), o1.getCount());
        });
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Player> calculateByPlayer(Date startDate, Date endDate) {
        List<Game> games = gameRepository.findByDeletedFalseAndDateBetweenOrderByDateAsc(startDate, endDate);
        List<Player> players = playerRepository.findByDeletedFalse();

        for(Game game : games) {
            Player matchPlayer = players.stream().filter(p->p.getId().equals(game.getMatchingPlayer().getId())).findFirst().get();
            matchPlayer.setMatchCount(matchPlayer.getMatchCount() + 1);
        }
        players.sort((p1, p2) -> {
            return Integer.compare(p2.getMatchCount(), p1.getMatchCount());
        });
        return players;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DayGameTable> listupResult(Date startDate, Date endDate) {
        List<Game> games = gameRepository.findByDeletedFalseAndDateBetweenOrderByDateAsc(startDate, endDate);
        return games.stream().map(DayGameTable::new).collect(Collectors.toList());
    }
}
