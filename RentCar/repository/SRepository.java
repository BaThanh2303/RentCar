package RentCar.repository;

import java.util.ArrayList;

public interface SRepository <S> {

    ArrayList<S> getAll();

    Boolean create(S s);

    Boolean update(S s);

    Boolean delete(S s);
    S find(String brand);


}

