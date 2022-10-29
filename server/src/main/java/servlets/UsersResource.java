package servlets;

import domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import java.io.IOException;


@Path("/user")
public class UsersResource extends HttpServlet {

    @GET
    public void create(){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("crud-java-web");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        User user = new User("teste", "teste", "teste");

        entityManager.getTransaction().begin();

        entityManager.persist(user);

    }
}
