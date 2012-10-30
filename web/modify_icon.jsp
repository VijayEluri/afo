<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@ page errorPage="fail.jsp" %>
<%@page import="org.macrobug.afo.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1"/>
        <link rel="stylesheet" href="ext/css.css" type="text/css" />
        <title>Le tue icone</title>
    </head>
    <body>
        <%
            String[] type = request.getParameterValues("icon_id");
            String user = (String) session.getAttribute("user");
            if (user == null) {
                throw new Exception("Autentificazione fallita<br/>Non risulti degno di questa sezione");
            }
            if (type!=null&&type.length>0) {
                boolean b = true;
                for(int i=0;i<type.length;i++){
                    Icon t = new Icon(Integer.parseInt(type[i]));
                    b&=t.delete(getServletContext().getRealPath("icon"));
                }%>
                <p style="float:left;font-size:xx-large;">Operazione <%= b ? "Riuscita" : "Fallita"%></p><%}%>
        <jsp:include flush="false" page="LogIn.jsp"/>
        <%if(new User(user).getIcons().length>0){%>
        <div style="font-size:large;float:left;">Il tuo avatar è visibile all'indirizzo <a href="avatar/<%=user%>.png">avatar/<%=user%>.png</a></div>
        <%}%>
        <div style="clear:left;">
            <div>
                Per caricare degli avatar invia un archivio .zip contenente le tue immagini in formato png
            </div>
            <form enctype='multipart/form-data' method='post' action='newIcon'>
                <p>
                    <label for="up">Carica un nuova pacchetto di avatar</label>
                    <input type='file' name='fileUp' id="up"/>
                    <input type='submit' value='Upload File'/>
                </p>
            </form>
        </div>
        <hr/>
        <form action="modify_icon.jsp" method="post">
            <table>
        <%
            Icon S[] = new User(user).getIcons();
            int max = S.length;
            for(int i=0;i<max;){%>
            <tr>
            <%for(int j=0;j<5&&i<max;j++,i++){%>
            <td>
                <label for="ico_cod:<%=S[i].getId()%>">
                <img alt="ico_cod:<%=S[i].getId()%>" src="icon/<%=S[i].getPath()%>"/>
                </label>
                <input type="checkbox" name="icon_id" value="<%=S[i].getId()%>" id="ico_cod:<%=S[i].getId()%>"/>
            </td><%}%>
            </tr>
            <%}%>
            </table>
            <input type="submit" value="Delete" /></form>
        <p class="box">
            <a href="http://validator.w3.org/check?uri=referer">
                <img
                    src="http://www.pdsolution.it/img/logo_1.jpg"
                    alt="Valid XHTML 1.1" height="15" width="80" />
            </a>
        </p>
    </body>
</html>
