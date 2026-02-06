# Cat Lady — Java Console Board Game

**Object-Oriented Design Group Project (COMP 2711)**  
**Language:** Java  
**Architecture:** Model–View–Controller (MVC)

**Contributors:**  
Flora Campbell · Emil Paul · Asher Lawrence  · Serena Davis

---

## Overview

This project is a Java console implementation of *Cat Lady* (designed by Josh Wood), a turn-based board game in which players compete to score points by collecting, feeding, and managing cats and item cards.

The project was developed for an Object-Oriented Design course with a strong emphasis on clean architecture, maintainability, and testability, rather than graphical presentation. The game supports 1–4 players and runs entirely in the terminal.


---

## Design & Architecture

### MVC Pattern
- **Model**: Encapsulates game rules, state, cards, actions, decks, and scoring.
- **View**: Handles all console input and output.
- **Controller**: Manages game flow, turn order, and coordination between model and view.

### SOLID Principles
The project intentionally applies SOLID design principles:

- **Single Responsibility Principle (SRP)**  
  Controllers are split by responsibility (game start, turns, input, feeding, game end).

- **Open/Closed Principle (OCP)**  
  New card types and actions can be added with minimal changes to existing code.

- **Liskov Substitution Principle (LSP)**  
  Polymorphic action and card hierarchies allow interchangeable behavior.

- **Interface Segregation Principle (ISP)**  
  Interfaces are kept small and focused.

- **Dependency Inversion Principle (DIP)**  
  High-level game flow depends on abstractions rather than concrete implementations.

---

## Testing

The project includes a test suite to validate:
- Game state transitions
- Player actions
- Card and deck behavior
- Scoring logic

Testing supported refactoring and helped prevent regressions during development.

---

## How to Run the Game

### Using VS Code (Recommended)

1. Open the project root (the folder containing `src/`) in VS Code.
2. Open `src/controller/Game.java`.
3. Select **Run Java** (not “Run Code”).

### Using the Command Line

From the project root:

```bash
cd src
javac controller/*.java model/*.java view/*.java
java controller.Game

## Gameplay Summary

- Players determine turn order before starting.
- The board uses rows and columns labeled **A–F**.
- On each turn, players may draw cards or use item cards (spray bottle, laser pointer, lost cat cards).
- After the deck is exhausted, players feed their cats.
- Final scores are calculated and displayed.

---

## Learning Outcomes

Through this project, I gained experience with:

- Designing medium-scale object-oriented systems
- Applying SOLID principles in practice
- Structuring applications using the MVC architectural pattern
- Writing testable and maintainable Java code
- Collaborating effectively in a team-based Git workflow

---

## Notes

This project was developed for academic purposes and focuses on software design quality and architecture rather than graphical presentation.

