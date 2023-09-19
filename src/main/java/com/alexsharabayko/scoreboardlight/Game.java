package com.alexsharabayko.scoreboardlight;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Game {
  private final List<Team> teams;

  private final String id;

  Game(List<Team> teams) {
    this.teams = teams;
    this.id = this.teams.stream().map(Team::getName).collect(Collectors.joining(":"));
  }

  @Getter
  @RequiredArgsConstructor
  static class Team {
    private final String name;
    private final Integer score;
  }
}
