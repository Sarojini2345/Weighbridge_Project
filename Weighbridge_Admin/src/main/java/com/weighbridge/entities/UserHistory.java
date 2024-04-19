package com.weighbridge.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_history_id")
    private List<UserHistoryUpdate> updates;

}
