public class Application
{
    public static void main(String[] args)
    {
        LevelBuilder builder = new LevelBuilder();
        GameLevelModel model = builder.buildOneLevel(args[0]);
        View view = new View();
        GameplayController controller = new GameplayController();

        model.addView(view);
        controller.addModel(model);
        controller.addView(view);
        view.addModel(model);
        view.addController(controller);

        view.build();
        view.display();
    }
}