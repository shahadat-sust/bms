<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<c:if test="${not empty successMsg || not empty failedMsg}">                           	
	<div class="row">
	    <div class="offset-lg-1 offset-xl-3 col-lg-10 col-xl-6">
	    	 <c:if test="${not empty failedMsg}">
                  <div class="alert alert-danger alert-dismissable" role="alert">
                      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                      </button>
                      <p class="mb-0">${failedMsg}</p>
                  </div>
	         </c:if>
	         <c:if test="${not empty successMsg}">
                  <div class="alert alert-success alert-dismissable" role="alert">
                      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                      </button>
                      <p class="mb-0">${successMsg}</p>
                  </div>
	         </c:if>
	     </div>
	</div>
</c:if>