package com.kma.ais_dekanat_desktop_ui.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by denysburlakov on 04.04.16.
 */
public class Room {
    private IntegerProperty roomId = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();

    public Room(Integer roomId, String name){
        this.roomId = new SimpleIntegerProperty(roomId);
        this.name = new SimpleStringProperty(name);
    }

    public Room() {}

    public int getRoomId() {
        return roomId.get();
    }

    public IntegerProperty roomIdProperty() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId.set(roomId);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String toString(){
        return getName();
    }
}
