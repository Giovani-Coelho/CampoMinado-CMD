package com.giovani.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

  public void open(int x, int y) {
    fields.parallelStream()
            .filter(c -> c.getX() == x && c.getY() == y)
            .findFirst()
            .ifPresent((c -> c.openField()));
  }

  public void toggleMarkup(int x, int y) {
    fields.parallelStream()
            .filter(c -> c.getX() == x && c.getY() == y)
            .findFirst()
            .ifPresent(c -> c.toggleMarking());
  }

  private void generateField() {
    for (int line = 0; line < x; line++) {
      for (int column = 0; column < y; column++) {
        fields.add( new Campo ( line, column ));
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
    long armedMines = 0;
    Predicate<Campo> mined = c -> c.isMinado();

    do {
      int aleatorio = (int) (Math.random() * fields.size());
      fields.get(aleatorio).undermine();
      armedMines = fields.stream().filter(mined).count();
    } while (armedMines < mines);
  }

  public boolean goalAchived() {
    return fields.stream().allMatch(c -> c.goalAchived());
  }

  public void restart() {
    fields.stream().forEach(c -> c.restart());
    raffleMines();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("  ");
    for (int c = 0; c < y; c++) {
      sb.append(" ");
      sb.append(c);
      sb.append(" ");
    }

    sb.append("\n");

    int i = 0;
    for (int l = 0; l < x; l++) {
      sb.append(l);
      sb.append(" ");
      for (int c = 0; c < y; c++) {
        sb.append(" ");
        sb.append(fields.get(i));
        sb.append(" ");
        i++;
      }
      sb.append("\n");
    }

    return sb.toString();
  }
}
