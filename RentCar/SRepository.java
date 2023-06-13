package RentCar;

import java.util.ArrayList;

public interface SRepository <S> {
    ArrayList<String> getlistModel();
    ArrayList<String> getListBrand();
    ArrayList<S> getAll();


    Boolean create(Car c);

    Boolean update(S s);

    Boolean update(Car c);

    Boolean delete(S s);
    S find(String brand);


}

