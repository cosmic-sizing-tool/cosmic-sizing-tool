package controllers;

/**
 * Created by Mathieu on 6/11/2016.
 */
public class Measurers extends Controller {

  public Result index() {
      return ok("index!");
  }

  public Result search() {
    return ok("search!");
  }
}
