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
}
