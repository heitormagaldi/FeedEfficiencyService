<%-- 
    Document   : formAnimal
    Created on : 20/01/2017, 21:25:13
    Author     : Heitor
--%>

<%@page import="java.io.IOException"%>
<%@page import="org.json.JSONArray"%>
<%@page import="embrapa.dao.DAORaca"%>
<%@page import="embrapa.to.TORaca"%>
<%@page import="embrapa.dao.DAOAnimal"%>
<%@page import="embrapa.bo.BOFactory"%>
<%@page import="embrapa.to.TOAnimal"%>
<%@page import="org.json.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>


<%

    TOAnimal t = new TOAnimal();

    t.setCodigo(Integer.parseInt(request.getParameter("codigo")));
    t = (TOAnimal) BOFactory.get(new DAOAnimal(), t);

    JSONArray jsRacas = BOFactory.list(new DAORaca());

%>

<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">


        <title>Cadastro de Animal</title>

        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/sb-admin.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>

    <body>
        <!-- jQuery -->
        <script src="js/jquery.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

        <div id="wrapper">

            <!-- Navigation -->
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="principal.jsp">AgroPROV</a>
                </div>
                <!-- Top Menu Items -->
                <ul class="nav navbar-right top-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
                        <ul class="dropdown-menu message-dropdown">
                            <li class="message-preview">
                                <a href="#">
                                    <div class="media">
                                        <span class="pull-left">
                                            <img class="media-object" src="http://placehold.it/50x50" alt="">
                                        </span>
                                        <div class="media-body">
                                            <h5 class="media-heading"><strong>John Smith</strong>
                                            </h5>
                                            <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                            <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="message-preview">
                                <a href="#">
                                    <div class="media">
                                        <span class="pull-left">
                                            <img class="media-object" src="http://placehold.it/50x50" alt="">
                                        </span>
                                        <div class="media-body">
                                            <h5 class="media-heading"><strong>John Smith</strong>
                                            </h5>
                                            <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                            <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="message-preview">
                                <a href="#">
                                    <div class="media">
                                        <span class="pull-left">
                                            <img class="media-object" src="http://placehold.it/50x50" alt="">
                                        </span>
                                        <div class="media-body">
                                            <h5 class="media-heading"><strong>John Smith</strong>
                                            </h5>
                                            <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                            <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="message-footer">
                                <a href="#">Read All New Messages</a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b class="caret"></b></a>
                        <ul class="dropdown-menu alert-dropdown">
                            <li>
                                <a href="#">Alert Name <span class="label label-default">Alert Badge</span></a>
                            </li>
                            <li>
                                <a href="#">Alert Name <span class="label label-primary">Alert Badge</span></a>
                            </li>
                            <li>
                                <a href="#">Alert Name <span class="label label-success">Alert Badge</span></a>
                            </li>
                            <li>
                                <a href="#">Alert Name <span class="label label-info">Alert Badge</span></a>
                            </li>
                            <li>
                                <a href="#">Alert Name <span class="label label-warning">Alert Badge</span></a>
                            </li>
                            <li>
                                <a href="#">Alert Name <span class="label label-danger">Alert Badge</span></a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">View All</a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> John Smith <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <ul class="nav navbar-nav side-nav">
                        <li>
                            <a href="principal.jsp"><i class="fa fa-fw fa-dashboard"></i> Principal</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </nav>

            <div id="page-wrapper">

                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Formul&aacute;rio de Cadastro de Animal
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="fa fa-dashboard"></i>  <a href="principal.jsp">Principal</a>
                                </li>
                                <li class="active">
                                    <i class="fa fa-edit"></i> Formul&aacute;rio de Cadastro de Animal
                                </li>
                            </ol>
                        </div>
                    </div>

                    <!-- /.row -->
                    <form action="formAnimal" method="POST">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label>C&oacute;digo do Animal</label>

                                    <input type="text" 
                                           name="txt_codigo" 
                                           id="txt_event_title"  
                                           class="form-control custom-height" 
                                           autocomplete="off" 
                                           placeholder="Preencher o c&oacute;digo do animal" 
                                           value="<%=t.getCodigo()%>" 
                                           disabled></input>
                                </div>
                                <div class="form-group">
                                    <label>Registro</label>
                                    <input type="text" 
                                           name="txt_registro" 
                                           id="txt_event_title"  
                                           class="form-control custom-height" 
                                           autocomplete="off" 
                                           placeholder=" Preencher o n&uacute;mero de registro do animal" 
                                           value="<%=t.getRegistro()%>"></input>
                                </div>
                                <div class="form-group">
                                    <label>Manejo</label>
                                    <input type="text" 
                                           name="txt_manejo" 
                                           id="txt_event_title"  
                                           class="form-control custom-height" 
                                           autocomplete="off" 
                                           placeholder="Preencher o c&oacute;digo de manejo animal" 
                                           value="<%=t.getManejo()%>"></input>
                                </div>
                                <div class="form-group">
                                    <label>Nome</label>
                                    <input type="text" 
                                           name="txt_nome" 
                                           id="txt_event_title"  
                                           class="form-control custom-height" 
                                           autocomplete="off" 
                                           placeholder="Preencher o nome do animal" 
                                           value="<%=t.getNome()%>"></input>
                                </div>

                                <div class="form-group">
                                    <label>Nascimento</label>
                                    <input type="text" 
                                           name="txt_nascimento" 
                                           id="txt_event_title"  
                                           class="form-control custom-height" 
                                           autocomplete="off" 
                                           placeholder="Preencher a data de nascimento do animal" 
                                           value="<%=t.getNascimento()%>"></input>
                                </div>

                                <button type="submit" class="btn btn-default">Gravar</button>
                                <button type="reset" class="btn btn-default" onclick="history.back()">Cancelar</button>



                            </div>
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label>Ra&ccedil;a </label>
                                    <%
                                        JSONObject temp;
                                        Integer codigo;
                                        String raca;

                                        for (int i = 0; i < jsRacas.length(); i++) {
                                            temp = jsRacas.getJSONObject(i);
                                    %>    
                                    <div class="radio">
                                        <label>
                                            <% try {
                                                    if (i == t.getRaca()) {%>
                                            <input type="radio" name="opt_raca" id="optionsRadios<%=i%>" value="option<%=i%>" checked><%=temp.getInt("codigo")%> - <%=temp.getString("descricao")%>
                                            <%} else {%>
                                            <input type="radio" name="opt_raca" id="optionsRadios<%=i%>" value="option<%=i%>" ><%=temp.getInt("codigo")%> - <%=temp.getString("descricao")%>
                                            <% }
                                                } finally {
                                                    // ... cleanup that will execute whether or not an error occurred ...
                                                }

                                            %>

                                        </label>
                                    </div>

                                    <%                                        }
                                    %>
                                </div>
                                <div class="form-group">
                                    <label>Tipo</label>


                                    <% if (t.getSexo().equals("F")) {%>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="opt_sexo" id="optionsRadios1" value="option1" checked>Novilha
                                        </label>
                                    </div>    
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="opt_sexo" id="optionsRadios1" value="option1" >Touro
                                        </label>
                                    </div>    

                                    <%} else {
                                        if (t.getSexo().equals("M")) { %>

                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="opt_sexo" id="optionsRadios1" value="option1" >Novilha
                                        </label>
                                    </div>    
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="opt_sexo" id="optionsRadios1" value="option1" checked>Touro
                                        </label>
                                    </div>   

                                    <% } else {
                                    %>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="opt_sexo" id="optionsRadios1" value="option1" >Novilha
                                        </label>
                                    </div>    
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="opt_sexo" id="optionsRadios1" value="option1" >Touro
                                        </label>
                                    </div>   

                                    <%
                                        }
                                    %>


                                </div>

                                <%                                        }
                                %>
                            </div>
                        </div>            
                </div>
                </form>  
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->



</body>

</html>
