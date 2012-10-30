<%@page contentType="application/xhtml+xml" pageEncoding="utf-8"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
  <head>
    <title>Errore</title>
    <meta http-equiv="REFRESH" content="5; URL=index.jsp"/>
    <link rel="stylesheet" href="ext/css.css" type="text/css" />
  </head>
  <body>
      <div>
          <div style="font-size:x-large;">Problemi d'accesso</div>
          <div style="font-size:large;"><%=exception.getMessage()%></div>
  </div><p class="box">
            <a href="http://validator.w3.org/check?uri=referer"><img
        src="http://www.pdsolution.it/img/logo_1.jpg"
       alt="Valid XHTML 1.1" height="15" width="80" /></a>
        </p>
  </body>
</html>
