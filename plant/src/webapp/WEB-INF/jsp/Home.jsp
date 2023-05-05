<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
          <c:set var="contextRoot" value="${pageContext.request.contextPath}" />


          <!DOCTYPE html>
          <html>

          <head>
            <meta charset="UTF-8">
            <title>Home</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
              crossorigin="anonymous">
          </head>

          <body>
            <jsp:include page="../layout/backStageSideBar_NavBar.jsp"></jsp:include>
            <script src="https://unpkg.com/axios@1.1.2/dist/axios.min.js"></script>

            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 main">
              <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
                crossorigin="anonymous"></script>

              <h1>庫存查詢</h1>

              <div class="dropdown">
          <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
            Dropdown button
          </button>
          <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1" id="dropdownHead">

          </ul>
        </div>




              <script>
                axios({
                  url: 'http://localhost:8080/plant/getAllStore',
                  method: 'get'
                })
                  .then(function res() {
                    console.log(res);
                    maker(res); 
                  })
                  .catch(function err() {
                    console.log(err);
                  })


                  function maker(res){

                    let dHead = document.getElementById("dropdownHead"); 

                    <li><a class="dropdown-item" href="#">Action</a></li>
                  }


              </script>



          </body>

          </html>