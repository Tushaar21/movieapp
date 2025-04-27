package sr.unasat.movies;

import sr.unasat.movies.config.JPAconfig;
import sr.unasat.movies.services.ServiceImpl;

public class Main {
    public static void main(String[] args) {
        JPAconfig.getEntityMangerFactory();

        ServiceImpl service = new ServiceImpl();
        service.start();

        JPAconfig.shutdown();
    }
}