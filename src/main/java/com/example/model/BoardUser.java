package com.example.model;

import javax.persistence.*;

/**
 * Created by Morgane TROYSI on 10/05/17.
 */

@Entity(name = "board_user")
@Table(name = "board_user")
public class BoardUser extends CommonEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name = "role")
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
