# Kwirk

## Objective
The objective of the game is to get from one end of the room to the other, and
go down the staircase on the other side.

## Gameplay Options
- Redo:
A button or key that will restart the current stage from the beginning.
- End:
A button or key that will return the player to the menu.
- Back:
A button or key that will undo the last move made, up to n times.

## Obstacles
To reach the staircase on the other side of the room, Kwirk must navigate around and
interact with various obstacles.
- Brick Walls:
Cannot be moved nor walked through, blocks must be pushed around them
- Blocks:
Blocks of various sizes. They are pushable by characters, and may block paths necessary
for a character to reach the stairs. They can also fill holes, allowing the player to
walk past.
- Holes:
Cannot be walked over. Blocks can be used to fill holes, or characters must maneuver
around them.
- Turnstiles:
Blocks set on an axis that turn 90 degress when pushed. They come in single, double,
triple, and quadruple variations. They cannot turn if something is blocking their
radius of movement.

# Ideas
``` 
  while initially scanning in the grid from a file:
    if square is a hole || pivot || block:
      place into temp array
    continue scanning and populating grid
  when finished scanning grid:
    for all hole || pivot || block temp arrays:
      for each pivot:
        add pivot to turnstile gamesquare collection
        if NSEW neighbor of pivot is a turnstile:
          add neighbor to a the collection
      
      