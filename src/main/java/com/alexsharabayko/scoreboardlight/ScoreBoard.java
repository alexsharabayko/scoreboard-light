package com.alexsharabayko.scoreboardlight;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreBoard {
  private final Map<String, Game> gamesMap = new LinkedHashMap<>();

  public Game startGame(List<String> participantNames) {
    final var game = new Game(participantNames);

    if (gamesMap.containsKey(game.getId())) {
      throw new IllegalStateException("There is such game in the score board");
    }

    gamesMap.put(game.getId(), game);
    return game;
  }

  public Game finishGame(String id) {
    if (!gamesMap.containsKey(id)) {
      throw new IllegalStateException("There is no game with such id");
    }

    return gamesMap.remove(id);
  }

  public Game updateScore(String id, List<Integer> scores) {
    if (!gamesMap.containsKey(id)) {
      throw new IllegalStateException("There is no game with such id");
    }

    final var game = gamesMap.get(id);
    final var participantNames = game.getParticipants().stream().map(Game.Participant::getName).toList();
    final var newGame = new Game(participantNames, scores);

    return gamesMap.replace(newGame.getId(), newGame);
  }

  public String getSummary() {
    return gamesMap.values().stream().map(Game::toString).collect(Collectors.joining("; "));
  }
}
