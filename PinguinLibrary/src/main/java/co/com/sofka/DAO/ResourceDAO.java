package co.com.sofka.DAO;

import co.com.sofka.model.Resource;

import java.sql.SQLException;
import java.util.List;

public interface ResourceDAO {
        void insertResource(Resource resource) throws SQLException;
        List<Resource> getAllResources();
        Resource getResourceById(int id);
        void updateResource(Resource resource);
        void deleteResource(Resource resource);
}
