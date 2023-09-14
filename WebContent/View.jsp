
<!DOCTYPE html>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.connection.DBConnection"%>
<%@page import="java.sql.Connection"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<meta name="description" content="">
	
	<meta name="author" content="">
	
	<link rel="icon" href="images/favicon.ico">
	
	<title>Smart E- Voting System</title>

    <!-- Bootstrap core CSS -->
    <link href="dist/css/bootstrap.min.css" rel="stylesheet">

    <!--Fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
    
    <!-- Icons -->
    <link href="css/font-awesome.css" rel="stylesheet">
    
    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid" id="wrapper">
		<div class="row">
			<nav class="sidebar col-xs-12 col-sm-4 col-lg-3 col-xl-2 bg-faded sidebar-style-1">
				<h1 class="site-title"><a href="index.jsp"><em class="fa fa-rocket"></em>Voting</a></h1>
				
				<a href="#menu-toggle" class="btn btn-default" id="menu-toggle"><em class="fa fa-bars"></em></a>
				
			<ul class="nav nav-pills flex-column sidebar-nav">
				
				<li class="nav-item"><a class="nav-link" href="AdminHome.jsp"><em class="fa fa-calendar-o"></em>Home</a></li>
					
					<li class="nav-item"><a class="nav-link" href="ViewUsers.jsp"><em class="fa fa-bar-chart"></em>View Voters</a></li>
					<li class="nav-item"><a class="nav-link" href="ViewCand.jsp"><em class="fa fa-bar-chart"></em>View Candidates</a></li>
                    <li class="nav-item"><a class="nav-link" href="AddParty.jsp"><em class="fa fa-bar-chart"></em>Add Party</a></li>
                    <li class="nav-item"><a class="nav-link" href="ViewCounts.jsp"><em class="fa fa-bar-chart"></em>View Counting</a></li>
					<li class="nav-item"><a class="nav-link" href="LogoutController"><em class="fa fa-hand-o-up"></em>Logout</a></li>
	
				</ul>
				
				<a href="#" class="logout-button"></a></nav>
			
			<main class="col-xs-12 col-sm-8 offset-sm-4 col-lg-9 offset-lg-3 col-xl-10 offset-xl-2 pt-3 pl-4">
				<header class="page-header row justify-center">
					<div class="col-md-6 col-lg-8" >
						<h1 class="float-left text-center text-md-left" style="color:#6365d2"></h1>
					</div>
					
					<div class="dropdown user-dropdown col-md-6 col-lg-4 text-center text-md-right"><a id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<img src="images/Admin.png" alt="profile photo" class="circle float-left profile-photo" width="50" height="auto">
						
						<div>
							<h4>
							<% response.setContentType("text/html");
							  HttpSession sess=request.getSession(false);
							  
							  if(sess!=null)
							  {
								  String email=(String)session.getAttribute("email");
								  out.print(""+email+"");
							  }  
							%>
							</h4>
							
							
						</div>
						</a>
						
						<div class="dropdown-menu dropdown-menu-right" style="margin-right: 1.5rem;" aria-labelledby="dropdownMenuLink">
						     
						     <a class="dropdown-item" href="LogoutController"><em class="fa fa-power-off mr-1"></em> Logout</a></div>
					</div>
					
					<div class="clear"></div>
				</header>
				
				<section class="row">
					<div class="col-sm-12">
						<section class="row">
							<div class="col-md-12 col-lg-12">
								  <h4 class="text-upper col-md-offset-6" style="Color:#7376df"><center><b>View Total Counts</b></center></h4><br>
                   <form class="subscription-form">
                    <div style="margin-left:20px;margin-right:20px">
                    <table class="table table-bordered col-md-offset-3" style="color:black">
                    <tr class="danger" style="color:black">
                   
                    <th> Party Name   </th>
                     <th>Candidate Area</th>
                     <th>Candidate Name</th>
                     <th>Date</th>
                     <th>Count</th>
                 
                     </tr>
                     <%
                     String selectparty=request.getParameter("selectparty");
                     String selectcand=request.getParameter("selectcand");
                     Connection con=DBConnection.getConnection();
                     Statement st=con.createStatement();
                     ResultSet rs=st.executeQuery("select * from tbl_vote where party='"+selectparty+"' and  cand='"+selectcand+"'");
                     while(rs.next())
                     {
                    	 %>
                    	 <TR>
                    	
                    	 <td><%=rs.getString(2) %></td>
                    	 <td><%=rs.getString(3) %></td>
                    	 <td><%=rs.getString(4) %></td>
                    	 <td><%=rs.getString(5) %></td>
                    	 <td><%=rs.getString(6) %></td>
          
                    	 
                    	 </TR>
                    	 
                    <%} %> 
                   
                    </table>
                    
                            
                  </form>
                </div>
              </div>
								</div>
							
					
						</section>
						
					</div>
				</section>
			</main>
		</div>
		
	</div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="dist/js/bootstrap.min.js"></script>
    
    <script src="js/chart.min.js"></script>
    <script src="js/chart-data.js"></script>
    <script src="js/easypiechart.js"></script>
    <script src="js/easypiechart-data.js"></script>
    <script src="js/bootstrap-datepicker.js"></script>
    <script src="js/custom.js"></script>
    <script>
	    window.onload = function () {
	var chart1 = document.getElementById("line-chart").getContext("2d");
	window.myLine = new Chart(chart1).Line(lineChartData, {
	responsive: true,
	scaleLineColor: "rgba(0,0,0,.2)",
	scaleGridLineColor: "rgba(0,0,0,.05)",
	scaleFontColor: "#c5c7cc"
	});
};
	</script>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    
	  </body>
</html>
