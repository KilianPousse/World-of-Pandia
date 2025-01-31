package fr.rabbyt;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Map {

    public static final Integer MAX_SIZE = 999;
    public static final Integer MAX_PIXEL_SIZE = 64;

    private Integer width;
    private Integer height;
    private Integer pixelSize;
    private HashMap<Long, MapObjet> objets;

    private static boolean isValidSize(Integer size) {
        return 0 < size && size <= MAX_SIZE;
    }

    private static boolean isValidPixelSize(Integer size) {
        return 0 < size && size <= MAX_PIXEL_SIZE;
    }

    public Map(Integer width, Integer height) {
        this.setSize(width, height);
        this.objets = new HashMap<>();
    }

    public Map(Integer width, Integer height, Integer pixelSize) {
        this.setSize(width, height);
        this.setPixelSize(pixelSize);
    }

    public void setSize(Integer width, Integer height) {
        this.setWidth(width);
        this.setHeight(height);
    }

    public Integer[] getSize() {
        Integer[] size = new Integer[2];
        size[0] = getWidth();
        size[1] = getHeight();
        return size;
    }

    public void setWidth(Integer width) {
        if(!isValidSize(width)) {
            throw new IllegalArgumentException("La largeur doit être entre 1 et " + MAX_SIZE);
        }
        this.width = width;
    }

    public void setHeight(Integer height) {
        if(!isValidSize(height)) {
            throw new IllegalArgumentException("La hauteur doit être entre 1 et " + MAX_SIZE);
        }
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }
    public Integer getHeight() {
        return height;
    }

    public void setPixelSize(Integer pixelSize) {
        if(!isValidPixelSize(pixelSize)) {
            throw new IllegalArgumentException("La taille d'un pixel doit être entre 1 et " + MAX_PIXEL_SIZE);
        }
        this.pixelSize = pixelSize;
    }

    public Integer getPixelSize() {
        return pixelSize;
    }

    public void addObjet(MapObjet objet) {
        this.objets.put(objet.getId(), objet);
    }

    public void removeObjet(MapObjet objet) {
        this.objets.remove(objet.getId());
    }

    public void removeObjet(Long id) {
        this.objets.remove(id);
    }

    
    public List<MapObjetDistance> getNeighbors(MapObjet objet, int radius) {
        return objets.values().stream() 
                              .filter(o -> o != objet) 
                              .filter(o -> Math.abs(o.getX() - objet.getX()) <= radius && Math.abs(o.getY() - objet.getY()) <= radius) 
                              .map(o -> new MapObjetDistance(o, objet.distance(o))) 
                              .sorted(Comparator.comparingDouble(MapObjetDistance::getDistance)) 
                              .collect(Collectors.toList()); 
    }
    
    public static class MapObjetDistance {
        private MapObjet objet;
        private double distance;

        public MapObjetDistance(MapObjet objet, double distance) {
            this.objet = objet;
            this.distance = distance;
        }

        public MapObjet getObjet() {
            return objet;
        }

        public double getDistance() {
            return distance;
        }
    }
}
