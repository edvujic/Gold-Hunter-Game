## Gold Collection Game

This project, implemented as part of the Software Lab I course at Kocaeli University's Computer Engineering Department, presents the Gold Collection Game.

### Overview
The Gold Collection Game is a strategic multiplayer game where four players compete on a dynamic rectangular board to collect gold while adhering to various rules and constraints.

### Features
- **Dynamic Board**: The game board consists of identical squares, with the size being dynamically determined (default size: 20x20 squares).
- **Player Characteristics**: Each player possesses unique characteristics affecting their gameplay strategy.
- **Gold Distribution**: Gold is randomly distributed across the board, with a certain percentage of squares containing gold (default: 20%). Additionally, a percentage of squares containing hidden gold is set (default: 10%).
- **Movement**: Players can move forward, backward, left, or right, with a specified number of steps per move (default: 3 steps).
- **Target Determination**: Players must decide their target gold before making a move, with associated costs for target determination.
- **Game End**: The game ends when all players run out of gold or when all gold on the squares is depleted.

### Player Characteristics
- **Player A**: Targets the nearest gold with a fixed cost for each move and target determination.
- **Player B**: Targets the most profitable gold square considering distance and gold amount, with associated costs for moves and target determination.
- **Player C**: Opens hidden gold squares before determining the target and targets the most profitable gold among all, with associated costs for moves and target determination.
- **Player D**: Anticipates other players' moves and targets the most profitable gold excluding those targeted by others, with associated costs for moves and target determination.

### Output
- Upon game completion, a summary table is generated displaying various statistics for each player, such as total steps, gold spent, treasury gold, and collected gold.
- The game is designed to work visually in a dynamic manner.
- Each player's actions are logged into separate files for analysis.

### How to Run
1. Clone the repository.
2. Open the project in your preferred IDE.
3. Compile and run the main file.
4. Adjust parameters as needed before starting the game.
