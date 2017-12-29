package fr.emse.majeureinfo.webserviceproject.model;

import javax.persistence.*;
import java.util.Set;

/**
 *
 * @author Valentin Bouiller
 *
 * **/

@Entity
@SuppressWarnings("serial")
public class Building {


    @Id
    @GeneratedValue
    private Long id;

    /**
     * The Rooms of a Building
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Room> rooms;

    /**
     * Each Building has a name
     * */
    @Column
    private String name;

    @SuppressWarnings("unused")
    public Building(){
    }
    public Building(Set<Room> rooms, String name) {
        this.rooms = rooms;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int Num_On_Light(){
        int i=0;
        for (Room room:rooms) {
            if(room.getLight().getStatus().equals(Status.ON))i++;
        }
        return i;
    }
    public int Num_On_Ringer(){
        int i=0;
        for (Room room:rooms) {
            if(room.getNoise().getStatus().equals(Status.ON))i++;
        }
        return i;
    }
}
