<%@page contentType="application/xhtml+xml" pageEncoding="iso-8859-1"%>
<%@ page import="org.macrobug.afo.bean.*"%>
<%
            String u = (String) session.getAttribute("user");
            Boolean ok = null;
            String type = request.getParameter("type");
            User us = new User(u);
            if (type != null) {
                switch (type.charAt(0)) {
                    case '@':
                        ok = us.setMail(request.getParameter("email"));
                        break;
                    case '§':
                        ok = us.setPassword(request.getParameter("n"));
                        break;
                    case '#':
                        ok = us.delete();
                        if (ok)
                            session.setAttribute("user", null);
                        break;
                }
            }
            u = (String) session.getAttribute("user");
            if (u == null)
                response.sendRedirect("index.jsp");
            %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
        <title>Modifica profilo</title>
        <meta http-equiv="Content-Script-Type" content="text/javascript"/>
        <script type="text/javascript">
            <!--
            function eq(f){
                if(f.n.value==f.rep.value)
                    f.submit()
                else
                    alert('La nuova password e la conferma non sono uguali');
            }
            //-->
        </script>
    </head>
    <body>
        <jsp:include flush="false" page="LogIn.jsp"/>
        <%if (ok!=null) {%><p style="float:left;font-size:xx-large;">"Operazione "<%=ok?"riuscita":"fallita"%></p><%}%>
        <form action="modify_user.jsp" method="post" accept-charset="utf-8">
            <table border="0">
                <thead>
                    <tr>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><label for="user">Username</label></td>
                        <td><input type="text" name="user" value="<%=us.getName()%>" disabled="disabled" id="user"/></td>
                    </tr>
                    <tr>
                        <td><label for="email">Email</label></td>
                        <td><input type="text" name="email" value="<%=us.getEmail()%>" id="email"/></td>
                    </tr>
                </tbody>
            </table>
            <p><input type="hidden" name="type" value="@" /><input type="submit" value="Ok" /></p>
        </form><hr/>
        <form action="modify_user.jsp" method="post" accept-charset="utf-8">
            <table border="0">
                <thead>
                    <tr>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><label for="old">Vecchia password</label></td>
                        <td><input type="password" name="old" value="" id="old"/></td>
                    </tr>
                    <tr>
                        <td><label for="new">Nuova password</label></td>
                        <td><input type="password" name="n" value="" id="new"/></td>
                    </tr>
                    <tr>
                        <td><label for="rep">Ripeti nuova password</label></td>
                        <td><input type="password" name="rep" value="" id="rep"/></td>
                    </tr>
                </tbody>
            </table>
            <p><input type="hidden" name="type" value="§" /><input type="button" value="Cambia password"  onclick="return eq(this.form);" /></p>
        </form>
        <hr/>
        <form action="modify_user.jsp" method="post"><p><input type="submit" value="Cancellati" />
        <input type="hidden" name="type" value="#" /></p></form>
<p class="box">
            <a href="http://validator.w3.org/check?uri=referer">
                <img
                    src="http://www.pdsolution.it/img/logo_1.jpg"
                    alt="Valid XHTML 1.1" height="15" width="80" />
            </a>
        </p>
    </body>
</html>