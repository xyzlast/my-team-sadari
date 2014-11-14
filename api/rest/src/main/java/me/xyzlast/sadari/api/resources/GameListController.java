package me.xyzlast.sadari.api.resources;

import me.xyzlast.domain.services.GamePlayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ykyoon on 14. 11. 14.
 */
@Controller
public class GameListController {
    @Autowired
    private GamePlayRecordService gamePlayRecordService;

    @ResponseBody
    @RequestMapping(value = "game/day", method = RequestMethod.GET)
    public Object get(@RequestParam("year") int year, @RequestParam("month") int month, @RequestParam("day") int day) {
        return gamePlayRecordService.getGamesByDays(year, month, day);
    }
}
