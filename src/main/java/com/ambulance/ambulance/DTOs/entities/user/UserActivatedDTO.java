package com.ambulance.ambulance.DTOs.entities.user;

import com.ambulance.ambulance.DTOs.entities.BaseActivatedDTO;
import com.ambulance.ambulance.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserActivatedDTO extends BaseActivatedDTO<User> {
    @Override
    public User asEntity() {
        User user = new User();
        user.setId(this.getId());
        user.setActivated(this.getActivated());
        return user;
    }
}
