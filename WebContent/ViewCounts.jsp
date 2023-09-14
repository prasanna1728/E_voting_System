<!DOCTYPE html>
<%@page import="com.bean.CandidateBean"%>
<%@page import="com.bean.PartyBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dao.UserDao"%>
<html lang="en">
<head>
<meta charset="utf-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<meta name="description" content="">

<meta name="author" content="">

<link rel="icon" href="images/favicon.ico">

<title>Smart E- Voting System</title>

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!--Fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Icons -->
<link href="css/font-awesome.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid" id="wrapper">
		<div class="row">
			<nav
				class="sidebar col-xs-12 col-sm-4 col-lg-3 col-xl-2 bg-faded sidebar-style-1">
				<h1 class="site-title">
					<a href="index.jsp"><em class="fa fa-rocket"></em>Voting</a>
				</h1>

				<a href="#menu-toggle" class="btn btn-default" id="menu-toggle"><em
					class="fa fa-bars"></em></a>

				<ul class="nav nav-pills flex-column sidebar-nav">

					<li class="nav-item"><a class="nav-link" href="AdminHome.jsp"><em class="fa fa-calendar-o"></em>Home</a></li>
					
					
					
					<li class="nav-item"><a class="nav-link" href="ViewUsers.jsp"><em class="fa fa-bar-chart"></em>View Voters</a></li>
					<li class="nav-item"><a class="nav-link" href="ViewCand.jsp"><em class="fa fa-bar-chart"></em>View Candidates</a></li>
                    <li class="nav-item"><a class="nav-link" href="AddParty.jsp"><em class="fa fa-bar-chart"></em>Add Party</a></li>
                    <li class="nav-item"><a class="nav-link" href="ViewCounts.jsp"><em class="fa fa-bar-chart"></em>View Counting</a></li>
					<li class="nav-item"><a class="nav-link" href="LogoutController"><em class="fa fa-hand-o-up"></em>Logout</a></li>
	
	
				</ul>

				<a href="#" class="logout-button"></a>
			</nav>

			<main
				class="col-xs-12 col-sm-8 offset-sm-4 col-lg-9 offset-lg-3 col-xl-10 offset-xl-2 pt-3 pl-4">
			<header class="page-header row justify-center">
				<div class="col-md-6 col-lg-8"></div>

				<div
					class="dropdown user-dropdown col-md-6 col-lg-4 text-center text-md-right">
					<a class="btn btn-stripped dropdown-toggle"
						href="https://example.com" id="dropdownMenuLink"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">


						<div class="dropdown-menu dropdown-menu-right"
							style="margin-right: 1.5rem;" aria-labelledby="dropdownMenuLink">
							<a class="dropdown-item" href="#"><em
								class="fa fa-user-circle mr-1"></em></a> <a class="dropdown-item"
								href="#"><em class="fa fa-power-off mr-1"></em></a>
						</div>
				</div>

				<div class="clear"></div>
			</header>

			<section class="row">
				<div class="col-sm-12">
					<section class="row">
						<div class="col-lg-offset-3 col-md-12 col-lg-9">
							<div class="jumbotron">
								<h1 class="mb-4" style="color: #6365d2">
									<center>View Counting</center>
								</h1>
								<div class="card mb-4">
									<div class="card-block">

										<form class="form-horizontal"
											action="View.jsp" method="post">
										<fieldset>
												<div class="form-group">
													<label class="col-12 control-label no-padding" for="name">Select Party</label>

													<div class="col-12 no-padding">
														<select name="selectparty" class="form-control">
                     
                      <%
                      UserDao dao=new UserDao();
                      
                      ArrayList<PartyBean> titlelist=dao.getCategory();
                      
                      for(int i=0;i<titlelist.size();i++)
                      {
                      %>
                      
                      <option  value="<%=titlelist.get(i).getPartyname()%>"><%=titlelist.get(i).getPartyname()%></option> 
                      
                      <%} %>
                      </select>	
													</div>
												</div>

											

												<div class="form-group">
													<label class="col-12 control-label no-padding" for="name">Select Candidate</label>

													<div class="col-12 no-padding">
														<select name="selectcand" class="form-control">
                     
                      <%
                      
                      
                     
                      ArrayList<CandidateBean> titlelist1=dao.getCategory1();
                      
                      for(int i=0;i<titlelist1.size();i++)
                      {
                      %>
                      
                      <option value="<%=titlelist1.get(i).getName()%>"><%=titlelist1.get(i).getName()%></option> 
                      
                      <%} %>
                      </select>	
													</div>
												</div>
												
											
												<!-- Form actions -->
												<div class="form-group">
													<div class="col-12 widget-right no-padding">
														<button type="submit" class="btn btn-primary btn-md float-right">View</button>
													</div>
												</div>
											</fieldset>
										</form>
									</div>
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
		window.onload = function() {
			var chart1 = document.getElementById("line-chart").getContext("2d");
			window.myLine = new Chart(chart1).Line(lineChartData, {
				responsive : true,
				scaleLineColor : "rgba(0,0,0,.2)",
				scaleGridLineColor : "rgba(0,0,0,.05)",
				scaleFontColor : "#c5c7cc"
			});
		};
	</script>
 <script type="text/javascript">

    function readURL(input) {

        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                document.getElementById('myimg').setAttribute('src',e.target.result);
            }

            reader.readAsDataURL(input.files[0]);
        }
    }


    document.getElementById('imgSel').onchange = function () { //set up a common class
        readURL(this);
    };


</script>
	

</body>
</html>
