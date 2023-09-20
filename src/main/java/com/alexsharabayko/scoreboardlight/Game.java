package com.alexsharabayko.scoreboardlight;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Game {
  private final List<Participant> participants;

  private final String id;

  Game(List<String> participantNames) {
    this.participants = participantNames.stream().map(name -> new Participant(name, 0)).toList();

    this.id = this.participants.stream().map(Participant::getName).collect(Collectors.joining(":"));
  }

  Game(List<String> participantNames, List<Integer> scores) {
    if (participantNames.size() != scores.size()) {
      throw new IllegalArgumentException("Amount of scores must be the same as amount of participants");
    }

    this.participants = new ArrayList<>();

    for (var i = 0; i < participantNames.size(); i++) {
      participants.add(new Participant(participantNames.get(i), scores.get(i)));
    }

    this.id = this.participants.stream().map(Participant::getName).collect(Collectors.joining(":"));
  }

  @Override
  public String toString() {
    return participants.stream().map(Participant::toString).collect(Collectors.joining(" - "));
  }

  @Getter
  @RequiredArgsConstructor
  static class Participant {
    private final String name;
    private final Integer score;

    @Override
    public String toString() {
      return String.format("%s %d", name, score);
    }
  }
}
