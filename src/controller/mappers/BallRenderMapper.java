package controller.mappers;

import physicsBall.PhysicsBallDTO;
import vista.BallRenderInfoDTO;

import java.util.ArrayList;
import java.util.List;

public class BallRenderMapper {

    public static List<BallRenderInfoDTO> toRenderableAsteroids(List<PhysicsBallDTO> physicsBall){
        List<BallRenderInfoDTO> renderAsteroids = new ArrayList<>();

        for (PhysicsBallDTO dto : physicsBall){
            renderAsteroids.add(toRenderDTO(dto));
        }

        return renderAsteroids;
    }

    public static BallRenderInfoDTO toRenderDTO(PhysicsBallDTO physicsDto) {
        return new BallRenderInfoDTO(
                physicsDto.x,
                physicsDto.y,
                physicsDto.radius
        );
    }
}
