package scratches.boot.jooq.mysql.user;

import scratches.boot.jooq.mysql.jooq.tables.records.UsersRecord;

/**
 * @author Rashidi Zin
 */
public interface UserRepository {

    UsersRecord findByUsername(String username);

}
