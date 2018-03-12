import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import java.net.URL;

/**
 * Created by yeguoxing on 2018/3/12.
 */
public class XMLCacheManagerTest {
    public static void main(String[] args){
        URL myUrl = XMLCacheManagerTest.class.getResource("cache3.xml");
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        CacheManager myCacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
        myCacheManager.init();
        Cache<String, String> fooCache =
                myCacheManager.getCache("foo", String.class, String.class);
        Cache<Number, String> barCache =
                myCacheManager.getCache("bar", Number.class, String.class);
        barCache.put(1,"test barCache");
        System.out.println("get long " + 1 + ":" + barCache.get(1));

        Cache<Long, String> simpleCache =
                myCacheManager.getCache("simpleCache", Long.class, String.class);
        simpleCache.put(1l,"test barCache");
        System.out.println("get simple " + 1l + ":" + simpleCache.get(1l));
        for (int i = 0; i <= 20; i++) {
            //写
            fooCache.put(i+"", "@" + i);
            //读
            String value = fooCache.get(i+"");
            System.out.println("get at " + i + ":" + value);
        }
        myCacheManager.removeCache("foo");
        myCacheManager.close();
    }
}
