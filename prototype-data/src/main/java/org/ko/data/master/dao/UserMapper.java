package org.ko.data.master.dao;


import org.ko.data.master.domain.User;

public interface UserMapper {

	User findById(Integer id);
}
