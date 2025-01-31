package fr.rabbyt;

import java.awt.Color;

public interface MapObjet {
    public void setMap(Map map);
    public void setId(Long id);
    public Long getId();
    public void setX(Integer x);
    public void setY(Integer y);
    public Integer getX();
    public Integer getY();
    public void setColor(Color color);
    public Color getColor();
    public boolean teleport(Integer x, Integer y);
    public void update();
    public Float distance(MapObjet objet);
} 