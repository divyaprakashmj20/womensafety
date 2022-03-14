package com.tus.womensafety.dto;
import com.tus.womensafety.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    Long id;
    String name;
    String email;
    String phoneNumber;
    List<UserEntity> relatives;
}
