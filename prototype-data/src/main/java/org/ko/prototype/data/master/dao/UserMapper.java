package org.ko.prototype.data.master.dao;


import org.ko.prototype.data.master.domain.User;

public interface UserMapper {

	User findById(Integer id);
}
