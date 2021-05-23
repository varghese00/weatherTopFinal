package controllers;

import java.util.List;

import models.Member;
import models.Reading;
import play.mvc.Controller;

public class Admin extends Controller {
    public static void index() {
        List <Member> members= Member.findAll();
        List<Reading> readings = Reading.findAll();
        render("admin.html", readings,members);
    }
}
