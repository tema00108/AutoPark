package by.incubator.application.dtoFacade;

import by.incubator.application.dto.VehicleDto;
import by.incubator.application.entity.Type;
import by.incubator.application.infrastructure.core.annotations.Autowired;
import by.incubator.application.service.OrderService;
import by.incubator.application.service.RentService;
import by.incubator.application.service.TypeService;
import by.incubator.application.service.VehicleService;
import by.incubator.application.vehicle.VehicleCollection;

import java.util.List;

public class DtoObjects {
    @Autowired
    VehicleCollection vehicleCollection;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    TypeService vehicleTypeService;
    @Autowired
    RentService rentService;
    @Autowired
    OrderService orderService;

    public DtoObjects() { }

    public List<VehicleDto> getVehicles() {
        return vehicleCollection.getVehicles().stream().map(vehicle -> {
            Type type = vehicleCollection.getTypeById(vehicle.getId());
            return VehicleDto.builder()
                    .id(vehicle.getId())
                    .typeId(vehicle.getTypeId())
                    .typeName(type.getName())
                    .taxCoefficient(type.getTaxCoefficient())
                    .color(vehicle.getColor())
                    .engineName(vehicle.getEngineName())
                    .engineTaxCoefficient(ve)
        })
    }
}