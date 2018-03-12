import org.ehcache.Cache;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;

import java.io.File;

/**
 * Created by yeguoxing on 2018/3/12.
 */
public class PersistentCacheManagerTest {
    public static void main(String[] args) {

        PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(getStoragePath() + File.separator + "myData"))
                .withCache("threeTieredCache",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, String.class,
                                ResourcePoolsBuilder.newResourcePoolsBuilder()
                                        .heap(10, EntryUnit.ENTRIES)  //堆
                                        .offheap(1, MemoryUnit.MB)    //堆外
                                        .disk(20, MemoryUnit.GB)      //磁盘
                        )
                ).build(true);

        Cache<Integer, String> threeTieredCache = persistentCacheManager.getCache("threeTieredCache", Integer.class, String.class);

        //读
        for (int i=0;i<=20000;i++){
            threeTieredCache.put(i, "$"+i);
        }

        //写
        for (int i=0;i<=20000;i++){
            String value = threeTieredCache.get(i);
            System.out.println("get at "+i+":"+value);
        }

        persistentCacheManager.close();
    }

    private static String getStoragePath() {
        // TODO Auto-generated method stub
        return "d:";
    }
}
