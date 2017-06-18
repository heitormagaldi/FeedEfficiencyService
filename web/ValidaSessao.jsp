<%

    try {
        if (session.getAttribute("login") == null) {
%>
<jsp:forward page="index.html" />
<%        }
    } catch (Exception e) {
        System.out.println("Erro ao validar:"+e);
    }


%>