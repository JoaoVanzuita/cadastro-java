package servlets;

public class Response {
    private String status;
    private String message;

    private Response() {
    }

    public static Response OK(String message){
        Response response = new Response();

        response.status = "OK";
        response.message = message;
        return response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}