package me.xyzlast.domain.utils;

import net.sf.ehcache.CacheManager;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.ehcache.EhCacheRegionFactory;
import org.hibernate.cfg.Settings;

import java.util.Properties;

/**
 * Created by ykyoon on 14. 11. 6.
 */
public class ExternalEhCacheProvider extends EhCacheRegionFactory {
    private static final long serialVersionUID = -7535263180055439241L;
    private static CacheManager externalCacheManager;

    public static void setCacheManager(CacheManager cacheManager) {
        externalCacheManager = cacheManager;
    }

    @Override
    public void start(Settings settings, Properties properties) throws CacheException {
        if(externalCacheManager == null) {
            throw new CacheException("external cache is not defined!");
        }
        this.manager = externalCacheManager;
    }

    @Override
    public void stop() {

    }
}
