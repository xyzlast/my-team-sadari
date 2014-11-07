package me.xyzlast.domain.services;

import me.xyzlast.domain.entities.Player;
import me.xyzlast.domain.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ykyoon on 14. 11. 6.
 */
@Service
public class PlayerManagementServiceImpl implements PlayerManagementService {
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    @Transactional(readOnly = false)
    public Player add(String name, int defaultAmount) {
        Player player = new Player();
        player.setName(name);
        player.setDefaultAmount(defaultAmount);
        return playerRepository.save(player);
    }

    @Override
    @Transactional(readOnly = false)
    public Player edit(Long id, String name, int defaultAmount) {
        Player player = playerRepository.findOne(id);
        player.setName(name);
        player.setDefaultAmount(defaultAmount);
        return playerRepository.save(player);
    }

    @Override
    @Transactional(readOnly = false)
    public Player remove(Long id) {
        Player player = playerRepository.findOne(id);
        player.setDeleted(true);
        return playerRepository.save(player);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Player> list() {
        return playerRepository.findAll(new Sort("name"));
    }
}
