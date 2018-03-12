import org.ehcache.UserManagedCache;
import org.ehcache.config.builders.UserManagedCacheBuilder;

/**
 * Created by yeguoxing on 2018/3/12.
 */
public class UserManagedCacheTest {
    public static void main(String[] args) {
        UserManagedCache<Integer, String> userManagedCache =
                UserManagedCacheBuilder.newUserManagedCacheBuilder(Integer.class, String.class)
                        .build(false);
        userManagedCache.init();

        for (int i=0;i<=20;i++){
            //写
            userManagedCache.put(i, "#"+i);
            //读
            String value = userManagedCache.get(i);
            System.out.println("get at "+i+":"+value);
        }

        userManagedCache.close();
    }
}
