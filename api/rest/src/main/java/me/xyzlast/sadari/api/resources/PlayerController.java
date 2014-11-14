package me.xyzlast.sadari.api.resources;

import me.xyzlast.domain.services.PlayerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ykyoon on 14. 11. 14.
 */
@Controller
public class PlayerController {
    @Autowired
    private PlayerManagementService playerManagementService;

    @ResponseBody
    @RequestMapping(value = "players")
    public Object getPlayers() {
        return playerManagementService.list();
    }
}
