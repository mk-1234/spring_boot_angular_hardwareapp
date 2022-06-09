package hr.tvz.krznar.hardwareapp;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Repository
public class JdbcHardwareRepository implements HardwareRepository {

    private static final String SELECT_ALL = "SELECT * FROM hardware";

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;

    public JdbcHardwareRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc)
                .withTableName("hardware")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Hardware> findAll() {
        return List.copyOf(jdbc.query(SELECT_ALL, this::mapRowToHardware));
    }

    @Override
    public List<Hardware> findAllThatStarts(String s) {
        return List.copyOf(jdbc.query(SELECT_ALL + " WHERE LOWER(code) LIKE concat(?, '%')", this::mapRowToHardware, s.toLowerCase()));
    }

    @Override
    public List<Hardware> findAllInStock() {
        return List.copyOf(jdbc.query(SELECT_ALL + " WHERE stock > 0", this::mapRowToHardware));
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        try {
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE code = ?", this::mapRowToHardware, code)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> saveHardware(Hardware hardware) {
        try {
            hardware.setId(saveHardwareDetails(hardware));
            return Optional.of(hardware);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> updateHardware(String code, Hardware hardware) {
        System.out.println("hardware type: " + hardware.getType().getClass().getSimpleName());
        System.out.println("hardware type string: " + hardware.getType().toString().getClass().getSimpleName());
        int executed = jdbc.update("UPDATE hardware SET " +
                "name = ?, " +
                "code = ?, " +
                "price = ?, " +
                "`type` = ?, " +
                "stock = ? " +
                "WHERE code = ?",
                hardware.getName(), hardware.getCode(), hardware.getPrice(), hardware.getType().toString(), hardware.getStock(), code);
        if (executed > 0) {
            return Optional.of(hardware);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByCode(String code) {
        jdbc.update("DELETE FROM review WHERE hardware_id = (SELECT id FROM hardware WHERE code = ?)", code);
        jdbc.update("DELETE FROM hardware WHERE code = ?", code);
    }

    private Hardware mapRowToHardware(ResultSet rs, int numRow) throws SQLException {
        return new Hardware(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("code"),
                rs.getDouble("price"),
                Type.valueOf(rs.getString("type")),
                rs.getInt("stock")
        );
    }

    private long saveHardwareDetails(Hardware hardware) {
        Map<String, Object> values = new HashMap<>();
        values.put("name", hardware.getName());
        values.put("code", hardware.getCode());
        values.put("price", hardware.getPrice());
        values.put("type", hardware.getType());
        values.put("stock", hardware.getStock());
        return inserter.executeAndReturnKey(values).longValue();
    }
}
