package servlets;

import by.incubator.application.infrastructure.core.annotations.Autowired;
import by.incubator.application.vehicle.VehicleCollection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/viewCars")
public class ViewCarsServlet extends HttpServlet {
    @Autowired
    private VehicleCollection vehicleTypeService;

    @Override
    public void init() throws ServletException{
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cars", vehicleTypeService.getVehicles());
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/viewCarsJSP.jsp");
        dispatcher.forward(request, response);
    }
}
