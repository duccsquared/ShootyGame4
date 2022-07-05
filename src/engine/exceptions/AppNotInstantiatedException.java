package engine.exceptions;

public class AppNotInstantiatedException extends Exception {
    public AppNotInstantiatedException() {
        super("Engine.App was accessed using getInstance without having been instantiated");
    }
}
