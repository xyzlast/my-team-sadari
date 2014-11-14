package me.xyzlast.sadari.api.resources;

import me.xyzlast.domain.services.GamePlayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ykyoon on 14. 11. 7.
 */
@RestController
public class CalendarController {

    @Autowired
    private GamePlayRecordService gamePlayRecordService;

    @ResponseBody
    @RequestMapping(value = "game/calendar", method = RequestMethod.GET)
    public Object get(@RequestParam("year") int year, @RequestParam("month") int month) {
        return gamePlayRecordService.getMonthResult(year, month);
    }
}
