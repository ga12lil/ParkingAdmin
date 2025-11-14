package com.ga12lil.ParkingAdmin.repository;

import com.ga12lil.ParkingAdmin.model.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CarRepository {
    private final JdbcTemplate jdbc;
    private final RowMapper<Car> mapper = new RowMapper<>() {
        @Override
        public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
            Car c = new Car();
            c.setId(rs.getInt("id"));
            c.setOwnerId(rs.getInt("owner_id"));
            c.setLicensePlate(rs.getString("license_plate"));
            c.setMake(rs.getString("make"));
            c.setModel(rs.getString("model"));
            c.setColor(rs.getString("color"));
            return c;
        }
    };

    public List<Car> findAll() {
        return jdbc.query("select * from cars order by license_plate", mapper);
    }

    public Car findById(int id) {
        return jdbc.queryForObject("select * from cars where id = ?", mapper, id);
    }

    public Car findByLicense(String license) {
        return jdbc.queryForObject("select * from cars where license_plate = ?", mapper, license);
    }

    public List<Car> findByLicenseLike(String part) {
        String p = "%" + part + "%";
        return jdbc.query("select * from cars where license_plate ilike ?", mapper, p);
    }

    public int create(Car car) {
        jdbc.update("insert into cars(owner_id, license_plate, make, model, color) values (?,?,?,?,?)",
                car.getOwnerId(), car.getLicensePlate(), car.getMake(), car.getModel(), car.getColor());
        Integer id = jdbc.queryForObject("select currval(pg_get_serial_sequence('cars','id'))::int", Integer.class);
        return id == null ? -1 : id;
    }

    public void update(Car car) {
        jdbc.update("update cars set owner_id=?, license_plate=?, make=?, model=?, color=? where id=?",
                car.getOwnerId(), car.getLicensePlate(), car.getMake(), car.getModel(), car.getColor(), car.getId());
    }

    public void delete(int id) {
        jdbc.update("delete from cars where id = ?", id);
    }
}
