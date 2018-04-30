# Model
GameLevelModel.class: GameLevelModel.java
	javac GameLevelModel.java

GameState.class: GameState.java
	javac GameState.java

# View

# Controllers

# GameSquares

GameSquare.class: GameSquare.java
	javac GameSquare.java
Player.class: Player.java GameSquare.class
	javac Player.java
Wall.class: Wall.java GameSquare.class
	javac Wall.java
Block.class: Block.java GameSquare.class
	javac Block.java
Hole.class: Hole.java GameSquare.class
	javac Hole.java
Turnstile.class: Turnstile.java GameSquare.class
	javac Turnstile.java
Pivot.class: Pivot.java Turnstile.class GameSquare.class
	javac Pivot.java
Stairs.class: Stairs.java GameSquare.class
	javac Stairs.java

# Collections

BlockCollection.class: BlockCollection.java Block.class 
	javac BlockCollection
GameSquareCollection.class: GameSquareCollection.java GameSquare.class
	javac GameSquareCollection
HoleCollection: HoleCollection.java Hole.class
	javac HoleCollection.java
TurnstileCollection.class: TurnstileCollection.java Turnstile.class Pivot.class
	javac TurnstileCollection.java

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