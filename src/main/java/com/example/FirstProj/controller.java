package com.example.FirstProj;


        import java.util.ArrayList;
        import java.util.List;

        import com.sun.org.apache.xpath.internal.operations.Mod;
        import org.springframework.web.bind.annotation.*;

@RestController
public class controller {
    private List<Model> data = new ArrayList<>();
    private Object temp;

    @GetMapping("/show")
    public void create() {
        for(Model m: data){
            System.out.print(m.getId()+"\t");
            System.out.println(m.getName());
        }
    }

    @PostMapping("/create")
    public String create(@RequestParam(value = "name", defaultValue = "World") String name) {
        temp = Math.round(Math.random()*1000000);
        data.add(new Model(Math.round(Math.random()*1000000), name));
        return String.format("EmpID: %d generated for name: %s", temp, name);
    }

    @GetMapping("/read")
    public String read(@RequestParam(value = "name", defaultValue = "World") String name) {
        for(Model m: data){
            if(m.getName().equals(name)){
                return String.format("Hello %s! Your EmpID is %d", m.getName(), m.getId());
            }
        }
        return String.format("No data found for your name: %s", name);
    }

    @PutMapping("/{id}/{name}")
    public String update(@PathVariable(value = "id") long id,
                        @PathVariable(value = "name") String name) {
        for(Model m: data){
            if(m.getId() == id){
                temp = m.getName();
                m.setName(name);
                return String.format("EmpID: %d\n Name Updated: %s --> %s!", m.getId(), temp ,m.getName());
            }
        }
        return String.format("No data found for EmpID: %d", id);
    }

    @DeleteMapping("/{id}")
    public String update(@PathVariable(value = "id") long id) {
        int i = 0;
        for(Model m: data){
            if(m.getId() == id){
                temp = m.getName();
                data.remove(i);
                return String.format("REMOVED DATA FOR: \n EmpID: %d\nName: %s", id, temp );
            }
            i++;
        }
        return String.format("No data found for EmpID: %d", id);
    }
}