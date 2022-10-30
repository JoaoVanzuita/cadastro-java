package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


@Path("/user")
public class UsersResource extends HttpServlet {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("crud-java-web");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void post(String body){

//        System.out.println(body);

        User user = new User();

        JsonObject json = new Gson().fromJson(body, JsonObject.class);

//        System.out.println(json);
//        System.out.println(json.get("name"));
//        System.out.println(json.get("email"));
//        System.out.println(json.get("password"));

        user.setName(json.get("name").getAsString());
        user.setEmail(json.get("email").getAsString());
        user.setPassword(json.get("password").getAsString());

//        System.out.println(user.getName());
//        System.out.println(user.getEmail());
//        System.out.println(user.getPassword());

        entityManager.getTransaction().begin();

        entityManager.persist(user);

        entityManager.getTransaction().commit();
    }

    @GET
    @Path("{id}")
    public void get(@PathParam("id") Long id){
        User user = entityManager.find(User.class, id);



    }
}
