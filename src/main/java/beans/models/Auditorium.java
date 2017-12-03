package beans.models;

import lombok.Data;
import util.CsvUtil;

import java.util.List;


@Data
public class Auditorium {

    private long   id;
    private String name;
    private int    seatsNumber;
    private String vipSeats;

    public Auditorium(String name, int seatsNumber, List<Integer> vipSeats) {
        this(-1, name, seatsNumber, vipSeats);
    }

    public Auditorium(long id, String name, int seatsNumber, List<Integer> vipSeats) {
        this(id, name, seatsNumber, CsvUtil.fromListToCsv(vipSeats));
    }

    public Auditorium(long id, String name, int seatsNumber, String vipSeats) {
        this.id = id;
        this.name = name;
        this.seatsNumber = seatsNumber;
        this.vipSeats = vipSeats;
    }

    public Auditorium withId(Long id) {
        return new Auditorium(id, name, seatsNumber, vipSeats);
    }

    public List<Integer> getVipSeatsList() {
        return CsvUtil.fromCsvToList(vipSeats, Integer:: valueOf);
    }

    public void setVipSeatsList(List<Integer> vipSeats) {
        this.vipSeats = CsvUtil.fromListToCsv(vipSeats);
    }

}
