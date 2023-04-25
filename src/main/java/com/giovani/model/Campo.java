package com.giovani.model;

import java.util.ArrayList;
import java.util.List;

public class Campo {
  private boolean minado;
  private boolean open;
  private boolean marked;

  private final int x;
  private final int y;

  private List<Campo> neighbors = new ArrayList<>();

  public Campo(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public boolean addNeighbor(Campo neighbor) {
    boolean differentLine = x != neighbor.x;
    boolean differentColumn = y != neighbor.y;
    boolean diagonal = differentLine && differentColumn;

    int deltaLX = Math.abs(x - neighbor.x);
    int deltaLY = Math.abs(y - neighbor.y);
    int generalDelta = deltaLX + deltaLY;

    if(generalDelta == 1 && !diagonal) {
      neighbors.add(neighbor);
      return true;
    }

    if(generalDelta == 2 && diagonal) {
      neighbors.add(neighbor);
      return true;
    }

    return false;
  }
}
