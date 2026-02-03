package controller.mappers;

import model.PlayerDTO;
import vista.PlayerRenderInfoDTO;

public class PlayerRenderMapper {

    public static PlayerRenderInfoDTO toRenderDTO(PlayerDTO playerDto) {
        if (playerDto == null) {
            return null;
        }

        return new PlayerRenderInfoDTO(
                playerDto.x,
                playerDto.y,
                playerDto.radius
        );
    }
}
