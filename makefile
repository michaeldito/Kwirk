GAMESQUARES = GameSquare.class Player.class Wall.class Hole.class Turnstile.class Pivot.class Stairs.class

clean:
	rm *.class

all: GameplayController.class

run:
	java GameplayController L2.csv

bnr:
	make clean
	make all
	make run

# Builder
LevelBuilder.class: LevelBuilder.java GameLevelModel.class
	javac LevelBuilder.java

# Model
GameLevelModel.class: GameLevelModel.java $(GAMESQUARES)
	javac GameLevelModel.java

GameState.class: GameState.java GameLevelModel.class
	javac GameState.java

# View

# Controllers
GameplayController.class: GameplayController.java GameLevelModel.class
	javac GameplayController.java

# GameSquares
GameSquare.class: GameSquare.java
	javac GameSquare.java
Player.class: Player.java GameSquare.class Circle.class
	javac Player.java
Wall.class: Wall.java GameSquare.class Square.class
	javac Wall.java
Block.class: Block.java GameSquare.class Square.class
	javac Block.java
Hole.class: Hole.java GameSquare.class Square.class
	javac Hole.java
Turnstile.class: Turnstile.java GameSquare.class Square.class
	javac Turnstile.java
Pivot.class: Pivot.java Turnstile.class GameSquare.class Circle.class
	javac Pivot.java
Stairs.class: Stairs.java GameSquare.class Right.class
	javac Stairs.java

# Shapes
Shape.class: Shape.java
	javac Shape.java
Circle.class: Circle.java Shape.class
	javac Circle.java
Quadrilateral.class: Quadrilateral.java Shape.class
	javac Quadrilateral.java
Square.class: Square.java Quadrilateral.class
	javac Square.java
Triangle.class: Triangle.java Shape.class
	javac Triangle.java
Right.class: Right.java Triangle.class
	javac Right.java
