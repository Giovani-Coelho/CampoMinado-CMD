package com.giovani.model;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
  private int x;
  private int y;
  private int mines;

  private final List<Campo> fields = new ArrayList<>();

  public Tabuleiro(int x, int y, int mines) {
    this.x = x;
    this.y = y;
    this.mines = mines;

    generateField();
    associateNeighbor();
    raffleMines();
  }

  private void generateField() {
    for (int line = 0; line < x; line++) {
      for (int column = 0; column < y; column++) {
        fields.add( new Campo ( x, column ));
      }
    }
  }

  private void associateNeighbor() {
    for(Campo c1 : fields) {
      for(Campo c2 : fields) {
        c1.addNeighbor(c2);
      }
    }
  }

  private void raffleMines() {
    
  }
}
