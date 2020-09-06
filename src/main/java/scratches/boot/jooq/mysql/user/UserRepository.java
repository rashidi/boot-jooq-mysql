package scratches.boot.jooq.mysql.user;

/**
 * @author Rashidi Zin
 */
public interface UserRepository<T> {

    T findByUsername(String username);

}
