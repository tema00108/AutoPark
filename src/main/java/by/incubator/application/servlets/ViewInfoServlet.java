package by.incubator.application.servlets;

import by.incubator.application.dtoFacade.DtoService;
import by.incubator.application.infrastructure.core.annotations.Autowired;
import by.incubator.application.infrastructure.core.impl.ApplicationContext;
import by.incubator.application.infrastructure.vehicleService.Fixer;
import by.incubator.application.infrastructure.vehicleService.impl.MechanicService;
import by.incubator.application.vehicle.parser.ParserBreakingInterface;
import by.incubator.application.vehicle.parser.ParserVehicleInterface;
import by.incubator.application.vehicle.parser.impl.ParserBreakingFromDB;
import by.incubator.application.vehicle.parser.impl.ParserVehicleFromDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/info")
public class ViewInfoServlet extends HttpServlet {
    @Autowired
    private DtoService vehicleTypeService;

    @Override
    public void init() throws ServletException{
        super.init();
        Map<Class<?>, Class<?>> interfaceToImplementation = initInterfaceToImplementation();
        ApplicationContext context = new ApplicationContext("by.incubator.application", interfaceToImplementation);
        vehicleTypeService = context.getObject(DtoService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id;
        String idString = request.getParameter("id");

        if (!idString.isEmpty()) {
            id = Long.parseLong(request.getParameter("id"));
        } else {
            throw new IllegalArgumentException("Parameter \"id\" is not set");
        }

        request.setAttribute("cars", vehicleTypeService
                                        .getVehicles()
                                        .stream()
                                        .filter(vehicleDto -> id == vehicleDto.getId())
                                        .collect(Collectors.toList()));
        request.setAttribute("rents", vehicleTypeService.getRents(id));
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/viewCarInfoJSP.jsp");
        dispatcher.forward(request, response);
    }

    private Map<Class<?>, Class<?>> initInterfaceToImplementation() {
        Map<Class<?>, Class<?>> map = new HashMap<>();
        map.put(Fixer.class, MechanicService.class);
        map.put(ParserVehicleInterface.class, ParserVehicleFromDB.class);
        map.put(ParserBreakingInterface.class, ParserBreakingFromDB.class);
        return map;
    }
}
