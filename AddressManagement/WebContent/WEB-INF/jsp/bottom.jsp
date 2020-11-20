<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
</article>

<jsp:useBean id="now" class="java.util.Date" />

<footer>Proceeded in ${now.time-starttime.time} ms</footer>
</body>
</html>