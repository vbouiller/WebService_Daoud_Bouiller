package fr.emse.majeureinfo.webserviceproject.model;

import javax.persistence.*;


@Entity
@SuppressWarnings("serial")
public class Room {


    @Id
    @GeneratedValue
    private Long id;
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

    public Room(Light light, Noise noise) {
        this.light = light;
        this.noise = noise;
    }
}
