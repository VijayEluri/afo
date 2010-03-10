<%@page contentType="application/xhtml+xml" pageEncoding="iso-8859-1"%>
<%@page import="org.macrobug.afo.bean.*"%>
<%@ page errorPage="fail.jsp"%>
<%
String type=request.getParameter("t");
if(type==null){%>
    <div style="float:right"><%
            String user=(String)session.getAttribute("user");
            if(user==null){%>
            <form action="LogIn.jsp" method="post" id="f" accept-charset="utf-8" >
                <table>
                    <tr><td><label for="u">User:</label></td><td><input type="text" value="" name="u" id="u"/></td></tr>
                    <tr><td><label for="p">Password:</label></td><td><input type="password" name="p" id="p"/></td></tr>
                </table><p>
                <input type="hidden" name="t" value="enter"/>
                <input type="submit" value="Accedi"/>
                <input type="submit" value="Nuovo" onclick="t.value='new'"/></p>
            </form>
            <%}
            else{%>
            <p style="float:right;">Ciao, <%=user%><br/><a href="index.jsp?type=out">logout</a><br/>
                <%if(!request.getRequestURI().contains("add_image")&&new User(user).getLevel()<1){%><a href="add_image.jsp">Carica immagini</a><br/><%}%>
                <%if(!request.getRequestURI().contains("modify_text")){%><a href="modify_text.jsp">I tuoi motti</a><br/><%}%>
                <%if(!request.getRequestURI().contains("modify_icon")){%><a href="modify_icon.jsp">I tuoi avatar</a><br/><%}%>
                <%if(!request.getRequestURI().contains("index")){%><a href="index.jsp">Pagina principale</a><br/><%}%>
            <%if(!request.getRequestURI().contains("user")){%><a href="modify_user.jsp">Modifica i tuoi dati</a><br/><%}%></p>
        <%}%></div><%}
else if(type.equals("enter")){
    if((new User(request.getParameter("u"),request.getParameter("p"))).exists()){
        session.setAttribute("user",request.getParameter("u"));
        response.sendRedirect("modify_text.jsp");
    }
    else{
        throw new Exception("Username o password errati<br/>Ritenta sarai più fortuanto");
    }
}
else{
    if(type.equals("new")){
        if(request.getParameter("u").length()<1||(new User(request.getParameter("u")).getId())!=-1){
            throw new Exception("Username già utilizzato<br/>Provane un altro");
        }
        else{
            if((new User(request.getParameter("u"),request.getParameter("p"),"")).save()){
                session.setAttribute("user",request.getParameter("u"));
                response.sendRedirect("modify_text.jsp");
            }
            else{
                throw new Exception("Registrazione fallita<br/>Prova più tardi");
            }
        }
    }
}%>