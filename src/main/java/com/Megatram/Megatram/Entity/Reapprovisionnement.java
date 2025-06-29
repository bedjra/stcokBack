package com.Megatram.Megatram.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class Reapprovisionnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;
    private String agent;

    public LocalDateTime getDate() {
        return date;
    }




    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private LocalDateTime date = LocalDateTime.now();


}
