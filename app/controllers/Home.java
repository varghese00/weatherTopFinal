package controllers;

import play.*;
import play.mvc.*;


public class Home extends Controller{
    public static void index() {
        Logger.info("Rendering home");
        render ("home.html");
    }
}
