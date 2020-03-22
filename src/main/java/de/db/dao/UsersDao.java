package de.db.dao;

import de.db.models.User;

import java.util.List;

public interface UsersDao extends CrudDao<User>{
    List<User> findAllByFirstName(String firstName);
}
