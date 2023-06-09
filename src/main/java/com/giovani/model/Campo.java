package com.giovani.model;

import com.giovani.error.ExplosionException;

import java.util.ArrayList;
import java.util.List;

public class Campo {
  private boolean minado;
  private boolean toOpen;
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

  public void toggleMarking() {
    if(!toOpen) {
      marked = !marked;
    }
  }

  public boolean openField() {
    // soh pode abrir o campo se estiver fechado(false) e nao estiver marcado.
    if(!toOpen && !marked) {
      toOpen = true;
      if(minado) {
        throw new ExplosionException();
      }// se o campo em volta estiver seguro continue abrindo.
      if(safeNeighborhood()) {
        neighbors.forEach(v -> v.openField());
      }
      return true;
    }

    return false;
  }

  public boolean safeNeighborhood() {
    // se retorna false eh pq tem uma bomba por perto
   return neighbors.stream().noneMatch(v -> v.minado);
  }

  public void undermine() {
      minado = true;
  }

  public boolean isMinado() {
    return minado;
  }

  public boolean isMarked() {
    return marked;
  }

  public boolean isOpen() {
    return toOpen ;
  }

  public void setOpen(boolean open) {
    this.toOpen = open;
  }

  public int getX() {
    return x;
  }

  public int getY() { return y; }

  public boolean goalAchived() {
    boolean unraveledField = !minado && toOpen;
    boolean protectedField = minado && marked;
    return unraveledField || protectedField;
  }

  public long minesInNeighborhood() {
    return neighbors.stream().filter(v -> v.minado).count();
  }

  public void restart() {
    toOpen = false;
    minado = false;
    marked = false;
  }

  public String toString() {
    if(marked) {
      return "x";
    } else if (toOpen && minado) {
      return "*";
    } else if (toOpen && minesInNeighborhood() > 0) {
      return Long.toString(minesInNeighborhood());
    } else if (toOpen) {
      return " ";
    }
    return "?";
  }
}
