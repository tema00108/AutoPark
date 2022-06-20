package by.incubator.application.dtoFacade;

import by.incubator.application.dto.OrderDto;
import by.incubator.application.dto.RentDto;
import by.incubator.application.dto.VehicleDto;
import by.incubator.application.dto.VehicleTypeDto;
import by.incubator.application.entity.Type;
import by.incubator.application.infrastructure.core.annotations.Autowired;
import by.incubator.application.logic.VehicleLogic;
import by.incubator.application.service.OrderService;
import by.incubator.application.service.RentService;
import by.incubator.application.service.TypeService;
import by.incubator.application.service.VehicleService;

import java.util.List;
import java.util.stream.Collectors;

public class DtoService {
    @Autowired
    VehicleService vehicleService;
    @Autowired
    TypeService typeService;
    @Autowired
    RentService rentService;
    @Autowired
    OrderService orderService;

    public DtoService() { }

    public List<VehicleDto> getVehicles() {
        return vehicleService.getAll().stream().map(vehicle -> {
            Type type = typeService.get(vehicle.getTypeId());

            return VehicleDto.builder()
                    .id(vehicle.getId())
                    .typeName(type.getName())
                    .taxCoefficient(type.getTaxCoefficient())
                    .color(vehicle.getColor())
                    .engineName(vehicle.getEngineType())
                    .engineTaxCoefficient(vehicle.getEngineTaxCoefficient())
                    .tax(VehicleLogic.getCalcTaxPerMonth(vehicle, type))
                    .manufactureYear(vehicle.getManufactureYear())
                    .mileage(vehicle.getMileage())
                    .modelName(vehicle.getModel())
                    .registrationNumber(vehicle.getRegistrationNumber())
                    .tankVolume(vehicle.getCapacity())
                    .weight(vehicle.getWeight())
                    .per100km(vehicle.getConsumption())
                    .maxKm(VehicleLogic.getMaxKilometers(vehicle))
                    .income(VehicleLogic.getTotalIncome(rentService.getAll()
                                                                   .stream()
                                                                   .filter(rent -> rent.getCarId().equals(vehicle.getId()))
                                                                   .collect(Collectors.toList())))
                    .build();
        }).collect(Collectors.toList());
    }

    public List<VehicleTypeDto> getTypes() {
        return typeService.getAll().stream().map(type ->
                VehicleTypeDto.builder()
               .typeId(type.getId())
               .name(type.getName())
               .taxCoefficient(type.getTaxCoefficient())
               .build()).collect(Collectors.toList());
    }

    public List<RentDto> getRents(long id) {
        return rentService.getAll().stream().filter(rent -> rent.getCarId().equals(id)).map(rent ->
                RentDto.builder()
                .carId(id)
                .date(rent.getDate())
                .cost(rent.getCost())
                .build()).collect(Collectors.toList());
    }

    public List<OrderDto> getOrders() {
        return orderService.getAll().stream().map(order ->
                OrderDto.builder()
                .vehicleId(order.getVehicleId())
                .defect(order.getDefect())
                .breakingAmount(order.getBreakingAmount())
                .build()).collect(Collectors.toList());
    }
}