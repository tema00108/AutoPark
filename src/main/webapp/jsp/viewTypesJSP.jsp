<%@ page import="java.util.List" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="by.incubator.application.dto.VehicleDto" %>
<%@ page import="by.incubator.application.dto.VehicleTypeDto" %><%--
  Created by IntelliJ IDEA.
  User: artem
  Date: 02.06.2022
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Просмотр типов машин</title>
    <link href="/resources/css/style.css" rel="stylesheet">
</head>
<body>
    <div class="center flex full-vh">
        <div class="vertical-center">
            <a class="ml-20" href="${pageContext.request.contextPath}/">На главную</a>
            <br />
            <br />
            <br />
            <br />
            <%
                String sortKey = null;
                String order = null;
                if (request.getParameter("name") != null) sortKey = "name";
                if (request.getParameter("tax") != null) sortKey = "tax";
                if (request.getParameter("asc") != null) order = "asc";
                if (request.getParameter("desc") != null) order = "desc";
            %>
            <%
                if (sortKey != null) {%>
            <%
                String clearPath = "/viewTypes";
                String ascPath = "?" + sortKey + "&asc";
                String descPath = "?" + sortKey + "&desc";
            %>
            <div>
                <a class="ml-20" href="<%=descPath%>">Сортировать по убыванию</a>
                <a class="ml-20" href="<%=ascPath%>">Сортировать по возрастанию</a>
                <a class="ml-20" href="${pageContext.request.contextPath}<%=clearPath%>">Очистить параметры поиска</a>
            </div>
            <br />
            <br />
            <br />
            <%}%>
            <table>
                <caption>Типы машин</caption>
                <tr>
                    <th>Название</th>
                    <th>Коэффициент дорожного налога</th>
                </tr>
                <%
                    List<VehicleTypeDto> dtoList = (List<VehicleTypeDto>) request.getAttribute("types");
                    Comparator<VehicleTypeDto> comparator = null;
                    if (sortKey != null && sortKey.equals("name")) {
                        comparator = Comparator.comparing(VehicleTypeDto::getName);
                    }
                    if (sortKey != null && sortKey.equals("tax")) {
                        comparator = Comparator.comparingDouble(VehicleTypeDto::getTaxCoefficient);
                    }
                    if (order != null && comparator != null && order.equals("desc")) {
                        comparator = comparator.reversed();
                    }
                    if (comparator != null) {
                        dtoList.sort(comparator);
                    }
                    for (VehicleTypeDto dto : dtoList) {
                %>
                <tr>
                    <td><%=dto.getName()%></td>
                    <td><%=dto.getTaxCoefficient()%></td>
                </tr>
                <%}%>
            </table>
            <%if (dtoList.size() > 0) {%>
            <p>Минимальный коэффициент:  <strong><%=dtoList.stream().map(VehicleTypeDto::getTaxCoefficient).min(Double::compareTo).get()%></strong></p>
            <p>Максимальный коэффициент: <strong><%=dtoList.stream().map(VehicleTypeDto::getTaxCoefficient).max(Double::compareTo).get()%></strong></p>
            <%}%>
            <br />
            <br />
            <br />
            <div>
                <% if (request.getParameter("name") == null) {%><a class="ml-20" href="${pageContext.request.contextPath}/viewTypes?name">Сортировать по названию</a><%}%>
                <% if (request.getParameter("tax") == null) {%><a class="ml-20" href="${pageContext.request.contextPath}/viewTypes?tax">Сортировать по коэффициенту</a><%}%>
            </div>
        </div>
    </div>
</body>
</html>
