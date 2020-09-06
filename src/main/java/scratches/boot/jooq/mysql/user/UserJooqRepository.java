package scratches.boot.jooq.mysql.user;

import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import scratches.boot.jooq.mysql.jooq.tables.records.UsersRecord;

import static scratches.boot.jooq.mysql.jooq.Tables.USERS;

/**
 * @author Rashidi Zin
 */
@Repository
@AllArgsConstructor
public class UserJooqRepository implements UserRepository<UsersRecord> {

    private final DSLContext dsl;

    @Override
    public UsersRecord findByUsername(String username) {
        return dsl.selectFrom(USERS).where(USERS.USERNAME.equalIgnoreCase(username)).limit(1).fetch().get(0);
    }

}
