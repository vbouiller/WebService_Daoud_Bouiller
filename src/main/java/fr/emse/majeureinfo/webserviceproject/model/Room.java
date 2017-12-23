package fr.emse.majeureinfo.webserviceproject.model;

import javax.persistence.*;


@Entity
@SuppressWarnings("serial")
public class Room {


    @Id
    @GeneratedValue
    private Long id;


    /**
     *  The room belongs to a building
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Building building;

    /**
     * The Light of a room
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Light light;

    /**
     * The Noise of a room
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Noise noise;


    @SuppressWarnings("unused")
    public Room(){
    }
    public Room(Building building, Light light, Noise noise) {
        this.building = building;
        this.light = light;
        this.noise = noise;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    public void setNoise(Noise noise) {
        this.noise = noise;
    }

    public Light getLight() {
        return light;
    }

    public Noise getNoise() {
        return noise;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }


}
