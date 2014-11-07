package me.xyzlast.domain.services;

import me.xyzlast.domain.entities.Player;

import java.util.List;

/**
 * Created by ykyoon on 14. 11. 6.
 */
public interface PlayerManagementService {
    Player add(String name, int defaultAmount);
    Player edit(Long id, String name, int defaultAmount);
    Player remove(Long id);
    List<Player> list();
}
