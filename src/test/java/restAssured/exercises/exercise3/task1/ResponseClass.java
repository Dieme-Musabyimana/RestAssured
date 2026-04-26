package restAssured.exercises.exercise3.task1;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ResponseClass extends Root {
    private Integer id;

    public Integer getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
}
