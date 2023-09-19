package com.alexsharabayko.scoreboardlight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoard {
  private final List<Game> games = new ArrayList<>();

  public void startGame(List<String> teamNames) {
    final var teams = teamNames.stream().map(name -> new Game.Team(name, 0)).toList();
    final var game = new Game(teams);

    games.add(game);
  }

  public String getSummary() {
    return games
        .stream()
        .map(game -> {
          return game.getTeams()
              .stream()
              .map(team -> String.format("%s %d", team.getName(), team.getScore()))
              .collect(Collectors.joining(" - "));
        })
        .collect(Collectors.joining("; "));
  }
}
