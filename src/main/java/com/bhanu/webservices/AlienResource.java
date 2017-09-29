package com.bhanu.webservices;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Path("/aliens")
public class AlienResource {

    AlienRepository repo = new AlienRepository();

        @GET
        @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
        public List<Alien> getAliens(){

            System.out.println("get Aliens called ");
            return repo.getAliens();
        }

        @GET
        @Path("alien/{id}")
        @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
        public Alien getAlien(@PathParam("id") int id){

            return repo.getAlien(id);
        }

        @POST
        @Path("alien")
        @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
        public Alien createAlien(Alien a1){

            System.out.println(a1);
            repo.create(a1);
            return a1;
        }

    @PUT
    @Path("alien")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Alien updateAlien(Alien a1){

        System.out.println(a1);
        if (repo.getAlien(a1.getId()).getId()==0){
            repo.create(a1);
        }else {
            repo.update(a1);
        }
        return a1;
    }

    @DELETE
    @Path("alien/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Alien deleteAlien(@PathParam("id") int id){

        Alien a=repo.getAlien(id);
        if (a.getId()!=0)
            repo.delete(id);
        return a;
    }
}


@XmlRootElement
class Alien{

    private int id;
    private String name;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Alien{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", points=" + address +
                '}';
    }

    public void setAddress(String address) {
        this.address = address;
    }


}


