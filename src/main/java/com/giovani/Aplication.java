package com.giovani;

import com.giovani.model.Tabuleiro;
import com.giovani.view.ConsoleTabuleiro;

public class Aplication {
  public static void main(String[] args) {
    Tabuleiro tabuleiro = new Tabuleiro(6,6,6);

    new ConsoleTabuleiro(tabuleiro);
  }
}
