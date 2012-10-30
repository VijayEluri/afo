<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@ page errorPage="fail.jsp" %>
<%@page import="org.macrobug.afo.bean.*"%>
<%@page import="org.macrobug.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1"/>
        <link rel="stylesheet" href="ext/css.css" type="text/css" />
        <title>I tuoi motti</title>
    </head>
    <body>
        <%
            String type = request.getParameter("type");
            String user = (String) session.getAttribute("user");
            if (user == null) {
                throw new Exception("Autentificazione fallita<br/>Non risulti degno di questa sezione");
            }
            if (type != null) {
                boolean b;
                if (type.equals("add")) {
                    Text t=new Text(new User(user),request.getParameter("text"));
                    b = t.save();
                } else {
                    Text t=new Text(Integer.parseInt(request.getParameter("code")));
                    b=t.delete();
                }%><p style="float:left;font-size:xx-large;">Operazione <%=b ? "Riuscita" : "Fallita"%></p><%}%>
        <jsp:include flush="false" page="LogIn.jsp"/>
        <%if(new User(user).getTexts().length>0){%>
        <div style="font-size:large;float:left;">La tua firma è visibile all'indirizzo <a href="utenti/<%=user%>.png">utenti/<%=user%>.png</a></div>
        <%}%>
        <div style="clear:left;">
            <div>
                Simboli speciali:<br/>~b : indicazione sul browser usato<br/>~h : indirizzo ip usato<br/>~d : data corrente
            </div>
            <form action="modify_text.jsp" method="post" accept-charset="utf-8">
                <p>
                    <input type="hidden" name="type" value="add"/>
                    <textarea cols="50" rows="5" name="text"></textarea>
                    <input type="submit" value="Aggiungi"/>
                </p>
            </form>
        </div>
        <hr/>
        <%
        Text S[] = new User(user).getTexts();
        Image i[]=Image.getImages();
        Random r=Random.getRandom();
            for (Text s : S) {%><div>
            <form action="modify_text.jsp" method="post">
                <p>
                    <input type="hidden" name="type" value="del"/>
                    <input type="hidden" name="code" value="<%=s.getId()%>"/>
                </p>
                <p>
                    <img src="niu.png?text=<%=s.getText()%>&file=<%=i[r.nextInt(i.length)].getId()%>" alt="img_cod:<%=s.getId()%>"/>
                    <input type="submit" value="Elimina"/>
                </p>
            </form>
        </div>
        <%}%>
        
        <p class="box">
            <a href="http://validator.w3.org/check?uri=referer">
                <img
                    src="http://www.pdsolution.it/img/logo_1.jpg"
                    alt="Valid XHTML 1.1" height="15" width="80" />
            </a>
        </p>
    </body>
</html>
