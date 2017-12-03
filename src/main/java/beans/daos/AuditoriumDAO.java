package beans.daos;

import beans.models.Auditorium;

import java.util.List;

public interface AuditoriumDAO {

    List<Auditorium> getAll();

    Auditorium getByName(String name);

    void delete(Auditorium auditorium);

    Auditorium add(Auditorium auditorium);
}
