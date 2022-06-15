package servlets;

import by.incubator.application.dtoFacade.DtoObjects;
import by.incubator.application.infrastructure.core.annotations.Autowired;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/info")
public class ViewInfoServlet extends HttpServlet {
    @Autowired
    private DtoObjects vehicleTypeService;

    @Override
    public void init() throws ServletException{
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("cars", vehicleTypeService
                                        .getVehicles()
                                        .stream()
                                        .filter(vehicleDto -> id == vehicleDto.getId())
                                        .collect(Collectors.toList()));
        request.setAttribute("rents", vehicleTypeService.getRent(id));
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/viewCarInfoJSP.jsp");
        dispatcher.forward(request, response);
    }
}
