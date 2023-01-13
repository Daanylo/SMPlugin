package smplugin.smplugin.selections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Selection {


    private UUID uuid;
    private int xA,zA,xB,zB;

    public Selection(int xA, int zA, int xB, int zB) {
        this.xA = xA;
        this.zA = zA;
        this.xB = xB;
        this.zB = zB;
    }
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getxA() {
        return xA;
    }

    public void setxA(int xA) {
        this.xA = xA;
    }

    public int getzA() {
        return zA;
    }

    public void setzA(int zA) {
        this.zA = zA;
    }

    public int getxB() {
        return xB;
    }

    public void setxB(int xB) {
        this.xB = xB;
    }

    public int getzB() {
        return zB;
    }

    public void setzB(int zB) {
        this.zB = zB;
    }

}
