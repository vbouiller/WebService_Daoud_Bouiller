package fr.emse.majeureinfo.webserviceproject.model;

import javax.persistence.*;


@Entity
@SuppressWarnings("serial")
public class Noise {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer level;

    @Enumerated(EnumType.STRING)
    private Status status;

    @SuppressWarnings("unused")
    public Noise(){
    }

    public Noise(Integer level, Status status){
        this.level=level;
        this.status=status;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public Integer getLevel(){
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
