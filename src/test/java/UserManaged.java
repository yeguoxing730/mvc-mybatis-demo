import org.ehcache.UserManagedCache;
import org.ehcache.config.builders.UserManagedCacheBuilder;

/**
 * Created by yeguo on 2018/3/11.
 */
public class UserManaged {
    public static void main(String[] args) {
        UserManagedCache<Long, String> userManagedCache =
                UserManagedCacheBuilder.newUserManagedCacheBuilder(Long.class, String.class)
                        .build(false);
        userManagedCache.init();
        userManagedCache.put(1L, "hello UserManagedCache!");
        System.out.println(userManagedCache.get(1L));
        userManagedCache.close();
    }
}
