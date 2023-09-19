package com.alexsharabayko.scoreboardlight;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Game {
  private final List<Team> teams;

  @Getter
  @RequiredArgsConstructor
  static class Team {
    private final String name;
    private final Integer score;
  }
}
