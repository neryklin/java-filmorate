package ru.yandex.practicum.filmorate.dal;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FriendRepository {
    private static final String INSERT_QUERY = "INSERT INTO friend (users_id, friend_id)" +
            " VALUES (:users_id, :friend_id)";
    private static final String DELETE_QUERY = "DELETE FROM friend where (users_id=:users_id and friend_id=:friend_id)";
    private static final String FIND_BY_ID_QUERY = """
            SELECT friend.friend_id as id,
            USERS.NAME,
            USERS.EMAIL,
            USERS.LOGIN,
            USERS.BIRTHDAY
            FROM friend
            JOIN users ON users.id=FRIEND_ID
            WHERE users_id=:users_id""";
    //  protected final RowMapper<Map<Long,List<Genre>>> mapper;
    private final NamedParameterJdbcOperations jdbc;
    private final UserRowMapper userRowMapper;

    public List<User> getFriendById(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("users_id", user.getId());
        return jdbc.query(FIND_BY_ID_QUERY, map, userRowMapper);
    }

    public User addFriend(User user, User fuser) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("users_id", user.getId());
        map.addValue("friend_id", fuser.getId());
        jdbc.update(INSERT_QUERY, map);
        return user;
    }

    public User deleteFriend(User user, User fuser) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("users_id", user.getId());
        map.addValue("friend_id", fuser.getId());
        jdbc.update(DELETE_QUERY, map);
        return user;
    }
}
