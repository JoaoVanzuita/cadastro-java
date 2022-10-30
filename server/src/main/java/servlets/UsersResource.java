package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domain.User;

import javax.persistence.*;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
public class UsersResource extends HttpServlet {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("crud-java-web");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(String body){

        User user = new User();

        JsonObject json = new Gson().fromJson(body, JsonObject.class);

        user.setName(json.get("name").getAsString());
        user.setEmail(json.get("email").getAsString());
        user.setPassword(json.get("password").getAsString());

        entityManager.getTransaction().begin();

        try{

            entityManager.persist(user);

        }catch (PersistenceException persistenceException){

            return Response.status(404).entity("{\"message\": \"User already registered\"}").build();
        }

        entityManager.getTransaction().commit();

        return Response.status(201).build();
    }

    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id){

        User user = entityManager.find(User.class, id);

        if(user == null){

            return Response.status(404).entity("{\"message\": \"User not found\"}").build();
        }

        String userJson = new Gson().toJson(user);

        return Response.status(200).entity(userJson).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String body){

        System.out.println("chegou");

        //Initialing json
        JsonObject json = new Gson().fromJson(body, JsonObject.class);

        //Get query parameters from request body
        String email = json.get("email").getAsString();
        String password = json.get("password").getAsString();

        //Initializing query
        String query = "select u from User u where email = ?1 and password = ?2";

        User user;

        //Getting user
        TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);

        try{

            user = typedQuery.setParameter(1, email).setParameter(2, password).getSingleResult();

        }catch (NoResultException noResultException){

            return Response.status(404).entity("{\"message\": \"User not found\"}").build();
        }

        return Response.status(200).entity(user.getIdUser()).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, String body){

        User user = entityManager.find(User.class, id);

        JsonObject json = new Gson().fromJson(body, JsonObject.class);

        user.setName(json.get("name").getAsString());
        user.setEmail(json.get("email").getAsString());
        user.setPassword(json.get("password").getAsString());

        entityManager.getTransaction().begin();

        entityManager.merge(user);

        entityManager.getTransaction().commit();

        return Response.status(204).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id){

        User user = entityManager.find(User.class, id);

        if(user == null){

            return Response.status(404).entity("{\"message\": \"User not found\"}").build();
        }

        entityManager.getTransaction().begin();

        entityManager.remove(user);

        entityManager.getTransaction().commit();

        return Response.status(204).build();
    }
}