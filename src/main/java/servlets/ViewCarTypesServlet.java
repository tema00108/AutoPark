package servlets;

import by.incubator.application.infrastructure.core.annotations.Autowired;
import by.incubator.application.vehicle.VehicleCollection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;

@javax.servlet.annotation.WebServlet("/viewTypes")
public class ViewCarTypesServlet extends javax.servlet.http.HttpServlet {
    @Autowired
    private VehicleCollection vehicleTypeService;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("types", vehicleTypeService.getTypes());
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/viewTypesJSP.jsp");
        dispatcher.forward(request, response);
    }
}
