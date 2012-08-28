<% if(session.getAttribute("usuarioSIST") == null) { %>
    <%@include file="/_modulos/_sistema/_admin/login.jsp" %>
<% } else { %>
    <%@include file="/_modulos/_sistema/_admin/index.jsp" %>
<% } %>
