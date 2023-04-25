package com.giovani.model;

import com.giovani.error.ExplosionException;

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

  public void toggleMarking() {
    if(!open) {
      marked = !marked;
    }
  }

  public boolean openField() {
    // soh pode abrir o campo se estiver fechado(false) e nao estiver marcado.
    if(!open && !marked) {
      open = true;
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

  public boolean isMarked() {
    return marked;
  }

  public boolean isOpen() {
    return open;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public boolean goalAchived() {
    // um campo desvendado eh quando ele nao esta minado e esta aberto.
    boolean unraveledField = !minado && open;
    // se o campo estiver minado e marcado ele esta protegido
    boolean protectedField = minado && marked;
    return unraveledField || protectedField;
  }

  public long minesInNeighborhood() {
    // quantidade de minas ao redor.
    return neighbors.stream().filter(v -> v.minado).count();
  }

  public void restart() {
    open = false;
    minado = false;
    marked = false;
  }
  
  public String toString() {
    if(marked) {
      return "x";
    } else if (open && minado) {
      return "*";
    } else if (open && minesInNeighborhood() > 0) {
      return Long.toString(minesInNeighborhood());
    } else if (open) {
      return "";
    }

    return "?";
  }
}
