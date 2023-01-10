package smplugin.smplugin.util;

import smplugin.smplugin.handlers.SelectionHandler;

public class ClaimUtil {
    SelectionHandler selectionHandler = new SelectionHandler();
    SelectionHandler.Selection selection = new SelectionHandler.Selection();
    public String calculateRegion() {
        String username = selectionHandler.getUsername();
        int minX = selectionHandler.getMinX();
        int maxX = selectionHandler.getMaxX();
        int minZ = selectionHandler.getMinZ();
        int maxZ = selectionHandler.getMaxZ();
        return String.valueOf(username + minX + maxX + minZ + maxZ);
    }

}
