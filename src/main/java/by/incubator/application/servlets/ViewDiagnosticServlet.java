package by.incubator.application.servlets;

import by.incubator.application.checkCars.WorkroomManager;
import by.incubator.application.dtoFacade.DtoService;
import by.incubator.application.infrastructure.core.annotations.Autowired;
import by.incubator.application.infrastructure.core.impl.ApplicationContext;
import by.incubator.application.infrastructure.validation.techroom.Workroom;
import by.incubator.application.infrastructure.vehicleService.Fixer;
import by.incubator.application.infrastructure.vehicleService.impl.MechanicService;
import by.incubator.application.vehicle.VehicleCollection;
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

@WebServlet("/viewDiagnostic")
public class ViewDiagnosticServlet extends HttpServlet {
    @Autowired
    private DtoService vehicleTypeService;
    @Autowired
    private WorkroomManager workroomManager;
    @Autowired
    private ApplicationContext context;

    @Override
    public void init() throws ServletException{
        super.init();
        Map<Class<?>, Class<?>> interfaceToImplementation = initInterfaceToImplementation();
        context = new ApplicationContext("by.incubator.application", interfaceToImplementation);
        vehicleTypeService = context.getObject(DtoService.class);
        workroomManager = context.getObject(WorkroomManager.class);
        workroomManager.checkVehiclesInWorkroom(context);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cars", vehicleTypeService.getVehicles());
        request.setAttribute("ordersBeforeRepairing", vehicleTypeService.getOrders());

        workroomManager.repairVehiclesInWorkroom(context);

        request.setAttribute("ordersAfterRepairing", vehicleTypeService.getOrders());
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/viewDiagnosticJSP.jsp");
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
