        </div>
    </div>
    <div>
    <script src="<%= request.getContextPath() %>/assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
    <script src="<%= request.getContextPath() %>/assets/vendor/slimscroll/jquery.slimscroll.js"></script>
    <script src="<%= request.getContextPath() %>/assets/libs/js/main-js.js"></script>
    <script src="<%= request.getContextPath() %>/assets/vendor/charts/chartist-bundle/chartist.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/vendor/charts/sparkline/jquery.sparkline.js"></script>
    <script src="<%= request.getContextPath() %>/assets/vendor/select2/js/select2.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/vendor/summernote/js/summernote-bs4.js"></script>
    <!--  <script src="<%= request.getContextPath() %>/assets/vendor/charts/morris-bundle/raphael.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/vendor/charts/morris-bundle/morris.js"></script> 
    <script src="<%= request.getContextPath() %>/assets/vendor/charts/c3charts/c3.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/vendor/charts/c3charts/d3-5.4.0.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/vendor/charts/c3charts/C3chartjs.js"></script>
    <script src="<%= request.getContextPath() %>/assets/libs/js/dashboard-ecommerce.js"></script>-->
   
     <script>
		    $(document).ready(function() {
		        $('.js-example-basic-multiple').select2({ tags: true });
		    });
		    </script>
		    <script>
		    $(document).ready(function() {
		        $('#summernote').summernote({
		            height: 300
		
		        });
		    });
	</script>
	 </div>
</body>
 
</html>