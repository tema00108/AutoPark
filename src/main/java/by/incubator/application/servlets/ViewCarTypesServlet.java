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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@javax.servlet.annotation.WebServlet("/viewTypes")
public class ViewCarTypesServlet extends javax.servlet.http.HttpServlet {
    @Autowired
    private DtoService vehicleTypeService;

    @Override
    public void init() throws ServletException {
        super.init();
        Map<Class<?>, Class<?>> interfaceToImplementation = initInterfaceToImplementation();
        ApplicationContext context = new ApplicationContext("by.incubator.application", interfaceToImplementation);
        vehicleTypeService = context.getObject(DtoService.class);
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("types", vehicleTypeService.getTypes());
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/viewTypesJSP.jsp");
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
