package com.giovani.view;

import com.giovani.error.ExplosionException;
import com.giovani.error.SairException;
import com.giovani.model.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class ConsoleTabuleiro {
  private Tabuleiro tabuleiro;
  private Scanner input = new Scanner(System.in);

  public ConsoleTabuleiro(Tabuleiro tabuleiro) {
    this.tabuleiro = tabuleiro;

    runGame();
  }

  private void runGame() {
    try {
      boolean continueGame = true;
      while(continueGame) {
        gameCycle();

        System.out.println("Jogar novamente? (Y/n) ");
        String inputResponse = input.nextLine();

        if("n".equalsIgnoreCase(inputResponse)) continueGame = false;

        tabuleiro.restart();
      }
    } catch (SairException e) {
      System.out.println("Fim do Jogo");
    } finally {
      input.close();
    }
  }

  private void gameCycle() {
    try{

      while(!tabuleiro.goalAchived()) {
        System.out.println(tabuleiro);

        String digitado = captureInputValue("Digite as coordenadas ( x, y )");
        // pegar o valor digitado das coordenadas
        Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                .map(e -> Integer.parseInt(e.trim())).iterator();

        digitado = captureInputValue("1 - Abrir ou 2 - (Des)Marcar");

        if("1".equalsIgnoreCase(digitado)) {
          tabuleiro.open(xy.next(), xy.next());
        }

        if("2".equals(digitado)) {
          tabuleiro.toggleMarkup(xy.next(), xy.next());
        }

      }

      System.out.println("Voce Ganhou!!!!");
    } catch (ExplosionException e) {
      System.out.println("Voce perdeu!");
    }
  }

  private String captureInputValue(String text) {
    System.out.print(text);
    String digitado = input.nextLine();

    if("sair".equalsIgnoreCase(digitado)) {
      throw new SairException();
    }

    return digitado;
  }
}
