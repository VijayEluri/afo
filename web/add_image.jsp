<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@ page errorPage="fail.jsp" %>   
<%String type = request.getParameter("type");
            String user = (String) session.getAttribute("user");
            if (user == null) {
                throw new Exception("Autentificazione fallita<br/>Non risulti degno di questa sezione");
            }%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1"/>
        <link rel="stylesheet" href="ext/css.css" type="text/css" />
        <title></title>
    </head>
  <body>
      <jsp:include flush="false" page="LogIn.jsp"/>
  <form enctype="multipart/form-data"
 method='post' action='newImage'>
     <p>
<input type='file' name='fileUp'/>
<input type='submit' value='Upload File'/>
</p>
</form>
<p class="box">
            <a href="http://validator.w3.org/check?uri=referer">
                <img
                    src="http://www.pdsolution.it/img/logo_1.jpg"
                    alt="Valid XHTML 1.1" height="15" width="80" />
            </a>
        </p>
  </body>
</html>
