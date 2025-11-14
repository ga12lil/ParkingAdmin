package com.ga12lil.ParkingAdmin.repository;

import com.ga12lil.ParkingAdmin.model.ParkingSpot;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpotRepository {
    private final JdbcTemplate jdbc;
    private final RowMapper<ParkingSpot> mapper = new RowMapper<>() {
        @Override
        public ParkingSpot mapRow(ResultSet rs, int rowNum) throws SQLException {
            ParkingSpot s = new ParkingSpot();
            s.setId(rs.getInt("id"));
            s.setSpotNumber(rs.getString("spot_number"));
            s.setLevel(rs.getInt("level"));
            s.setIsAvailable(rs.getBoolean("is_available"));
            return s;
        }
    };

    public List<ParkingSpot> findAll() {
        return jdbc.query("select * from parking_spots order by spot_number", mapper);
    }

    public ParkingSpot findById(int id) {
        return jdbc.queryForObject("select * from parking_spots where id = ?", mapper, id);
    }

    public ParkingSpot findBySpotNumber(String spotNumber) {
        return jdbc.queryForObject("select * from parking_spots where spot_number = ?", mapper, spotNumber);
    }

    public int create(ParkingSpot s) {
        jdbc.update("insert into parking_spots(spot_number, level, is_available) values (?,?,?)",
                s.getSpotNumber(), s.getLevel(), s.getIsAvailable());
        Integer id = jdbc.queryForObject("select currval(pg_get_serial_sequence('parking_spots','id'))::int", Integer.class);
        return id == null ? -1 : id;
    }

    public void update(ParkingSpot s) {
        jdbc.update("update parking_spots set spot_number=?, level=?, is_available=? where id=?",
                s.getSpotNumber(), s.getLevel(), s.getIsAvailable(), s.getId());
    }

    public void delete(int id) {
        jdbc.update("delete from parking_spots where id = ?", id);
    }
}
