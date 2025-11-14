package com.ga12lil.ParkingAdmin.repository;

import com.ga12lil.ParkingAdmin.model.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OwnerRepository {
    private final JdbcTemplate jdbc;
    private final RowMapper<Owner> mapper = new RowMapper<>() {
        @Override
        public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
            Owner o = new Owner();
            o.setId(rs.getInt("id"));
            o.setFirstName(rs.getString("first_name"));
            o.setLastName(rs.getString("last_name"));
            o.setPhone(rs.getString("phone"));
            o.setEmail(rs.getString("email"));
            return o;
        }
    };

    public List<Owner> findAll() {
        return jdbc.query("select * from owners order by last_name, first_name", mapper);
    }

    public Owner findById(int id) {
        return jdbc.queryForObject("select * from owners where id = ?", mapper, id);
    }

    public List<Owner> findByName(String namePart) {
        String p = "%" + namePart + "%";
        return jdbc.query("select * from owners where first_name ilike ? or last_name ilike ?", mapper, p, p);
    }

    public int create(Owner owner) {
        jdbc.update("insert into owners(first_name,last_name,phone,email) values (?,?,?,?)",
                owner.getFirstName(), owner.getLastName(), owner.getPhone(), owner.getEmail());
        Integer id = jdbc.queryForObject("select currval(pg_get_serial_sequence('owners','id'))::int", Integer.class);
        return id == null ? -1 : id;
    }

    public void update(Owner owner) {
        jdbc.update("update owners set first_name=?, last_name=?, phone=?, email=? where id=?",
                owner.getFirstName(), owner.getLastName(), owner.getPhone(), owner.getEmail(), owner.getId());
    }

    public void delete(int id) {
        jdbc.update("delete from owners where id = ?", id);
    }
}
