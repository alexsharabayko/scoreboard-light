# Simple Scoreboard app

## ScoreBoard Class

The `ScoreBoard` class provides functionality for managing games and their scores. It includes the following methods:

### `startGame(List<String> teamNames)`

- Starts a new game with the given team names.
- Throws an `IllegalStateException` if a game with the same ID already exists.

### `finishGame(String id)`

- Finishes and removes a game with the specified ID.
- Throws an `IllegalStateException` if no game with the given ID exists.

### `updateScore(String id, List<Integer> scores)`

- Updates the scores for the teams in a game with the specified ID.
- Throws an `IllegalStateException` if no game with the given ID exists.
- Throws an `IllegalArgumentException` if the number of scores does not match the number of teams.

### `getSummary()`

- Retrieves a summary of all the games in the scoreboard.

## Game Class

The `Game` class represents an individual game and includes information about teams and their scores. It provides two
constructors:

### `Game(List<String> teamNames)`

- Creates a new game with the provided team names and initializes their scores to zero.

### `Game(List<String> teamNames, List<Integer> scores)`

- Creates a new game with the provided team names and initial scores.

Both constructors automatically generate a unique ID for the game based on team names.

## Team Class (Nested within Game)

The `Team` class represents a team within a game and includes the team's name and score.

## Usage

You can use these classes to create and manage a scoreboard system for various games. Here's a basic example of how to
use them:

```java
// Create a ScoreBoard instance
ScoreBoard scoreBoard=new ScoreBoard();

// Start a new game
    List<String> teamNames=List.of("Team A","Team B","Team C");
    Game game=scoreBoard.startGame(teamNames);

// Update scores for the game
    List<Integer> scores=List.of(10,20,15);
    scoreBoard.updateScore(game.getId(),scores);

// Finish the game
    scoreBoard.finishGame(game.getId());

// Get a summary of all games
    String summary=scoreBoard.getSummary();
    System.out.println(summary);
```

## Dependencies

The code uses the Lombok library for simplified getter methods. Make sure to include the Lombok dependency in your
project if you use these classes.

## Notes

- classes are placed in a single package for simplicity
- game supports more than 2 participants in it because it could be used for sports like athletic for example
- game doesn't support any sophisticated mechanism for scores for simplicity. For example in sprint the measure is time,
  not score
- there is no any smart classes hierarchy for simplicity
- Game and Participant classes could be converted to Records