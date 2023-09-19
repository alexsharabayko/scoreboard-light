package com.alexsharabayko.scoreboardlight;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Game {
  private final List<Team> teams;

  private final String id;

  Game(List<String> teamNames) {
    this.teams = teamNames.stream().map(name -> new Team(name, 0)).toList();

    this.id = this.teams.stream().map(Team::getName).collect(Collectors.joining(":"));
  }

  Game(List<String> teamNames, List<Integer> scores) {
    if (teamNames.size() != scores.size()) {
      throw new IllegalArgumentException("Amount of scores must be the same as amount of teams");
    }

    this.teams = new ArrayList<>();

    for (var i = 0; i < teamNames.size(); i++) {
      teams.add(new Team(teamNames.get(i), scores.get(i)));
    }

    this.id = this.teams.stream().map(Team::getName).collect(Collectors.joining(":"));
  }

  @Getter
  @RequiredArgsConstructor
  static class Team {
    private final String name;
    private final Integer score;
  }
}
