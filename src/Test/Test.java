import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

/**
 * Created by yeguo on 2018/3/11.
 */
public class Test {
    public static void main(String[] args) {
        CacheManager cacheManager = CacheManagerBuilder
                .newCacheManagerBuilder()
                .withCache(
                        "preConfigured",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                Long.class, String.class,
                                ResourcePoolsBuilder.heap(100)).build())
                .build(true);

        Cache<Long, String> preConfigured = cacheManager.getCache(
                "preConfigured", Long.class, String.class);

        Cache<Long, String> myCache = cacheManager.createCache(
                "myCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        Long.class, String.class,
                        ResourcePoolsBuilder.heap(100)).build());

        preConfigured.put(2L, "hello Ehcache");
        String value1=preConfigured.get(2L);
        System.out.println(value1);
        myCache.put(1L, "da one!");
        String value = myCache.get(1L);
        System.out.println(value);
        cacheManager.close();

    }
}
