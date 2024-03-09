package domain;

public abstract class Entity {
    protected int id;
    public Entity(int id){
        this.id = id;
    }

    public int getId() {return id; }

}
