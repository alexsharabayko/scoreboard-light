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

  @Nested
  class FinishGameChecks extends BaseChecks {
    @Test()
    @DisplayName("should remove a game from the scoreboard")
    void shouldRemoveGame() {
      scoreBoard.startGame(List.of("Poland", "Germany"));
      scoreBoard.startGame(List.of("Mexico", "Brazil"));
      final var initialSummary = scoreBoard.getSummary();

      scoreBoard.finishGame("Mexico:Brazil");
      final var finalSummary = scoreBoard.getSummary();

      Assertions.assertEquals("Poland 0 - Germany 0; Mexico 0 - Brazil 0", initialSummary);
      Assertions.assertEquals("Poland 0 - Germany 0", finalSummary);
    }

    @Test()
    @DisplayName("could return finished game")
    void couldReturnFinshedGame() {
      scoreBoard.startGame(List.of("Poland", "Germany"));

      final var val1 = scoreBoard.finishGame("Mexico:Brazil");
      final var val2 = scoreBoard.finishGame("Poland:Germany");

      Assertions.assertNull(val1);
      Assertions.assertEquals("Poland:Germany", val2.getId());
    }
  }

  @Nested
  class UpdateScoreChecks extends BaseChecks {
    @Test()
    @DisplayName("should update a game from the scoreboard")
    void shouldUpdateGame() {
      scoreBoard.startGame(List.of("Poland", "Germany"));
      scoreBoard.startGame(List.of("Mexico", "Brazil"));
      final var initialSummary = scoreBoard.getSummary();

      scoreBoard.updateScore("Poland:Germany", List.of(3, 1));
      scoreBoard.updateScore("Mexico:Brazil", List.of(1, 2));
      final var finalSummary = scoreBoard.getSummary();

      Assertions.assertEquals("Poland 0 - Germany 0; Mexico 0 - Brazil 0", initialSummary);
      Assertions.assertEquals("Poland 3 - Germany 1; Mexico 1 - Brazil 2", finalSummary);
    }

    @Test()
    @DisplayName("should return correct boolean flag")
    void shouldReturnUpdatedGame() {
      scoreBoard.startGame(List.of("Poland", "Germany"));

      final var val = scoreBoard.updateScore("Poland:Germany", List.of(3, 1));

      Assertions.assertEquals("Poland:Germany", val.getId());
    }

    @Test()
    @DisplayName("should throw exception if there is no game")
    void shouldThrowExceptionIfNoGames() {
      Assertions.assertThrows(IllegalStateException.class, () -> scoreBoard.updateScore("Poland:Germany", List.of(3, 1)));
    }

    @Test()
    @DisplayName("should throw exception if size of scores differs")
    void shouldThrowExceptionIfSizeOfScoresDiffer() {
      scoreBoard.startGame(List.of("Poland", "Germany"));

      Assertions.assertThrows(IllegalArgumentException.class, () -> scoreBoard.updateScore("Poland:Germany", List.of(3, 1, 2)));
    }
  }
}