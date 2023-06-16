package subway.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import subway.domain.entity.Line;

@Repository
public class LineDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertAction;

    private RowMapper<Line> rowMapper = (rs, rowNum) ->
            new Line(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("color"),
                    rs.getLong("additional_charge")
            );

    public LineDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("line")
                .usingGeneratedKeyColumns("id");
    }

    public Line insert(Line line) {
        Long lineId = insertAction.executeAndReturnKey(new BeanPropertySqlParameterSource(line)).longValue();
        return new Line(lineId, line.getName(), line.getColor(), line.getAdditionalCharge());
    }

    public List<Line> findAll() {
        String sql = "select id, name, color, additional_charge from LINE";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Line findById(Long id) {
        String sql = "select id, name, color, additional_charge from LINE WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void update(Line newLine) {
        String sql = "update LINE set name = ?, color = ?, additional_charge = ? where id = ?";
        jdbcTemplate.update(sql, newLine.getName(), newLine.getColor(), newLine.getAdditionalCharge(), newLine.getId());
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("delete from Line where id = ?", id);
    }
}
