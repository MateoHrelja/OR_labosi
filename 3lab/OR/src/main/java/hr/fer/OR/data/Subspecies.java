package hr.fer.OR.data;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class Subspecies {

    @NonNull
    private final String name;


    public Subspecies(@NonNull String name) {this.name = name;}

    @NonNull
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subspecies)) return false;
        Subspecies that = (Subspecies) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
