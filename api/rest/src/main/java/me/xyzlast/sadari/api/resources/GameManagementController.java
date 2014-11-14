package me.xyzlast.sadari.api.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.xyzlast.domain.entities.Game;
import me.xyzlast.domain.entities.Player;
import me.xyzlast.domain.entities.PlayerResult;
import me.xyzlast.domain.services.GamePlayRecordService;
import me.xyzlast.domain.services.PlayerManagementService;
import me.xyzlast.sadari.api.utils.DateUtils;
import me.xyzlast.sadari.api.vo.GameDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ykyoon on 14. 11. 14.
 */
@Controller
public class GameManagementController {
    @Autowired
    private GamePlayRecordService gamePlayRecordService;
    @Autowired
    private PlayerManagementService playerManagementService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DateUtils dateUtils;

    @ResponseBody
    @RequestMapping(value = "game/edit/{id}", method = RequestMethod.GET)
    public Object getGame(@PathVariable(value = "id") long id) {
        Game game = gamePlayRecordService.read(id);
        List<Player> players = playerManagementService.list();

        return new GameDetail(game, players);
    }

    @ResponseBody
    @RequestMapping(value = "game/remove/{id}", method = RequestMethod.DELETE)
    public Object removeGame(@PathVariable(value = "id") long id) {
        return gamePlayRecordService.remove(id) != null;
    }

    @ResponseBody
    @RequestMapping(value = "game/add", method = RequestMethod.GET)
    public Object createGame() {
        List<Player> players = playerManagementService.list();
        return new GameDetail(players);
    }

    @ResponseBody
    @RequestMapping(value = "game/add", method = RequestMethod.POST)
    public Object createGame(@RequestBody String jsonString) throws Exception {
        Game game = parseGameFromJson(jsonString);
        return gamePlayRecordService.recordNewGame(game.getDate(),
                game.getMatchingNumber(), game.getCost(), game.getDescription(), game.getResults());
    }

    @ResponseBody
    @RequestMapping(value = "game/edit/{id}", method = RequestMethod.POST)
    public Object editGame(@PathVariable(value = "id") long id, @RequestBody String jsonString) throws Exception {
        Game game = parseGameFromJson(jsonString);
        return gamePlayRecordService.update(id, game.getDate(),
                game.getMatchingNumber(), game.getCost(), game.getDescription(), game.getResults());
    }

    private Game parseGameFromJson(String jsonString) throws Exception {
        Map<String, Object> map = objectMapper.readValue(jsonString, Map.class);
        Date date = dateUtils.convertFrom(map.get("date").toString());
        String matchingNumber = map.get("matchingNumber").toString();
        long cost = Long.parseLong(map.get("cost").toString());
        String description = map.get("description") != null ? map.get("description").toString() : "";

        List<Map<String, Object>> playerResultMaps = (List<Map<String, Object>>) map.get("playerResults");
        List<PlayerResult> playerResults = new ArrayList<>();
        for(Map<String, Object> playerResultMap : playerResultMaps) {
            PlayerResult playerResult = new PlayerResult();
            Long playerId = Long.parseLong(playerResultMap.get("playerId").toString());
            playerResult.setPlayer(new Player(playerId));
            playerResult.setOwnNumber(playerResultMap.get("ownNumber").toString());
            playerResults.add(playerResult);
        }

        Game game = new Game();
        game.setDate(date);
        game.setMatchingNumber(matchingNumber);
        game.setCost(cost);
        game.setDescription(description);
        game.setResults(playerResults);

        return game;
    }
}
