package com.giovani;

import com.giovani.model.Tabuleiro;

public class Aplication {
  public static void main(String[] args) {
    Tabuleiro tabuleiro = new Tabuleiro(6,6,6);

    tabuleiro.open(3,3);
    tabuleiro.toggleMarkup(2,3);

    System.out.println(tabuleiro);
  }
}
