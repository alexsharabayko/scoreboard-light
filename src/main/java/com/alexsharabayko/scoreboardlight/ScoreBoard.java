package com.alexsharabayko.scoreboardlight;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreBoard {
  private final Map<String, Game> gamesMap = new LinkedHashMap<>();

  public String startGame(List<String> teamNames) {
    final var teams = teamNames.stream().map(name -> new Game.Team(name, 0)).toList();
    final var game = new Game(teams);

    gamesMap.put(game.getId(), game);

    return game.getId();
  }

  public Game finishGame(String id) {
    return gamesMap.remove(id);
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
