package smplugin.smplugin.selections;

import org.bukkit.Location;

public class Selection {
    private int xA,zA,xB,zB;
    private String territoryname;

    public Selection(String territoryname, int xA, int zA, int xB, int zB) {
        this.territoryname = territoryname;
        this.xA = xA;
        this.zA = zA;
        this.xB = xB;
        this.zB = zB;
    }
    public void setTerritoryName(String territoryname) {
        this.territoryname = territoryname;
    }
    public String getTerritoryname() {
        return territoryname;
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

    public int getArea(){
        int sideX = Math.abs((getxA()-getxB())) + 1;
        int sideZ = Math.abs((getzA()-getzB())) + 1;
        return sideX * sideZ;
    }

    public boolean isWithin(Location currentLocation) {
        int xA = getxA();
        int xB = getxB();
        int zA = getzA();
        int zB = getzB();
        int locX = currentLocation.getBlockX();
        int locZ = currentLocation.getBlockZ();
        int minX = Math.min(xA, xB);
        int maxX = Math.max(xA, xB);
        int minZ = Math.min(zA, zB);
        int maxZ = Math.max(zA, zB);
        return (minX <= locX && locX <= maxX) && (minZ <= locZ && locZ <= maxZ);
    }

}
