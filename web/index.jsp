<%@page contentType="application/xhtml+xml" pageEncoding="iso-8859-1"%>
<%
boolean b=request.getHeader("user-agent").toLowerCase().indexOf("firefox")!=-1;
String type=request.getParameter("type");
if(type!=null&&type.equals("out"))
    session.setAttribute("user",null);
%>
<%--
TODO Creare una pagina per la cancellazione delle immagini
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1"/>
        <link rel="stylesheet" href="ext/css.css" type="text/css" />
        <title>Crea la tua firma</title>
        <script type="text/javascript" src="ext/back.js" />
    </head>
    <body onload="init()">
        <jsp:include flush="false" page="LogIn.jsp"/>
        <div>
                <img src="utenti/img.png" alt="Immagine"/>
                <img src="utenti/All.png" alt="Immagine"/>
                <img src="avatar/All.png" alt="Immagine"/>
        </div>
        <hr/>
        <div class="crea">
            <form action="niu.png" method="post" id="ff" accept-charset="utf-8">
                
                <p><textarea name="text" rows="4" cols="20"></textarea></p>
                <table border="1">
                    <tr>
                        <%
                        org.macrobug.afo.bean.Image[] s=org.macrobug.afo.bean.Image.getImages();
                        for(org.macrobug.afo.bean.Image i:s){%>
                        <td>
                            <label for="r<%=i.getId()%>"><img src="niu.png?text=%20&amp;file=<%=i.getId()%>" alt="immagine"/></label>
                            <input type="radio" name="file" value="<%=i.getId()%>" id="r<%=i.getId()%>"/>
                    </td><%}%></tr>
                </table>
               <p>
                   <%if(b){%><input type="button" value="Crea" onclick="ocio()"/>
                   <%}else{%><input type="submit" value="Crea"/><%}%>
               </p>
            </form>
        </div>
        <hr/>
        <p style="float:left"><%=org.macrobug.afo.bean.User.count()-2%> registrati</p>
         <p class="box">
    <a href="http://validator.w3.org/check?uri=referer"><img
        src="http://www.pdsolution.it/img/logo_1.jpg"
        alt="Valid XHTML 1.1" height="15" width="80" /></a>
  </p>
    </body>
</html>