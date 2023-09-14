<!DOCTYPE html>

<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
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
					
						<li class="nav-item"><a class="nav-link" href="CandidateHome.jsp"><em class="fa fa-calendar-o"></em>Home</a></li>
					<li class="nav-item"><a class="nav-link" href="CandidateProfile.jsp"><em class="fa fa-bar-chart"></em>My Profile</a></li>
					<li class="nav-item"><a class="nav-link" href="CandidateDetails.jsp"><em class="fa fa-bar-chart"></em>Add Details</a></li>
					<li class="nav-item"><a class="nav-link" href="LogoutController"><em class="fa fa-hand-o-up"></em>Logout</a></li>
				</ul>
				
				<a href="#" class="logout-button"></a></nav>
			
			<main class="col-xs-12 col-sm-8 offset-sm-4 col-lg-9 offset-lg-3 col-xl-10 offset-xl-2 pt-3 pl-4">
				<header class="page-header row justify-center">
					<div class="col-md-6 col-lg-8" >
						
					</div>
					
					<div class="dropdown user-dropdown col-md-6 col-lg-4 text-center text-md-right"><a id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						
						
						
					</div>
					
					<div class="clear"></div>
				</header>
				<%   HttpSession sessio=request.getSession(); 
                     String email=(String)session.getAttribute("emailID"); 
    		
    		Connection con=DBConnection.getConnection();
    		
    		String sql="select * from tbl_cand where email='"+email+"'";
    		
    		PreparedStatement ps=con.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		
    		while(rs.next())
    				
    		{
    		
    		%>
				<section class="row">
					<div class="col-sm-12">
						<section class="row">
							<div class="col-lg-offset-3 col-md-12 col-lg-9">
								<div class="jumbotron">
									<h1 class="mb-4"><center>My Profile</center></h1>
									<div class="card mb-4">
									<div class="card-block">
									
										<form class="form-horizontal" action="" method="post">
											<fieldset>
									<label style="color:black"><b>Name:  </b><%=rs.getString(2) %></label><br>
									<label style="color:black"><b>Address:  </b> <%= rs.getString(3) %></label><br>
									<label style="color:black"><b>Email:  </b><%= rs.getString(4) %></label><br>
									<label style="color:black"><b>Mobile No:  </b> <%= rs.getString(5) %></label><br>
									<label style="color:black"><b>Date of Birth:  </b> <%= rs.getString(6) %></label><br>
									
											
										<%} %>		<!-- Form actions -->
												
													
												
											</fieldset>
										</form>
									</div>
								</div>
									
									<div class="col-12 widget-right no-padding">
													<a href="UserHome.jsp" class="btn btn-primary btn-md float-right"> Back </a>
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
