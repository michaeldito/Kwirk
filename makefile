GAMESQUARES = GameSquare.class Player.class Wall.class Hole.class Turnstile.class Pivot.class Stairs.class
SHAPES = Shape.class Triangle.class Quadrilateral.class Square.class Right.class
LEVELS = L1.csv L2.csv L3.csv L4.csv L5.csv L6.csv L7.csv L8.csv L9.csv L10.csv L11.csv L12.csv L13.csv L14.csv
all: $(GAMESQUARES) $(SHAPES) GameLevelModel.class LevelBuilder.class GameplayController.class View.class Kwirk.class

submit:
	mkdir ditoP
	cp ditoPI.pdf ditoP
	cp ditoPN.pdf ditoP
	cp ditoP.txt ditoP
	cp ditoP.bmp ditoP
	cp ditoP.jar ditoP
	tar -czvf ditoP.tgz ditoP
	cp ditoP.tgz ~tiawatts/cs360drop

toBlue:
	make all
	jar cvfm ditoP.jar manifest.txt *.class KwirkCover.png Victory.png L*.csv
	scp ditoP.jar mdito@blue.cs.sonoma.edu:~/cs360s18/Kwirk

c:
	make clean

clean:
	rm *.class

m r:
	make run
	
run:
	java Kwirk $(LEVELS)

bnr:
	make clean
	make all
	make run

# Kwirk
Kwirk.class: Kwirk.java GameLevelModel.class LevelBuilder.class View.class GameplayController.class
	javac Kwirk.java

# Builder
LevelBuilder.class: LevelBuilder.java GameLevelModel.class
	javac LevelBuilder.java

# Model
GameLevelModel.class: GameLevelModel.java $(GAMESQUARES)
	javac GameLevelModel.java

# View
View.class: View.java
	javac View.java

# Controller
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
