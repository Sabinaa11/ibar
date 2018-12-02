package com.sinam.service;

import com.sinam.model.User;

import java.util.List;

public interface UserService {

    /**
     * Вернуть всех юзеров у которых isActive false
     * Доступно только для админа, поэтому в параметрах должен приходить айди юзера который делает запрос
     * @param currentUserId
     * @return
     */
    List<User> getBlackList(Long currentUserId);

    /**
     * Активировать юзера(поменять isActive на true)
     * Решить что делать: При повторном получении списка заданий статус юзера снова поменяется на false
     * Доступно только для админа, поэтому в параметрах должен приходить айди юзера который делает запрос
     * @param userId
     * @param currentUserId
     */
    void activateUser(Long userId, Long currentUserId);

    /**
     * Создание нового юзера. Создавать может только юзер с ролью ADMIN
     * @param user
     * @param currentUserId
     * @return
     */
    User create(User user, Long currentUserId);
}