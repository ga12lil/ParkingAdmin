package com.ga12lil.ParkingAdmin.repository;

import com.ga12lil.ParkingAdmin.model.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationRepository {
    private final JdbcTemplate jdbc;
    private final RowMapper<Reservation> mapper = new RowMapper<>() {
        @Override
        public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Reservation r = new Reservation();
            r.setId(rs.getInt("id"));
            r.setSpotId(rs.getInt("spot_id"));
            r.setCarId(rs.getInt("car_id"));
            r.setOwnerId(rs.getInt("owner_id"));
            Timestamp st = rs.getTimestamp("start_time");
            if (st != null) r.setStartTime(st.toLocalDateTime());
            Timestamp et = rs.getTimestamp("end_time");
            if (et != null) r.setEndTime(et.toLocalDateTime());
            r.setPaid(rs.getBoolean("paid"));
            return r;
        }
    };

    public List<Reservation> findAll() {
        return jdbc.query("select * from reservations order by start_time desc", mapper);
    }

    public Reservation findById(int id) {
        return jdbc.queryForObject("select * from reservations where id = ?", mapper, id);
    }

    public List<Reservation> findByCarLicense(String license) {
        String sql = "select r.* from reservations r join cars c on r.car_id = c.id where c.license_plate = ?";
        return jdbc.query(sql, mapper, license);
    }

    public List<Reservation> findByOwnerName(String namePart) {
        String sql = "select r.* from reservations r join owners o on r.owner_id = o.id where o.first_name ilike ? or o.last_name ilike ?";
        String p = "%" + namePart + "%";
        return jdbc.query(sql, mapper, p, p);
    }

    public int create(Reservation r) {
        jdbc.update("insert into reservations(spot_id, car_id, owner_id, start_time, end_time, paid) values (?,?,?,?,?,?)",
                r.getSpotId(), r.getCarId(), r.getOwnerId(), r.getStartTime(), r.getEndTime(), r.getPaid());
        Integer id = jdbc.queryForObject("select currval(pg_get_serial_sequence('reservations','id'))::int", Integer.class);
// mark spot unavailable
        jdbc.update("update parking_spots set is_available = false where id = ?", r.getSpotId());
        return id == null ? -1 : id;
    }

    public void update(Reservation r) {
        jdbc.update("update reservations set spot_id=?, car_id=?, owner_id=?, start_time=?, end_time=?, paid=? where id=?",
                r.getSpotId(), r.getCarId(), r.getOwnerId(), r.getStartTime(), r.getEndTime(), r.getPaid(), r.getId());
    }

    public void cancel(int id) {
// free spot if needed
        Integer spotId = jdbc.queryForObject("select spot_id from reservations where id = ?", Integer.class, id);
        jdbc.update("delete from reservations where id = ?", id);
        if (spotId != null) jdbc.update("update parking_spots set is_available = true where id = ?", spotId);
    }

    public void markPaid(int id, boolean paid) {
        jdbc.update("update reservations set paid = ? where id = ?", paid, id);
    }
}
