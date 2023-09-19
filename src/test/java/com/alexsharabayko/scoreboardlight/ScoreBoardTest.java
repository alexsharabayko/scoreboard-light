package com.alexsharabayko.scoreboardlight;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

class ScoreBoardTest {
  class BaseChecks {
    ScoreBoard scoreBoard;

    @BeforeEach()
    void setUp() {
      scoreBoard = new ScoreBoard();
    }
  }

  @Nested
  class StartGameChecks extends BaseChecks {
    @Test()
    @DisplayName("should add empty game to the scoreboard")
    void shouldAddEmptyGame() {
      final var emptySummary = scoreBoard.getSummary();

      scoreBoard.startGame(List.of("Poland", "Germany"));
      scoreBoard.startGame(List.of("Mexico", "Brazil"));
      final var updatedSummary = scoreBoard.getSummary();

      Assertions.assertEquals("", emptySummary);
      Assertions.assertEquals("Poland 0 - Germany 0; Mexico 0 - Brazil 0", updatedSummary);
    }

    @Test()
    @DisplayName("should return generated id of new game")
    void shouldReturnId() {
      final var id = scoreBoard.startGame(List.of("Poland", "Germany"));
      Assertions.assertEquals("Poland:Germany", id);
    }
  }
}