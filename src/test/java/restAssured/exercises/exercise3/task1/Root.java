package restAssured.exercises.exercise3.task1;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class Root {
    private String name;
    private String username;
    private String email;
    private Address address;


    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public Address getAddress(){
        return address;
    }
    public void setAddress(Address address){
        this.address=address;
    }
}
