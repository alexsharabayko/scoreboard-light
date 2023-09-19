package com.alexsharabayko.scoreboardlight;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreBoard {
  private final Map<String, Game> gamesMap = new LinkedHashMap<>();

  public String startGame(List<String> teamNames) {
    final var game = new Game(teamNames);
    gamesMap.put(game.getId(), game);
    return game.getId();
  }

  public Game finishGame(String id) {
    return gamesMap.remove(id);
  }

  public Game updateScore(String id, List<Integer> scores) {
    if (!gamesMap.containsKey(id)) {
      throw new IllegalStateException("There is no game with such id");
    }

    final var game = gamesMap.get(id);
    final var teamNames = game.getTeams().stream().map(Game.Team::getName).toList();
    final var newGame = new Game(teamNames, scores);

    return gamesMap.replace(newGame.getId(), newGame);
  }

  public String getSummary() {
    return gamesMap
        .values()
        .stream()
        .map(game -> game.getTeams()
            .stream()
            .map(team -> String.format("%s %d", team.getName(), team.getScore()))
            .collect(Collectors.joining(" - ")))
        .collect(Collectors.joining("; "));
  }
}
