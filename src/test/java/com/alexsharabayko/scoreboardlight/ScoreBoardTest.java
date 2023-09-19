package com.alexsharabayko.scoreboardlight;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

class ScoreBoardTest {
  ScoreBoard scoreBoard;

  @BeforeEach()
  void setUp() {
    scoreBoard = new ScoreBoard();
  }

  @Test()
  @DisplayName("startGame method should start empty game")
  void startGameShouldStartEmptyGame() {
    final var emptySummary = scoreBoard.getSummary();

    scoreBoard.startGame(List.of("Poland", "Germany"));
    scoreBoard.startGame(List.of("Mexico", "Brazil"));
    final var updatedSummary = scoreBoard.getSummary();

    Assertions.assertEquals("", emptySummary);
    Assertions.assertEquals("Poland 0 - Germany 0; Mexico 0 - Brazil 0", updatedSummary);
  }
}